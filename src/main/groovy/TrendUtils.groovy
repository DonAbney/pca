class TrendUtils {

  def static COLLATE_SIZE = 2
  def static COLLATE_STEP = 1

  def static findTrend(List list) {

  	if (list == null) return null 
  	if (list.isEmpty()) return null

  	def collatedList = list.collate(COLLATE_SIZE, COLLATE_STEP)

  	def trend = 0.0d
  	collatedList.each {
  		trend += gatherTrend(it)
  	}

  	trend

  }

  def private static gatherTrend(pair) {
  		if (pair.size() != COLLATE_SIZE) return 0.0d

  		if (bothElementsEqualZero(pair)) return 0.0d

  		if (firstElementEqualZeroAndSecondGreaterThanZero(pair)) return 1.0d

  		(pair[1] - pair[0])/pair[0]
  }

  def private static bothElementsEqualZero(pair) {
    pair[0] == 0.0d && pair[1] == 0.0d
  }

  def private static firstElementEqualZeroAndSecondGreaterThanZero(pair) {
  	pair[0] == 0.0d && pair[1] > 0.0d
  }

}