package com.pca

class TweetToHtmlConverterTest extends GroovyTestCase {

    void testWhenATweetIsEnteredAnHtmlVersionIsReturned() {
        def twitterDisplay = new TweetToHtmlConverter()
        def tweet = new Tweet(tweetHandle: "124", tweetText: "THIS IS MAH TWEET")
        def expectedHtml = "<div class='tweet'>" +
                "<div class='handle'><p>${tweet.tweetHandle}</p></div>" +
                "<div class='tweet-text'><p>${tweet.tweetText }</p></div>" + "</div>"

        assertEquals(twitterDisplay.display(tweet), expectedHtml)
    }

    void testWhenTweetIsNull() {
        def twitterDisplay = new TweetToHtmlConverter()
        def tweet
        def expectedHtml = "<div class='tweet'>" +
                "<div class='handle'><p></p></div>" +
                "<div class='tweet-text'><p></p></div>" + "</div>"
        assertEquals(twitterDisplay.display(tweet), expectedHtml)
    }

    void testWhenTweetHandleIsNull() {
        def twitterDisplay = new TweetToHtmlConverter()
        def tweet = new Tweet(tweetText: "THIS IS MAH TWEET")
        def expectedHtml = "<div class='tweet'>" +
                "<div class='handle'><p></p></div>" +
                "<div class='tweet-text'><p></p></div>" + "</div>"
        assertEquals(twitterDisplay.display(tweet), expectedHtml)
    }

    void testWhenTweetTextIsNull() {
        def twitterDisplay = new TweetToHtmlConverter()
        def tweet = new Tweet(tweetHandle: "124")
        def expectedHtml = "<div class='tweet'>" +
                "<div class='handle'><p></p></div>" +
                "<div class='tweet-text'><p></p></div>" + "</div>"
        assertEquals(twitterDisplay.display(tweet), expectedHtml)
    }
}
