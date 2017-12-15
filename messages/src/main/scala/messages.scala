package org.akkajs

import prickle._

package object messages {
  case class Tweet(from: String, msg: String)
  case class Track(topic: String)

  def serialize(tweet: Tweet): String = Pickle.intoString(tweet)
  def serialize(track: Track): String = Pickle.intoString(track)

  def deserializeTweet(tweet: String): Tweet =
    Unpickle[Tweet].fromString(tweet).get
  def deserializeTrack(track: String): Track =
    Unpickle[Track].fromString(track).get
}
