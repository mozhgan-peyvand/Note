plugins {
    id("merat.android.library")
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
}

android {
    namespace = "com.peivandian.note_ui"
}

dependencies {

    implementation(libs.coreKtx)
    implementation(libs.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)

    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    debugImplementation("androidx.compose.ui:ui-tooling")
    implementation("androidx.compose.material3:material3")
    implementation ("androidx.navigation:navigation-compose:2.7.1")
    implementation ("com.google.accompanist:accompanist-pager-indicators:0.30.1")
    implementation ("androidx.compose.foundation:foundation:1.4.1")

    implementation(project(":feature-note:note-domain"))
    implementation(project(":feature-note:note-models"))
    implementation(project(":base"))
    implementation("com.mohamedrejeb.richeditor:richeditor-compose:1.0.0-rc01")



    //hilt
    implementation(libs.hiltAndroid)
    kapt(libs.hiltCompiler)
    implementation(libs.hiltNavigationCompose)

}