package com.pca

class TweetRouterTest extends GroovyTestCase {
    void testShouldStoreGreyListedTweets() {
        def tweetRouter = new TweetRouter()
        def tweets = [
            new Tweet(tweetHandle: 'Handle1', tweetText: 'Text 1'),
            new Tweet(tweetHandle: 'Handle2', tweetText: 'Text 2'),
        ]

        tweetRouter.addTweet(tweets[0]);
        tweetRouter.addTweet(tweets[1]);

        def tweetList = tweetRouter.greyList

        assert tweetList.size() == 2
        assert tweetList.contains(tweets[0])
        assert tweetList.contains(tweets[1])
    }
}
