package test.groovy

import main.groovy.Twitter

class TwitterTest extends GroovyTestCase {

    def twitter = new Twitter()

    void testConnectShouldConnectToTwitter() {
           assertNotNull(twitter.connect())
    }


}
