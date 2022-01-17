package com.taltou.txt_file_transformation.services;

import com.taltou.txt_file_transformation.domains.Output;

import java.io.IOException;

public interface IFileTransformationService {

    Output processTransformation(String inputFile, String outputFile, String outputType) throws IOException;
}
