
apply<CommonConventionsPlugin>()

dependencies {
    implementation(project(":common-utils"))
    api(project(":common-domain"))
}


description = "customer-domain-core"