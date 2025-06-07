package models
package domain

import java.util.UUID

case class OrganizationMember (
  idOrganization: UUID,
  idUser: UUID
)

object OrganizationMember:
  def apply(org: Organization, user: User): OrganizationMember =
    OrganizationMember(org.id, user.id)