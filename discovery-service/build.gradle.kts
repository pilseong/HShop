import org.springframework.boot.gradle.tasks.bundling.BootBuildImage

plugins {
    id("application-conventions")
    id("org.springframework.boot") version Versions.SPRING_BOOT
    id("io.spring.dependency-management") version "1.1.4"
}
dependencies {
    implementation(Dependencies.SPRING_CLOUD_STARTER_NETFLIX_EUREKA_SERVER)
}

application {

    mainClass.set("net.philipheur.hshop.discoveryservice.DiscoveryServiceApplicationKt")
}

tasks.named<BootBuildImage>("bootBuildImage") {
    imageName.set("${project.group}/discovery-service:${project.version}")
}

extra["springCloudVersion"] = "2023.0.0"

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}


description = "discovery-server"