package net.philipheur.hshop.cartservice.messaging.listener

import net.philipheur.hshop.cartservice.domain.service.dto.message.CustomerModel
import net.philipheur.hshop.cartservice.domain.service.ports.input.message.CustomerMessageListener
import net.philipheur.hshop.common.utils.logging.LoggerDelegator
import net.philipheur.hshop.infrastructure.kafka.consumer.KafkaConsumer
import net.philipheur.hshop.infrastructure.kafka.model.avro.CustomerAvroModel
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.handler.annotation.Header
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component

@Component
class CustomerKafkaListener(
    private val messageListener: CustomerMessageListener,
) : KafkaConsumer<CustomerAvroModel> {

    private val log by LoggerDelegator()

    @KafkaListener(
        id = "\${kafka-consumer-config.customer-group-id}",
        topics = ["\${cart-service.customer-topic-name}"]
    )
    override fun receive(
        @Payload models: List<CustomerAvroModel>,
        @Header(KafkaHeaders.RECEIVED_KEY) keys: List<String>,
        @Header(KafkaHeaders.RECEIVED_PARTITION) partitions: List<Int>,
        @Header(KafkaHeaders.OFFSET) offsets: List<Long>
    ) {
        log.info(
            "${models.size} number of customer create message received with " +
                    "keys:$keys, partitions:$partitions and offsets: $offsets",
        )

        models.forEach { model ->
            log.info(
                "Processing create customer " +
                        "for customer id: ${model.id} "
            )
            messageListener.customerCreated(
                CustomerModel(
                    id = model.id,
                    email = model.email,
                    firstName = model.firstName,
                    lastName = model.lastName
                )
            )
        }
    }
}