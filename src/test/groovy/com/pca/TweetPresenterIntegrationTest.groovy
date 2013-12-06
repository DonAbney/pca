package com.pca

import groovy.util.GroovyTestCase;
import org.junit.Test;

class TweetPresenterIntegrationTest extends GroovyTestCase {
    public void testsShouldCreateHTMLFileWhenClassCreatedAndNoFileExists() {
        def htmlFile = "tweetList.html"

        new File(htmlFile).delete()
        new TweetPresenter()
        assert new File(htmlFile).exists()
    }

    public void testsShouldCreateNewHTMLFileWhenClassCreatedAndOldFileExists() {
        def htmlFile = "tweetList.html"
        def templateFile = new File("template.html")

        def testFile = new File(htmlFile)
        testFile.createNewFile()
        testFile << "previous content"

        new TweetPresenter()

        def finalFile = new File(htmlFile)
        assert finalFile.exists();
        assert finalFile.text != "previous content"
    }

    public void testsShouldCopyTemplateFileContentIntoDisplayFile()  {
        def templateFile = new File("template.html")
        def templateContent = templateFile.text

        def presenter = new TweetPresenter()

        //File.metaclass.text { -> return "Dummy template2"}
        def displayFile = new File("tweetList.html")
        def displayContents = displayFile.text
        assertEquals(templateContent, displayContents)
    }
}
