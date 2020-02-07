package com.kamalova.amphora.api.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@JacksonXmlRootElement(localName = "node")
@XmlRootElement
public class FamilyNodeRequest {
    @JacksonXmlProperty(isAttribute = true)
    @XmlAttribute
    private String name;
    @JacksonXmlProperty
    private Integer age;
    @JacksonXmlProperty
    private String mother;
    @JacksonXmlProperty
    private String father;
}
