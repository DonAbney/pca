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

        def greyList = tweetRouter.greyList
        def whiteList = tweetRouter.whiteList

        assert greyList.size() == 2
        assert greyList.contains(tweets[0])
        assert greyList.contains(tweets[1])
        assert whiteList.size() == 0
    }

    void testShouldStoreWhiteListedTweets() {
        def tweetRouter = new TweetRouter(whiteListedHandles: ['white'])
        def tweets = [
            new Tweet(tweetHandle: 'grey', tweetText: 'Text 2'),
            new Tweet(tweetHandle: 'white', tweetText: 'Text 3'),
        ]

        tweetRouter.addTweet(tweets[0]);
        tweetRouter.addTweet(tweets[1]);

        def greyList = tweetRouter.greyList
        def whiteList = tweetRouter.whiteList

        assert greyList.size() == 1
        assert whiteList.size() == 1
        assert greyList.contains(tweets[0])
        assert !greyList.contains(tweets[1])
        assert !whiteList.contains(tweets[0])
        assert whiteList.contains(tweets[1])
    }

    void testThatTweetInWhiteListIsCaseInsensitive() {
        def tweetRouter = new TweetRouter(whiteListedHandles: ['white'])
        def tweet = new Tweet(tweetHandle: 'WhItE', tweetText: 'Text')

        tweetRouter.addTweet(tweet)

        def whiteList = tweetRouter.whiteList

        assert whiteList.size() == 1
        assert whiteList.contains(tweet)
    }

    void testShouldStoreBlackListedTweetsByHandle() {
        def tweetRouter = new TweetRouter(blackListedHandles: ['black'])
        def tweets = [
                new Tweet(tweetHandle: 'grey', tweetText: 'Text 2'),
                new Tweet(tweetHandle: 'black', tweetText: 'Text 3'),
        ]

        tweetRouter.addTweet(tweets[0]);
        tweetRouter.addTweet(tweets[1]);

        def greyList = tweetRouter.greyList
       // def whiteList = tweetRouter.whiteList
        def blackList = tweetRouter.blackList

        assert greyList.size() == 1
        assert blackList.size() == 1
        assert greyList.contains(tweets[0])
        assert !greyList.contains(tweets[1])
        assert !blackList.contains(tweets[0])
        assert blackList.contains(tweets[1])
    }


}
