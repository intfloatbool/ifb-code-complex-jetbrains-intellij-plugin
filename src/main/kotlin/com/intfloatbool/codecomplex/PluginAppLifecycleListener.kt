package com.intfloatbool.codecomplex

import com.intellij.ide.AppLifecycleListener
import com.intfloatbool.codecomplex.settingz.PluginSettings

class PluginAppLifecycleListener : AppLifecycleListener {
    override fun appFrameCreated(commandLineArgs: MutableList<String>) {
        val state = PluginSettings.instance.state
        if(state != null) {
            PluginGlobalz.updateGlobalsFromState(state)
        }
    }
}