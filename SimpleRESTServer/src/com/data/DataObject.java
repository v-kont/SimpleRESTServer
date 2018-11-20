package com.data;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DataObject {

	private Map<String, Object> recordData = new HashMap<>();
	
	public DataObject() {
		
	}
	
	protected DataObject(Long uid){
		this.recordData.put("uid", uid);
	}
	
	public long getUID() {
		return Long.valueOf(this.recordData.get("uid").toString());
	}
	
	public void setValue(String attributename, Object value) throws Exception{
		String attr = attributename.trim().toLowerCase();
		
		if(attr.equalsIgnoreCase("uid")) {
			throw new Exception("uid can't be changed!");
		}
		
		this.recordData.put(attr, value);
	}
	
	public Object getValue(String attributename) {
		return this.recordData.get(attributename.trim().toLowerCase());
	}
	
	public String getStringValue(String attributename) {
		return this.recordData.get(attributename.trim().toLowerCase()).toString();
	}
	
	public double getDoubleValue(String attributename) {
		return Double.parseDouble(getStringValue(attributename.trim().toLowerCase()));
	}
	
	public String queryData() {
		Iterator<String> iterator = this.recordData.keySet().iterator();
		String valueList = "";
		String attributename = "";
		String value = "";
		
		while(iterator.hasNext()) {
			
			attributename = iterator.next();
			value = getStringValue(attributename);
			
			if(!valueList.isEmpty()) {
		    	valueList = valueList.concat(", ");
		    }
		    valueList = valueList.concat("\"" + attributename + "\":\"" + value + "\"");
		}
		
		return "{".concat(valueList).concat("}");
	}
	
}
