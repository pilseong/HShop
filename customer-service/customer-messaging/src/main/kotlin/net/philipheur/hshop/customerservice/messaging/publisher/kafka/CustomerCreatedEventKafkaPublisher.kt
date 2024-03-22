package net.philipheur.hshop.customerservice.messaging.publisher.kafka

import net.philipheur.hshop.common.utils.logging.LoggerDelegator
import net.philipheur.hshop.customerservice.domain.core.event.CustomerCreatedEvent
import net.philipheur.hshop.customerservice.domain.service.config.CustomerServiceConfigData
import net.philipheur.hshop.customerservice.domain.service.ports.output.message.publisher.CustomerMessagePublisher
import net.philipheur.hshop.infrastructure.kafka.model.avro.CustomerAvroModel
import net.philipheur.hshop.infrastructure.kafka.producer.service.KafkaProducer
import org.springframework.stereotype.Component


@Component
class CustomerCreatedEventKafkaPublisher(
    private val configData: CustomerServiceConfigData,
    private val kafkaProducer: KafkaProducer<String, CustomerAvroModel>,
) : CustomerMessagePublisher {

    private val log by LoggerDelegator()
    override fun publish(customerCreatedEvent: CustomerCreatedEvent) {
        log.info(
            "[CustomerService] publishing created event for " +
                    "customer id: ${customerCreatedEvent.customer.id!!.value}"
        )

        try {
            val customerAvroModel = CustomerAvroModel(
                customerCreatedEvent.customer.id!!.value,
                customerCreatedEvent.customer.email,
                customerCreatedEvent.customer.firstName,
                customerCreatedEvent.customer.lastName
            )

            kafkaProducer.send(
                topicName = configData.customerTopicName,
                key = customerAvroModel.id.toString(),
                message = customerAvroModel,
            ) { result, ex ->
                if (ex == null) {
                    val metadata = result!!.recordMetadata;
                    log.info(
                        "[CustomerService] customer created event sent successfully to Kafka for " +
                                "customer id: ${customerAvroModel.id} " +
                                "Topic: ${metadata.topic()}; " +
                                "Partition: ${metadata.partition()}; " +
                                "Offset: ${metadata.offset()}; " +
                                "TimeStamp: ${metadata.timestamp()};, " +
                                "at time: ${System.nanoTime()}"
                    )
                } else {
                    log.error(
                        "[CustomerService] Error while sending customer created event " +
                                "message $customerAvroModel " +
                                "to topic ${configData.customerTopicName}",
                        ex
                    )
                }
            }

            log.info(
                "[CustomerService] CustomerAvroModel sent to Kafka " +
                        "for customer id: ${customerAvroModel.id} and "
            )
        } catch (ex: Exception) {
            log.error(
                "[CustomerService] Error while sending CustomerAvroModel message " +
                        "to kafka with " +
                        "customer id: ${customerCreatedEvent.customer.id!!.value}, " +
                        "error: ${ex.message}",
                ex
            )
        }
    }
}