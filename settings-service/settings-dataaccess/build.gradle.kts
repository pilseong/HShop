apply<CommonConventionsPlugin>()

plugins {
    kotlin("plugin.jpa") version Versions.KOTLIN
}

dependencies {
    api(project(":common-utils"))
    api(project(":settings-domain-service"))

    api(Dependencies.SPRING_BOOT_STARTER_DATA_JPA)
    implementation(Dependencies.SPRING_TX)
    implementation(Dependencies.SPRING_WEB)
    runtimeOnly(Dependencies.POSTGRESQL)
    testImplementation(Dependencies.SPRING_BOOT_STARTER_TEST)
    testImplementation(Dependencies.SPRING_SECURITY_CRYPTO)
    testImplementation(Dependencies.H2_DATABASE)
//    runtimeOnly(Dependencies.MYSQL_CONNECTOR)
}

description = "settings-dataaccess"