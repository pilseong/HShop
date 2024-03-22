apply<CommonConventionsPlugin>()

dependencies {
    implementation(project(":common-utils"))
//    implementation(project(":outbox"))
    implementation(project(":cart-domain-service"))
    api(project(":kafka-model"))
//    implementation(project(":kafka-producer"))
    api(project(":kafka-consumer"))
}

description = "cart-messaging"