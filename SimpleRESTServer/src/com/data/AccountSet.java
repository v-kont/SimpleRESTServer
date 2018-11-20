package com.data;

import java.util.Comparator;
import java.util.Set;

public class AccountSet extends DataObjectSet {

	public DataObject findByCard(String cardNumber) throws Exception {
		String cardnum = cardNumber.trim().toLowerCase();
		
		for(DataObject tempAccount : this.recordList.values()) {
			if(tempAccount != null && tempAccount.getStringValue("cardnum").equalsIgnoreCase(cardnum)) {
				return tempAccount;
			}
		}
		
		return null;
	}
	
	public DataObject findByPhone(String phoneNumber) throws Exception {
		String phonenum = phoneNumber.trim().toLowerCase();
		
		for(DataObject tempAccount : this.recordList.values()) {
			if(tempAccount != null &&tempAccount.getValue("phone").toString().equalsIgnoreCase(phonenum)) {
				return tempAccount;
			}
		}
		
		return null;
	}
	
	@Override
	protected DataObject getNewDataObject() {
		Set<Long> keyset = recordList.keySet();
		long uid = keyset.stream().max(Comparator.naturalOrder()).orElse(0L);
		DataObject newDataObject = new Account(++uid);
		return newDataObject;
	}
}
