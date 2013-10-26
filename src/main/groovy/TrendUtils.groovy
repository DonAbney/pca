class TrendUtils {

  def static findTrend(List l) {

  	if (l == null || l.isEmpty()) return null

  	if (l.size() == 1) return 0.0d

  	def collatedList = l.collate(2, 1)

  	collatedList.each{println it}

  	def trend = 0.0d
  	collatedList.each {
  		if (it.size() != 2) return

  		if (it[0] == 0.0d && it[1] > 0.0d) {
  			trend += 1.0d 
  			return
  		}

  		if (it[0] == 0.0d && it[1] == 0.0d) return 

  		trend += (it[1] - it[0])/it[0]
  	}

  	trend

  }

}