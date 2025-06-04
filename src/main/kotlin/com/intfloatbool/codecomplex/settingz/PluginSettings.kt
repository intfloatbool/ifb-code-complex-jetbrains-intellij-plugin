package com.intfloatbool.codecomplex.settingz

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.ui.JBColor
import com.intfloatbool.codecomplex.PluginGlobalz

@Service(
    Service.Level.APP
)

@State (
    name = "PluginSettings",
    storages = [Storage("CodeComplexPluginSettings.xml")]
)
class PluginSettings : PersistentStateComponent<PluginSettings.State> {
    class State {
        var linearComplexityLabelOption: String = "O ( N )"
        var quadraticComplexityLabelOption: String = "O ( N ^ 2 )"
        var cubicComplexityLabelOption: String = "O ( N ^ 3 )"
        var veryHardComplexityLabelOption: String = "O ( !!! )"

        var linearComplexityColorOption: RGBAData = PluginGlobalz.jbColorToRGBAData(JBColor.green)
        var quadraticComplexityColorOption: RGBAData = PluginGlobalz.jbColorToRGBAData(JBColor.yellow)
        var cubicComplexityColorOption: RGBAData = PluginGlobalz.jbColorToRGBAData(JBColor.orange)
        var veryHardComplexityColorOption: RGBAData = PluginGlobalz.jbColorToRGBAData(JBColor.red)
    }

    var linearComplexityLabelOption: String = "O ( N )"
    var quadraticComplexityLabelOption: String = "O ( N ^ 2 )"
    var cubicComplexityLabelOption: String = "O ( N ^ 3 )"
    var veryHardComplexityLabelOption: String = "O ( !!! )"

    var linearComplexityColorOption: JBColor = JBColor.green
    var quadraticComplexityColorOption: JBColor = JBColor.yellow
    var cubicComplexityColorOption: JBColor = JBColor.orange
    var veryHardComplexityColorOption: JBColor = JBColor.red


    override fun getState(): State? {
        val state = State()
        state.linearComplexityLabelOption = linearComplexityLabelOption;
        state.quadraticComplexityLabelOption = quadraticComplexityLabelOption;
        state.cubicComplexityLabelOption = cubicComplexityLabelOption;
        state.veryHardComplexityLabelOption = veryHardComplexityLabelOption;

        state.linearComplexityColorOption = PluginGlobalz.jbColorToRGBAData(linearComplexityColorOption)
        state.quadraticComplexityColorOption = PluginGlobalz.jbColorToRGBAData(quadraticComplexityColorOption)
        state.cubicComplexityColorOption = PluginGlobalz.jbColorToRGBAData(cubicComplexityColorOption)
        state.veryHardComplexityColorOption = PluginGlobalz.jbColorToRGBAData(veryHardComplexityColorOption)

        return state
    }

    override fun loadState(s: State) {
        linearComplexityLabelOption = s.linearComplexityLabelOption;
        quadraticComplexityLabelOption = s.quadraticComplexityLabelOption;
        cubicComplexityLabelOption = s.cubicComplexityLabelOption;
        veryHardComplexityLabelOption = s.veryHardComplexityLabelOption;

        linearComplexityColorOption = PluginGlobalz.rgbaDataToJBColor(s.linearComplexityColorOption)
        quadraticComplexityColorOption = PluginGlobalz.rgbaDataToJBColor(s.quadraticComplexityColorOption)
        cubicComplexityColorOption = PluginGlobalz.rgbaDataToJBColor(s.cubicComplexityColorOption)
        veryHardComplexityColorOption = PluginGlobalz.rgbaDataToJBColor(s.veryHardComplexityColorOption)
    }


    companion object {
        val instance : PluginSettings
            get() = ApplicationManager.getApplication().getService(PluginSettings::class.java)
    }
}