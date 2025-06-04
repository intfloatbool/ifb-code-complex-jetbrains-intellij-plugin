import com.intfloatbool.codecomplex.evaluators.EContentComplexity
import com.intfloatbool.codecomplex.evaluators.KotlinComplexityEvaluator
import org.junit.jupiter.api.DisplayName
import kotlin.test.Test
import kotlin.test.assertEquals

class KotlinComplexityEvaluatorTests {

    @Test
    @DisplayName("Linear O(N)")
    fun testLinear() {

        assertComplexity("""
            fun linearA() {
        val arrList = ArrayList<Int>()
        arrList.add(10)
        arrList.add(20)
        for(e in arrList) {
            println(e)
        }
    }
        """.trimIndent(),
            EContentComplexity.LINEAR_TIME)

        assertComplexity("""
            fun linearB() {
        val arrList = ArrayList<Int>()
        arrList.add(10)
        arrList.add(20)
        arrList.forEach( {e -> println(e)})
    }
        """.trimIndent(),
            EContentComplexity.LINEAR_TIME)

        assertComplexity("""
            fun linearC() {
        val arrList = ArrayList<Int>()
        arrList.add(10)
        arrList.add(20)
        arrList.stream().filter { e -> e != 10 }
    }
        """.trimIndent(),
            EContentComplexity.LINEAR_TIME)
    }

    @Test
    @DisplayName("O(N ^ 2)")
    fun testQuadro() {
        assertComplexity("""
            fun quadroA() {
        val arrList = ArrayList<Int>()
        arrList.add(10)
        arrList.add(20)
        for(e in arrList) {
            for(i in 0..100) {
                println(e + i)
            }
        }
    }
        """.trimIndent(),
            EContentComplexity.QUADRATIC_TIME)

        assertComplexity("""
            fun quadroB() {
        val arrList = ArrayList<Int>()
        arrList.add(10)
        arrList.add(20)
        arrList.forEach( {e -> {
            arrList.stream().filter { e != 100 }
        }})
    }
        """.trimIndent(),
            EContentComplexity.QUADRATIC_TIME)
    }

    @Test
    @DisplayName("O(N ^ 3)")
    fun testCubic() {
        assertComplexity("""
            fun cubicA() {
        val arrList = ArrayList<Int>()
        arrList.add(10)
        arrList.add(20)
        for(e in arrList) {
            for(i in 0..100) {
                for(x in arrList) {
                    println(e + i + x)
                }
            }
        }
    }
        """.trimIndent(),
            EContentComplexity.CUBIC_TIME)

        assertComplexity("""
            fun cubicB() {
        val arrList = ArrayList<Int>()
        arrList.add(10)
        arrList.add(20)
        val anotherArrList = ArrayList<String>()
        anotherArrList.add("ffa")
        anotherArrList.add("xxa")
        arrList.forEach( {e -> {
            val x = e + 10
            val mapped = arrList.stream().map({x -> {
                val res = x.toString()
                anotherArrList.forEach({a -> {
                    println(a + res)
                }})
            }})
            println(mapped)
        }})
    }
        """.trimIndent(),
            EContentComplexity.CUBIC_TIME)
    }

    @Test
    @DisplayName("O( BFG !!)")
    fun testBFG() {
        assertComplexity("""
            fun bfgA() {
        val arrList = ArrayList<Int>()
        arrList.add(10)
        arrList.add(20)
        for(e in arrList) {
            for(i in 0..100) {
                for(x in arrList) {
                    for(j in 0..1000) {
                        println(e + i + x + j)
                    }
                }
            }
        }
    }
        """.trimIndent(),
            EContentComplexity.TERRIBLE_LOOPING)

        assertComplexity("""
            fun bfgB() {
        val arrList = ArrayList<Int>()
        arrList.add(10)
        arrList.add(20)
        for(e in arrList) {
            for(i in 0..100) {
                for(x in arrList) {
                    arrList.forEach { j -> println(e + i + x + j) }
                }
            }
        }
    }
        """.trimIndent(),
            EContentComplexity.TERRIBLE_LOOPING)
    }

    fun assertComplexity(content: String, expectedComplexity: EContentComplexity) {
        val evaluator = KotlinComplexityEvaluator()
        val complexity = evaluator.evaluate(content)
        assertEquals(expectedComplexity, complexity)
    }
}