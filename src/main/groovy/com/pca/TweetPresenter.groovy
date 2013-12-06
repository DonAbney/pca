package com.pca


class TweetPresenter {
    def templateFileName = "template.html"
    def tweets = []

    def TweetPresenter() {
        def templateText = initializeHtmlTemplateFile()
        initializeDisplayHtmlFile(templateText)
    }

    def addTweet(tweet) {
        def tweetHtml = new TweetToHtmlConverter().display(tweet)
        tweets.add(tweetHtml)
    }

    private String initializeHtmlTemplateFile() {
        def templateFile = new File(templateFileName)
        if (!templateFile.exists()) {
            throw new GroovyRuntimeException()
        }

        templateFile.text
    }

    private void initializeDisplayHtmlFile(templateText) {
        def htmlFile = new File("tweetList.html")
        htmlFile.createNewFile()
        htmlFile.write(templateText)
    }

}
