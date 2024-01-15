plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
}

android {
    namespace = "tr.berke.sedef.greenmiles.com"
    compileSdk = 34
    buildFeatures{
        viewBinding = true;
    }

    defaultConfig {
        applicationId = "tr.berke.sedef.greenmiles.com"
        minSdk = 28
        targetSdk = 33
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
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment:2.7.6")
    implementation("androidx.navigation:navigation-ui:2.7.6")
    implementation("com.google.android.gms:play-services-maps:18.2.0")
    implementation("com.google.firebase:firebase-firestore:24.10.0")
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
    implementation("com.google.firebase:firebase-storage:20.3.0")

    testImplementation("junit:junit:4.13.2")
    testImplementation ("io.mockk:mockk:1.12.0")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation ("androidx.test:runner:1.4.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")


    //NavigationComponent dependencies
    implementation ("androidx.navigation:navigation-fragment:2.3.0")
    implementation ("androidx.navigation:navigation-ui:2.3.0")

    //Animation dependencies
    implementation("com.airbnb.android:lottie:6.1.0")

    //Firebase dependencies
    implementation(platform("com.google.firebase:firebase-bom:32.7.0"))
    // For Firebase Authentication library
    implementation("com.google.firebase:firebase-auth")

    //implementation picasso library for upload image to web

    implementation("com.squareup.picasso:picasso:2.5.2")

}