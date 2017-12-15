package org.akkajs

import org.scalatest._
import akka.actor._
import akka.testkit._
import scala.scalajs.js
import akka.stream._
import akka.stream.scaladsl._
import akka.stream.testkit.scaladsl._

import org.akkajs.messages._

class TwitterFlowSpec extends TestKit(ActorSystem("WSCSpec"))
    with WordSpecLike
    with Matchers
    with BeforeAndAfterAll {

  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }

  "A Twitter Flow" must {

    "parse incoming tweets and emit valid ones" in {
      import system.dispatcher
      implicit val materializer = ActorMaterializer()

      val (pub, sub) = TestSource.probe[String]
        .via(TwitterFlow())
        .toMat(TestSink.probe[Tweet])(Keep.both)
        .run()

      sub.request(n = 2)

      val tweet1 = Tweet("foo", "bar")
      pub.sendNext(serialize(tweet1))

      pub.sendNext("{unparsableMessage}")

      val tweet2 = Tweet("bar", "baz")
      pub.sendNext(serialize(tweet2))

      sub.expectNextUnordered(tweet1, tweet2)
    }
  }
}
