package net.philipheur.hshop.cartservice.domain.service.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.ConstructorBinding

@ConfigurationProperties(prefix = "cart-service")
data class CartServiceConfigData @ConstructorBinding constructor(
    val customerTopicName: String,
)