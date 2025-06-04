package com.intfloatbool.codecomplex

import com.intellij.codeInsight.codeVision.CodeVisionAnchorKind
import com.intellij.codeInsight.codeVision.CodeVisionEntry
import com.intellij.codeInsight.codeVision.CodeVisionRelativeOrdering
import com.intellij.codeInsight.codeVision.ui.model.RichTextCodeVisionEntry
import com.intellij.codeInsight.codeVision.ui.model.richText.RichText
import com.intellij.codeInsight.hints.codeVision.DaemonBoundCodeVisionProvider
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.PsiRecursiveElementWalkingVisitor
import com.intfloatbool.codecomplex.psiprocess.JavaFunctionProcessor
import com.intfloatbool.codecomplex.psiprocess.KotlinFunctionProcessor
import com.intfloatbool.codecomplex.psiprocess.PsiFunctionProcessor

class MethodComplexityCodeVisionProvider : DaemonBoundCodeVisionProvider {
    private val functionProcessors = listOf<PsiFunctionProcessor>(
        JavaFunctionProcessor(),
        KotlinFunctionProcessor()
    )

    private val codeVisionBuffer = mutableListOf<Pair<TextRange, CodeVisionEntry>>()

    override val defaultAnchor: CodeVisionAnchorKind = CodeVisionAnchorKind.Top

    override val id: String = "MethodComplexityCodeVisionProvider"
    override val name: String = "Method Complexity"

    override val relativeOrderings: List<CodeVisionRelativeOrdering>
        get() = emptyList<CodeVisionRelativeOrdering>()

//    override fun handleClick(editor: Editor, textRange: TextRange, entry: CodeVisionEntry) {
//        // пока ничего, потом можно всплывающее окно
//        if(!entriesContentMap.contains(entry.hashCode()))
//            return
//
//        Messages.showMessageDialog(editor.project, entriesContentMap.get(entry.hashCode()), "Method Content", Messages.getInformationIcon())
//    }


    override fun computeForEditor(editor: Editor, file: PsiFile): List<Pair<TextRange, CodeVisionEntry>> {
        //entriesContentMap.clear()


        codeVisionBuffer.clear()

        file.accept(object : PsiRecursiveElementWalkingVisitor() {
            override fun visitElement(element: PsiElement) {

                for(processor in functionProcessors) {

                    val (isComputed, result) = processor.tryComputeText(element)
                    if(!isComputed) {
                        continue
                    }

                    val text = RichText(result.text)
                    text.setForeColor(fgColor = result.color)
                    val textRange = element.textRange
                    val entry = RichTextCodeVisionEntry(providerId = id, text = text)

                    codeVisionBuffer.add(Pair(textRange, entry))
                    //entriesContentMap.put(entry.hashCode(), element.text)
                }


                super.visitElement(element)
            }
        })

        return  codeVisionBuffer
    }
}


