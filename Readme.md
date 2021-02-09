Smart Analyzer Test
================================

***Smart XML Analyzer***

### Build
- mvn clean install

### Execute tests
1. 
Input:
> java -jar target/SmartAnalyzerTest-1.0-SNAPSHOT.jar src/test/resources/sample-0-origin.html src/test/resources/sample-1-evil-gemini.html

Output:
> page-wrapper > div.row:nth-child(3) > div.col-lg-8 > div.panel.panel-default > div.panel-body > a.btn.btn-success

2.
Input:
> java -jar target/SmartAnalyzerTest-1.0-SNAPSHOT.jar src/test/resources/sample-0-origin.html src/test/resources/sample-2-container-and-clone.html

Output:
> page-wrapper > div.row:nth-child(3) > div.col-lg-8 > div.panel.panel-default > div.panel-body > div.some-container > a.btn.test-link-ok

3.
Input:
> java -jar target/SmartAnalyzerTest-1.0-SNAPSHOT.jar src/test/resources/sample-0-origin.html src/test/resources/sample-3-the-escape.html

Output:
> page-wrapper > div.row:nth-child(3) > div.col-lg-8 > div.panel.panel-default > div.panel-footer > a.btn.btn-success

4.
Input:
> java -jar target/SmartAnalyzerTest-1.0-SNAPSHOT.jar src/test/resources/sample-0-origin.html src/test/resources/sample-4-the-mash.html

Output:
> page-wrapper > div.row:nth-child(3) > div.col-lg-8 > div.panel.panel-default > div.panel-footer > a.btn.btn-success
