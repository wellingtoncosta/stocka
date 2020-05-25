import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
    kotlin("jvm") version "1.3.72"
    kotlin("plugin.serialization") version "1.3.72"
}

group = "io.github.wellingtoncosta"
version = "1.0.0"

repositories {
    jcenter()
    mavenCentral()
    maven(url = "https://kotlin.bintray.com/kotlinx")
}

application {
    mainClassName = "io.github.wellingtoncosta.todoapp.backend.app.ServerKt"
}

tasks.withType<Jar> {
    manifest {
        attributes(
            mapOf(
                "Main-Class" to application.mainClassName
            )
        )
    }

    from(configurations.runtimeClasspath.map { configuration ->
        configuration.asFileTree.map {
            if(it.isDirectory) it else zipTree(it)
        }
    })

    archiveFileName.set("${project.name}.jar")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime:0.20.0")

    implementation("io.ktor:ktor-server-core:1.3.2")
    implementation("io.ktor:ktor-server-jetty:1.3.2")
    implementation("io.ktor:ktor-serialization:1.3.2")

    implementation("com.zaxxer:HikariCP:3.4.5")
    implementation("org.postgresql:postgresql:42.2.12")
    implementation("org.jetbrains.exposed:exposed:0.17.7")
    implementation("org.flywaydb:flyway-core:6.4.2")

    implementation("ch.qos.logback:logback-classic:1.2.3")

    implementation("de.huxhorn.sulky:de.huxhorn.sulky.ulid:8.2.0")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
