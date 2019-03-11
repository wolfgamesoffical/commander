import org.jetbrains.gradle.ext.CopyrightConfiguration
import org.jetbrains.gradle.ext.ProjectSettings

plugins {
    `java-library`
    `maven-publish`

    id("com.jfrog.bintray") version "1.8.4"
    id("org.jetbrains.gradle.plugin.idea-ext") version "0.5"
}

val versionObject = Version(major="1", minor="0", patch="0")

project.group = "com.typicalbot.commander"
project.version = versionObject

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

repositories {
    jcenter()
}

dependencies {
    // JDA
    api("net.dv8tion:JDA:3.8.3_461")
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    options.isIncremental = true
}

idea {
    project {
        this as ExtensionAware
        configure<ProjectSettings> {
            this as ExtensionAware
            configure<CopyrightConfiguration> {
                useDefault = "TypicalBot Commander License"
                profiles {
                    create("TypicalBot Commander License") {
                        keyword = "Copyright"
                        notice = """
                            MIT License

                            Copyright (c) 2019 Nicholas Sylke and Bryan Pikaard.

                            Permission is hereby granted, free of charge, to any person obtaining a copy
                            of this software and associated documentation files (the "Software"), to deal
                            in the Software without restriction, including without limitation the rights
                            to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
                            copies of the Software, and to permit persons to whom the Software is
                            furnished to do so, subject to the following conditions:

                            The above copyright notice and this permission notice shall be included in all
                            copies or substantial portions of the Software.

                            THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
                            IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
                            FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
                            AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
                            LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
                            OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
                            SOFTWARE.
                        """.trimIndent()
                    }
                }
            }
        }
    }
}

class Version(
        val major: String,
        val minor: String,
        val patch: String) {
    override fun toString() = "$major.$minor.$patch"
}