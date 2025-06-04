package com.intfloatbool.codecomplex.evaluators

enum class EContentComplexity(val value: Int) {
    NONE(0),
    CONSTANT_TIME(0), // O(1)
    LINEAR_TIME(1), // O(n)
    QUADRATIC_TIME(2), // O( n ^ 2 )
    CUBIC_TIME(3), // O ( n ^ 3 )
    TERRIBLE_LOOPING(4); // BFG

    companion object {
        infix fun from(value: Int): EContentComplexity? = EContentComplexity.entries.firstOrNull { it.value == value }
    }
}