import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val generateGrammarSource: AntlrTask by tasks

plugins {
    idea
    java
    antlr
    kotlin("jvm") version "1.2.71"
}

repositories {
    mavenCentral()
}

dependencies {
    compile(kotlin("stdlib-jdk8"))
    antlr( "org.antlr", "antlr4", "4.7.1")
    compile("org.antlr", "antlr4-runtime", "4.7.1")
    testCompile("junit", "junit", "4.12")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

generateGrammarSource.apply {
    outputDirectory = file("${project.buildDir}/generated-src/antlr/main/myLangParser")
    arguments.addAll(listOf("-Dlanguage=Java", "-visitor", "-package", "myLangParser","${project.projectDir}/src/main/antlr/MyLang.g4"))
}

tasks.withType<JavaCompile> {
    dependsOn(generateGrammarSource)
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}