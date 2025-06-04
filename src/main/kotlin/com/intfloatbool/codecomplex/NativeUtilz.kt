package com.intfloatbool.codecomplex

import java.io.File
import java.lang.UnsupportedOperationException

object NativeUtilz {

    @JvmStatic
    @Throws(UnsupportedOperationException::class)
    internal fun libAbsPath(libName: String): String? {
        val osName = System.getProperty("os.name")!!.lowercase()
        val archName = System.getProperty("os.arch")!!.lowercase()
        val ext: String
        val os: String
        val prefix: String
        when {
            "windows" in osName -> {
                ext = "dll"
                os = "windows"
                prefix = ""
            }
            "linux" in osName -> {
                ext = "so"
                os = "linux"
                prefix = "lib"
            }
            "mac" in osName -> {
                ext = "dylib"
                os = "macos"
                prefix = "lib"
            }
            else -> {
                throw UnsupportedOperationException("Unsupported operating system: $osName")
            }
        }
        val arch = when {
            "amd64" in archName || "x86_64" in archName -> "x64"
            "aarch64" in archName || "arm64" in archName -> "aarch64"
            else -> throw UnsupportedOperationException("Unsupported architecture: $archName")
        }
        val libUrl = javaClass.getResource("/lib/$os/$arch/$prefix$libName.$ext") ?: return null
        return File.createTempFile("$prefix$libName", ".$ext").apply {
            writeBytes(libUrl.openStream().use { it.readAllBytes() })
            deleteOnExit()
        }.path
    }
}