package com.intfloatbool.codecomplex

import com.intellij.ui.JBColor
import com.intfloatbool.codecomplex.evaluators.ComplexityEvaluator
import com.intfloatbool.codecomplex.evaluators.EContentComplexity
import com.intfloatbool.codecomplex.psiprocess.PsiTextData
import com.intfloatbool.codecomplex.settingz.PluginSettings
import com.intfloatbool.codecomplex.settingz.RGBAData
import javaslang.Tuple4
import java.awt.Color

object PluginGlobalz {

    // runtime settings
    var linearComplexityColorOption: JBColor = JBColor.green
    var quadraticComplexityColorOption: JBColor = JBColor.yellow
    var cubicComplexityColorOption: JBColor = JBColor.orange
    var veryHardComplexityColorOption: JBColor = JBColor.red

    var linearComplexityLabelOption: String = "O ( N )"
    var quadraticComplexityLabelOption: String = "O ( N ^ 2 )"
    var cubicComplexityLabelOption: String = "O ( N ^ 3 )"
    var veryHardComplexityLabelOption: String = "O ( !!! )"


    val emptyTextDataPair = Pair(false, PsiTextData("", JBColor.gray))

    private val headersMap = mapOf(
        EContentComplexity.LINEAR_TIME to "O ( N )",
        EContentComplexity.QUADRATIC_TIME to "O ( N ^ 2 )",
        EContentComplexity.CUBIC_TIME to "O ( N ^ 3 )",
        EContentComplexity.TERRIBLE_LOOPING to "O ( ! )"
    )

    fun isPremium(): Boolean {
        return true
    }

    fun jbColorToRGBAData(color: JBColor): RGBAData {
        return RGBAData(color.red, color.green, color.blue, color.alpha)
    }

    fun rgbaDataToJBColor(rgba: RGBAData): JBColor {
        val color = Color(rgba.r, rgba.g, rgba.b, rgba.a)
        return JBColor(color, color)
    }

    fun colorToJbColor(color: Color) : JBColor {
        return JBColor(color, color)
    }

    fun resolveTextPairForMethodBody(methodBody: String, complexityEvaluator: ComplexityEvaluator): Pair<Boolean, PsiTextData> {
        val complexity = complexityEvaluator.evaluate(methodBody)
        val textHeader = complexityToString(complexity);
        if (textHeader == null) {
            return emptyTextDataPair
        }

        val color = complexityToColor(complexity)
        return Pair(true, PsiTextData(textHeader, color))
    }

    fun complexityToString(complexity: EContentComplexity): String? {
        if(!headersMap.contains(complexity)) {
            return null
        }

        var textHeader = headersMap[complexity];
        if (textHeader == null) {
            return null
        }

        if(complexity == EContentComplexity.LINEAR_TIME) {
            textHeader = linearComplexityLabelOption
        }
        if(complexity == EContentComplexity.QUADRATIC_TIME) {
            textHeader = quadraticComplexityLabelOption
        }
        if(complexity == EContentComplexity.CUBIC_TIME) {
            textHeader = cubicComplexityLabelOption
        }
        if(complexity == EContentComplexity.TERRIBLE_LOOPING) {
            textHeader = veryHardComplexityLabelOption
        }

        return textHeader
    }

    fun complexityToColor(complexity: EContentComplexity): JBColor {
        var color = JBColor.gray;
        if(PluginGlobalz.isPremium())
        {
            if(complexity == EContentComplexity.LINEAR_TIME) {
                color = linearComplexityColorOption
            }
            if(complexity == EContentComplexity.QUADRATIC_TIME) {
                color = quadraticComplexityColorOption
            }
            if(complexity == EContentComplexity.CUBIC_TIME) {
                color = cubicComplexityColorOption
            }
            if(complexity == EContentComplexity.TERRIBLE_LOOPING) {
                color = veryHardComplexityColorOption
            }
        }

        return color
    }

    fun updateGlobalsFromState(s: PluginSettings.State) {
        linearComplexityLabelOption = s.linearComplexityLabelOption;
        quadraticComplexityLabelOption = s.quadraticComplexityLabelOption;
        cubicComplexityLabelOption = s.cubicComplexityLabelOption;
        veryHardComplexityLabelOption = s.veryHardComplexityLabelOption;

        linearComplexityColorOption = rgbaDataToJBColor(s.linearComplexityColorOption)
        quadraticComplexityColorOption = rgbaDataToJBColor(s.quadraticComplexityColorOption)
        cubicComplexityColorOption = rgbaDataToJBColor(s.cubicComplexityColorOption)
        veryHardComplexityColorOption = rgbaDataToJBColor(s.veryHardComplexityColorOption)
    }

}