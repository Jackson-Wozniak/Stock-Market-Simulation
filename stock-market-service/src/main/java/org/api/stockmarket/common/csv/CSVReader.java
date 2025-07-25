package org.api.stockmarket.common.csv;

import java.util.*;

public interface CSVReader<Type extends CSVObject<Entity>, Entity>{
    List<Type> map();
    List<Entity> toEntity();
    List<Entity> toEntity(List<Type> data);
    long lineCount();
}
