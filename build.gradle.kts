import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val ktor_version: String by project
val logback_version: String by project

kotlin {
    // for strict mode
    explicitApi()
}

plugins {
    kotlin("jvm") version "1.7.10"
    kotlin("plugin.serialization") version "1.7.10"
    `maven-publish`
}

group = "com.github.otuva"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))

    dependencies {
        // Networking
        implementation("io.ktor:ktor-client-core:$ktor_version")
        implementation("io.ktor:ktor-client-cio:$ktor_version")

        // ktor auth-logging-json plugins
        implementation("io.ktor:ktor-client-auth:$ktor_version")
        implementation("io.ktor:ktor-client-content-negotiation:$ktor_version")
        implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")
        implementation("io.ktor:ktor-client-logging:$ktor_version")
        implementation("ch.qos.logback:logback-classic:$logback_version")

        // date and time
        implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")
    }
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.github.otuva"
            artifactId = "eksisozluk"
            version = "0.0.19-alpha"

            from(components["kotlin"])
        }
    }
}