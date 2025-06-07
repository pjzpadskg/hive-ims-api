package models
package domain

import java.util.UUID
import java.time.Instant

case class Organization (
  id: UUID,
  name: String,
  idAdmin: UUID,
  createdAt: Instant,
  updatedAt: Option[Instant],
)

object Organization:
  def apply(name: String, admin: User): Organization =
    Organization(UUID.randomUUID, name, admin.id, Instant.now, None)