package com.pca;

import groovy.util.GroovyTestCase;
import org.junit.Test;

public class TweetPresenterTest  extends GroovyTestCase  {

    public void testsShouldCreateHTMLFileWhenClassCreatedAndNoFileExists() {
        def htmlFile = "tweetList.html"
        new File(htmlFile).delete()
        new TweetPresenter()
        assert new File(htmlFile).exists()
    }

    public void testsShouldCreateNewHTMLFileWhenClassCreatedAndOldFileExists() {
        def htmlFile = "tweetList.html"
        def testFile = new File(htmlFile)
        testFile.createNewFile()
        testFile << "previous content"
        new TweetPresenter()
        def finalFile = new File(htmlFile)
        assert finalFile.exists();
        assert finalFile.size() == 0
    }
}
