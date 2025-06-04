package com.intfloatbool.codecomplex.psiprocess

import com.intellij.psi.PsiElement
import com.intellij.psi.PsiMethod
import com.intfloatbool.codecomplex.PluginGlobalz
import com.intfloatbool.codecomplex.evaluators.JavaComplexityEvaluator

class JavaFunctionProcessor : PsiFunctionProcessor {
    val jce = JavaComplexityEvaluator()
    override fun tryComputeText(psiElement: PsiElement): Pair<Boolean, PsiTextData> {
        if(psiElement !is PsiMethod) {
            return PluginGlobalz.emptyTextDataPair
        }
        return PluginGlobalz.resolveTextPairForMethodBody(psiElement.text, jce)
    }
}