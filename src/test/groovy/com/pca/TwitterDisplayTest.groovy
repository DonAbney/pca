package com.pca

class TwitterDisplayTest extends GroovyTestCase {

    void testWhenATweetIsEnteredAnHtmlVersionIsReturned() {
        def twitterDisplay = new TwitterDisplay()
        new Tweet(tweetHandle: "124", tweetText: "THIS IS MAH TWEET")
        def expectedHtml = "<html><body><ul><li>THIS IS MAH TWEET</li></body></html>"

        assertEquals(twitterDisplay.display(tweet), expectedHtml)
    }

}
