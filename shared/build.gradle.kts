import org.jetbrains.kotlin.gradle.dsl.KotlinTargetContainerWithPresetFunctions
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    id("com.android.library")
    id("kotlinx-serialization")
    id("com.squareup.sqldelight")
    id("org.jetbrains.kotlin.multiplatform")
    id("org.jetbrains.kotlin.native.cocoapods")
}

version = 1.0

android {
    compileSdkVersion(29)
    defaultConfig {
        minSdkVersion(16)
        targetSdkVersion(29)
    }
}

sqldelight {
    database(name = "TodoAppDatabase") {
        packageName = "io.github.wellingtoncosta.todoapp"
    }
}

kotlin {
    android("android")

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-core:1.3.2")
                implementation("io.ktor:ktor-client-json:1.3.2")
                implementation("io.ktor:ktor-client-logging:1.3.2")
                implementation("io.ktor:ktor-client-serialization:1.3.2")

                implementation("org.jetbrains.kotlin:kotlin-stdlib-common:1.3.72")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-common:1.3.7")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-common:0.20.0")
            }
        }

        val androidMain by getting {
            dependencies {
                api("io.ktor:ktor-client-android:1.3.2")
                api("io.ktor:ktor-client-core-jvm:1.3.2")
                api("io.ktor:ktor-client-logging-jvm:1.3.2")
                api("io.ktor:ktor-client-serialization-jvm:1.3.2")

                api("com.squareup.sqldelight:android-driver:1.3.0")

                api("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.7")
                api("org.jetbrains.kotlinx:kotlinx-serialization-runtime:0.20.0")
            }
        }

        val iosMain by creating {
            iosTarget {
                dependencies {
                    api("io.ktor:ktor-client-ios:1.3.2")
                    api("io.ktor:ktor-client-core-native:1.3.2")
                    api("io.ktor:ktor-client-logging-native:1.3.2")
                    api("io.ktor:ktor-client-serialization-native:1.3.2")

                    api("com.squareup.sqldelight:native-driver:1.3.0")

                    api("org.jetbrains.kotlinx:kotlinx-coroutines-core-native:1.3.7")
                    api("org.jetbrains.kotlinx:kotlinx-serialization-runtime-native:0.20.0")
                }
            }
        }
    }

    cocoapods {
        authors = "Wellington Costa Pereira"
        license = "MIT"
        summary = "Common core for Todo App."
        homepage = "https://github.com/wellingtoncosta/todo-app-kotlin-multiplatform"
    }
}

fun KotlinTargetContainerWithPresetFunctions.iosTarget(
    configuration: KotlinTargetContainerWithPresetFunctions.() -> Unit
) {
    apply(configuration)

    val isDevice = System.getenv("SDK_NAME")?.startsWith("iphoneos") == true
    if (isDevice) {
        iosArm64("ios")
    } else {
        iosX64("ios")
    }

    targets.getByName<KotlinNativeTarget>("ios").compilations.forEach {
        it.kotlinOptions.freeCompilerArgs += arrayOf("-linker-options", "-lsqlite3")
    }
}
