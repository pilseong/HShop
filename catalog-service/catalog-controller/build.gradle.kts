apply<CommonConventionsPlugin>()

plugins {
    id("io.spring.dependency-management") version "1.1.4"
}

dependencies {
    implementation(project(":common-utils"))
    implementation(project(":common-controller"))
    api(project(":catalog-domain-service"))

    implementation(Dependencies.OPEN_CSV)
    implementation(Dependencies.SPRING_BOOT_STARTER_WEB)
    implementation(Dependencies.SPRING_BOOT_STARTER_SECURITY)
    implementation(Dependencies.SPRING_BOOT_STARTER_VALIDATION)
    implementation(Dependencies.SPRING_BOOT_STARTER_ACTUATOR)
    implementation(Dependencies.SPRING_BOOT_STARTER_AOP)

    api(Dependencies.SPRING_CLOUD_STARTER_OPENFEIGN)
    implementation("org.springframework.cloud:spring-cloud-starter-circuitbreaker-resilience4j")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${Versions.SPRING_CLOUD}")
    }
}




description = "catalog-controller"