plugins {
    // Apply the Kotlin JVM plugin to add support for Kotlin.
    kotlin("jvm") version "1.6.0"
    kotlin("plugin.serialization") version "1.5.31"
    id("com.github.johnrengelman.shadow") version "7.0.0"
    `maven-publish`

    // Apply the application plugin to add support for building a jar
    java
}

repositories {
    // Use mavenCentral
    mavenCentral()
    jcenter()

    maven(url = "https://jitpack.io")
    maven(url = "https://repo.spongepowered.org/maven")
    maven(url = "https://repo.velocitypowered.com/snapshots/")
}

dependencies {
    // Align versions of all Kotlin components
    compileOnly(platform("org.jetbrains.kotlin:kotlin-bom"))

    // Use the Kotlin JDK 8 standard library.
    compileOnly(kotlin("stdlib"))

    // Use the Kotlin reflect library.
    compileOnly(kotlin("reflect"))

    // Use the kotest library
    testImplementation("io.kotest:kotest-assertions-core:5.2.1")
    testImplementation("io.kotest:kotest-runner-junit5:5.2.1")

    // Compile Minestom into project
    compileOnly("com.github.Minestom", "Minestom", "7867313290")

    // Use kotlinx serialization
    compileOnly("org.jetbrains.kotlinx", "kotlinx-serialization-json", "1.3.1")

    // implement KStom
    compileOnly("com.github.Project-Cepi:KStom:af120b5455")

    // Add MiniMessage
    compileOnly("net.kyori:adventure-text-minimessage:4.10.1")

    // Add Hotbarty
    compileOnly("com.github.Project-Cepi:Gooey:f083a23ef5")

    // Add Subfuzzy
    compileOnly("com.github.Project-Cepi:Subfuzzy:3bd3e24424")

    // Use mworlza's canvas
    implementation("com.github.Project-Cepi:canvas:d3d937c54d")

    // Add fuzzywuzzy
    implementation("me.xdrop:fuzzywuzzy:1.4.0")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}

configurations {
    testImplementation {
        extendsFrom(configurations.compileOnly.get())
    }
}

tasks {
    named<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar>("shadowJar") {
        archiveBaseName.set("kepi")
        mergeServiceFiles()
        minimize()

    }

    withType<Test> { useJUnitPlatform() }

    build { dependsOn(shadowJar) }

}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

val compileKotlin: org.jetbrains.kotlin.gradle.tasks.KotlinCompile by tasks
compileKotlin.kotlinOptions.jvmTarget = JavaVersion.VERSION_17.toString()
compileKotlin.kotlinOptions {
    freeCompilerArgs = listOf("-Xinline-classes", "-Xopt-in=kotlin.RequiresOptIn")
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = project.properties["group"] as? String?
            artifactId = project.name
            version = project.properties["version"] as? String?

            from(components["java"])
        }
    }
}