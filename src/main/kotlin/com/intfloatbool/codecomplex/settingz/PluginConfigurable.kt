package com.intfloatbool.codecomplex.settingz

import com.intellij.ide.DataManager
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.options.Configurable
import com.intellij.openapi.util.NlsContexts
import com.intellij.ui.JBColor
import com.intfloatbool.codecomplex.PluginGlobalz
import java.awt.Color
import javax.swing.JComponent

class PluginConfigurable : Configurable {
    private var settingComponent: PluginSettingsComponent? = null

    override fun createComponent(): JComponent? {
        settingComponent = PluginSettingsComponent()
        return settingComponent!!.panel
    }

    override fun isModified(): Boolean {

        val settings = PluginSettings.instance
        if(
            settingComponent!!.getLinearCompLabelText() != settings.linearComplexityLabelOption
            ||
            settingComponent!!.getQuadraticCompLabelText() != settings.quadraticComplexityLabelOption
            ||
            settingComponent!!.getCubicCompLabelText() != settings.cubicComplexityLabelOption
            ||
            settingComponent!!.getVeryHardCompLabelText() != settings.veryHardComplexityLabelOption

            ||
            settingComponent!!.getLinearCompColor() != settings.linearComplexityColorOption
            ||
            settingComponent!!.getQuadraticCompColor() != settings.quadraticComplexityColorOption
            ||
            settingComponent!!.getCubicCompColor() != settings.cubicComplexityColorOption
            ||
            settingComponent!!.getVeryHardCompColor() != settings.veryHardComplexityColorOption
        ) {
            return true
        }

        return false
    }

    override fun apply() {
        val settings = PluginSettings.instance
        settings.linearComplexityLabelOption = settingComponent!!.getLinearCompLabelText()
        settings.quadraticComplexityLabelOption = settingComponent!!.getQuadraticCompLabelText()
        settings.cubicComplexityLabelOption = settingComponent!!.getCubicCompLabelText()
        settings.veryHardComplexityLabelOption = settingComponent!!.getVeryHardCompLabelText()

        settings.linearComplexityColorOption = toJbColor(settingComponent!!.getLinearCompColor())
        settings.quadraticComplexityColorOption = toJbColor(settingComponent!!.getQuadraticCompColor())
        settings.cubicComplexityColorOption = toJbColor(settingComponent!!.getCubicCompColor())
        settings.veryHardComplexityColorOption = toJbColor(settingComponent!!.getVeryHardCompColor())

        applyToGlobals(settings)
        reopenAffectedFiles()
    }

    fun toJbColor(input: Color): JBColor {
        return JBColor(input, input)
    }

    override fun reset() {
        val settings = PluginSettings.instance
        settingComponent!!.setLinearCompLabelText(settings.linearComplexityLabelOption)
        settingComponent!!.setQuadraticCompLabelText(settings.quadraticComplexityLabelOption)
        settingComponent!!.setCubicCompLabelText(settings.cubicComplexityLabelOption)
        settingComponent!!.setVeryHardCompLabelText(settings.veryHardComplexityLabelOption)

        settingComponent!!.setLinearCompColor(settings.linearComplexityColorOption)
        settingComponent!!.setQuadraticCompColor(settings.quadraticComplexityColorOption)
        settingComponent!!.setCubicCompColor(settings.cubicComplexityColorOption)
        settingComponent!!.setVeryHardCompColor(settings.veryHardComplexityColorOption)

        applyToGlobals(settings)
        reopenAffectedFiles()
    }

    fun applyToGlobals(settings: PluginSettings) {
        PluginGlobalz.linearComplexityLabelOption = settings.linearComplexityLabelOption;
        PluginGlobalz.quadraticComplexityLabelOption = settings.quadraticComplexityLabelOption;
        PluginGlobalz.cubicComplexityLabelOption = settings.cubicComplexityLabelOption;
        PluginGlobalz.veryHardComplexityLabelOption = settings.veryHardComplexityLabelOption;

        PluginGlobalz.linearComplexityColorOption = settings.linearComplexityColorOption;
        PluginGlobalz.quadraticComplexityColorOption = settings.quadraticComplexityColorOption;
        PluginGlobalz.cubicComplexityColorOption = settings.cubicComplexityColorOption;
        PluginGlobalz.veryHardComplexityColorOption = settings.veryHardComplexityColorOption;
    }

    fun reopenAffectedFiles() {
        val project = DataManager.getInstance()
            .getDataContext(settingComponent!!.resetButton)
            .getData(CommonDataKeys.PROJECT)
        if(project != null) {
            val fileEditorManager = FileEditorManager.getInstance(project)
            val openFiles = fileEditorManager.openFiles

            for (file in openFiles) {
                val extension = file.extension?.lowercase()
                if (extension == "kt" || extension == "java") {
                    fileEditorManager.closeFile(file)
                    fileEditorManager.openFile(file, true)
                }
            }
        }
    }

    override fun getDisplayName(): @NlsContexts.ConfigurableName String? {
        return "IFB CodeComplex Plugin"
    }

}