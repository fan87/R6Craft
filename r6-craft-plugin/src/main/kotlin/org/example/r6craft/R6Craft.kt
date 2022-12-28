package org.example.r6craft

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.plugin.java.JavaPlugin
import org.example.r6craft.commands.CommandManager
import org.example.r6craft.config.ConfigManager
import org.example.r6craft.main.Loader
import java.util.logging.Level

object R6Craft {

    val plugin = Loader.INSTANCE
    val logger: Logger = LogManager.getFormatterLogger()

    fun onDisable() {

    }

    fun onEnable() {
        ConfigManager
        CommandManager
    }

    fun onLoad() {

    }

    fun onTabComplete(
        sender: CommandSender,
        command: Command,
        alias: String,
        args: Array<out String>
    ): MutableList<String>? {
        return null
    }

    fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): Boolean {
        return false
    }

}