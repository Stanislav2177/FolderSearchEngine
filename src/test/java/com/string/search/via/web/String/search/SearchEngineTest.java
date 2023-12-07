package com.string.search.via.web.String.search;

import com.string.search.via.web.String.search.service.engine.SearchEngine;
import com.string.search.via.web.String.search.service.fileTraverse.FileSystemTraverse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SearchEngineTest {
    private SearchEngine searchEngine;
    private FileSystemTraverse traverse;

    @BeforeEach
    void set(){
        searchEngine = new SearchEngine();
        traverse = new FileSystemTraverse();
    }


    @Test
    public void testSearchInHtml() throws IOException {
        String path = "C:\\Users\\Stanislav\\Github\\FolderSearchEngine\\foldersForTesting\\folder-to-test-html";
        String search = "Ipsum";

        Map<String, List<Integer>> stringListMap = searchEngine.searchInHtmlFile(path, search);


        for (String s : stringListMap.keySet()) {
            assert(!stringListMap.get(s).isEmpty());
        }
    }

    @Test
    public void testSearchInBinary() throws IOException{
        String path = "C:\\Users\\Stanislav\\FolderSearchEngine\\Github\\foldersForTesting\\folder-to-test-binary";
        String search = "FF";

        Map<String, List<Integer>> stringListMap = searchEngine.searchInBinaryFile(path, search);
        for (String s : stringListMap.keySet()) {
            assert(!stringListMap.get(s).isEmpty());
        }
    }

    @Test
    public void testToTraverseAndUnzipAdnSearchForSpecificString() throws IOException {
        String root =  "C:\\Users\\Stanislav\\Github\\FolderSearchEngine\\foldersForTesting\\folder-to-test";

        int count = 0;
        Map<String, List<Integer>> stringListMap
                = searchEngine.traverseAndSearchByLink(root, "Stan");

        for (String s : stringListMap.keySet()) {
            count += stringListMap.get(s).size();
        }

        assertEquals(count, 58);

    }
}
