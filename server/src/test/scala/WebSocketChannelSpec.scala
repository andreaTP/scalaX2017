package org.akkajs

import org.scalatest._
import akka.actor._
import akka.testkit._
import scala.scalajs.js

import org.akkajs.messages._

class WebSocketChannelSpec extends TestKit(ActorSystem("WSCSpec"))
    with WordSpecLike
    with Matchers
    with BeforeAndAfterAll {

  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }

  "A WebSocketChannel" must {

    "send messages to channel" in {

      val probe = TestProbe()

      val mockWSChannel = js.Dynamic.literal(
        "send" -> {(x: Any) => probe.ref ! deserializeTweet(x.toString)}
      )

      val channelActor = system.actorOf(Props(
        new WSTwitterChannelActor(mockWSChannel) {
          override def preStart() = {}
      }))

      val foo = Tweet("foo", "foo")
      channelActor ! foo
      probe.expectMsg(foo)

      val bar = Tweet("bar", "bar")
      channelActor ! bar
      probe.expectMsg(bar)

    }
  }
}
