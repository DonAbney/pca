class TrendUtils {

  def static findTrend(List l) {

  	if (l == null || l.isEmpty()) return null

  	if (l.size() == 1) return 0.0d

  	def collatedList = l.collate(2, 1)

  	def trend = 0.0d
  	collatedList.each {
  		trend += gatherTrend(it)
  	}

  	trend

  }

  def private static gatherTrend(list) {
  		if (list.size() != 2) return 0.0d

  		if (list[0] == 0.0d && list[1] == 0.0d) return 0.0d

  		if (list[0] == 0.0d && list[1] > 0.0d) return 1.0d

  		(list[1] - list[0])/list[0]
  }

}