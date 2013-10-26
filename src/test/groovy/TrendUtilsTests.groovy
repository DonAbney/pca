class TrendUtilsTests extends GroovyTestCase {

  void testShouldReturnNullWhenListIsEmpty() {
    def l = []

    assert null == TrendUtils.findTrend(l)
  }

  void testShouldReturnNullWhenListIsNull() {

	assert null == TrendUtils.findTrend(null)

  }

  void testShouldReturnZeroWhenListOfOnlyOneNumber() {
  	def l = [90.0d]

  	assert 0.0d == TrendUtils.findTrend(l)
  }

  void testShouldReturnProperPercentageWhenListOfTwoNumbersIncreasing() {
    def l = [90.0d, 100.0d]

    assert 0.1111111111111111 == TrendUtils.findTrend(l)
  }

  void testShouldReturnProperPercentageWhenListOfThreeNumbersIncreasing() {
    def l = [90.0d, 100.0d, 110.0d]

    assert 0.2111111111111111 == TrendUtils.findTrend(l)
  }

}
