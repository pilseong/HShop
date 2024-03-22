apply<CommonConventionsPlugin>()

plugins {
    kotlin("plugin.jpa") version Versions.KOTLIN
}

dependencies {
    implementation(project(":common-utils"))
    implementation(project(":common-domain"))
    implementation(project(":user-domain-service"))

    api(Dependencies.SPRING_BOOT_STARTER_DATA_JPA)
    implementation(Dependencies.SPRING_TX)
    implementation(Dependencies.SPRING_WEB)

    runtimeOnly(Dependencies.POSTGRESQL)
    runtimeOnly(Dependencies.H2_DATABASE)
    testImplementation(Dependencies.SPRING_BOOT_STARTER_TEST)
    testImplementation(Dependencies.SPRING_SECURITY_CRYPTO)
}

description = "user-dataaccess"