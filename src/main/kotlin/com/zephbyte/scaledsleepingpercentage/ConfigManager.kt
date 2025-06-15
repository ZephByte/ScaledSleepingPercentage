package com.zephbyte.scaledsleepingpercentage

import com.electronwill.nightconfig.core.file.CommentedFileConfig
import com.electronwill.nightconfig.core.file.FileNotFoundAction
import com.electronwill.nightconfig.core.io.WritingMode
import net.fabricmc.loader.api.FabricLoader

object ConfigManager {
    private val CONFIG_PATH = FabricLoader.getInstance().configDir.resolve("$MOD_ID.toml")
    private const val DEFAULT_PERCENTAGE = 50

    // Define your config properties here
    var sleepingPercentage: Int = DEFAULT_PERCENTAGE
        private set // Make setter private to ensure it's only changed via loadConfig

    fun loadConfig() {
        val builder = CommentedFileConfig.builder(CONFIG_PATH)
            .autosave()
            .writingMode(WritingMode.REPLACE) // Or WritingMode.MERGE to preserve comments/structure

        val config = builder.build()
        config.load() // Load the file

        // Read values, providing defaults if they don't exist
        sleepingPercentage = config.getOptional<Int>("general.sleepingPercentage")
            .orElse(DEFAULT_PERCENTAGE) // Default value if not found

        // Ensure the config file has the default if it was missing
        // This also writes comments if createDefaultConfig was called
        if (!config.contains("general.sleepingPercentage")) {
            config.set<Int>("general.sleepingPercentage", sleepingPercentage)
            config.setComment("general.sleepingPercentage", " The percentage of players that need to sleep to skip the night. (0-100)")
        }

        config.save() // Save any changes or defaults
        config.close() // Close the file

        LOGGER.info("Config loaded. Sleeping percentage set to $sleepingPercentage")
    }
}