package models
package domain

import java.util.UUID

case class Product (
  id: UUID,
  name: String,
  image: String,
  description: String,
  stock: Int,
  idOrganization: UUID,
  idCategory: UUID
)

object Product:
  def apply (
    name: String, 
    image: String, 
    description: String, 
    stock: Int,
    org: UUID,
    category: ProductCategory
  ): Product = Product(UUID.randomUUID, name, image, description, stock, org, category.id)