class TrendUtilsTests extends GroovyTestCase {

  void testShouldReturnNullWhenListIsEmpty() {
    def l = []

    assert null == TrendUtils.findTrend(l)
  }

}
