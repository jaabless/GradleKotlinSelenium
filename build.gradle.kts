plugins {
    kotlin("jvm") version "1.9.22"
    id("java")
}

group = "com.automation"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    // Selenium WebDriver
    implementation("org.seleniumhq.selenium:selenium-java:4.15.0")

    // Kotlin Standard Library
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.22")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.9.22")

    // Testing frameworks
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
    testImplementation("org.testng:testng:7.8.0")

    // Logging
    implementation("org.slf4j:slf4j-api:2.0.9")
    implementation("ch.qos.logback:logback-classic:1.4.11")

    // WebDriver Management
    implementation("io.github.bonigarcia:webdrivermanager:5.6.3")

    // Configuration
    implementation("com.typesafe:config:1.4.2")

    // JSON parsing
    implementation("com.google.code.gson:gson:2.10.1")
}

tasks.test {
    useJUnitPlatform()
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = "21"
    }
    // Disable incremental compilation cache to avoid Windows/OneDrive file locking issues
    incremental = false
}


