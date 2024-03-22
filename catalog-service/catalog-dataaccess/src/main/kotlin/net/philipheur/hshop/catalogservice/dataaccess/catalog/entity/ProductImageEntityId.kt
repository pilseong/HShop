package net.philipheur.hshop.catalogservice.dataaccess.catalog.entity

import java.io.Serializable

data class ProductImageEntityId (
    var id: Long = 0L,
    var product: ProductEntity? = null
): Serializable