package org.example.r6craft.feature.impl

import org.bukkit.event.EventHandler
import org.bukkit.event.player.AsyncPlayerChatEvent
import org.example.r6craft.feature.Feature

class ExampleFeature: Feature("Example") {

    @EventHandler
    fun onChat(event: AsyncPlayerChatEvent) {
        event.player.sendMessage("Chat Event!")
    }

}