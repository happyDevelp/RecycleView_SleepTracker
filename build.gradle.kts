// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    kotlin("kapt") version "1.9.10"
}


buildscript {
    repositories {
        google()
    }
    dependencies{
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.7.5")
        classpath ("android.arch.navigation:navigation-safe-args-gradle-plugin:1.0.0")
        classpath ("com.android.tools.build:gradle:3.6.1")
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.61")

    }
}