package com.intfloatbool.codecomplex.nativejna

import com.intfloatbool.codecomplex.NativeUtilz
import com.sun.jna.Library
import com.sun.jna.Native
import com.sun.jna.Pointer

interface TreeSitterJavaLang : Library {
    fun tree_sitter_java(): Pointer

    companion object {
        val INSTANCE: TreeSitterJavaLang by lazy {

            val libPath = NativeUtilz.libAbsPath("tree-sitter-java");
            Native.load(libPath, TreeSitterJavaLang::class.java)
        }

        val LanguagePointer: Long by lazy {
            Pointer.nativeValue(INSTANCE.tree_sitter_java())
        }
    }
}