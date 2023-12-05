package com.string.search.via.web.String.search.service.documentReader;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Service
public class HtmlOpener {
    //Jsoup is an open-source Java library used mainly for extracting data from HTML
    //and will be used for the purpose of the task


    public List<Integer> openHtmlFileInWeb(String url, String search) throws IOException {

        //For testing if page is loaded successfully
//        Document doc = Jsoup.connect(url).get();
//        doc.select("p").forEach(System.out::println);
        List<Integer> indexesList = new ArrayList<>();

        try {
            // Connect to the URL and get the HTML document
            Document document = Jsoup.connect(url).get();

            // Use Jsoup selectors to find elements containing the specified text
            Elements elementsContainingText = document.getElementsContainingOwnText(search);

            indexesList = new ArrayList<>();

            for (Element element : elementsContainingText) {
                String elementText = element.text();

                int index = -1;
                while ((index = elementText.indexOf(search, index + 1)) != -1) {
                    indexesList.add(index);
//                    System.out.println("Found at index " + index + " in element: " + elementText);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(indexesList);

        return indexesList;
    }

    public List<Integer> openHtmlFileLocally(String location, String text) throws IOException {
        File folder = new File(location);
        File[] files = folder.listFiles();
        List<Integer> indexesList = new ArrayList<>();


        for (File file : files) {
            if(file.getName().endsWith(".html")){
                Document document = Jsoup.parse(file, "UTF-8");
                Elements elementsContainingText = document.getElementsContainingOwnText(text);

                for (Element element : elementsContainingText) {
                    String elementText = element.text();

                    int index = -1;
                    while ((index = elementText.indexOf(text, index + 1)) != -1) {
                        indexesList.add(index);
//                    System.out.println("Found at index " + index + " in element: " + elementText);
                    }
                }
            }
        }
        System.out.println(indexesList);

        return indexesList;

    }
}
