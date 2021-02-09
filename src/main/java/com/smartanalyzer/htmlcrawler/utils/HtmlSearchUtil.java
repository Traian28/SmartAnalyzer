package com.smartanalyzer.htmlcrawler.utils;

import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.List;

public class HtmlSearchUtil {

    public static Element searchElementByAttributesList(Document document, List<Attribute> keyAttributes) {
        return SearchElementUtil.searchElementByAttributes(document, keyAttributes);
    }

    @SneakyThrows
    public static Document checkHtml(Path htmlOk) {
        return Jsoup.parse(new File(htmlOk.toUri()), StandardCharsets.UTF_8.name());
    }

}
