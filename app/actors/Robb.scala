package actors


/**
 * Created by arefaey on 8/8/14.
 */

import akka.actor.{ActorLogging, Actor}
import org.eclipse.jetty.websocket.client.{ClientUpgradeRequest, WebSocketClient}
import utils.SimpleEchoSocket
import java.net.URI

class Robb(url: String, sword: Long, socket: SimpleEchoSocket) extends Actor with ActorLogging {

  override def preStart() = {
    log.info("Robb fork is starting")
    wsConnect(url)
  }

  def receive = {
    case _ =>
      log.info("Robb got a message from: " + sender)
  }

  def wsConnect(url: String): Unit = {
    val client: WebSocketClient = new WebSocketClient()

    try {
      client.start()
      val echoUri: URI = new URI(url)
      val request: ClientUpgradeRequest = new ClientUpgradeRequest()
      client.connect(socket, echoUri, request)
      log.info("Connecting to : " + echoUri)
    }
    catch {
      case t: Throwable =>
        log.error(t.getMessage)
    }
  }
}
