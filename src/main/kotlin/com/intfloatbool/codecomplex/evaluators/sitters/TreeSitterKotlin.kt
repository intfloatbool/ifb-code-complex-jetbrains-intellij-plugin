package com.intfloatbool.codecomplex.evaluators.sitters

import com.intfloatbool.codecomplex.NativeUtilz
import com.intfloatbool.codecomplex.nativejna.TreeSitterKotlinLang

object TreeSitterKotlin {
    private const val KOTLIN_LANG_LIB_NAME = "tree-sitter-kotlin"

    init {
        loadLibrary()
    }

    @JvmStatic
    @Throws(UnsatisfiedLinkError::class)
    internal fun loadLibrary() {
        try {
            System.loadLibrary(KOTLIN_LANG_LIB_NAME)
            println("[TreeSitterKotlin] System.loadLibrary -> Lib loaded: ${KOTLIN_LANG_LIB_NAME}")
        } catch (ex: UnsatisfiedLinkError) {
            @Suppress("UnsafeDynamicallyLoadedCode")
            val absPath = NativeUtilz.libAbsPath(KOTLIN_LANG_LIB_NAME)
            System.load(absPath ?: throw ex)
            println("[TreeSitterKotlin] System.load -> Lib loaded: ${absPath}")
        }
    }

    fun language(): Long {
        val ptrLong = TreeSitterKotlinLang.LanguagePointer
        return ptrLong
    }
}