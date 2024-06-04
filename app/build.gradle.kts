plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
}

android {
    namespace = "com.example.proyecto_2"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.proyecto_2"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.1")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.3.1")

    // Import the BoM for the Firebase platform
    dependencies {
        // Import the BoM for the Firebase platform
        implementation(platform("com.google.firebase:firebase-bom:32.3.1"))

        // Add the dependency for the Firebase Authentication library
        // When using the BoM, you don't specify versions in Firebase library dependencies
        implementation("com.google.firebase:firebase-auth-ktx")
    }

    implementation("com.github.Gruzer:simple-gauge-android:0.3.1")
    implementation("org.quanqi:android-holo-graph:0.1.0")
    implementation("androidx.drawerlayout:drawerlayout:1.2.0")
    implementation("androidx.coordinatorlayout:coordinatorlayout:1.2.0")

    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.6.10") // Asegúrate de usar la versión correcta de Kotlin
    implementation("com.squareup.okhttp3:okhttp:4.9.3") // Para okhttp3
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.3") // Para logging interceptor
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4") // Para coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4") // Para coroutines en Android




    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.media3.common)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}