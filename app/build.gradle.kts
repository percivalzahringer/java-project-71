plugins {
    id("java")
    application
    checkstyle
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
}

tasks.withType<JavaCompile>(){
    options.compilerArgs.addAll(listOf("-Aproject=${project.group}/${project.name}"))
}

tasks.getByName("run", JavaExec::class) {
    standardInput = System.`in`
}


tasks.test {
    useJUnitPlatform()
}