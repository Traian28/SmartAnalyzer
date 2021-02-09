package com.smartanalyzer.htmlcrawler;

import com.smartanalyzer.htmlcrawler.utils.HtmlSearchUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SearchUtilTest {

    private static final String ORIGINAL_HTML = "sample-0-origin.html";
    private static final Path DIR_PATH = Paths.get("src", "test", "resources");
    private static final String OK_BUTTON = "make-everything-ok-button";

    @Test
    public void testHtmlCrawler_evilGemini() {
        testHtmlCrawler(
                "#page-wrapper > div.row:nth-child(3) > div.col-lg-8 > div.panel.panel-default > div.panel-body > a.btn.btn-success","sample-1-evil-gemini.html"

        );
    }

    @Test
    public void testHtmlCrawler_containerAndClone() {
        testHtmlCrawler(
                "#page-wrapper > div.row:nth-child(3) > div.col-lg-8 > div.panel.panel-default > div.panel-body > div.some-container > a.btn.test-link-ok", "sample-2-container-and-clone.html"
        );
    }

    @Test
    public void testHtmlCrawler_theEscape() {
        testHtmlCrawler(
                "#page-wrapper > div.row:nth-child(3) > div.col-lg-8 > div.panel.panel-default > div.panel-footer > a.btn.btn-success", "sample-3-the-escape.html"
        );
    }

    @Test
    public void testHtmlCrawler_theMash() {
        testHtmlCrawler(
                "#page-wrapper > div.row:nth-child(3) > div.col-lg-8 > div.panel.panel-default > div.panel-footer > a.btn.btn-success", "sample-4-the-mash.html"
        );
    }

    private void testHtmlCrawler(String expectedCssSelector, String changedFile) {
        Document originalDoc = HtmlSearchUtil.checkHtml(DIR_PATH.resolve(SearchUtilTest.ORIGINAL_HTML));
        Document changedDoc = HtmlSearchUtil.checkHtml(DIR_PATH.resolve(changedFile));

        Element originalElement = originalDoc.getElementById(OK_BUTTON);
        Element foundElement = HtmlSearchUtil.searchElementByAttributesList(changedDoc, originalElement.attributes().asList());

        assertThat(foundElement.cssSelector(), is(expectedCssSelector));
    }

}
