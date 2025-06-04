package com.intfloatbool.codecomplex.nativejna

import com.intfloatbool.codecomplex.NativeUtilz
import com.sun.jna.Library
import com.sun.jna.Native
import com.sun.jna.Pointer

interface TreeSitterKotlinLang : Library {
    fun tree_sitter_kotlin(): Pointer

    companion object {
        val INSTANCE: TreeSitterKotlinLang by lazy {
            val libPath = NativeUtilz.libAbsPath("tree-sitter-kotlin")
            Native.load(libPath, TreeSitterKotlinLang::class.java)
        }

        val LanguagePointer: Long by lazy {
            Pointer.nativeValue(INSTANCE.tree_sitter_kotlin())
        }
    }
}