package test.groovy

import main.groovy.Tweet
import main.groovy.Twitter

class TwitterTest extends GroovyTestCase {

    def twitter = new Twitter()

    void testThatPublicTimelineCanBeReturned() {
        def result = twitter.getPublicTimeline()

        assert result.size() > 0

        result.each {
            assert it instanceof Tweet
        }


    }
}
