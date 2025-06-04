plugins {
    id("java")

    id("org.jetbrains.kotlin.jvm") version "1.9.25"
    id("org.jetbrains.intellij.platform") version "2.3.0"
}

group = "com.intfloatbool"
version = "1.0"

kotlin {
    jvmToolchain(21)
}


repositories {
    mavenCentral()
    intellijPlatform {
        defaultRepositories()
    }
}

// Configure Gradle IntelliJ Plugin
// Read more: https://plugins.jetbrains.com/docs/intellij/tools-intellij-platform-gradle-plugin.html
dependencies {
    intellijPlatform {
        create("IC", "2024.2.5")
        //intellijIdeaCommunity("2024.2.1")
//        testFramework(org.jetbrains.intellij.platform.gradle.TestFrameworkType.Platform)

        // Add necessary plugin dependencies for compilation here, example:
        bundledPlugin("com.intellij.java")
        bundledPlugin("org.jetbrains.kotlin")


    }
    implementation("io.github.tree-sitter:ktreesitter:0.24.1")
    // jna уже есть в клиенте jetbrains (поэтому complieOnly), иначе будетjava.lang.NoClassDefFoundError
    compileOnly("net.java.dev.jna:jna:5.13.0")
    testImplementation(kotlin("test"))
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.0")


}

intellijPlatform {
    pluginConfiguration {
        ideaVersion {
            sinceBuild = "241"
        }

        changeNotes = """
      Initial version
    """.trimIndent()
    }
}

tasks {
    // Set the JVM compatibility versions
    withType<JavaCompile> {
        sourceCompatibility = "21"
        targetCompatibility = "21"
    }
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "21"
    }
}

tasks.test {
    useJUnitPlatform()
}

tasks.processResources {
    from("src/main/resources/lib") {
        into("lib")
    }
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

tasks {
    patchPluginXml {
        sinceBuild.set("241")
        untilBuild.set("999.*") // или просто убери
    }

    buildPlugin {
        dependsOn(patchPluginXml)
    }
}