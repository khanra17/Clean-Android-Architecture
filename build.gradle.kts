// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        gradlePluginPortal()
    }
}
plugins{
    id("com.android.application") version "8.1.0-alpha10" apply false
    id("com.android.library") version "8.1.0-alpha10" apply false
    id("com.google.dagger.hilt.android") version "2.45" apply false
    id("com.google.devtools.ksp") version "1.8.10-1.0.9" apply false
}
apply(file("gradle/projectDependencyGraph.gradle"))
// TODO: Migrate to version catalog