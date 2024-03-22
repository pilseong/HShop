import org.springframework.boot.gradle.tasks.bundling.BootBuildImage

plugins {
    id("application-conventions")
    id("org.springframework.boot") version Versions.SPRING_BOOT
    id("io.spring.dependency-management") version "1.1.4"
}

dependencies {
    implementation(project(":common-controller"))
//    implementation(project(":common-dataaccess"))
    implementation(project(":customer-controller"))
    implementation(project(":customer-dataaccess"))
    implementation(project(":customer-domain-service"))
    implementation(project(":customer-domain-core"))
    implementation(project(":customer-messaging"))
    implementation(project(":kafka-config-data"))

    implementation(Dependencies.SPRING_CLOUD_STARTER_CONFIG)
    implementation(Dependencies.SPRING_BOOT_STARTER)

    implementation(Dependencies.SPRING_CLOUD_STARTER_BUS_AMQP)
    implementation(Dependencies.SPRING_BOOT_STARTER_ACTUATOR)
    implementation(Dependencies.SPRING_CLOUD_STARTER_NETFLIX_EUREKA_CLIENT)
    developmentOnly(Dependencies.SPRING_BOOT_DEVTOOLS)
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${Versions.SPRING_CLOUD}")
    }
}


application {
    // Define the main class for the application.
    mainClass.set("net.philipheur.hshop.customerservice.CustomerServiceApplicationKt")
}

tasks.named<BootBuildImage>("bootBuildImage") {
    imageName.set("${project.group}/customer-service:${project.version}")
}

description = "customer-app"