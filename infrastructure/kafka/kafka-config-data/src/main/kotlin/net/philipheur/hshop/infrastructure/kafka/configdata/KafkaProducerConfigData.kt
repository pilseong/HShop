package net.philipheur.hshop.infrastructure.kafka.configdata

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.ConstructorBinding

@ConfigurationProperties(prefix = "kafka-producer-config")
data class KafkaProducerConfigData
@ConstructorBinding constructor(
    var keySerializerClass: String,
    var valueSerializerClass: String,
    var compressionType: String,
    var acks: String,
    var batchSize: Int,
    var batchSizeBoostFactor: Int,
    var lingerMs: Int,
    var requestTimeoutMs: Int,
    var retryCount: Int,
)