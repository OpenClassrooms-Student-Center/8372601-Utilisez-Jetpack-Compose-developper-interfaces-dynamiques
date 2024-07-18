plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.opc.bestpodcast"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.opc.bestpodcast"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_19
        targetCompatibility = JavaVersion.VERSION_19
    }
    kotlinOptions {
        jvmTarget = "19"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    // compose BoM
    implementation(platform(libs.androidx.compose.bom))

    // Material 3
    implementation(libs.androidx.material3)

    // Facultatif : Icones Material Design supplémentaires
    implementation(libs.androidx.material.icons)

    // Outillage
    implementation(libs.androidx.ui.tooling.preview)

    // Intégration avec les activités
    implementation(libs.androidx.activity.compose)

    // Facultatif : Intégration avec les viewModel
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    // BOM Compose pour les test UI
    androidTestImplementation(platform(libs.androidx.compose.bom))
    // UI Tests
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    androidTestImplementation(libs.androidx.ui.test.junit4)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.coil.compose)

    implementation(libs.androidx.ui.text.google.fonts)
}
