buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath(kotlin("gradle-plugin", "1.3.72"))
    }
}

repositories {
    jcenter()
    mavenCentral()
    maven(url = "https://kotlin.bintray.com/kotlinx")
}

plugins {
    application
    kotlin("jvm") version "1.3.72"
    id("com.github.johnrengelman.shadow") version "5.0.0"
}

group = "io.github.wellingtoncosta"
version = "1.0.0"

application {
    mainClassName = "io.github.wellingtoncosta.todoapp.backend.ServerKt"
}

tasks.withType<Jar> {
    manifest {
        attributes(
            mapOf(
                "Main-Class" to application.mainClassName
            )
        )
    }
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("io.ktor:ktor-server-core:1.3.2")
    implementation("io.ktor:ktor-server-jetty:1.3.2")
    implementation("ch.qos.logback:logback-classic:1.2.3")
}
