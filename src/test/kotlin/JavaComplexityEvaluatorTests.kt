import com.intfloatbool.codecomplex.evaluators.EContentComplexity
import com.intfloatbool.codecomplex.evaluators.JavaComplexityEvaluator
import org.junit.jupiter.api.DisplayName
import kotlin.test.Test
import kotlin.test.assertEquals

class JavaComplexityEvaluatorTests {

//LOOPS START
    @Test
    @DisplayName("Linear O(N)")
    fun testLinear() {

        val evaluator = JavaComplexityEvaluator()

        val content = """
            public int linearLoop(int iterations) {
                int total = 0;
                for(int i = 0; i < iterations; i++) {
                    total++;
                }
                return total;
            }
        """.trimIndent()
        val complexity = evaluator.evaluate(content)

        assertEquals(EContentComplexity.LINEAR_TIME, complexity)
    }

    @Test
    @DisplayName("Quadro O(N ^ 2)")
    fun testQuadro() {

        val evaluator = JavaComplexityEvaluator()

        val content = """
            public int quadroLoop(int iterations) {
                int total = 0;
                for(int i = 0; i < iterations; i++) {
                    for (int j = 0; j < iterations; j++) {
                        total++;
                    }
                }
                return total;
            }
        """.trimIndent()
        val complexity = evaluator.evaluate(content)

        assertEquals(EContentComplexity.QUADRATIC_TIME, complexity)
    }

    @Test
    @DisplayName("CUBIC O(N ^ 3)")
    fun testCubic() {

        val evaluator = JavaComplexityEvaluator()

        val content = """
            public int cubicLoop(int iterations) {
                int total = 0;
                for(int i = 0; i < iterations; i++) {
                    for (int j = 0; j < iterations; j++) {
                        for (int k = 0; k < iterations; k++) {
                            total++;
                        }
                    }
                }
                return total;
            }
        """.trimIndent()
        val complexity = evaluator.evaluate(content)

        assertEquals(EContentComplexity.CUBIC_TIME, complexity)
    }

    @Test
    @DisplayName("BFG O(FUCK A LOT)")
    fun testBFG() {

        val evaluator = JavaComplexityEvaluator()

        val content = """
           public int bfgLoop(int iterations) {
                int total = 0;
                for(int i = 0; i < iterations; i++) {
                    for (int j = 0; j < iterations; j++) {
                        for (int k = 0; k < iterations; k++) {
                            for (int z = 0; z < iterations; z++) {
                                total++;
                            }
                        }
                    }
                }
                return total;
            }
        """.trimIndent()
        val complexity = evaluator.evaluate(content)

        assertEquals(EContentComplexity.TERRIBLE_LOOPING, complexity)
    }

    @Test
    @DisplayName("linear functions")
    fun linearFunctionsTest() {

        assertComplexity("""
            private void MethodX() {
        var arrList = new ArrayList<Integer>();
        arrList.add(10);
        arrList.add(20);
        arrList.add(30);
        
        arrList.forEach(e -> {
            var x = e + 1;
            System.out.println(x);
        });
    }
        """.trimIndent(),
            EContentComplexity.LINEAR_TIME)

        assertComplexity("""
            var arrList = new ArrayList<Integer>();
        arrList.add(10);
        arrList.add(20);
        arrList.add(30);

        for(int i = 0; i < 10; i++) {
            arrList.forEach(e -> {
                var x = e + 1;
                System.out.println(x);
            });
        }
    }
        """.trimIndent(),
            EContentComplexity.QUADRATIC_TIME)

        assertComplexity("""
           private void MethodX() {
        var arrList = new ArrayList<Integer>();
        arrList.add(10);
        for(int i = 0; i < 10; i++) {
            arrList.removeIf(e -> e != 10);
        }
    }
        """.trimIndent(),
            EContentComplexity.QUADRATIC_TIME)

        assertComplexity("""
           private void MethodX() {
        var arrList = new ArrayList<Integer>();
        arrList.add(10);
        for(int i = 0; i < 10; i++) {
            _arrList.replaceAll(n -> n * 2);
        }
    }
        """.trimIndent(),
            EContentComplexity.QUADRATIC_TIME)

        assertComplexity("""
           private void MethodX() {
        var arrList = new ArrayList<Integer>();
        arrList.add(10);
        var stream = arrList.stream();
        for(int i = 0; i < 10; i++) {
            stream.filter(e -> {
                arrList.forEach(x -> x += 10);
                return false;
            });
        }
    }
        """.trimIndent(),
            EContentComplexity.CUBIC_TIME)

    }

    fun assertComplexity(content: String, expectedComplexity: EContentComplexity) {
        val evaluator = JavaComplexityEvaluator()

        val complexity = evaluator.evaluate(content)
        assertEquals(expectedComplexity, complexity)
    }

// LOOPS END
}