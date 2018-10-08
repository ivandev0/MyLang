val generateGrammarSource: AntlrTask by tasks

plugins {
    idea
    java
    antlr
}

repositories {
    mavenCentral()
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
    arguments.addAll(listOf("-Dlanguage=Java", "-visitor", "-package", "myLangParser","${project.projectDir}/src/main/antlr/MyLang.g4"))
}

tasks.withType<JavaCompile> {
    dependsOn(generateGrammarSource)
}