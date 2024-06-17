// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        // Other repositories
    }
    dependencies {

        classpath ("com.android.tools.build:gradle:7.0.0") // Or any version that supports API level 34
    }
}
plugins {
    id("com.android.application") version "8.1.0" apply false
    id("org.jetbrains.kotlin.android") version "1.8.0" apply false
}

