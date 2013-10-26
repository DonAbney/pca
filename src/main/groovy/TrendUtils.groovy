class TrendUtils {

  def static findTrend(List l) {

  	if (l == null) return null 
  	if (l.isEmpty()) return null

  	def collatedList = l.collate(2, 1)

  	def trend = 0.0d
  	collatedList.each {
  		trend += gatherTrend(it)
  	}

  	trend

  }

  def private static gatherTrend(pair) {
  		if (pair.size() != 2) return 0.0d

  		if (bothElementsEqualZero(pair)) return 0.0d

  		if (pair[0] == 0.0d && pair[1] > 0.0d) return 1.0d

  		(pair[1] - pair[0])/pair[0]
  }

  def private static bothElementsEqualZero(pair) {
    return (pair[0] == 0.0d && pair[1] == 0.0d)
  }

}