package com.smartanalyzer.htmlcrawler.utils;

import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;

import static java.util.Comparator.comparingInt;
import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.joining;

public class SearchElementUtil {

    public static Element searchElementByAttributes(Document document, List<Attribute> keyAttributes) {
        Objects.requireNonNull(document, "document");

        if (keyAttributes == null || keyAttributes.isEmpty()) {
            throw new IllegalArgumentException("Should specify attributes");
        }

        AbstractMap.SimpleEntry<Element, List<Attribute>> foundEntry = document.getAllElements()
                .stream()
                .map(new Function<Element, AbstractMap.SimpleEntry<Element, List<Attribute>>>() {
                    @Override
                    public AbstractMap.SimpleEntry<Element, List<Attribute>> apply(Element e) {
                        return new AbstractMap.SimpleEntry<>(e, innerJoin(e.attributes().asList(), keyAttributes));
                    }
                })
                .max(comparingInt(new ToIntFunction<AbstractMap.SimpleEntry<Element, List<Attribute>>>() {
                    @Override
                    public int applyAsInt(AbstractMap.SimpleEntry<Element, List<Attribute>> entry) {
                        return entry.getValue().size();
                    }
                }))
                .orElseThrow(new Supplier<RuntimeException>() {
                    @Override
                    public RuntimeException get() {
                        return new RuntimeException(joinAttributes(keyAttributes));
                    }
                });


        searchAttributes(keyAttributes, foundEntry);
        return foundEntry.getKey();
    }

//    AbstractMap.SimpleEntry<Element, List<Attribute>> foundEntry = document.getAllElements()
//            .stream()
//            .map(e -> new AbstractMap.SimpleEntry<>(e, innerJoin(e.attributes().asList(), keyAttributes)))
//            .max(comparingInt(entry -> entry.getValue().size()))
//            .orElseThrow(() -> new RuntimeException(joinAttributes(keyAttributes)));

    private static void searchAttributes(List<Attribute> keyAttributes, AbstractMap.SimpleEntry<Element, List<Attribute>> foundEntry) {
        System.out.println("Key attributes: " + keyAttributes);
        System.out.println("Found attributes: " + foundEntry.getValue());
        System.out.println("Common attributes: " + innerJoin(keyAttributes, foundEntry.getValue()));
    }

    private static String joinAttributes(List<Attribute> keyAttributes) {
        return keyAttributes.stream().map(Attribute::toString).collect(joining(", "));
    }

    private static <E> List<E> innerJoin(Collection<E> left, Collection<E> right) {
        requireNonNull(left, "left");
        requireNonNull(right, "right");
        List<E> result = new ArrayList<>(left);
        result.retainAll(right);
        return result;
    }
}
