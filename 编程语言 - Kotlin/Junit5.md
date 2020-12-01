测试用例[https://github.com/junit-team/junit5-samples](https://github.com/junit-team/junit5-samples)

```plain
package com.example
class Calculator {
fun add(a: Int, b: Int): Int {
return a + b
}
fun div(a: Int, b: Int): Double {
assert(b != 0) { "Division by Zero" }
return a / b * 1.0
}
}
```
测试方法
```plain
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
class CalculatorTests {
@Test
fun `1 + 1 = 2`() {
val calculator = Calculator()
assertEquals(2, calculator.add(1, 1), "1 + 1 should equal 2")
}
@ParameterizedTest(name = "{0} + {1} = {2}")
@CsvSource(
"0,    1,   1",
"1,    2,   3",
"49,  51, 100",
"1,  100, 101"
)
fun add(first: Int, second: Int, expectedResult: Int) {
val calculator = Calculator()
assertEquals(expectedResult, calculator.add(first, second)) {
"$first + $second should equal $expectedResult"
}
}
@Test
fun divisionByZeroError() {
val calculator = Calculator()
val exception = assertThrows<AssertionError> {
calculator.div(1, 0)
}
assertEquals("Division by Zero", exception.message)
}
}
```
