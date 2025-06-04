package com.intfloatbool.codecomplex.psiprocess

import com.intellij.psi.PsiElement

interface PsiFunctionProcessor {
    fun tryComputeText(psiElement: PsiElement): Pair<Boolean, PsiTextData>
}

