package main.groovy

import groovy.xml.MarkupBuilder


class Twitter {

    def tweets = []
    def whiteList = []
    def blackList = []

    def getPublicTimeline() {
        tweets
    }

    String displayPublicTimeline() {
        def stringWriter = new StringWriter()
        def html = new MarkupBuilder(stringWriter)

        html.html {
            body {
                ul {
                    filteredTweets().each { li(it.tweetText)}
                }
            }
        }

        stringWriter.toString()
    }

    def filteredTweets() {
        tweets.grep( { ! ( it.tweetText =~ /black/ ) } )
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
}
