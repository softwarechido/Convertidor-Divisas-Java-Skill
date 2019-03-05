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
version = "1.0.0-kotlin"
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

    val junitJupiterVersion = "5.2.0"

    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitJupiterVersion")
    testImplementation("org.junit.platform:junit-platform-runner:1.2.0")
    testImplementation("org.junit.jupiter:junit-jupiter-params:$junitJupiterVersion")
    testRuntime("org.junit.jupiter:junit-jupiter-engine:$junitJupiterVersion")
}

/**
 * Extension function to change easily the java compatibility version
 */
fun KotlinCompile.configureWithJavaCompat(javaVersion: String = JavaVersion.VERSION_1_8.toString()) {
    kotlinOptions.jvmTarget = javaVersion

    sourceCompatibility = javaVersion
    targetCompatibility = javaVersion
}

/**
 * Configure the JAR to include dependencies and create a _fat jar_
 */
tasks.withType<Jar> {
    for (it in configurations["compileClasspath"]) {
        from(zipTree(it.absoluteFile))
    }
}