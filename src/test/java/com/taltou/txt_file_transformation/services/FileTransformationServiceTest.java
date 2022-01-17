package com.taltou.txt_file_transformation.services;

import com.taltou.txt_file_transformation.domains.Output;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileTransformationServiceTest {


    private  FileTransformationService fileInformationsService = new FileTransformationService();


    String inputFile= "src/main/resources/data/input_data.txt";
    String outputFile= "src/main/resources/data/result_data.xml";
    String outputFileType= "XML";


    @Test
    void testOneerror() throws IOException {
        Output output=fileInformationsService.builtInformations(inputFile);
        assertEquals(output.getErrors().size(), 1);
    }

    @Test
    void testFourreferences() throws IOException {
        Output output=fileInformationsService.builtInformations(inputFile);
        assertEquals(output.getReferences().size(), 4);
    }

    @Test
    void testFileName() throws IOException {
        Output output=fileInformationsService.processTransformation(inputFile,outputFile,outputFileType);
        assertTrue(output.getInputFile().contains("input_data"));
    }

}
