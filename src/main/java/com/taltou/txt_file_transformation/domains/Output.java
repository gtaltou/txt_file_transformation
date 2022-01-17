package com.taltou.txt_file_transformation.domains;

import java.util.ArrayList;
import java.util.List;

public class Output {

    private final String inputFile;
    private final List<Reference> references;
    private final List<Error> errors;

    public Output(String inputFile) {
        this.inputFile = inputFile;
        this.references = new ArrayList<>();
        this.errors = new ArrayList<>();
    }

    public String getInputFile() {
        return inputFile;
    }

    public void addReference(Reference reference) {
        this.references.add(reference);
    }

    public List<Reference> getReferences() {
        return references;
    }

    public void addError(Error error) {
        this.errors.add(error);
    }

    public List<Error> getErrors() {
        return errors;
    }
}
