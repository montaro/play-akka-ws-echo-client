package globals

import actors.Stark
import play.api.{Logger, Application, GlobalSettings}
import akka.actor.{Props, ActorRef, ActorSystem}

/**
 * Created by arefaey on 8/7/14.
 */

object Master extends GlobalSettings {

  private[this] var _actorSystem: ActorSystem = _
  private[this] var _stark: ActorRef = _
  var maxIdentifier: Int = 0

  def actorSystem = _actorSystem

  def stark = _stark

  override def onStart(app: Application) {
    println("Application has started")
    _actorSystem = ActorSystem("GOT")
    _stark = actorSystem.actorOf(Props(classOf[Stark]))
  }

  override def onStop(app: Application) {
    Logger.info("Application shutdown...")
    _actorSystem.shutdown
    _actorSystem.awaitTermination()
  }

}