package com.pca


class TweetPresenter {
    def TweetPresenter() {
        def htmlFile =  new File("tweetList.html")
        if (htmlFile.exists()) {
            htmlFile.delete()
        }
        htmlFile.createNewFile()
    }
}
