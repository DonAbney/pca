package com.pca

class GreyListTimerTest extends GroovyTestCase {
    void testIfATweetIsEligibleForDisplay() {
        def tweet = new Tweet(tweetHandle: 'kumar', tweetText: 'am I eligible?')
        assert tweet.eligible
    }

    void testThatATweetIsDisplayedIfItIsEligible() {
        def tweet = new Tweet(tweetHandle: 'kumar', tweetText: 'have I been displayed?')
        def manager = new GreyListManager()
        def result = manager.sendOffToDisplay(tweet)

        assert result == "<div class='tweet'>" +
                "<div class='handle'><p>kumar</p></div>" +
                "<div class='tweet-text'><p>have I been displayed?</p></div>" + "</div>"

    }
}

