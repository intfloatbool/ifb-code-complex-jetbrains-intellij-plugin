package com.intfloatbool.codecomplex

import kotlin.math.abs

class RateLimiter {
    private var lastCallTime = 0L
    private var _intervalMs = 0L
    constructor(intervalMs: Long) {
        _intervalMs = intervalMs
    }

    fun isReady(): Boolean {
        val now = System.currentTimeMillis()
        if ( abs(now - lastCallTime) >= _intervalMs) {
            lastCallTime = now
            return true
        }
        return false
    }
}