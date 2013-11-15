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
        filterWhiteListTweet(tweet) || ! blackListed(tweet)
    }

    def blackListed(tweet) {
        return (blackList && tweet.tweetText =~ blackList.join('|')) || userBlackList.contains(tweet.tweetHandle)
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
    
    def filterWhiteListTweet(tweet) {
       whiteList.contains(tweet.tweetHandle)
    }

}
