
apply<CommonConventionsPlugin>()

dependencies {
    implementation(project(":common-utils"))
    implementation(project(":common-domain"))
}

description = "user-domain-core"