
apply<CommonConventionsPlugin>()

dependencies {

    implementation(project(":common-utils"))
    implementation(project(":common-domain"))
    implementation(project(":common-controller"))
    implementation(project(":user-domain-service"))

    implementation(Dependencies.OPEN_CSV)
    implementation(Dependencies.SPRING_BOOT_STARTER_WEB)
    api(Dependencies.SPRING_BOOT_STARTER_SECURITY)
    implementation(Dependencies.SPRING_BOOT_STARTER_VALIDATION)

    implementation(Dependencies.JJWT_API)
    runtimeOnly(Dependencies.JJWT_JACKSON)
    runtimeOnly(Dependencies.JJWT_IMPL)
}

description = "user-controller"