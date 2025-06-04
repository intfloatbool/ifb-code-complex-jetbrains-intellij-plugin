package com.intfloatbool.codecomplex.algocomplexity

import com.intfloatbool.codecomplex.evaluators.EContentComplexity
import io.github.treesitter.ktreesitter.Node

object AlgoComplexityStatic {
//    val COMPLEXITY_COST_MAP = mapOf(
//        EContentComplexity.LINEAR_TIME to 1,
//        EContentComplexity.QUADRATIC_TIME to 2,
//        EContentComplexity.CUBIC_TIME to 3,
//        EContentComplexity.TERRIBLE_LOOPING to 4,
//        )

    fun printRecursivelyDebug(node: Node, depth: Int, indent: String = "") {
        println("$indent-${node.type}-[${depth}]-'${node.text()}'")
        for(child in node.children) {
            printRecursivelyDebug(child, depth + 1, "$indent ")
        }
    }

    fun TreeNodeToBlockNode(node: Node, complexityParser: (Node) -> EContentComplexity, depth: Int = 0): CodeBlockNode {
        val blockNode = CodeBlockNode()
        val complexity = complexityParser(node)
        //blockNode.Name = "${node.type}_${depth}"
        blockNode.Complexity = complexity
        blockNode.Type = node.type
        for(child in node.children) {
            val childBlockNode = TreeNodeToBlockNode(child, complexityParser, depth + 1);
            blockNode.Childs.add(childBlockNode)
        }
        return blockNode
    }

    fun CalculateRootChildsComplexity(blockNode: CodeBlockNode, depth: Int, complexityNode: ComplexityNode): Unit {

        complexityNode.Complexity = blockNode.Complexity
        //complexityNode.Name = blockNode.Name

        if (blockNode.Childs.isEmpty()) {
            CalculateAndSetComplexity(blockNode, complexityNode)
            return
        }

        for(child in blockNode.Childs) {
            val cn = ComplexityNode()
            complexityNode.Childs.add(cn)
            CalculateRootChildsComplexity(child, depth + 1, cn)
        }

        // process root
        if (depth == 0) {
            //println("Process ROOT '${blockNode.Name}'")
            var maxComplexity = EContentComplexity.NONE

            for(child in blockNode.Childs) {
                val childComplexity = child.Complexity

                if(childComplexity == EContentComplexity.NONE || childComplexity == EContentComplexity.CONSTANT_TIME) {
                    continue
                }

                if(childComplexity.value > maxComplexity.value) {
                    maxComplexity = childComplexity
                }
            }

            // setup root node complexity by max
            blockNode.Complexity = maxComplexity
        }

        // child of root
        if (depth == 1) {
            CalculateAndSetComplexity(blockNode, complexityNode)
        }

    }

    fun CalculateAndSetComplexity(blockNode: CodeBlockNode, complexityNode: ComplexityNode) {
        val nodeCost = CalculateCost(complexityNode)
        blockNode.TotalCost = nodeCost
        if(nodeCost > EContentComplexity.TERRIBLE_LOOPING.value) {
            blockNode.Complexity = EContentComplexity.TERRIBLE_LOOPING
            return
        }
        val complexity = EContentComplexity.from(nodeCost)
        if(complexity == null) {
            blockNode.Complexity = EContentComplexity.NONE
            return
        }
        blockNode.Complexity = complexity
    }

    fun CalculateCost(complexityNode: ComplexityNode): Int {

        var cost = complexityNode.Complexity.value

        var i = 0
        for(child in complexityNode.Childs) {
            cost += CalculateCost( child)
            i++
        }

        return cost
    }
}