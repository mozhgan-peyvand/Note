plugins {
    id("merat.android.library")
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
}

android {
    namespace = "com.peivandian.di"
}

dependencies {

    implementation(libs.coreKtx)
    implementation(libs.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)

    implementation(project(":feature-note:note-models"))
    implementation(project(":feature-note:note-data"))

    //hilt
    implementation(libs.hiltAndroid)
    kapt(libs.hiltCompiler)
    implementation(libs.hiltNavigationCompose)
    kapt(libs.hiltAndroidX)

    //room
    implementation(libs.roomKtx)
    implementation(libs.roomRuntime)
    kapt(libs.roomCompiler)
    implementation(libs.roomCommon)

    // Android Test
    androidTestImplementation ("androidx.test.ext:junit:1.1.5")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")
    androidTestImplementation ("io.mockk:mockk:1.13.4")
    androidTestImplementation ("io.mockk:mockk-android:1.13.4")
    androidTestImplementation ("app.cash.turbine:turbine:0.12.1")


}