
apply<CommonConventionsPlugin>()

dependencies {
    implementation(project(":common-controller"))
    implementation(project(":cart-domain-service"))

    implementation(Dependencies.SPRING_BOOT_STARTER_WEB)
//    implementation(Dependencies.SPRING_BOOT_STARTER_JSON)
    implementation(Dependencies.SPRING_BOOT_STARTER_SECURITY)
    implementation(Dependencies.SPRING_BOOT_STARTER_VALIDATION)

    implementation(Dependencies.JJWT_API)
    runtimeOnly(Dependencies.JJWT_JACKSON)
    runtimeOnly(Dependencies.JJWT_IMPL)
}

description = "cart-controller"