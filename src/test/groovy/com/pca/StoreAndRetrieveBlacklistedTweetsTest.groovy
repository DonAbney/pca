package com.pca

/**
 * Created with IntelliJ IDEA.
 * User: Owner
 * Date: 11/14/13
 * Time: 7:55 PM
 * To change this template use File | Settings | File Templates.
 */
class StoreAndRetrieveBlacklistedTweetsTest extends GroovyTestCase {
    void testRetrieveBlackListOnDemand() {
        def blackList = ['black', 'badword']
        Tweet tweetOne = new Tweet(tweetHandle: 'Jimmy', tweetText: 'This should be black-listed, you suck')
        Tweet tweetTwo = new Tweet(tweetHandle: 'Jimmy', tweetText: 'This includes a badword.')
        Tweet tweetFour = new Tweet(tweetHandle: 'Jimmy', tweetText: 'This includes a another badword.')
        Tweet tweetThree = new Tweet(tweetHandle: 'Jimmy', tweetText: 'Okay to display')
        Tweet tweetFive = new Tweet(tweetHandle: 'Jimmy', tweetText: 'Okay to display too')
        def twitter = new Twitter(blackList: blackList)
        twitter.setTweets([tweetOne, tweetTwo, tweetThree, tweetFour, tweetFive])
        def result = twitter.getBlackListedTweets()
        assert result.size() == 3
        result.each { assert it.tweetText =~ blackList.join('|') }
    }
}
