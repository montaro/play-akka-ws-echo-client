package actors

/**
 * Created by arefaey on 8/8/14.
 */

import akka.actor.{Props, ActorLogging, Actor}
import play.api.Play
import protocol.fork
import utils.SimpleEchoSocket
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

class Stark extends Actor with ActorLogging {

  var sword: Long = 0
  val url: String = Play.current.configuration.getString("server.url") match {
    case Some(url: String) =>
      url
    case _ =>
      throw new IllegalArgumentException("Server URL is invalid in the configuration")
  }
  val socket: SimpleEchoSocket = new SimpleEchoSocket()

  override def preStart() = {
    log.info("Lord Stark is starting...")
    self ! fork
  }

  def receive = {
    case fork =>
      sword = sword + 1
      context.actorOf(Props(classOf[Robb], url, sword, socket), sword.toString)
      context.system.scheduler.scheduleOnce(10 milliseconds, self, fork)
  }

}
