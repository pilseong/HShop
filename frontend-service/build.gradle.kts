plugins {
    id("application-conventions")
    id("org.springframework.boot") version Versions.SPRING_BOOT
}

dependencies {
    implementation(Dependencies.SPRING_BOOT_STARTER_WEB)
}

description="frontend-service"