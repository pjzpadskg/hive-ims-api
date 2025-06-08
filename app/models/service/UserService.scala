package models
package service

import java.util.UUID
import javax.inject.{ Singleton, Inject }
import scala.concurrent.{ Future, ExecutionContext }
import cats.data.{ EitherT, OptionT }
import cats.implicits._

import errors.Error
import errors.Error._
import domain.User
import dtos.{ UserCreate, UserCredentials }
import repo.UserRepo

@Singleton
class UserService @Inject()(
  UserRepo: UserRepo
)(using ExecutionContext) {
  def create(user: UserCreate): EitherT[Future, Error, Unit] = for {
    _ <- OptionT(UserRepo.get(user.toCredentials))
      .toLeft(()).leftMap(_ => Error(CONFLICT, USER_CONFLICT))
    _ <- EitherT.pure(UserRepo.create(user.toUser))
  } yield ()

  def get(user: UserCredentials): EitherT[Future, Error, User] =
    OptionT(UserRepo.get(user)).toRight(Error(NOT_FOUND, USER_NOT_FOUND))
}