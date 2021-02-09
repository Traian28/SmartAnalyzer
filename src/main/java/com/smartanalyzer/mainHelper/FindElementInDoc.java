package com.smartanalyzer.mainHelper;

import com.smartanalyzer.htmlcrawler.utils.HtmlSearchUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.nio.file.Paths;

import static java.util.Objects.requireNonNull;

public class FindElementInDoc {

    private static final String OK_BUTTON = "make-everything-ok-button";

    public static void findElement (String[] args) {

        //Required Args
        String originalHtml = requireNonNull(args[0], "Origin file path is required at position 0");
        String changedHtml = requireNonNull(args[1], "Other sample file path is required at position 1");

        String elementId = args.length == 3 ? args[2] : OK_BUTTON;

        Document originalDoc = requireNonNull(HtmlSearchUtil.checkHtml(Paths.get(originalHtml)), "unable to find original");
        Document changedDoc = requireNonNull(HtmlSearchUtil.checkHtml(Paths.get(changedHtml)), "unable to find changed sample file");

        Element originalElement = requireNonNull(originalDoc.getElementById(elementId), "unable to find element in original file");
        Element foundElement = HtmlSearchUtil.searchElementByAttributesList(changedDoc, originalElement.attributes().asList());

        System.out.println("Element found: " + foundElement.cssSelector());

    }
}
