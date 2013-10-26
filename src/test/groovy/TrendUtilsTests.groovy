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

}
