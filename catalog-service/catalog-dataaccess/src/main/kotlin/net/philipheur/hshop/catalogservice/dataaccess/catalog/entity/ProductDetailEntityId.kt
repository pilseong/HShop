package net.philipheur.hshop.catalogservice.dataaccess.catalog.entity

import java.io.Serializable

data class ProductDetailEntityId (
    var id: Long = 0L,
    var product: ProductEntity? = null
): Serializable