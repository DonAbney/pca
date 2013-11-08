package com.pca

import groovy.xml.MarkupBuilder

class Twitter {

    def tweets = []
    def whiteList = []
    def blackList = []

    def getPublicTimeline() {
        tweets
    }

    String displayPublicTimeline() {
       def whiteListedTweets = []

        if(whiteList.size() > 0) {
            whiteListedTweets = filterWhiteListTweets()
       }
       else {
           whiteListedTweets = tweets
        }

        def stringWriter = new StringWriter()
        def html = new MarkupBuilder(stringWriter)

        html.html {
            body {
                ul {
                    whiteListedTweets.each { li(it.tweetText)}
                }
            }
        }

        stringWriter.toString()
    }

    def findTweetsForHashtag(hashtag) {
        '#sportsRockNot'
//        [ new Tweet(tweet), new Tweet() ]
    }

    def filterWhiteListTweets() {
        tweets.findAll{x ->
            whiteList.contains(x.tweetHandle)
        }
    }
}
