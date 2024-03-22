import org.springframework.boot.gradle.tasks.bundling.BootBuildImage

plugins {
    id("application-conventions")
    id("org.springframework.boot") version Versions.SPRING_BOOT
    id("io.spring.dependency-management") version "1.1.4"
}
dependencies {
    implementation(Dependencies.SPRING_CLOUD_STARTER_GATEWAY)
    implementation(Dependencies.SPRING_CLOUD_STARTER_NETFLIX_EUREKA_CLIENT)

    implementation(Dependencies.SPRING_BOOT_STARTER_ACTUATOR)
    implementation(Dependencies.SPRING_BOOT_STARTER_WEBFLUX)
    implementation(Dependencies.SPRING_CLOUD_STARTER_CONFIG)
    implementation(Dependencies.SPRING_CLOUD_STARTER_BUS_AMQP)
    developmentOnly(Dependencies.SPRING_BOOT_DEVTOOLS)
    runtimeOnly("io.netty:netty-resolver-dns-native-macos:4.1.107.Final:osx-aarch_64")

    implementation(project(":common-utils"))
    implementation(Dependencies.JJWT_API)
    runtimeOnly(Dependencies.JJWT_JACKSON)
    runtimeOnly(Dependencies.JJWT_IMPL)
}

application {
    mainClass.set("net.philipheur.hshop.gatewayservice.GatewayServiceApplicationKt")
}

tasks.named<BootBuildImage>("bootBuildImage") {
    imageName.set("${project.group}/discovery-service:${project.version}")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${Versions.SPRING_CLOUD}")
    }
}

configurations {
    runtimeOnly {
        exclude(group = "commons-logging", module = "commons-logging")
    }
}

description = "gateway-server"