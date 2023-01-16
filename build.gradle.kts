import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.10"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    jcenter()
    mavenCentral()
    mavenLocal()
}

dependencies {
    implementation("org.testng:testng:7.1.0")
    implementation("org.testng:testng:7.1.0")
    implementation("org.testng:testng:7.1.0")
    testImplementation(kotlin("test"))
    // https://mvnrepository.com/artifact/org.testng/testng
    testImplementation("org.testng:testng:7.7.1")

    implementation("io.appium:java-client:8.3.0")
    implementation("org.seleniumhq.selenium:selenium-java:4.7.2")
    // https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-api
    implementation("org.seleniumhq.selenium:selenium-api:4.7.2")
    // https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-remote-driver
    implementation("org.seleniumhq.selenium:selenium-remote-driver:4.7.2")
}

tasks.test {
    useTestNG()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}