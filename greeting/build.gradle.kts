import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
//val localUserId: String = gradleLocalProperties(rootDir,providers).getProperty("USERNAME")
//val localToken: String = gradleLocalProperties(rootDir,providers).getProperty("TOKEN")

plugins {
    alias(libs.plugins.android.library)
//    id 'com.android.library'
    alias(libs.plugins.kotlin.android)
//    id 'org.jetbrains.kotlin.android'
    `maven-publish`
//    id 'maven-publish'
}

android {
    namespace = "com.kaydu.greeting"
    compileSdk = 33

    defaultConfig {
        minSdk = 24
        targetSdk = 33

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"),
                          "proguard-rules.pro")
        }
    }

    buildFeatures.compose = true
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.1"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)

}

publishing {
    repositories {
        maven {
            val userId: String by project
            val token: String by project
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/MiaShopgal/PrivateSampleAndroidLibrary")
            credentials {
                //read property from gradle.property
                username = userId
                password = token
                //read property from local.property
//                username = localUserId
//                password = localToken
            }
        }
    }
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.github.kayduemre"
            artifactId = "greeting"
            version = "1.0.1"

            afterEvaluate{
                from(components["release"])
            }
        }
    }
}