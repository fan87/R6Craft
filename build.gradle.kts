import project.ReadMeChapterGenerator
import java.util.Properties

plugins {
}

group = "me.fan87"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
}


tasks {
    register<ReadMeChapterGenerator>("generateReadMe") {
        group = "readme"
        description = "Generate the readme file with table of contents"
    }


    register<JavaExec>("runServer") {
        group = "run"
        description = "Run the test Minecraft Server"

        dependsOn(":r6-craft-plugin:build")


        val runDir = File(projectDir, "run/")
        val serverJar = File(runDir, "server.jar")
        val eulaFile = File(runDir, "eula.txt")
        val serverPropertiesFile = File(runDir, "server.properties")

        jvmArgs("-Xms512M", "-Xmx2G", "-Dlog4j.configurationFile=log4j2.xml")
        mainClass.set("org.bukkit.craftbukkit.bootstrap.Main")
        classpath(serverJar.absolutePath)
        workingDir(runDir.absolutePath)
        standardInput = System.`in`

        doFirst {
            if (!runDir.exists()) {
                runDir.mkdirs()
            }
            if (!serverJar.exists()) {
                serverJar.writeBytes(GradleUtils.getServerJar(project).readBytes())
            }

            if (!eulaFile.exists()) {
                eulaFile.createNewFile()
                eulaFile.writeText("eula=true")
            }

            if (!serverPropertiesFile.exists()) {
                serverPropertiesFile.createNewFile()
            }
            val properties = Properties()
            properties.load(serverPropertiesFile.reader())
            properties["enable-rcon"] = "true"
            properties["rcon.port"] = "25575"
            properties["rcon.password"] = GradleUtils.getRconPassword()
            properties.store(serverPropertiesFile.writer(), "Server Properties for R6Craft")
            val pluginFile: File = project(":r6-craft-plugin").tasks.getByName<Jar>("jar").archiveFile.get().asFile
            val destPluginFile = File(runDir, "/plugins/r6-craft-plugin-DEV.jar")
            if (!destPluginFile.parentFile.exists()) {
                destPluginFile.parentFile.mkdirs()
            }
            if (!destPluginFile.exists()) {
                destPluginFile.createNewFile()
            }
            destPluginFile.writeBytes(pluginFile.readBytes())

        }

    }
}