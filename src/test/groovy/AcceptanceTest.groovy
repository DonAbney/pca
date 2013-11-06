package test.groovy

import groovy.util.XmlSlurper
import main.groovy.Tweet
import main.groovy.Twitter

class AcceptanceTest extends GroovyTestCase {

    void testWhenIRequestTweetsFromThePublicTimelineThenTheyContainInsertedTweets() {
    
        Tweet tweetOne = new Tweet('Don', 'This is a tweet from Don')
        Tweet tweetTwo = new Tweet('DJ', 'This is a tweet from DJ')

        def twitter = new Twitter()
        twitter.setTweets([tweetOne, tweetTwo])

        def result = twitter.displayPublicTimeline()

        assert result.contains('<li>This is a tweet from Don</li>')
        assert result.contains('<li>This is a tweet from DJ</li>')
    }


    void testWhenIRequestTweetsFromThePublicTimelineThenTheyReturnedAsValidHtml() {
        Tweet tweetOne = new Tweet('Don', 'This is a tweet from Don')
        Tweet tweetTwo = new Tweet('DJ', 'This is a tweet from DJ')

        def twitter = new Twitter()
        twitter.setTweets([tweetOne, tweetTwo])

        def result = twitter.displayPublicTimeline()

        def rootNode = new XmlSlurper().parseText(result)
    }
}
