package com.pca


class TweetPresenter {
    def templateFileName = "testTweetTemplate.html"

    def TweetPresenter() {
        def templateText = initializeHtmlTemplateFile()
        initializeDisplayHtmlFile(templateText)
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
