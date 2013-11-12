package com.pca

import org.junit.Before

class TwitterTest extends GroovyTestCase {


    def twitter;

    @Before
    void setUp() {
       twitter = new Twitter()
    }

    void testThatPublicTimelineCanBeReturned() {
        
        twitter.setTweets([new Tweet(tweetHandle: 'junk', tweetText: 'junk')])
        def result = twitter.getPublicTimeline()

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

}
