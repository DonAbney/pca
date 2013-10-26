class TrendUtils {

  def static findTrend(List l) {

  	if (l == null || l.isEmpty()) return null

  	if (l.size() == 1) return 0.0d

  	def collatedList = l.collate(2, 1)

  	collatedList.each{println it}
  	collatedList.collect {it.size() == 2 ? it[0] == 0.0d ? 1.0d : (it[1] - it[0])/it[0] : 0.0d}.sum()

  }

}