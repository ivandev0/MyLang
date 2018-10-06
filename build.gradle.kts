val generateGrammarSource: AntlrTask by tasks

plugins {
    idea
    java
    antlr
}

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    antlr( "org.antlr", "antlr4", "4.7.1")
    compile("org.antlr", "antlr4-runtime", "4.7.1")
    testCompile("junit", "junit", "4.12")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

generateGrammarSource.apply {
    outputDirectory = file("${project.projectDir}/src/main/java/antlr/")
    arguments.addAll(listOf("-Dlanguage=Java", "-visitor", "-package", "antlr"))
}

tasks.withType<JavaCompile> {
    dependsOn(generateGrammarSource)
}