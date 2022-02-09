plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdkVersion(Configs.compileSdkVersion)
    buildToolsVersion(Configs.buildToolsVersion)

    defaultConfig {
        applicationId(Configs.applicationId)
        minSdkVersion(Configs.minSdkVersion)
        targetSdkVersion(Configs.targetSdkVersion)
        versionCode = Configs.versionCode
        versionName = Configs.versionName
        multiDexEnabled = true

        testInstrumentationRunner(Configs.testInstrumentationRunner)
    }

    buildTypes {
        val properties = CredentialHelper.getCredentialProperties(rootDir.absolutePath)
        val userAgent = "\"${properties.getProperty(CredentialHelper.KEY_USER_AGENT)}\""
        val oauthToken = "\"${properties.getProperty(CredentialHelper.KEY_OAUTH_TOKEN)}\""

        getByName("debug") {
            buildConfigField("String", CredentialHelper.KEY_USER_AGENT, userAgent)
            buildConfigField("String", CredentialHelper.KEY_OAUTH_TOKEN, oauthToken)
        }

        getByName("release") {
            buildConfigField("String", CredentialHelper.KEY_USER_AGENT, userAgent)
            buildConfigField("String", CredentialHelper.KEY_OAUTH_TOKEN, oauthToken)

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
        freeCompilerArgs = freeCompilerArgs + "-Xopt-in=kotlin.RequiresOptIn"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    // Core
    implementation(Libraries.kotlinStdLib)
    implementation(Libraries.coreKtx)
    implementation(Libraries.appCompat)
    implementation(Libraries.multidex)

    // Design
    implementation(Libraries.materialDesign) // TODO: 05/01/22 Remove material dependency 
    implementation(Libraries.constraintLayout)

    // Navigation
    implementation(Libraries.navigationFragment)
    implementation(Libraries.navigationUi)

    // Network
    implementation(Libraries.retrofit)
    implementation(Libraries.retrofitConverterMoshi)
    implementation(Libraries.moshi)
    kapt(Libraries.moshiCodeGen)

    // Room
    implementation(Libraries.roomKtx)
    implementation(Libraries.roomRuntime)
    implementation(Libraries.roomPaging)
    kapt(Libraries.roomCompiler)

    // Dependency Injection
    implementation (Libraries.hilt)
    kapt(Libraries.hiltCompiler)

    // Paging
    implementation(Libraries.paging)

    // Test
    testImplementation(Libraries.jUnit)
    androidTestImplementation(Libraries.jUnitTest)
    androidTestImplementation(Libraries.espresso)
}