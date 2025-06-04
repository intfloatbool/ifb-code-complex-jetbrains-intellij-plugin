package com.intfloatbool.codecomplex.psiprocess

import com.intellij.psi.PsiElement
import com.intellij.psi.PsiMethod
import com.intfloatbool.codecomplex.PluginGlobalz
import com.intfloatbool.codecomplex.evaluators.KotlinComplexityEvaluator
import org.jetbrains.kotlin.psi.KtNamedFunction

class KotlinFunctionProcessor : PsiFunctionProcessor {
    val kce = KotlinComplexityEvaluator()
    override fun tryComputeText(psiElement: PsiElement): Pair<Boolean, PsiTextData> {
        if(psiElement !is KtNamedFunction) {
            return PluginGlobalz.emptyTextDataPair
        }
        return PluginGlobalz.resolveTextPairForMethodBody(psiElement.text, kce)
    }
}