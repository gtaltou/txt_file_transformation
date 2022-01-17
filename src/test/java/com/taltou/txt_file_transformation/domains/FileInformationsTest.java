package com.taltou.txt_file_transformation.domains;

import com.taltou.txt_file_transformation.errors.FileTransformationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileInformationsTest {


    @Test
    void testMissingArgument() {
        FileTransformationException thrown = assertThrows(FileTransformationException.class, () -> new Reference("1462100403;B;105.23"));
        assertTrue(thrown.getMessage().contains("arg"));

    }

    @Test
    void testReferenceColor() {
        Reference reference = new Reference("1462100403;B;105.23;97");
        assertTrue(reference.getColor().equals(Color.B));
    }


    @Test
    void testExceptionOnReferenceColor() {
        FileTransformationException thrown = assertThrows(FileTransformationException.class, () -> new Reference("1462100403;A;105.23;97"));
        assertTrue(thrown.getMessage().contains("color"));
    }

    @Test
    void testExceptionOnReferencenNumber() {
        FileTransformationException thrown = assertThrows(FileTransformationException.class, () -> new Reference("14621004;R;105.23;97"));
        assertTrue(thrown.getMessage().contains("numReference"));
    }


}
