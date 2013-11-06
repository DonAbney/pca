package test.groovy

import main.groovy.Tweet
import main.groovy.Twitter
import org.junit.Before

class TwitterTest extends GroovyTestCase {


    def twitter;

    @Before
    void setUp() {
       twitter = new Twitter()
    }

    void testThatPublicTimelineCanBeReturned() {
        
        twitter.setTweets([new Tweet('junk', 'junk')])
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
        def result = twitter.findTweetsForHashtag("Hashtag")
        assertEquals(2, result.size())

    }
}
