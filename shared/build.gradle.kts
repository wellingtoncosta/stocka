import org.jetbrains.kotlin.gradle.dsl.KotlinTargetContainerWithPresetFunctions

plugins {
    id("org.jetbrains.kotlin.multiplatform")
    id("org.jetbrains.kotlin.native.cocoapods")
    id("com.squareup.sqldelight")
}

version = 1.0

sqldelight {
    database(name = "TodoAppDatabase") {
        packageName = "io.github.wellingtoncosta.todoapp"
    }
}

kotlin {
    sourceSets["commonMain"].dependencies {
        implementation("org.jetbrains.kotlin:kotlin-stdlib-common:1.3.72")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-common:1.3.5")
    }

    jvm()
    sourceSets["jvmMain"].dependencies {
        implementation("com.squareup.sqldelight:sqlite-driver:1.3.0")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.5")
    }

    setupIosBuild()
    sourceSets["iosMain"].dependencies {
        implementation("com.squareup.sqldelight:native-driver:1.3.0")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-native:1.3.5")
    }

    cocoapods {
        authors = "Wellington Costa Pereira"
        license = "MIT"
        summary = "Common core for Todo App."
        homepage = "https://github.com/wellingtoncosta/todo-app-kotlin-multiplatform"
    }
}

fun KotlinTargetContainerWithPresetFunctions.setupIosBuild() {
    val sdkName = System.getenv("SDK_NAME")
    if (sdkName != null && sdkName.startsWith("iphoneos")) {
        iosArm64("ios")
    } else {
        iosX64("ios")
    }
}
