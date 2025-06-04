package com.intfloatbool.codecomplex.algocomplexity

import com.intfloatbool.codecomplex.evaluators.EContentComplexity

class CodeBlockNode {
    lateinit var Type: String
    var Name: String = ""
    lateinit var Complexity: EContentComplexity
    var TotalCost: Int = 0
    var Childs: MutableList<CodeBlockNode> = mutableListOf()
}