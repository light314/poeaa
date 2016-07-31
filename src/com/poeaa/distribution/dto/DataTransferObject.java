package com.poeaa.distribution.dto;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class DataTransferObject {
	
	public Map writeMapReflect() throws Exception {
		Map result = null;
		try {
			Field[] fields = this.getClass().getDeclaredFields();
			result = new HashMap();
			for (int i = 0; i<fields.length; i++){
				result.put(fields[i].getName(), fields[i].get(this));
			}
		} catch (Exception e){		throw (new Exception(e)); }
		return result;
	}
	
}
