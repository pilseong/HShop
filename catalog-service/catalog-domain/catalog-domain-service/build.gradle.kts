
apply<CommonConventionsPlugin>()

dependencies {
    implementation(project(":common-utils"))
//    api(project(":saga"))
//    api(project(":outbox"))

    api(project(":catalog-domain-core"))

    implementation(Dependencies.SPRING_BOOT_STARTER_VALIDATION)
    implementation(Dependencies.SPRING_TX)
    implementation(Dependencies.SPRING_SECURITY_CRYPTO)
    implementation(Dependencies.SPRING_BOOT_STARTER_JSON)
    testImplementation(Dependencies.MOKITO_KOTLIN)
    testImplementation(Dependencies.SPRING_BOOT_STARTER_TEST)

}
description = "catalog-domain-service"