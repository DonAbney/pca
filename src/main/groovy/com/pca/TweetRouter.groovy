package com.pca

class TweetRouter {
    def greyList = new TweetList()
    def whiteList = new TweetList()
    def blackList = new TweetList()
    def whiteListedHandles
    def blackListedHandles

    def addTweet(tweet) {
        if (isWhiteListed(tweet)) {
            whiteList.add(tweet)
        }
        else if(isBlackListed(tweet)){
            blackList.add(tweet)
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

    private isBlackListed(tweet){
        blackListedHandles.find {blackListedHandle ->
            blackListedHandle.equalsIgnoreCase(tweet.tweetHandle)
        }

    }
}
