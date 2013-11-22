package com.pca

class TweetToHtmlConverter {

    def display(tweet) {

        if (tweet?.tweetHandle && tweet.tweetText) {
            "<div class='tweet'>" +
                    "<div class='handle'><p>${tweet.tweetHandle}</p></div>" +
                    "<div class='tweet-text'><p>${tweet.tweetText }</p></div>" + "</div>"
        } else {
            "<div class='tweet'>" +
                    "<div class='handle'><p></p></div>" +
                    "<div class='tweet-text'><p></p></div>" + "</div>"
        }

    }
}
