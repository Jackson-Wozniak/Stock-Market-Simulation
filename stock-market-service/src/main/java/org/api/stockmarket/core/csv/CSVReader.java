package org.api.stockmarket.core.csv;

import java.util.*;

public interface CSVReader<Type extends CSVObject<Entity>, Entity>{
    List<Type> map();
    List<Entity> toEntities();
    List<Entity> toEntities(List<Type> data);
    long lineCount();
}
