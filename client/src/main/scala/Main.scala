package org.akkajs

import akka.actor._

object Main extends App {

  implicit val system = ActorSystem("ui-akkajs")

  val ui = system.actorOf(Props(new UIActor()))

}
