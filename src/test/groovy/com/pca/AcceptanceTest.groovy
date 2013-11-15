package com.pca

import groovy.util.XmlSlurper

class AcceptanceTest extends GroovyTestCase {

    void testWhenIRequestAPublicTimelineIRecieveAPublicTimeline() {

        Tweet tweetOne = new Tweet(tweetHandle: 'Mike', tweetText: 'This is a tweet from Mike')
        Tweet tweetTwo = new Tweet(tweetHandle: 'Brani', tweetText: 'This is a tweet from Brani')

        def twitter = new Twitter()
        twitter.setTweets([tweetOne,tweetTwo])
        def result = twitter.getTweets()

        assertTrue(result.size() >= 0)

        result.each{
            assert it instanceof Tweet
        }

    }

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
        def blackList = ['black', 'badword']

        Tweet tweetOne = new Tweet(tweetHandle: 'Jimmy', tweetText: 'This should be black-listed, you suck')
        Tweet tweetTwo = new Tweet(tweetHandle: 'Jimmy', tweetText: 'This includes a badword.')
        Tweet tweetThree = new Tweet(tweetHandle: 'Jimmy', tweetText: 'Okay to display')

        def twitter = new Twitter(whiteList:whiteList, blackList:blackList)
        twitter.setTweets([tweetOne, tweetTwo, tweetThree])

        def result = twitter.displayPublicTimeline()

        assert !result.contains('<li>This should be black-listed, you suck</li>')
        assert !result.contains('<li>This includes a badword.</li>')
        assert result.contains('<li>Okay to display</li>')
    }


    void testWhenIRequestTweetsFromThePublicTimelineNonWhiteListUsersWithBlacklistTweetsAreFilteredOut() {
        def whiteList = ['Jimmy']
        def blackList = ['black']

        Tweet tweetOne = new Tweet(tweetHandle: 'Ken', tweetText: 'This should not be white-listed, it should be black-listed')

        def twitter = new Twitter(whiteList:whiteList, blackList: blackList)
        twitter.setTweets([tweetOne])

        def result = twitter.displayPublicTimeline()

        assert !result.contains('<li>This should not be white-listed, it should be black-listed</li>')
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
