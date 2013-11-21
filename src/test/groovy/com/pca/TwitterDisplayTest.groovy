package com.pca

class TwitterDisplayTest extends GroovyTestCase {

    void testWhenATweetIsEnteredAnHtmlVersionIsReturned() {
        def twitterDisplay = new TwitterDisplay()
        def tweet = new Tweet(tweetHandle: "124", tweetText: "THIS IS MAH TWEET")
        def expectedHtml = "<div><p>124</p></div>" +
                "<div><p>THIS IS MAH TWEET</p></div>"

        assertEquals(twitterDisplay.display(tweet), expectedHtml)
    }

}
