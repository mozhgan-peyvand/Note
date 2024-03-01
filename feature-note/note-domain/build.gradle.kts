plugins {
    id("merat.android.library")
}

android {
    namespace = "com.peivandian.note_domain"
}

dependencies {

    implementation(libs.coreKtx)
    implementation(libs.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)

    implementation(project(":feature-note:note-models"))
    implementation(project(":base"))

}