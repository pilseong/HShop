plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.7.0"
}
rootProject.name = "HShop"


include(":config-server")
project(":config-server").projectDir =
    file("config-server")

include(":discovery-service")
project(":discovery-service").projectDir =
    file("discovery-service")

include(":gateway-service")
project(":gateway-service").projectDir =
    file("gateway-service")

include(":backend-service")
include(":frontend-service")
include(":common-domain")
include(":common-utils")
include(":common-controller")


project(":common-domain").projectDir =
    file("common/common-domain")
project(":common-utils").projectDir =
    file("common/common-utils")
project(":common-controller").projectDir =
    file("common/common-controller")

include(":user-app")
include(":user-controller")
include(":user-dataaccess")
include(":user-domain-core")
include(":user-domain-service")


project(":user-app").projectDir =
    file("user-service/user-app")
project(":user-controller").projectDir =
    file("user-service/user-controller")
project(":user-dataaccess").projectDir =
    file("user-service/user-dataaccess")
project(":user-domain-core").projectDir =
    file("user-service/user-domain/user-domain-core")
project(":user-domain-service").projectDir =
    file("user-service/user-domain/user-domain-service")


include(":catalog-app")
include(":catalog-controller")
include(":catalog-dataaccess")
include(":catalog-domain-core")
include(":catalog-domain-service")


project(":catalog-app").projectDir =
    file("catalog-service/catalog-app")
project(":catalog-controller").projectDir =
    file("catalog-service/catalog-controller")
project(":catalog-dataaccess").projectDir =
    file("catalog-service/catalog-dataaccess")
project(":catalog-domain-core").projectDir =
    file("catalog-service/catalog-domain/catalog-domain-core")
project(":catalog-domain-service").projectDir =
    file("catalog-service/catalog-domain/catalog-domain-service")


include(":settings-app")
include(":settings-controller")
include(":settings-dataaccess")
include(":settings-domain-core")
include(":settings-domain-service")


project(":settings-app").projectDir =
    file("settings-service/settings-app")
project(":settings-controller").projectDir =
    file("settings-service/settings-controller")
project(":settings-dataaccess").projectDir =
    file("settings-service/settings-dataaccess")
project(":settings-domain-core").projectDir =
    file("settings-service/settings-domain/settings-domain-core")
project(":settings-domain-service").projectDir =
    file("settings-service/settings-domain/settings-domain-service")


include(":customer-domain-core")
include(":customer-domain-service")
include(":customer-dataaccess")
include(":customer-messaging")
include(":customer-controller")
include(":customer-app")

project(":customer-domain-core").projectDir =
    file("customer-service/customer-domain/customer-domain-core")
project(":customer-domain-service").projectDir =
    file("customer-service/customer-domain/customer-domain-service")
project(":customer-dataaccess").projectDir =
    file("customer-service/customer-dataaccess")
project(":customer-messaging").projectDir =
    file("customer-service/customer-messaging")
project(":customer-controller").projectDir =
    file("customer-service/customer-controller")
project(":customer-app").projectDir =
    file("customer-service/customer-app")


include(":kafka-config-data")
include(":kafka-model")
include(":kafka-producer")
include(":kafka-consumer")


project(":kafka-config-data").projectDir =
    file("infrastructure/kafka/kafka-config-data")
project(":kafka-model").projectDir =
    file("infrastructure/kafka/kafka-model")
project(":kafka-producer").projectDir =
    file("infrastructure/kafka/kafka-producer")
project(":kafka-consumer").projectDir =
    file("infrastructure/kafka/kafka-consumer")


include(":cart-domain-core")
include(":cart-domain-service")
include(":cart-dataaccess")
include(":cart-messaging")
include(":cart-controller")
include(":cart-app")

project(":cart-domain-core").projectDir =
    file("cart-service/cart-domain/cart-domain-core")
project(":cart-domain-service").projectDir =
    file("cart-service/cart-domain/cart-domain-service")
project(":cart-dataaccess").projectDir =
    file("cart-service/cart-dataaccess")
project(":cart-messaging").projectDir =
    file("cart-service/cart-messaging")
project(":cart-controller").projectDir =
    file("cart-service/cart-controller")
project(":cart-app").projectDir =
    file("cart-service/cart-app")