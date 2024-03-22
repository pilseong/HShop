apply<CommonConventionsPlugin>()

plugins {
    // Apply the application plugin to add support for building a CLI application in Java.
    application
}

dependencies {
    implementation(Dependencies.SPRING_BOOT_STARTER_THYMELEAF)
}