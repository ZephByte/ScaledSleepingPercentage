package com.zephbyte.scaledsleepingpercentage

import net.fabricmc.api.ModInitializer

class ScaledSleepingPercentage : ModInitializer {

    override fun onInitialize() {

        LOGGER.info("Scaled Sleeping Percentage mod initializing...")

        ConfigManager.loadConfig()
        //ModCommands.register()

        LOGGER.info("Scaled Sleeping Percentage mod initialized.")

    }
}