package test.groovy

import main.groovy.Twitter

class TwitterTest extends GroovyTestCase {

    def twitter = new Twitter()

    void testThatPublicTimelineCanBeReturned() {
        def result = twitter.getPublicTimeline()

        assertNotNull(result)
    }
}
