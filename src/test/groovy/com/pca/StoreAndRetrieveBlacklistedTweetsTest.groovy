package com.pca

class StoreAndRetrieveBlacklistedTweetsTest extends GroovyTestCase {
    void testRetrieveBlackListOnDemand() {
        def blackList = ['black', 'badword']

        def badList = [new Tweet(tweetHandle: 'Jimmy', tweetText: 'This should be black-listed, you suck'),
        new Tweet(tweetHandle: 'Jimmy', tweetText: 'This includes a badword.'),
        new Tweet(tweetHandle: 'Jimmy', tweetText: 'This includes a another badword.')]

        Tweet goodTweet1 = new Tweet(tweetHandle: 'Jimmy', tweetText: 'Okay to display')
        Tweet goodTweet2 = new Tweet(tweetHandle: 'Jimmy', tweetText: 'Okay to display too')
        def twitter = new Twitter(blackList: blackList)

        def tweetList = badList + goodTweet1 + goodTweet2

        twitter.setTweets(tweetList)
        def result = twitter.getBlackListedTweets()

        assertEquals(3, result.intersect(badList).size())
    }
}
