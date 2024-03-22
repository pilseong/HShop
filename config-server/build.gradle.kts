
plugins {
    id("application-conventions")
    id("org.springframework.boot") version Versions.SPRING_BOOT
    id("io.spring.dependency-management") version "1.1.4"
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${Versions.SPRING_CLOUD}")
    }
}

dependencies {
    implementation(Dependencies.SPRING_AUTOCONFIGURE)
    implementation(Dependencies.SPRING_CLOUD_CONFIG_SERVER)
    implementation(Dependencies.SPRING_CLOUD_STARTER_BUS_AMQP)
    implementation(Dependencies.SPRING_BOOT_STARTER_ACTUATOR)
    implementation(Dependencies.SPRING_BOOT_STARTER_SECURITY)
    implementation(project(":common-utils"))
}

application {
    // Define the main class for the application.
    mainClass.set("net.philipheur.hshop.configserver.ConfigServerKt")
}


description = "config-server"