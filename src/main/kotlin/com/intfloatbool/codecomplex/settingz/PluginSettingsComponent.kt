package com.intfloatbool.codecomplex.settingz
import com.intellij.codeInsight.codeVision.CodeVisionHost
import com.intellij.codeInsight.codeVision.CodeVisionState
import com.intellij.codeInsight.codeVision.EditorCodeVisionContext
import com.intellij.codeInsight.daemon.DaemonCodeAnalyzer
import com.intellij.ide.DataManager
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.ui.ColorPanel
import com.intellij.ui.JBColor
import com.intellij.util.ui.FormBuilder
import com.intfloatbool.codecomplex.PluginGlobalz
import java.awt.Color
import java.awt.Font
import javax.swing.JButton
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JTextField
import javax.swing.SwingConstants
import javax.swing.border.EmptyBorder

//CodeComplex by IFB
class PluginSettingsComponent {

    private val linearCompLabelTextField = JTextField()
    private val quadraticCompLabelTextField = JTextField()
    private val cubicCompLabelTextField = JTextField()
    private val veryHardCompLabelTextField = JTextField()

    private val pluginHeader = JLabel("CodeComplex – shows O-complexity for Java & Kotlin methods.")
    private val freeVersionLabel = JLabel("Free version. Buy premium to customize colors!")

    // premium only
    private val linearCompColorPanel = ColorPanel()
    private val quadraticCompColorPanel = ColorPanel()
    private val cubicCompColorPanel = ColorPanel()
    private val veryHardCompColorPanel = ColorPanel()

    public val resetButton = JButton("Reset to defaults")

    lateinit var panel: JPanel

    init {

        resetButton.addActionListener {
            resetForce()
        }

        pluginHeader.font = Font("Dialog", Font.BOLD, 14)
        pluginHeader.horizontalAlignment = SwingConstants.CENTER

        if(PluginGlobalz.isPremium())
        {
            panel = FormBuilder.createFormBuilder()

                .addComponent(pluginHeader)

                .addSeparator()

                .addLabeledComponent(JLabel("Linear Complexity Label O(N) :"), linearCompLabelTextField)
                .addLabeledComponent(JLabel("Choose color:"), linearCompColorPanel)

                .addLabeledComponent(JLabel("Quadratic Complexity Label O(N^2) :"), quadraticCompLabelTextField)
                .addLabeledComponent(JLabel("Choose color:"), quadraticCompColorPanel)

                .addLabeledComponent(JLabel("Cubic Complexity Label O(N^3) :"), cubicCompLabelTextField)
                .addLabeledComponent(JLabel("Choose color:"), cubicCompColorPanel)

                .addLabeledComponent(JLabel("Very Hard Complexity Label > O(N^3) :"), veryHardCompLabelTextField)
                .addLabeledComponent(JLabel("Choose color:"), veryHardCompColorPanel)

                .addSeparator()

                .addComponent(resetButton)

                .panel
        }
        else
        {
            freeVersionLabel.font = Font("Serif", Font.ITALIC, 18)
            freeVersionLabel.horizontalAlignment = SwingConstants.CENTER

            linearCompColorPanel.selectedColor = JBColor.green
            quadraticCompColorPanel.selectedColor = JBColor.yellow
            cubicCompColorPanel.selectedColor = JBColor.orange
            veryHardCompColorPanel.selectedColor = JBColor.red

            panel = FormBuilder.createFormBuilder()

                .addComponent(pluginHeader)

                .addSeparator()

                .addComponent(freeVersionLabel)

                .addSeparator()

                .addLabeledComponent(JLabel("Linear Complexity Label O(N) :"), linearCompLabelTextField)

                .addLabeledComponent(JLabel("Quadratic Complexity Label O(N^2) :"), quadraticCompLabelTextField)

                .addLabeledComponent(JLabel("Cubic Complexity Label O(N^3) :"), cubicCompLabelTextField)

                .addLabeledComponent(JLabel("Very Hard Complexity Label > O(N^3) :"), veryHardCompLabelTextField)

                .addSeparator()

                .addComponent(resetButton)

                .panel
        }
    }

    fun resetForce() {
        linearCompLabelTextField.text = "O ( N )"
        quadraticCompLabelTextField.text = "O ( N ^ 2 )"
        cubicCompLabelTextField.text = "O ( N ^ 3 )"
        veryHardCompLabelTextField.text = "O ( !!! )"

        linearCompColorPanel.selectedColor = JBColor.green
        quadraticCompColorPanel.selectedColor = JBColor.yellow
        cubicCompColorPanel.selectedColor = JBColor.orange
        veryHardCompColorPanel.selectedColor = JBColor.red
    }

    fun getLinearCompLabelText(): String = linearCompLabelTextField.text
    fun getQuadraticCompLabelText(): String = quadraticCompLabelTextField.text
    fun getCubicCompLabelText(): String = cubicCompLabelTextField.text
    fun getVeryHardCompLabelText():String = veryHardCompLabelTextField.text

    //setterz text
    fun setLinearCompLabelText(value: String) {
        linearCompLabelTextField.text = value
    }
    fun setQuadraticCompLabelText(value: String) {
        quadraticCompLabelTextField.text = value
    }
    fun setCubicCompLabelText(value: String) {
        cubicCompLabelTextField.text = value
    }
    fun setVeryHardCompLabelText(value: String) {
        veryHardCompLabelTextField.text = value
    }


    // premium only
    fun getLinearCompColor(): Color = linearCompColorPanel.selectedColor ?: Color.magenta
    fun getQuadraticCompColor(): Color = quadraticCompColorPanel.selectedColor ?: Color.magenta
    fun getCubicCompColor(): Color = cubicCompColorPanel.selectedColor ?: Color.magenta
    fun getVeryHardCompColor(): Color = veryHardCompColorPanel.selectedColor ?: Color.magenta

    //setterz color
    fun setLinearCompColor(value: JBColor) {
        linearCompColorPanel.selectedColor = value
    }
    fun setQuadraticCompColor(value: JBColor) {
        quadraticCompColorPanel.selectedColor = value
    }
    fun setCubicCompColor(value: JBColor) {
        cubicCompColorPanel.selectedColor = value
    }
    fun setVeryHardCompColor(value: JBColor) {
        veryHardCompColorPanel.selectedColor = value
    }

}