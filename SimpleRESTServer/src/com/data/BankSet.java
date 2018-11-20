package com.data;

import java.util.Comparator;
import java.util.Set;

public class BankSet extends DataObjectSet {

	public DataObject findByName(String name) throws Exception {
		String curname = name.trim().toLowerCase();
		DataObject foundedBank = null;
		
		for(DataObject tempBank : this.recordList.values()) {
			if(tempBank.getValue("name").toString().equalsIgnoreCase(curname)) {
				foundedBank = tempBank;
			}
		}
		
		if(foundedBank == null) {
			throw new Exception("Bank not found!");
		}
		
		return foundedBank;
	}
	
	@Override
	protected DataObject getNewDataObject() {
		Set<Long> keyset = recordList.keySet();
		long uid = keyset.stream().max(Comparator.naturalOrder()).orElse(0L);
		DataObject newDataObject = new Bank(++uid);
		return newDataObject;
	}
	
}
