import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    id("java")
    jacoco
    application
    checkstyle
    id("io.freefair.lombok") version "8.6"
}

jacoco {
    toolVersion = "0.8.12"
}

application {
    mainClass.set("hexlet.code.App")
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.2"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.2")

    implementation("org.apache.commons:commons-lang3:3.14.0")
    implementation ("info.picocli:picocli:4.7.5")

    annotationProcessor("info.picocli:picocli-codegen:4.7.5")

    implementation("com.fasterxml.jackson.core:jackson-databind:2.17.0")

    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.17.1")
    implementation("commons-io:commons-io:2.7")

    implementation("com.google.guava:guava:33.1.0-jre")

    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.17.1")
}

tasks.withType<JavaCompile>(){
    options.compilerArgs.addAll(listOf("-Aproject=${project.group}/${project.name}"))
}

tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
    testLogging {
        exceptionFormat = TestExceptionFormat.FULL
        events = mutableSetOf(TestLogEvent.FAILED, TestLogEvent.PASSED, TestLogEvent.SKIPPED)
        showStandardStreams = true
    }
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports { xml.required.set(true) }
}