package me.fan87.r6craft.main

import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.plugin.java.JavaPlugin
import me.fan87.r6craft.R6Craft

class Loader: JavaPlugin() {

    companion object {
        lateinit var INSTANCE: Loader
    }

    init {
        INSTANCE = this
    }

    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        alias: String,
        args: Array<out String>
    ): MutableList<String>? {
        return R6Craft.onTabComplete(sender, command, alias, args)
    }

    override fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): Boolean {
        return R6Craft.onCommand(sender, command, label, args)
    }

    override fun onDisable() {
        R6Craft.onDisable()
    }

    override fun onLoad() {
        R6Craft.onLoad()
    }

    override fun onEnable() {
        R6Craft.onEnable()
    }
}