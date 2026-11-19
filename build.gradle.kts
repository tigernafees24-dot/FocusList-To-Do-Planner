plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    // Play Store par daalne se pehle isay apne unique package name se badal lein
    // (namespace aur applicationId dono, aur MainActivity.kt ka package bhi).
    namespace = "com.aajkekaam.todo"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.aajkekaam.todo"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
    }

    // GitHub Actions keystore ko environment variables se deta hai.
    // Android Studio se signed build banate waqt ye hissa istemal nahi hota.
    val keystorePath: String? = System.getenv("KEYSTORE_PATH")
    signingConfigs {
        if (keystorePath != null) {
            create("release") {
                storeFile = file(keystorePath)
                storePassword = System.getenv("KEYSTORE_PASSWORD")
                keyAlias = System.getenv("KEY_ALIAS")
                keyPassword = System.getenv("KEY_PASSWORD")
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            if (keystorePath != null) {
                signingConfig = signingConfigs.getByName("release")
            }
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.15.0")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("androidx.activity:activity-ktx:1.9.3")
    implementation("androidx.webkit:webkit:1.12.1")
}
