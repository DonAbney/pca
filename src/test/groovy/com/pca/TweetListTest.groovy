package com.pca

class TweetListTest extends GroovyTestCase {
    def tweetList = new TweetList()

    void testShouldRetrieveStoredTweets() {
        def tweets = [
            new Tweet(tweetHandle: 'Handle1', tweetText: 'Text 1'),
            new Tweet(tweetHandle: 'Handle1', tweetText: 'Text 1'),
        ]
        tweetList.add(tweets[0]);
        tweetList.add(tweets[1]);

        assert tweetList.size() == 2
        assert tweetList.contains(tweets[0])
        assert tweetList.contains(tweets[1])
    }
}
