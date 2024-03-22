apply<CommonConventionsPlugin>()

plugins {
    kotlin("plugin.jpa") version Versions.KOTLIN
}

dependencies {
    api(project(":cart-domain-service"))

    api(Dependencies.SPRING_TX)
    api(Dependencies.SPRING_BOOT_STARTER_DATA_JPA)
    runtimeOnly(Dependencies.POSTGRESQL)
}

description = "cart-dataaccess"