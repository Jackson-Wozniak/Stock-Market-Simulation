package org.api.stockmarket.common.csv;

import org.api.stockmarket.common.exceptions.ConfigurationException;
import org.api.stockmarket.modules.stocks.entity.Stock;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class CSVUtils {
    public static List<String[]> fileContents(String filepath, String calledClass){
        try{
            ClassPathResource resource = new ClassPathResource(filepath);
            InputStreamReader streamReader = new InputStreamReader(resource.getInputStream());
            return toArray(new BufferedReader(streamReader).lines().toList());
        }catch (Exception ex){
            ConfigurationException.exit("Error reading file " + filepath + ".", calledClass);
            return List.of();
        }
    }

    public static long lineCount(String filepath, String calledClass){
        try{
            ClassPathResource resource = new ClassPathResource(filepath);
            InputStreamReader streamReader = new InputStreamReader(resource.getInputStream());
            return new BufferedReader(streamReader).lines().toList().size();
        }catch (Exception ex){
            ConfigurationException.exit("Error reading file " + filepath + ".", calledClass);
            return 0L;
        }
    }

    public static List<String[]> toArray(List<String> strings){
        return strings.stream().map(str -> str.split(",")).filter(arr -> !arr[0].contains(("#"))).toList();
    }
}
