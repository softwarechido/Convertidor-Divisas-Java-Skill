import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        mavenCentral()
    }
}

plugins {
    java
    kotlin("jvm") version "1.3.21"
}

group = "convertidordivisas"
version = "1.0-kotlin"
description = "convertidordivisas"

tasks.withType<JavaCompile> {
    targetCompatibility = JavaVersion.VERSION_1_8.toString()
    sourceCompatibility = JavaVersion.VERSION_1_8.toString()
}

tasks.withType<KotlinCompile> { configureWithJavaCompat() }

val compileTestKotlin by tasks.getting(KotlinCompile::class) { configureWithJavaCompat() }


repositories {
    mavenCentral()
}

/**
 * Our dependencies with other libs and jars
 */
dependencies {
    implementation(kotlin("stdlib-jdk8", "1.3.21"))

    implementation("com.amazon.alexa:ask-sdk:2.11.2")
}

/**
 * Extension function to change easily the java compatibility version
 */
fun KotlinCompile.configureWithJavaCompat(javaVersion: String = JavaVersion.VERSION_1_8.toString()) {
    kotlinOptions.jvmTarget = javaVersion

    sourceCompatibility = javaVersion
    targetCompatibility = javaVersion
}