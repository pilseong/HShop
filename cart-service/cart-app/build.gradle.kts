import org.springframework.boot.gradle.tasks.bundling.BootBuildImage

plugins {
    id("application-conventions")
    id("org.springframework.boot") version Versions.SPRING_BOOT
    id("io.spring.dependency-management") version "1.1.4"
}

dependencies {
    implementation(project(":common-controller"))
//    implementation(project(":common-dataaccess"))
    implementation(project(":cart-controller"))
    implementation(project(":cart-dataaccess"))
    implementation(project(":cart-domain-service"))
    implementation(project(":cart-domain-core"))
    implementation(project(":cart-messaging"))
    implementation(project(":kafka-config-data"))

    implementation(Dependencies.SPRING_CLOUD_STARTER_CONFIG)
    implementation(Dependencies.SPRING_BOOT_STARTER)

    implementation(Dependencies.SPRING_CLOUD_STARTER_BUS_AMQP)
    implementation(Dependencies.SPRING_BOOT_STARTER_ACTUATOR)
    implementation(Dependencies.SPRING_CLOUD_STARTER_NETFLIX_EUREKA_CLIENT)
//    developmentOnly(Dependencies.SPRING_BOOT_DEVTOOLS)
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${Versions.SPRING_CLOUD}")
    }
}


application {
    // Define the main class for the application.
    mainClass.set("net.philipheur.hshop.cartservice.CartServiceApplicationKt")
}

tasks.named<BootBuildImage>("bootBuildImage") {
    imageName.set("${project.group}/cart-service:${project.version}")
}

description = "cart-app"