apply<CommonConventionsPlugin>()

dependencies {
    api(project(":kafka-config-data"))
    api(Dependencies.SPRING_KAFKA)
    api(Dependencies.KAFKA_AVRO_SERIALIZER)
}

description = "kafka-consumer"
