package com.intfloatbool.codecomplex.algocomplexity

import com.intfloatbool.codecomplex.evaluators.EContentComplexity

class ComplexityNode {
    lateinit var Name: String
    lateinit var Complexity: EContentComplexity
    var Childs: MutableList<ComplexityNode> = mutableListOf()
}

