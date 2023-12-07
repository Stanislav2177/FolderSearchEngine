package com.string.search.via.web.String.search.testServiceNew;

import com.string.search.via.web.String.search.service.fileTraverse.FileSystemTraverse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FileSystemTraverseTest {

    @InjectMocks
    private FileSystemTraverse fileSystemTraverse;

    @Mock
    private File mockFile;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListFolderContentsWhenFolderExists() {
        String folderPath = "//home//Github//FolderForSearching";
        File[] mockFiles = {mock(File.class), mock(File.class)};
        when(mockFile.exists()).thenReturn(true);
        when(mockFile.isDirectory()).thenReturn(true);
        when(mockFile.listFiles()).thenReturn(mockFiles);
        when(mockFiles[0].getAbsolutePath()).thenReturn("file1");
        when(mockFiles[1].getAbsolutePath()).thenReturn("file2");

        List<String> result =
                fileSystemTraverse.getAllFolderPathsWhichAreDirectories(folderPath);

        assertEquals(Arrays.asList("file1", "file2"), result);
    }
}
