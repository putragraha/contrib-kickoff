object Libraries {

    // Classpath Gradle Plugin
    const val classpathGradle = "com.android.tools.build:gradle:${Versions.classpathGradleVersion}"
    const val classpathKotlinGradle =
        "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}"
    const val classpathHilt =
        "com.google.dagger:hilt-android-gradle-plugin:${Versions.classpathHiltVersion}"

    // Core
    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlinVersion}"
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtxVersion}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompatVersion}"
    const val multidex = "androidx.multidex:multidex:${Versions.multidexVersion}"

    // Design
    const val materialDesign =
        "com.google.android.material:material:${Versions.materialDesignVersion}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constrainLayoutVersion}"

    // Navigation
    const val navigationFragment =
        "androidx.navigation:navigation-fragment-ktx:${Versions.navigationVersion}"
    const val navigationUi =
        "androidx.navigation:navigation-ui-ktx:${Versions.navigationVersion}"

    // Network
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
    const val retrofitConverterMoshi =
        "com.squareup.retrofit2:converter-moshi:${Versions.retrofitVersion}"
    const val moshi = "com.squareup.moshi:moshi-kotlin:${Versions.moshiVersion}"
    const val moshiCodeGen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshiVersion}"

    // Room
    const val roomKtx = "androidx.room:room-ktx:${Versions.roomVersion}"
    const val roomRuntime = "androidx.room:room-runtime:${Versions.roomVersion}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.roomVersion}"

    // Dependency Injection
    const val hilt = "com.google.dagger:hilt-android:${Versions.hiltVersion}"
    const val hiltCompiler = "com.google.dagger:hilt-compiler:${Versions.hiltVersion}"

    // Image Load
    const val glide = "com.github.bumptech.glide:glide:${Versions.glideVersion}"
    const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glideVersion}"

    // Paging
    const val paging = "androidx.paging:paging-runtime:${Versions.pagingVersion}"

    // Test
    const val jUnit = "junit:junit:${Versions.jUnitVersion}"
    const val jUnitTest = "androidx.test.ext:junit:${Versions.jUnitTestVersion}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espressoVersion}"
}