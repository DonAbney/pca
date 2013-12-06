package com.pca;

import groovy.util.GroovyTestCase;
import org.junit.Test;

public class TweetPresenterTest extends GroovyTestCase  {

    public void testsShouldThrowAnExceptionIfNoHtmlTemplateFileExists(){
        File.metaClass.exists { -> return false }

        shouldFail {
            def presenter = new TweetPresenter()
        }
    }

    public void testsShouldConvertNewTweetToHTMLAndAddToCollection() {
        def tweetPresenter = new TweetPresenter()

        tweetPresenter.addTweet(new Tweet(tweetHandle: 'Mark', tweetText: 'This rocks'))

        assertEquals(1, tweetPresenter.tweets.size())
        assert tweetPresenter.tweets[0].contains('This rocks')
        assert tweetPresenter.tweets[0].contains('Mark')
    }


}
