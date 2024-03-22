
apply<CommonConventionsPlugin>()

dependencies {
    implementation(project(":common-utils"))
    implementation(project(":common-controller"))
    implementation(project(":settings-domain-service"))

    implementation(Dependencies.OPEN_CSV)
    implementation(Dependencies.SPRING_BOOT_STARTER_WEB)
    api(Dependencies.SPRING_BOOT_STARTER_SECURITY)
    implementation(Dependencies.SPRING_BOOT_STARTER_VALIDATION)
}

description = "settings-controller"