package com.pca

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
        Tweet tweetOne = new Tweet(tweetHandle: 'Don', tweetText: 'This is a tweet from Don')
        Tweet tweetTwo = new Tweet(tweetHandle: 'DJ', tweetText: 'This is a tweet from DJ')

        def twitter = new Twitter()
        twitter.setTweets([tweetOne, tweetTwo])

        def result = twitter.displayPublicTimeline()
        // uncomment the following line, to make the XmlSlurper throw an exception
        // result += '<head>'


        // If the HTML is not valid, then the XmlSlurper will throw an exception and fail the test
        def rootNode = new XmlSlurper().parseText(result)
        // Consider adding a try catch block, to gracefully fail if it throws an exception
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

    void testWhenIRequestTweetsFromThePublicTimelineWhiteListUsersAreNotFiltered() {
        def whiteList = ['Jimmy']

        Tweet tweetOne = new Tweet(tweetHandle: 'Jimmy', tweetText: 'This should be white-listed')

        def twitter = new Twitter(whiteList:whiteList)
        twitter.setTweets([tweetOne])

        def result = twitter.displayPublicTimeline()

        assert result.contains('<li>This should be white-listed</li>')
    }
    
     void testWhenIRequestTweetsFromThePublicTimelineAreFilteredByTheWordBlacklist(){
        def whiteList = []
        def blackList = ['black']

        Tweet tweetOne = new Tweet(tweetHandle: 'Jimmy', tweetText: 'This should be black-listed, you suck')

        def twitter = new Twitter(whiteList:whiteList, blackList:blackList)
        twitter.setTweets([tweetOne])

        def result = twitter.displayPublicTimeline()

        assert !result.contains('<li>This should be black-listed, you suck</li>')
    }


    void testWhenIRequestTweetsFromThePublicTimelineNonWhiteListUsersAreFilteredOut() {
        def whiteList = ['Jimmy']

        Tweet tweetOne = new Tweet(tweetHandle: 'Ken', tweetText: 'This should not be white-listed')

        def twitter = new Twitter(whiteList:whiteList)
        twitter.setTweets([tweetOne])

        def result = twitter.displayPublicTimeline()

        assert !result.contains('<li>This should not be white-listed</li>')
    }

    void testWhenIRequestTweetsFromThePublicTimelineWithHashtagThenTheTweetsDisplayedHaveHashTag() {

        def expectedHashTag = "#sportsRockNot"

        Tweet tweetOne = new Tweet(tweetHandle: 'Don', tweetText: 'This is a tweet from Don and ' + expectedHashTag)
        Tweet tweetTwo = new Tweet(tweetHandle: 'DJ', tweetText: expectedHashTag + 'This is a tweet from DJ' )

        def twitter = new Twitter()
        twitter.setTweets([tweetOne, tweetTwo])

        def result = twitter.findTweetsForHashtag(expectedHashTag)

        assert result.size() == 2

        result.each{
            assert it.tweetText.contains(expectedHashTag)
        }

    }
}
