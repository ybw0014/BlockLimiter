package net.guizhanss.blocklimiter.core.services

import io.github.thebusybiscuit.slimefun4.libraries.dough.config.Config
import net.guizhanss.blocklimiter.BlockLimiter
import net.guizhanss.blocklimiter.utils.ConfigUtils.saveDefaultFile
import net.guizhanss.guizhanlib.slimefun.addon.AddonConfig
import org.bukkit.configuration.ConfigurationSection

class ConfigService(private val plugin: BlockLimiter) {
    private val config = AddonConfig(plugin, "config.yml")
    private val limitGroupsConfig = Config(plugin, "limit-groups.yml")

    var autoUpdate = true
        private set
    var limitGroups: ConfigurationSection? = null
        private set

    init {
        reload()
    }

    fun reload() {
        config.reload()
        config.addMissingKeys()
        saveDefaultFile(plugin, "limit-groups.yml")
        limitGroupsConfig.reload()

        autoUpdate = config.getBoolean("options.auto-update", true)
        limitGroups = limitGroupsConfig.configuration.getConfigurationSection("")

        config.save()
    }
}
