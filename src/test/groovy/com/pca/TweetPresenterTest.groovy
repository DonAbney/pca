package com.pca;

import groovy.util.GroovyTestCase;
import org.junit.Test;

public class TweetPresenterTest  extends GroovyTestCase  {
    def testTemplateFileName = "testTweetTemplate.html"

    public void testsShouldCreateHTMLFileWhenClassCreatedAndNoFileExists() {
        def htmlFile = "tweetList.html"
        createTemplate()
        new File(htmlFile).delete()
        new TweetPresenter()
        assert new File(htmlFile).exists()
    }

    public void testsShouldCreateNewHTMLFileWhenClassCreatedAndOldFileExists() {
        def htmlFile = "tweetList.html"
        createTemplate()
        def testFile = new File(htmlFile)
        testFile.createNewFile()
        testFile << "previous content"

        new TweetPresenter()

        def finalFile = new File(htmlFile)
        assert finalFile.exists();
        assert finalFile.text != "previous content"
    }

    public void testsShouldThrowAnExceptionIfNoHtmlTemplateFileExists(){
        new File("testTweetTemplate.html").delete()
        shouldFail {
            def presenter = new TweetPresenter()
        }
    }

    public void testsShouldCopyTemplateFileContentIntoDisplayFile()  {
        def templateFile = createTemplate()
        templateFile << "Dummy template"

        def presenter = new TweetPresenter()

        def displayFile = new File("tweetList.html")
        def displayContents = displayFile.text
        assertEquals("Dummy template", displayContents)
    }

    private File createTemplate() {
        def templateFile = new File("testTweetTemplate.html")
        templateFile.createNewFile()
        templateFile.write("")
        templateFile
    }
}
