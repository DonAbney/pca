package com.pca

import groovy.xml.MarkupBuilder

class Twitter {

    def tweets = []
    def whiteList = []
    def blackList = []
    def userBlackList = []

    def addTweet(tweet) {
        if (displayTweet(tweet)) {
            tweets.push(tweet)
        }
    }

    String displayPublicTimeline() {
        def stringWriter = new StringWriter()
        def html = new MarkupBuilder(stringWriter)

        html.html {
            body {
                ul {
                    tweets.each { li(it.tweetText) }
                }
            }
        }

        stringWriter.toString()
    }

    private displayTweet(tweet) {
        filterByWhiteListUser(tweet) || !(filterByBlackListedWord(tweet) || filterByBlackListedUser(tweet))
    }

    private filterByBlackListedWord(tweet) {
        return blackList && tweet.tweetText =~ blackList.join('|')
    }

    private filterByBlackListedUser(tweet) {
        return userBlackList.contains(tweet.tweetHandle)
    }

    def findTweetsForHashtag(hashtag) {
        def results = []
        tweets.each {
            if (it.tweetText.contains(hashtag)) {
                results << it
            }
        }
        return results
    }

    private filterByWhiteListUser(tweet) {
       whiteList.contains(tweet.tweetHandle)
    }

    def getBlackListedTweets() {
        def result = []
        tweets.each {
            if (filterByBlackListedWord(it)) {
                result.add(it)
            }
        }
        result
    }
}
