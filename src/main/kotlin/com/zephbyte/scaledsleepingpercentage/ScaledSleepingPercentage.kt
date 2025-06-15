package com.zephbyte.scaledsleepingpercentage

import net.fabricmc.api.ModInitializer

class ScaledSleepingPercentage : ModInitializer {

   var CONFIG: ScaledSleepingConfig = ScaledSleepingConfig.createAndLoad()

    override fun onInitialize() {

        LOGGER.info("Scaled Sleeping Percentage mod initializing...")

        LOGGER.info("Scaled Sleeping Percentage mod initialized.")

    }
}