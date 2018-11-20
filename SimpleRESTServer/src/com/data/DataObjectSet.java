package com.data;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class DataObjectSet {

	protected Map<Long, DataObject> recordList;
	
	DataObjectSet(){
		recordList = new HashMap<>();
	}
	
	public DataObject add() {
		DataObject newDataObject = getNewDataObject();
		this.recordList.put(newDataObject.getUID(), newDataObject);
		return newDataObject;
	}
	
	protected DataObject getNewDataObject() {
		Set<Long> keyset = recordList.keySet();
		long uid = keyset.stream().max(Comparator.naturalOrder()).orElse(0L);
		DataObject newDataObject = new DataObject(++uid);
		return newDataObject;
	}
	
	public String queryData() {
		Iterator<Long> iterator = this.recordList.keySet().iterator();
		String valueList = "";
		
		while(iterator.hasNext()) {
			long recorduid = iterator.next();
			if(!valueList.isEmpty()) {
		    	valueList = valueList.concat(", ");
		    }
		    valueList = valueList.concat(this.recordList.get(recorduid).queryData());
		}
		
		return "[".concat(valueList).concat("]");
	}
	
}
