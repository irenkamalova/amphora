package com.kamalova.amphora.api.controller.model;

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
    String name;
    @JacksonXmlProperty
    Integer age;
    @JacksonXmlProperty
    String mother;
    @JacksonXmlProperty
    String father;
}
