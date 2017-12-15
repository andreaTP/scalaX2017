package org.akkajs

import scala.scalajs.js
import scala.util.Try
import js.Dynamic.{global => g}

import akka.actor._

import org.akkajs.messages._

class TwitterActor() extends Actor {

  val twitterModule = g.require("node-tweet-stream")

  val twitter = js.Dynamic.newInstance(twitterModule)(Main.credentials)

  twitter.on("tweet", (tweet: js.Dynamic) => {
    Try {
      context.parent ! Tweet(tweet.user.name.toString, tweet.text.toString)
    }
  })

  def receive = {
    case track: Track =>
      twitter.track(track.topic)
  }

  override def postStop() = {
    twitter.untrackAll()
    twitter.abort()
  }
}
