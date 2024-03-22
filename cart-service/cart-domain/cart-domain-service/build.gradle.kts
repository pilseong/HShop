
apply<CommonConventionsPlugin>()

dependencies {
    api(project(":common-utils"))
    api(project(":common-controller"))
    api(project(":cart-domain-core"))

    implementation(Dependencies.SPRING_TX)
    implementation(Dependencies.SPRING_BOOT_STARTER_JSON)
    implementation(Dependencies.SPRING_BOOT_STARTER_VALIDATION)
    implementation(Dependencies.SPRING_SECURITY_CRYPTO)
    implementation(Dependencies.SPRING_SECURITY_CORE)

    testImplementation(Dependencies.MOKITO_KOTLIN)
    testImplementation(Dependencies.SPRING_BOOT_STARTER_TEST)

}
description = "cart-domain-service"