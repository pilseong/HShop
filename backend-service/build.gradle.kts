plugins {
    id("application-conventions")
    id("org.springframework.boot") version Versions.SPRING_BOOT
    kotlin("plugin.jpa") version Versions.KOTLIN
}

dependencies {
    implementation(Dependencies.SPRING_BOOT_STARTER_WEB)
    implementation(Dependencies.SPRING_BOOT_STARTER_DATA_JPA)
    implementation(Dependencies.SPRING_BOOT_STARTER_TEST)
    implementation(Dependencies.SPRING_BOOT_STARTER_SECURITY)
    implementation(Dependencies.WEBJARS_JQUERY)
    implementation(Dependencies.WEBJARS_BOOTSTRAP)
    implementation(Dependencies.WEBJARS_LOCATOR_CORE)
    runtimeOnly(Dependencies.SPRING_BOOT_DEVTOOLS)
    runtimeOnly(Dependencies.MYSQL_CONNECTOR)
    testImplementation(Dependencies.H2_DATABASE)
}

description="backend-service"