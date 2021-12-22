object Libraries {

    // Classpath Gradle Plugin
    const val classpathGradle = "com.android.tools.build:gradle:${Versions.classpathGradleVersion}"
    const val classpathKotlinGradle =
        "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}"

    // Core
    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlinVersion}"
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtxVersion}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompatVersion}"

    // Design
    const val materialDesign =
        "com.google.android.material:material:${Versions.materialDesignVersion}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constrainLayoutVersion}"

    // Test
    const val jUnit = "junit:junit:${Versions.jUnitVersion}"
    const val jUnitTest = "androidx.test.ext:junit:${Versions.jUnitTestVersion}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espressoVersion}"
}