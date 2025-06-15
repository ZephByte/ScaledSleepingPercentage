package com.zephbyte.scaledsleepingpercentage

import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents
import net.minecraft.world.GameRules

class ScaledSleepingPercentage : ModInitializer {

    override fun onInitialize() {
        LOGGER.info("Scaled Sleeping Percentage mod initializing...")

        ConfigManager.loadConfig()
        registerGameruleSyncOnServerStart()

        LOGGER.info("Scaled Sleeping Percentage mod initialized.")
    }

    /**
     * Registers a listener to synchronize the PLAYERS_SLEEPING_PERCENTAGE gamerule
     * with the mod's configuration when the server starts.
     */
    private fun registerGameruleSyncOnServerStart() {
        ServerLifecycleEvents.SERVER_STARTED.register { server ->
            val gameRules = server.gameRules // From MinecraftServer
            val desiredPercentage = ConfigManager.sleepingPercentage

            val percentageRuleKey = GameRules.PLAYERS_SLEEPING_PERCENTAGE
            // GameRules.get() for PLAYERS_SLEEPING_PERCENTAGE returns a GameRules.Rule<Int>
            val rule = gameRules.get(percentageRuleKey)

            if (rule.get() != desiredPercentage) {
                rule.set(desiredPercentage, server)
                // Using .id for the gamerule name in the log
                LOGGER.info("Gamerule '${percentageRuleKey.name}' set to $desiredPercentage.")
            } else {
                LOGGER.info("Gamerule '${percentageRuleKey.name}' is already $desiredPercentage.")
            }
        }
    }
}