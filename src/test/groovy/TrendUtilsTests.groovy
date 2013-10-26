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

  void testShouldReturnProperPercentageWhenListOfThreeDifferentNumbersIncreasing() {
    def l = [20.0d, 60.0d, 110.0d]

    assert 2.8333333333333335 == TrendUtils.findTrend(l)
  }

  void testShouldReturnProperPercentageWhenListOfFourDifferentNumbersIncreasing() {
    def l = [20.0d, 60.0d, 110.0d, 150.0d]

    assert 3.1969696969696972 == TrendUtils.findTrend(l)
  }

  void testShouldReturnProperPercentageWhenListOfTwoNumbersDecreasing() {
    def l = [100.0d, 90.0d]

    assert -0.1 == TrendUtils.findTrend(l)
  }

  void testShouldReturnProperPercentageWhenListOfThreeNumbersDecreasing() {
    def l = [110.0d, 100.0d, 90.0d]

    assert -0.19090909090909092 == TrendUtils.findTrend(l)
  }

  void testShouldReturnProperPercentWhenFirstNumberIsZeroAndSecondNumberIsPositive() {
    def l = [0.0d, 50.0d]

    assert 1.0d == TrendUtils.findTrend(l)
  }

  void testShouldReturnProperPercentWhenFirstNumberIsZeroAndSecondNumberIsZero() {
    def l = [0.0d, 0.0d]

    assert 0.0d == TrendUtils.findTrend(l)
  }

  void testShouldReturnProperPercentWhenFirstNumberIsZero() {
    def l = [0.0d, 50.0d, 60.0d]

    assert 1.2d == TrendUtils.findTrend(l)
  }

  void testShouldReturnProperPercentWhenAllZeroes() {
    def l = [0.0d, 0.0d, 0.0d]

    assert 0.0d == TrendUtils.findTrend(l)
  }

  void testShouldReturnProperTrendWhenFluxuationUpAndDown() {
    def l = [10.0d, 20.0d, 10.0d]

    assert 0.5d == TrendUtils.findTrend(l)
  }

  void testShouldHandleRealWorldExample1() {
    def l = [2556.0d, 1160.0d, 1050.0d, 369.0d]

    assert -1.2895648989723782 == TrendUtils.findTrend(l)
  }

}
