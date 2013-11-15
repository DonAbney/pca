package com.pca

import org.junit.Before

class TwitterTest extends GroovyTestCase {


    def twitter;

    @Before
    void setUp() {
       twitter = new Twitter()
    }

    void testThatAnyEmptyPublicTimelineCanBeReturned() {

        def result = twitter.getTweets()

        assert result.size() == 0
    }

    void testThatPublicTimelineCanBeReturned() {
        
        twitter.setTweets([new Tweet(tweetHandle: 'junk', tweetText: 'junk')])
        def result = twitter.getTweets()

        assert result.size() > 0

        result.each {
            assert it instanceof Tweet
        }
    }

    void testThatPublicTimelineFilteredByHashTagReturnsAllTweetsWhenAllMatchHashTag() {
        def hashTag = 'Hashtag'
        def tweets = [
                new Tweet([tweetHandle: "123", tweetText: "123 ${hashTag}"]),
                new Tweet([tweetHandle: "124", tweetText: "${hashTag} 321"])
        ]

        twitter.setTweets(tweets)
        def result = twitter.findTweetsForHashtag(hashTag)
        assertEquals(2, result.size())
        result.each{
            assert it.tweetText.contains(hashTag)
        }
    }

    void testThatPublicTimelineFilteredByHashTagOnlyReturnsTweetsWithThatHashTag() {
        def hashTag = 'Hashtag'
        def tweets = [
                new Tweet([tweetHandle: "123", tweetText: "123 ${hashTag}"]),
                new Tweet([tweetHandle: "124", tweetText: "${hashTag} 321"]),
                new Tweet([tweetHandle: "124", tweetText: "NoTag 321"])
        ]

        twitter.setTweets(tweets)
        def result = twitter.findTweetsForHashtag(hashTag)
        assertEquals(2, result.size())
        result.each{
            assert it.tweetText.contains(hashTag)
        }
    }

    void testThatPublicTimelineFilteredByHashTagReturnsNoTweetsWhenNoneMatch() {
        def hashTag = 'Hashtag'
        def tweets = [
                new Tweet([tweetHandle: "123", tweetText: "123"]),
                new Tweet([tweetHandle: "124", tweetText: "321"]),
                new Tweet([tweetHandle: "124", tweetText: "NoTag 321"])
        ]

        twitter.setTweets(tweets)
        def result = twitter.findTweetsForHashtag(hashTag)
        assert result instanceof List
        assertEquals(0, result.size())

    }

    void testThatDisplayPublicTimelineShowsTweetsFromWhitelistedUsers() {
        def whitelist = ['Jimmy']
        def client = new Twitter(whiteList: whitelist)
        Tweet tweetOne = new Tweet(tweetHandle: 'Jimmy', tweetText: 'This should be white-listed')
        client.setTweets([tweetOne])

        def result = client.displayPublicTimeline()
        assert result.contains('<li>This should be white-listed</li>')
    }

    void testThatDisplayPublicTimelineDoesNotShowTweetsFromUserNotOnWhiteListAndViolatesBlacklist() {
        def whitelist = ['Ken']
        def blackList = ['This']
        def client = new Twitter(whiteList: whitelist, blackList: blackList)
        Tweet tweetOne = new Tweet(tweetHandle: 'Jimmy', tweetText: 'This should be white-listed')
        client.setTweets([tweetOne])

        def result = client.displayPublicTimeline()
        assert !result.contains('<li>This should be white-listed</li>')
    }

    void testThatDisplayPublicTimelineShowsTweetsFromUserOnWhitelistEvenWhenItViolatesBlackList() {
        def whitelist = ['Ken']
        def blackList = ['This']
        def client = new Twitter(whiteList: whitelist, blackList: blackList)
        Tweet tweetOne = new Tweet(tweetHandle: 'Ken', tweetText: 'This should be white-listed')
        client.setTweets([tweetOne])

        def result = client.displayPublicTimeline()
        assert result.contains('<li>This should be white-listed</li>')
    }
}
