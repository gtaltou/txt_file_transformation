package com.taltou.txt_file_transformation.domains;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.taltou.txt_file_transformation.errors.FileTransformationException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Arrays;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JacksonXmlRootElement(localName = "reference")
public class Reference {

    @JacksonXmlProperty(isAttribute = true)
    private String numReference;
    @JacksonXmlProperty(isAttribute = true)
    private int size;
    @JacksonXmlProperty(isAttribute = true)
    private double price;
    @JacksonXmlProperty(localName = "color", isAttribute = true)
    private Color color;

    public Reference (String line){
        String[] reference = line.split(";");
        handleInputData(reference);
        this.numReference = reference[0];
        this.size = Integer.parseInt(reference[3]);
        this.price = Double.parseDouble(reference[2]);
        this.color = Color.valueOf(reference[1]);
    }


    private  void handleInputData(String[] reference) {
        String colors = Arrays.asList(Color.values()).toString();


        if (!colors.contains(reference[1])) {
            throw new FileTransformationException("The color reference is wrong... please check the input value");
        }

        if (reference.length!=4) {
            throw new FileTransformationException("The number of arguments is incorrect... please check the input values");
        }
        if (reference[0].length() != 10) {
            throw new FileTransformationException("The The numReference is wrong... please check the input values");
        }

    }


}
