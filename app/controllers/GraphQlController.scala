package controllers

import javax.inject._
import akka.actor.ActorSystem
import io.circe.{Json, parser}
import play.api.libs.circe.Circe
import play.api.mvc._
import service.QueryServiceImpl

import scala.concurrent.{ExecutionContext, Future}

/**
 * This controller creates an `Action` that demonstrates how to write
 * simple asynchronous code in a controller. It uses a timer to
 * asynchronously delay sending a response for 1 second.
 *
 * @param cc standard controller components
 * @param actorSystem We need the `ActorSystem`'s `Scheduler` to
 * run code after a delay.
 * @param exec We need an `ExecutionContext` to execute our
 * asynchronous code.  When rendering content, you should use Play's
 * default execution context, which is dependency injected.  If you are
 * using blocking operations, such as database or network access, then you should
 * use a different custom execution context that has a thread pool configured for
 * a blocking API.
 */
@Singleton
class GraphQlController @Inject()(qs: QueryServiceImpl, cc: ControllerComponents, actorSystem: ActorSystem)(implicit exec: ExecutionContext)
  extends AbstractController(cc) with Circe {

  /**
   * Creates an Action that returns a plain text message after a delay
   * of 1 second.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/message`.
   */
  def graphql: Action[Json] = Action(circe.json).async {
    request => getFutureMessage(request)
  }

  private def getFutureMessage(request: Request[Json]): Future[Result] = {
    parser.parse(request.body.toString()) match {
      case Right(value) =>
        qs.graphql(value).map(_.fold(Ok(_), BadRequest(_)))
      case _ => Future(BadRequest("parse request body."))
    }
  }

}
