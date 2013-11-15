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

    def displayTweet(tweet) {
        filterByWhiteListUser(tweet) || ! ( filterByBlackListedWord(tweet) || filterByBlackListedUser(tweet) )
    }

    def filterByBlackListedWord(tweet) {
        return blackList && tweet.tweetText =~ blackList.join('|')
    }
    def filterByBlackListedUser(tweet) {
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
    
    def filterByWhiteListUser(tweet) {
       whiteList.contains(tweet.tweetHandle)
    }

}
