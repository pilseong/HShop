
apply<CommonConventionsPlugin>()

dependencies {
    implementation(project(":common-utils"))
    implementation(project(":common-domain"))
//    api(project(":saga"))
//    api(project(":outbox"))
    api(project(":user-domain-core"))

    implementation(Dependencies.SPRING_BOOT_STARTER_VALIDATION)
    implementation(Dependencies.SPRING_TX)
    implementation(Dependencies.SPRING_SECURITY_CRYPTO)
    implementation(Dependencies.SPRING_SECURITY_CORE)
    implementation(Dependencies.SPRING_BOOT_STARTER_JSON)
    testImplementation(Dependencies.MOKITO_KOTLIN)
    testImplementation(Dependencies.SPRING_BOOT_STARTER_TEST)

    implementation(Dependencies.JJWT_API)
    runtimeOnly(Dependencies.JJWT_JACKSON)
    runtimeOnly(Dependencies.JJWT_IMPL)

}
description = "user-domain-service"