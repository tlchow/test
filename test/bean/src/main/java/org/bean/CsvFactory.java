package org.bean;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.propertyeditors.CustomDateEditor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;


public class CsvFactory {
		
	
	public static <T> List<T> mapCsvToModel(Properties convertProp, File csvFile, Class<T> modelClass) {
		try {
		List<T> modelArray = new ArrayList<>();
		
		CsvMapper mapper = new CsvMapper();
		CsvSchema schema = CsvSchema.builder()
				.setNullValue("")
				.build().withHeader();
		MappingIterator<Map<String, String>> it;
			it = mapper.readerFor(Map.class)
					.with(schema)
					.readValues(csvFile);
	
		while (it.hasNext()) {
			T model = modelClass.newInstance();
			modelArray.add(model);
			Map<String,String> rowAsMap = it.next();
			Map<String, String> beanMap = new HashMap<String, String>();
			for(String key: convertProp.keySet().toArray(new String[0])) {
				beanMap.put(convertProp.getProperty(key), rowAsMap.get(key));
			}
			BeanWrapper wBean = new BeanWrapperImpl(model); 
			wBean.setAutoGrowNestedPaths(true);
			wBean.registerCustomEditor(java.util.Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
			wBean.setPropertyValues(beanMap);
		}
		return modelArray;
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
