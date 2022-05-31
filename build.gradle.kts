plugins {
    kotlin("jvm") version "1.6.21"
}

group = "com.nexenio"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    implementation("commons-codec:commons-codec:1.15")
    implementation("de.mkammerer:argon2-jvm:2.11")

    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.8.2")
    testImplementation("org.assertj:assertj-core:3.22.0")
}

tasks.test {
    useJUnitPlatform()
}
