package com.string.search.via.web.String.search.controller;


import com.string.search.via.web.String.search.dto.JsonResponse;
import com.string.search.via.web.String.search.service.documentReader.HtmlOpener;
import com.string.search.via.web.String.search.service.engine.SearchEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

@RestController
public class FolderInputController {

    @Autowired
    private SearchEngine searchEngine;

    @GetMapping("/search")
    public List<ResponseEntity<JsonResponse>> search(
            @RequestParam("location") String location,
            @RequestParam("text") String text,
            @RequestParam("type") String type
    ) throws IOException {
        Map<String, List<Integer>> map = new HashMap<>();

        System.out.println(type);

        if (type.equals("plain")) {
            map = searchEngine.searchInFile(location, text);
        } else if (type.equals("binary")) {
            map = searchEngine.searchInBinaryFile(location, text);
        } else if (type.equals("html")) {
            try {
                URL url = new URL(location);

                if ("http".equals(url.getProtocol()) || "https".equals(url.getProtocol())) {
                    if (url.getHost().startsWith("www")) {
                        HtmlOpener htmlOpener = new HtmlOpener();
                        try {
                            List<Integer> integers = htmlOpener.openHtmlFileInWeb(location, text);
                            map.put(String.valueOf(url), integers);
                            JsonResponse jsonResponse = new JsonResponse();

                            jsonResponse.setLocation(String.valueOf(url));
                            jsonResponse.setIdx(integers);

                            return Collections.singletonList(ResponseEntity.ok(jsonResponse));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        return Collections.emptyList();
                    }
                } else {
                    return Collections.emptyList();
                }
            } catch (MalformedURLException e) {
                HtmlOpener htmlOpener = new HtmlOpener();
                List<Integer> integers
                        = htmlOpener.openHtmlFileLocally(location, text);
                map = searchEngine.searchInHtmlFile(location, text);
            }
        } else {
            // Handle the case where the type is not "plain", "binary", or "html"
            return Collections.emptyList();
        }

        List<ResponseEntity<JsonResponse>> list = new ArrayList<>();

        for (String link : map.keySet()) {
            JsonResponse jsonResponse = new JsonResponse();
            jsonResponse.setLocation(link);

            System.out.println(link);

            List<Integer> integers = map.get(link);
            jsonResponse.setIdx(integers);

            System.out.println(integers);
            list.add(ResponseEntity.ok(jsonResponse));
        }

        return list;
    }

}
