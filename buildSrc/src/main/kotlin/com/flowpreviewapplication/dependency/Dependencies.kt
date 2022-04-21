package com.flowpreviewapplication.dependency

class Dependencies {

    /**
     * To define plugins
     */
    object BuildPlugins {
        val android by lazy { "com.android.tools.build:gradle:${Versions.gradlePlugin}" }
        val kotlin by lazy { "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}" }
    }

    /**
     * To define dependencies
     */
    object Deps {
        val appCompat by lazy { "androidx.appcompat:appcompat:${Versions.appCompat}" }
//        val kotlin by lazy { "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}" }
        val materialDesign by lazy { "com.google.android.material:material:${Versions.material}" }
        val constraintLayout by lazy { "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}" }
        val junit by lazy { "junit:junit:${Versions.jUnit}" }

    }
}