package org.akkajs

import scala.scalajs.js
import js.Dynamic.{global => g}

import akka.actor._

import org.akkajs.messages._

object DemoTwitterActor extends js.JSApp {
  def main() = {

    val system = ActorSystem("node-twitter-demo")

    system.actorOf(Props(new Actor {
      val twitterActor = context.actorOf(Props[TwitterActor])

      twitterActor ! Track("Pizza")

      def receive = {
        case tweet: Tweet =>
          println(s"Tweet: ${tweet.msg}\n")
      }
    }))
  }
}
