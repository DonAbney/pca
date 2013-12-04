package com.pca

import groovy.xml.MarkupBuilder

class Twitter {

    def tweets = []
    def whiteList = []
    def blackListedWords = []
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
        return blackListedWords && tweet.tweetText.toUpperCase() =~ blackListedWords.join('|').toUpperCase()
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
        whiteList.find { whiteListedHandle ->
            whiteListedHandle.equalsIgnoreCase(tweet.tweetHandle)
        }
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

    def addUserToBlackList(String s) {
        userBlackList.add(s)
        !whiteList.contains(s) ?: whiteList.remove(s)
    }
}
