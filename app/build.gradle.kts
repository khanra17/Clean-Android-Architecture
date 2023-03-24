plugins {
    kotlin("android")
    kotlin("kapt")
    id("com.android.application")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.khanra17.cleanandroidarchitecture"
    compileSdk = 33
    compileSdkPreview = "UpsideDownCake"

    defaultConfig {
        applicationId = "com.khanra17.cleanandroidarchitecture"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "com.khanra17.cleanandroidarchitecture.PostAppTestRunner"
        testInstrumentationRunnerArguments["clearPackageData"] = "true"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    testOptions {
        execution = "ANDROIDX_TEST_ORCHESTRATOR"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(mapOf("path" to ":domain")))
    implementation(project(mapOf("path" to ":data-remote")))
    implementation(project(mapOf("path" to ":data-local")))
    implementation(project(mapOf("path" to ":data-repository")))
    implementation(project(mapOf("path" to ":presentation-common")))
    implementation(project(mapOf("path" to ":presentation-post")))
    implementation(project(mapOf("path" to ":presentation-user")))

    // Core
    implementation("androidx.activity:activity-compose:1.8.0-alpha02")
    implementation("androidx.navigation:navigation-compose:2.5.3")
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0-alpha01")

    // Compose
    implementation(platform("androidx.compose:compose-bom:2023.01.00"))
    implementation("androidx.compose.material3:material3")
    debugImplementation("androidx.compose.ui:ui-tooling")

    // Hilt
    implementation("com.google.dagger:hilt-android:2.45")
    kapt("com.google.dagger:hilt-android-compiler:2.45")

    // Test *FIXME: Instrumentation tests are not working
    androidTestImplementation("androidx.test.ext:junit:1.2.0-alpha01")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.0-alpha01")
    androidTestImplementation("androidx.test.espresso:espresso-idling-resource:3.6.0-alpha01")
    androidTestImplementation("androidx.test:core:1.6.0-alpha01")
    androidTestImplementation("androidx.test:rules:1.6.0-alpha01")
    androidTestImplementation("androidx.test:runner:1.6.0-alpha01")
    androidTestImplementation("com.google.dagger:hilt-android-testing:2.45")
    kaptAndroidTest("com.google.dagger:hilt-android-compiler:2.45")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.5.0-alpha01")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.5.0-alpha01")
    androidTestUtil("androidx.test:orchestrator:1.5.0-alpha01")
}