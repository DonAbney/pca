package test.groovy

import groovy.util.XmlSlurper
import main.groovy.Tweet
import main.groovy.Twitter

class AcceptanceTest extends GroovyTestCase {

    void testWhenIRequestTweetsFromThePublicTimelineThenTheyContainInsertedTweets() {
    
        Tweet tweetOne = new Tweet(tweetHandle: 'Don', tweetText: 'This is a tweet from Don')
        Tweet tweetTwo = new Tweet(tweetHandle: 'DJ', tweetText: 'This is a tweet from DJ')

        def twitter = new Twitter()
        twitter.setTweets([tweetOne, tweetTwo])

        def result = twitter.displayPublicTimeline()

        assert result.contains('<li>This is a tweet from Don</li>')
        assert result.contains('<li>This is a tweet from DJ</li>')
    }

    void testWhenIRequestTweetsFromThePublicTimelineThenTheyReturnedAsValidHtml() {
        Tweet tweetOne = new Tweet('Don', 'This is a tweet from Don')
        Tweet tweetTwo = new Tweet('DJ', 'This is a tweet from DJ')

        def twitter = new Twitter()
        twitter.setTweets([tweetOne, tweetTwo])

        def result = twitter.displayPublicTimeline()

        def rootNode = new XmlSlurper().parseText(result)
	}
	
    void testWhenIRequestTweetsFromThePublicTimelineWhitelistUsersAreNotFilteredByTheWordBlacklist(){

        def whiteList = ['Ken', 'Jimmy']
        def blackList = ['suck']

        Tweet tweetOne = new Tweet(tweetHandle: 'Jimmy', tweetText: 'This should be white-listed, you suck')

        def twitter = new Twitter(whiteList:whiteList, blackList:blackList)
        twitter.setTweets([tweetOne])

        def result = twitter.displayPublicTimeline()

        assert result.contains('<li>This should be white-listed, you suck</li>')
    }


    void testWhenIRequestTweetsFromThePublicTimelineWithHashtagThenTheTweetsDisplayedHaveHashTag() {

        def expectedHashTag = "#sportsRockNot"

        Tweet tweetOne = new Tweet(tweetHandle: 'Don', tweetText: 'This is a tweet from Don and ' + expectedHashTag)
        Tweet tweetTwo = new Tweet(tweetHandle: expectedHashTag + 'DJ', tweetText: 'This is a tweet from DJ')

        def twitter = new Twitter()
        twitter.setTweets([tweetOne, tweetTwo])

        def result = twitter.findTweetsForHashtag(expectedHashTag)

        assert result.contains('#sportsRockNot')

        assertEquals(1, result.findAll(expectedHashTag).size())
    }

}
