package com.smartanalyzer;

import com.smartanalyzer.mainHelper.FindElementInDoc;

public class MainApplication {

    public static void main(String[] args) {
        if (args == null || args.length < 2) {
            System.out.println("Should specify 2 or 3 args");
            return;
        }

        FindElementInDoc.findElement(args);

    }
}