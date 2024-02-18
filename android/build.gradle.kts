import org.jetbrains.kotlin.js.translate.context.Namer.kotlin

buildscript {
    repositories {
        mavenLocal()
        mavenCentral()
    }
    dependencies {
        classpath("com.google.gms:google-services:4.4.0")
        classpath("com.google.android.libraries.mapsplatform.secrets-gradle-plugin:secrets-gradle-plugin:2.0.1")
        classpath("org.openapitools:openapi-generator-gradle-plugin:6.6.0")
}
}

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.2.1" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id("org.openapi.generator") version "6.6.0"
}
val generatedSourcesPath = "$rootDir/app/src/api"
val apiDescriptionFile = "$rootDir/app/src/main/resources/api.json"
val apiRootName = "com.anurag.firebaseauthflow.api.client"
openApiGenerate {
    generatorName.set("kotlin")
    inputSpec.set(apiDescriptionFile)
    outputDir.set(generatedSourcesPath)
    apiPackage.set("$apiRootName.api")
    invokerPackage.set("$apiRootName.invoker")
    modelPackage.set("$apiRootName.model")
    configOptions.set(mapOf("library" to "jvm-retrofit2"))
   skipValidateSpec.set(true)
}
