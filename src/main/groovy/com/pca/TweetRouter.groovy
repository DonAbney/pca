package com.pca

class TweetRouter {
    def greyList = new TweetList()
    def addTweet(tweet) {
        greyList.add(tweet)
    }
}
