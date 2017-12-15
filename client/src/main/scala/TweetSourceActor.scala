package org.akkajs

import scala.scalajs.js

import akka.actor._
import akka.stream._
import akka.stream.scaladsl._

import scala.util.Try
import scala.util.control.NonFatal
import scala.concurrent.duration._

import org.scalajs.dom

import org.akkajs.messages._

object TwitterFlow {

  def apply() =
    Flow[String]
      .map(elem => {
        try {
          Some(deserializeTweet(elem))
        } catch {
          case NonFatal(e) => None
        }
      })
      .filter(_.isDefined)
      .map(_.get)
      .throttle(1, 750 millis, 1, ThrottleMode.shaping)

}

class TweetSourceActor() extends Actor {
  import context.dispatcher

  val ws = new dom.WebSocket(s"ws://localhost:9002")

  implicit val materializer = ActorMaterializer()

  val wsQueue =
    Source
      .queue[String](10, OverflowStrategy.dropHead)
      .via(TwitterFlow())
      .to(Sink.actorRef(self, PoisonPill))
      .run()

  ws.onmessage = { (event: dom.MessageEvent) =>
    wsQueue.offer(event.data.toString)
  }

  case object Ready

  ws.onopen = { (event: dom.Event) =>
    self ! Ready
  }

  def receive = {
    case Ready =>
      context.become(operative)
  }

  def operative: Receive = {
    case tweet: Tweet =>
      context.parent ! tweet
    case track: Track =>
      ws.send(serialize(track))
  }

  override def postStop() =
    ws.close()

}
