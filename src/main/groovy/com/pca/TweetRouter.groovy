package com.pca

class TweetRouter {
    def greyList = new TweetList()
    def whiteList = new TweetList()
    def whiteListedHandles
    def addTweet(tweet) {
        if (isWhiteListed(tweet)) {
            whiteList.add(tweet)
        }
        else {
            greyList.add(tweet)
        }
    }

    private isWhiteListed(tweet) {
        whiteListedHandles.find {whiteListedHandle ->
            whiteListedHandle.equalsIgnoreCase(tweet.tweetHandle)
        }
    }
}
