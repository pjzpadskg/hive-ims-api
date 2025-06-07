package models
package domain

import java.util.UUID

case class ProductCategory (id: UUID, name: String)

object ProductCategory:
  def apply(name: String): ProductCategory = ProductCategory(UUID.randomUUID, name)