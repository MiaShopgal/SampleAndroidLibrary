import java.util.Properties

// Load local.properties
val localProperties = Properties()
file("local.properties").takeIf { it.exists() }?.inputStream()?.use { inputStream ->
    localProperties.load(inputStream)
}

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://maven.pkg.github.com/MiaShopgal/PrivateSampleAndroidLibrary")
            credentials {
                username = localProperties.getProperty("USERNAME") ?: ""
                password = localProperties.getProperty("TOKEN") ?: ""
            }
        }
    }
}
rootProject.name = "SampleAndroidLibrary"
include (":app",":greeting")
