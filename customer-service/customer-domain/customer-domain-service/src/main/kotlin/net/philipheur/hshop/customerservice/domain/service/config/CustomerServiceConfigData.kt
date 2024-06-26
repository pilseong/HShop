package net.philipheur.hshop.customerservice.domain.service.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.ConstructorBinding

@ConfigurationProperties(prefix = "customer-service")
data class CustomerServiceConfigData @ConstructorBinding constructor(
    val customerTopicName: String,
)