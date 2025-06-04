package com.intfloatbool.codecomplex.evaluators

import com.intfloatbool.codecomplex.algocomplexity.AlgoComplexityStatic
import com.intfloatbool.codecomplex.algocomplexity.ComplexityNode
import com.intfloatbool.codecomplex.evaluators.sitters.TreeSitterKotlin
import io.github.treesitter.ktreesitter.Language
import io.github.treesitter.ktreesitter.Node
import io.github.treesitter.ktreesitter.Parser

class KotlinComplexityEvaluator : ComplexityEvaluator {

    private var parser: Parser

    constructor() {
        val language = Language(TreeSitterKotlin.language())
        parser = Parser(language)
    }

    private val _complexityResolver: (node: Node) -> EContentComplexity = ::complexityResolver

    private fun complexityResolver(node: Node): EContentComplexity {
        if (node.type == "for_statement") {
            return EContentComplexity.LINEAR_TIME
        }
        if (node.type == "simple_identifier") {
            val nodeText = node.text()
            if (nodeText == "forEach" ||
                nodeText == "removeIf" ||
                nodeText == "replaceAll" ||
                nodeText == "map" ||
                nodeText == "reduce" ||
                nodeText == "collect" ||
                nodeText == "anyMatch" ||
                nodeText == "filter") {
                return EContentComplexity.LINEAR_TIME
            }
        }
        return EContentComplexity.NONE
    }

    override fun evaluate(code: String): EContentComplexity {

        val tree = parser.parse(code)
        val rootNode = tree.rootNode
        val blockRootNode = AlgoComplexityStatic.TreeNodeToBlockNode(rootNode, _complexityResolver)
        AlgoComplexityStatic.CalculateRootChildsComplexity(blockRootNode, 0, ComplexityNode())
        //AlgoComplexityStatic.printRecursivelyDebug(rootNode, 0)
        return blockRootNode.Complexity
    }
}