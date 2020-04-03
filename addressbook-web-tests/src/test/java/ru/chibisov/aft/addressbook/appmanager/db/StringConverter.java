package ru.chibisov.aft.addressbook.appmanager.db;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class StringConverter implements AttributeConverter<String, String> {

    @Override
    public String convertToDatabaseColumn(String attribute) {
        return attribute;
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        return dbData == null || dbData.isEmpty() ? null : dbData;
    }
}