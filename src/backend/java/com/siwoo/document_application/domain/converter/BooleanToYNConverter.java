package com.siwoo.document_application.domain.converter;


import javax.persistence.AttributeConverter;

/*
    Boolean to String "Y" or "N" converter
*/
public class BooleanToYNConverter implements AttributeConverter<Boolean, String> {

    @Override
    public String convertToDatabaseColumn(Boolean secret) {
        return (secret != null && secret) ? "Y" : "N";
    }

    @Override
    public Boolean convertToEntityAttribute(String dbData) {
        return "Y".equals(dbData);
    }
}
