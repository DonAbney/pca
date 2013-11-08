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

    void testThatPublicTimelineFilteredByHashTagCanBeReturned() {

        def tweets = [
                new Tweet([tweetHandle: "123", tweetText: "123 Hashtag"]),
                new Tweet([tweetHandle: "124", tweetText: "Hashtag 321"])
        ]

         twitter.setTweets(tweets)
        def result = twitter.findTweetsForHashtag("sportsRockNot")
        assertTrue(result.contains("sportsRockNot"))
    }

    void testThatATweetNotFromAWhiteListedUserIsNotReturnedInThePublicTimeline() {
        twitter.whiteList =  ["WhiteListed"]
        def tweets =[new Tweet(tweetHandle: "Jimmy", tweetText: "text")]
        twitter.setTweets(tweets)

        def result = twitter.displayPublicTimeline()

        assertFalse(result.contains(tweets[0].tweetText))
    }
}
