package com.intfloatbool.codecomplex.evaluators.sitters

import com.intfloatbool.codecomplex.NativeUtilz
import com.intfloatbool.codecomplex.nativejna.TreeSitterJavaLang

object TreeSitterJava {

    private const val JAVA_LANG_LIB_NAME = "tree-sitter-java"


    init {
        loadLibrary()
    }

    @JvmStatic
    @Throws(UnsatisfiedLinkError::class)
    internal fun loadLibrary() {

        try {
            System.loadLibrary(JAVA_LANG_LIB_NAME)
            println("[TreeSitterJava] System.loadLibrary -> Lib loaded: ${JAVA_LANG_LIB_NAME}")
        } catch (ex: UnsatisfiedLinkError) {
            @Suppress("UnsafeDynamicallyLoadedCode")
            val absPath = NativeUtilz.libAbsPath(JAVA_LANG_LIB_NAME)
            System.load(absPath ?: throw ex)
            println("[TreeSitterJava] System.load -> Lib loaded: ${absPath}")
        }
    }

    fun language(): Long {
        val ptrLong = TreeSitterJavaLang.LanguagePointer
        //println("Ptr Loaded: " + ptrLong)
        return ptrLong
    }


}