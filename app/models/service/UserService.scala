package models
package service

import java.util.UUID
import javax.inject.{ Singleton, Inject }
import scala.concurrent.{ Future, ExecutionContext }
import cats.data.{ EitherT, OptionT }
import cats.implicits._

import errors.Error
import errors.Error._
import dtos.UserCreate
import repo.UserRepo

@Singleton
class UserService @Inject()(
  UserRepo: UserRepo
)(using ExecutionContext) {
  def create(user: UserCreate): EitherT[Future, Error, Unit] =
    for {
      _ <- OptionT(UserRepo.get(user)).toRight(Error(CONFLICT, USER_ALREADY_EXISTS))
      _ <- EitherT.pure(UserRepo.create(user.toUser))
    } yield ()
}