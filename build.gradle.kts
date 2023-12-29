plugins {
    kotlin("multiplatform") version "1.9.20"
    id("com.github.johnrengelman.shadow") version "8.1.1"
    java
}

group = "ru.cororo"
version = "1.0"

repositories {
    mavenCentral()
}

kotlin {
    val targets = listOf(
// FIXME: Not works
//        linuxX64("linux"),
        mingwX64("mingw")
    )

    jvm("jvm") {
        compilations.getByName("main") {
            dependencies {
                implementation(kotlin("stdlib"))
            }
        }
    }

    targets.forEach {
        it.apply {
            compilations.getByName("main") {
                cinterops {
                    val libcurl by creating {
                        packageName("curl")
                    }
                }
            }
            binaries {
                executable {
                    runTask.let { task ->
                        task?.args("https://example.org/")
                    }
                    entryPoint = "main"
                }
            }
        }
    }
}

val shadowJar = tasks.withType<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar> {
    manifest {
        attributes["Main-Class"] = "MainKt"
    }

    val target = kotlin.targets.find { it.targetName == "jvm" }!!
    from(target.compilations["main"].output)

    val runtimeClasspath = target.compilations["main"].compileDependencyFiles
    configurations = mutableListOf(runtimeClasspath)
}

tasks.getByName("jvmJar") {
    dependsOn(shadowJar)
}