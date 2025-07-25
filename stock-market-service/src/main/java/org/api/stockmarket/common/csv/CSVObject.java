package org.api.stockmarket.common.csv;

public interface CSVObject<Type> {
    Type mapToEntity();
    boolean fieldsValid();
}
