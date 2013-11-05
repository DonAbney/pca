package main.groovy

import groovy.xml.MarkupBuilder

class Twitter {

    def tweets = []

    def getPublicTimeline() {
        tweets
    }

    String displayPublicTimeline() {
        def stringWriter = new StringWriter()
        def html = new MarkupBuilder(stringWriter)

        html.html {
            body {
                ul {
                    tweets.each { li(it.tweetText)}
                }
            }
        }

        stringWriter.toString()
    }
}
