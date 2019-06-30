package com.perago.techtest;

import java.io.Serializable;
import java.util.Optional;

/**
 * The object representing a diff.
 * Implement this class as you see fit.
 *
 */
public class DiffContainer<T extends Serializable > {

    private String fieldName;
    private Object originalValue;
    private Object modifiedValue;


    public DiffContainer() {
    }

    public DiffContainer(String fieldName, Object originalValue, Object modifiedValue) {
        this.fieldName = fieldName;
        this.originalValue = originalValue;
        this.modifiedValue = modifiedValue;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Object getOriginalValue() {
        return originalValue;
    }

    public Object getModifiedValue() {
        return modifiedValue;
    }

    @Override
    public String toString() {
        return "DiffContainer{" +
                "fieldName='" + fieldName + '\'' +
                ", originalValue=" + originalValue +
                ", modifiedValue=" + modifiedValue +
                '}';
    }
}