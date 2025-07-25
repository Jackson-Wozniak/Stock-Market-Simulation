package org.api.stockmarket.common.csv;

import java.util.*;

public interface CSVReader<Type>{
    List<CSVObject<Type>> map();
    List<Type> toEntity(CSVObject<Type> data);
}
