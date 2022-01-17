package com.taltou.txt_file_transformation.domains;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Error {

    @JacksonXmlProperty(isAttribute = true)
    private int item;
    @JacksonXmlProperty(isAttribute = true)
    private String message;
    @JacksonXmlText
    private String value;
}
