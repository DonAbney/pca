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

    void testTweetsWithWhiteListedHandleAreRoutedToWhiteList() {
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

    void testTweetsWithBlackListedHandleAreRoutedToBlackList() {
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

    void testThatTweetsBlackListedByHandleIsCaseInsensitive() {
        def tweetRouter = new TweetRouter(blackListedHandles: ['black'])
        def tweet = new Tweet(tweetHandle: 'BlAcK', tweetText: 'Text')

        tweetRouter.addTweet(tweet)

        def blackList = tweetRouter.blackList

        assert blackList.size() == 1
        assert blackList.contains(tweet)
    }

    void testTweetsWithBlackListedWordAreRoutedToBlackList() {
        def tweetRouter = new TweetRouter(blackListedWords: ['black'])
        def tweets = [
            new Tweet(tweetHandle: 'GoodGuy', tweetText: 'Text 2'),
            new Tweet(tweetHandle: 'GoodGuy', tweetText: 'Text with black in it.'),
        ]

        tweetRouter.addTweet(tweets[0]);
        tweetRouter.addTweet(tweets[1]);

        def blackList = tweetRouter.blackList
        def greyList = tweetRouter.greyList

        assert blackList.size() == 1
        assert !blackList.contains(tweets[0])
        assert blackList.contains(tweets[1])

        assert greyList.size() == 1
        assert greyList.contains(tweets[0])
        assert !greyList.contains(tweets[1])
    }

    void testThatTweetsBlackListedByWordIsCaseInsensitive() {
        def tweetRouter = new TweetRouter(blackListedWords: ['black'])
        def tweet = new Tweet(tweetHandle: 'GoodGuy', tweetText: 'Text has BlAcK in it.')

        tweetRouter.addTweet(tweet)

        def blackList = tweetRouter.blackList

        assert blackList.size() == 1
        assert blackList.contains(tweet)
    }

    void testTweetsWithWhiteListedHandleAreRoutedToWhiteListEvenWithBlackListedWord() {
        def tweetRouter = new TweetRouter(whiteListedHandles: ['white'], blackListedWords: ['black'])
        def tweet = new Tweet(tweetHandle: 'white', tweetText: 'Text with black in it.')

        tweetRouter.addTweet(tweet)

        def whiteList = tweetRouter.whiteList

        assert whiteList.size() == 1
        assert whiteList.contains(tweet)
    }

}
