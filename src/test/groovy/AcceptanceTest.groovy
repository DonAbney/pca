package test.groovy

import main.groovy.Tweet
import main.groovy.Twitter

class AcceptanceTest extends GroovyTestCase {

    void testWhenIRequestTweetsFromThePublicTimelineThenTheyAreDisplayed() {
    
        Tweet tweetOne = new Tweet('Don', 'This is a tweet from Don')
        Tweet tweetTwo = new Tweet('DJ', 'This is a tweet from DJ')

        def twitter = new Twitter()
        twitter.setTweets([tweetOne, tweetTwo])

        def result = twitter.displayPublicTimeline()

        assert result.contains('<li>This is a tweet from Don</li>')
        assert result.contains('<li>This is a tweet from DJ</li>')
    }

    void testWhenIRequestTweetsFromThePublicTimelineWhitelistUsersAreNotFilteredByTheWordBlacklist(){

        def whiteList = ['Ken', 'Jimmy']
        def blackList = ['suck']

        Tweet tweetOne = new Tweet('Jimmy', 'This should be white-listed, you suck')

        def twitter = new Twitter(whiteList:whiteList, blackList:blackList)
        twitter.setTweets([tweetOne])

        def result = twitter.displayPublicTimeline()

        assert result.contains('<li>This should be white-listed, you suck</li>')
    }


    void testWhenIRequestTweetsFromThePublicTimelineWithHashtagThenTheTweetsDisplayedHaveHashTag() {

        def expectedHashTag = "#sportsRockNot"

        Tweet tweetOne = new Tweet('Don', 'This is a tweet from Don and ' + expectedHashTag)
        Tweet tweetTwo = new Tweet(expectedHashTag + 'DJ', 'This is a tweet from DJ')

        def twitter = new Twitter()
        twitter.setTweets([tweetOne, tweetTwo])

        def result = twitter.displayPublicTimeline(expectedHashTag)

        assert result.contains('#sportsRockNot"')

        assertEquals(2, result.findAll(expectedHashTag).size())
    }

}
