
apply<CommonConventionsPlugin>()

dependencies {
    api(project(":common-domain"))
    implementation(Dependencies.SPRING_WEB)
    implementation(Dependencies.SPRING_AUTOCONFIGURE)
    implementation(Dependencies.OPEN_CSV)
    implementation(Dependencies.JJWT_API)
    runtimeOnly(Dependencies.JJWT_JACKSON)
    runtimeOnly(Dependencies.JJWT_IMPL)
    testImplementation("com.google.jimfs:jimfs:1.3.0")
}


description = "common-utils"