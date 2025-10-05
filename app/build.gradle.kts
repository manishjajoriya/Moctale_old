import java.io.FileInputStream
import java.util.Properties

plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.kotlin.compose)
  id("com.google.devtools.ksp")
  id("com.google.dagger.hilt.android")
}

val localProperties =
  Properties().apply {
    val localFile = rootProject.file("local.properties")
    if (localFile.exists()) {
      load(FileInputStream(localFile))
    }
  }

val authToken: String = localProperties.getProperty("auth_token", "")

android {
  namespace = "com.manishjajoriya.moctale"
  compileSdk = 36

  defaultConfig {
    applicationId = "com.manishjajoriya.moctale"
    minSdk = 26
    targetSdk = 36
    versionCode = 1
    versionName = "1.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    buildConfigField("String", "AUTH_TOKEN", "\"$authToken\"")
    android.buildFeatures.buildConfig = true
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
  }
  buildFeatures { compose = true }
}

//noinspection UseTomlInstead
dependencies {
  val retrofitVersion = "3.0.0"
  val daggerHiltVersion = "2.57.2"
  val coilVersion = "3.3.0"
  val viewmodelVersion = "2.9.4"
  val splashScreen = "1.0.1"
  val navVersion = "2.9.5"
  val youtubePlayerVersion = "13.0.0"
  val mpAndroidCharVersion = "3.1.0"

  //  Api Calling
  implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
  implementation("com.squareup.retrofit2:converter-gson:$retrofitVersion")
  // Dependency Injection
  implementation("com.google.dagger:hilt-android:$daggerHiltVersion")
  ksp("com.google.dagger:hilt-android-compiler:$daggerHiltVersion")
  implementation("androidx.hilt:hilt-navigation-compose:1.3.0")
  // Image Loading
  implementation("io.coil-kt.coil3:coil-compose:$coilVersion")
  implementation("io.coil-kt.coil3:coil-network-okhttp:$coilVersion")
  // ViewModel
  implementation("androidx.lifecycle:lifecycle-viewmodel-compose:$viewmodelVersion")
  // Splash Screen
  implementation("androidx.core:core-splashscreen:$splashScreen")
  // Navigation
  implementation("androidx.navigation:navigation-compose:$navVersion")
  // YouTube PlayBack
  implementation("com.pierfrancescosoffritti.androidyoutubeplayer:core:$youtubePlayerVersion")
  // MPAndroidChart
  implementation("com.github.PhilJay:MPAndroidChart:v$mpAndroidCharVersion")

  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.lifecycle.runtime.ktx)
  implementation(libs.androidx.activity.compose)
  implementation(platform(libs.androidx.compose.bom))
  implementation(libs.androidx.compose.ui)
  implementation(libs.androidx.compose.ui.graphics)
  implementation(libs.androidx.compose.ui.tooling.preview)
  implementation(libs.androidx.compose.material3)
  testImplementation(libs.junit)
  androidTestImplementation(libs.androidx.junit)
  androidTestImplementation(libs.androidx.espresso.core)
  androidTestImplementation(platform(libs.androidx.compose.bom))
  androidTestImplementation(libs.androidx.compose.ui.test.junit4)
  debugImplementation(libs.androidx.compose.ui.tooling)
  debugImplementation(libs.androidx.compose.ui.test.manifest)
}
