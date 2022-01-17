package com.taltou.txt_file_transformation.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import com.taltou.txt_file_transformation.domains.Error;
import com.taltou.txt_file_transformation.domains.Output;
import com.taltou.txt_file_transformation.domains.OutputType;
import com.taltou.txt_file_transformation.domains.Reference;
import com.taltou.txt_file_transformation.errors.FileTransformationException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class FileTransformationService implements IFileTransformationService{

    /**
     * Process informations by a given filePath
     * @param  inputFile : the inputFile
     * @return Output : the outputFile
     * @throws IOException: the iOException
     */

    public Output processInformations(String inputFile) throws IOException {
        List<String> refferenceList= Files.lines(Paths.get(inputFile)).collect(Collectors.toList());

        Output output = new Output(inputFile);

        for (int item = 0; item < refferenceList.size(); item++) {

            String occurrence = refferenceList.get(item);
            try {
                Reference reference = new Reference(occurrence);
                output.addReference(reference);
            } catch (FileTransformationException exception){
                Error error = new Error(item, exception.getMessage(),occurrence);
                output.addError(error);
            }
        }
        return output;
    }


    /**
     * Process the output by a given type of output format
     * @param inputFile : the input file
     * @param outputType : the output type
     * @throws IOException : the iOException
     * @return Output : the return type
     */

    public Output processTransformation(String inputFile, String outputFile, String outputType) throws IOException {

        Output output = processInformations(inputFile);

        if (outputType.equals(OutputType.XML.toString())) {
            builtXMLFile(output, outputFile);
        }
        else if (outputType.equals(OutputType.JSON.toString())) {
            builtJsonFile(output, outputFile);
        }
        else{
            throw  new RuntimeException("The type you specified is not taken into account; accepted types are \"XML\" or \"JSON\"");
        }

        return output;
    }


    /**
     * Building the  json file on output result
     * @param output: the output
     * @param outPutFile: the outputFile
     * @throws IOException : the IOException
     */
    private void builtJsonFile(Output output, String outPutFile) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File(outPutFile), output);
    }

    /**
     * Building the  XML file on output result
     * @param output: the output
     * @param outPutFile: the outputFile
     * @throws IOException : the IOException
     */
    private void builtXMLFile(Output output, String outPutFile) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true);
        xmlMapper.writeValue(new File(outPutFile), output);
    }

}
