package com.pca

class AcceptanceTest extends GroovyTestCase {

    void testWhenIRequestAPublicTimelineIRecieveAPublicTimeline() {
        def twitter = new Twitter()

        twitter.addTweet(new Tweet(tweetHandle: 'Mike', tweetText: 'This is a tweet from Mike'))
        twitter.addTweet(new Tweet(tweetHandle: 'Brani', tweetText: 'This is a tweet from Brani'))

        def result = twitter.getTweets()

        assertTrue(result.size() >= 0)

        result.each {
            assert it instanceof Tweet
        }

    }

    void testWhenIRequestTweetsFromThePublicTimelineThenTheyContainInsertedTweets() {
        def twitter = new Twitter()

        twitter.addTweet(new Tweet(tweetHandle: 'Don', tweetText: 'This is a tweet from Don'))
        twitter.addTweet(new Tweet(tweetHandle: 'DJ', tweetText: 'This is a tweet from DJ'))

        def result = twitter.displayPublicTimeline()

        assert result.contains('<li>This is a tweet from Don</li>')
        assert result.contains('<li>This is a tweet from DJ</li>')
    }

    void testWhenIRequestTweetsFromThePublicTimelineThenTheyReturnedAsValidHtml() {
        def twitter = new Twitter()

        twitter.addTweet(new Tweet(tweetHandle: 'Don', tweetText: 'This is a tweet from Don'))
        twitter.addTweet(new Tweet(tweetHandle: 'DJ', tweetText: 'This is a tweet from DJ'))

        // uncomment the following line, to make the XmlSlurper throw an exception
        // result += '<head>'

        // If the HTML is not valid, then the XmlSlurper will throw an exception and fail the test
        // Consider adding a try catch block, to gracefully fail if it throws an exception
    }

    void testWhenIRequestTweetsFromThePublicTimelineWhitelistUsersAreNotFilteredByTheWordBlacklist() {

        def whiteList = ['Ken', 'Jimmy']
        def blackList = ['suck']

        def twitter = new Twitter(whiteList: whiteList, blackListedWords: blackList)

        twitter.addTweet(new Tweet(tweetHandle: 'Jimmy', tweetText: 'This should be white-listed, you suck'))

        def result = twitter.displayPublicTimeline()

        assert result.contains('<li>This should be white-listed, you suck</li>')
    }

    void testWhenIRequestTweetsFromThePublicTimelineWhiteListUsersAreNotFiltered() {
        def whiteList = ['Jimmy']

        def twitter = new Twitter(whiteList: whiteList)

        twitter.addTweet(new Tweet(tweetHandle: 'Jimmy', tweetText: 'This should be white-listed'))

        def result = twitter.displayPublicTimeline()

        assert result.contains('<li>This should be white-listed</li>')
    }

    void testWhenIRequestTweetsFromThePublicTimelineAreFilteredByTheWordBlacklist() {
        def whiteList = []
        def blackList = ['black', 'badword']

        def twitter = new Twitter(whiteList: whiteList, blackListedWords: blackList)

        twitter.addTweet(new Tweet(tweetHandle: 'Jimmy', tweetText: 'This should be black-listed, you suck'))
        twitter.addTweet(new Tweet(tweetHandle: 'Jimmy', tweetText: 'This includes a badword.'))
        twitter.addTweet(new Tweet(tweetHandle: 'Jimmy', tweetText: 'Okay to display'))

        def result = twitter.displayPublicTimeline()

        assert !result.contains('<li>This should be black-listed, you suck</li>')
        assert !result.contains('<li>This includes a badword.</li>')
        assert result.contains('<li>Okay to display</li>')
    }

    void testWhenIRequestTweetsFromThePublicTimelineNonWhiteListUsersWithBlacklistTweetsAreFilteredOut() {
        def whiteList = ['Jimmy']
        def blackList = ['black']

        def twitter = new Twitter(whiteList: whiteList, blackListedWords: blackList)

        twitter.addTweet(new Tweet(tweetHandle: 'Ken', tweetText: 'This should not be white-listed, it should be black-listed'))

        def result = twitter.displayPublicTimeline()

        assert !result.contains('<li>This should not be white-listed, it should be black-listed</li>')
    }

    void testWhenIRequestTweetsFromThePublicTimelineWithHashtagThenTheTweetsDisplayedHaveHashTag() {

        def expectedHashTag = "#sportsRockNot"

        def twitter = new Twitter()

        twitter.addTweet(new Tweet(tweetHandle: 'Don', tweetText: 'This is a tweet from Don and ' + expectedHashTag))
        twitter.addTweet(new Tweet(tweetHandle: 'DJ', tweetText: expectedHashTag + 'This is a tweet from DJ'))

        def result = twitter.findTweetsForHashtag(expectedHashTag)

        assert result.size() == 2

        result.each {
            assert it.tweetText.contains(expectedHashTag)
        }

    }

    void testThatTweetFromBlacklistedUserIsNotDisplayed() {
        def userBlackList = ['Jimmy']
        def twitter = new Twitter(userBlackList: userBlackList)

        twitter.addTweet(new Tweet(tweetHandle: 'Jimmy', tweetText: 'Does not matter what is here'))

        def result = twitter.displayPublicTimeline()

        assert !result.contains('Does not matter what is here')
    }

    void testThatTweetInWhitelistIsCaseInsensitive() {
        def userWhitelist = ['michael']
        def blacklist = ['superbad']
        def twitter = new Twitter(whiteList: userWhitelist, blackListedWords: blacklist)

        twitter.addTweet(new Tweet(tweetHandle: 'Michael', tweetText: 'This tweet is superbad.'))

        def result = twitter.displayPublicTimeline()

        assert result.contains('This tweet is superbad.')

    }

}
