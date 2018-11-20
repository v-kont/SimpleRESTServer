package com.data;

import java.util.Comparator;
import java.util.Set;

public class TransactionSet extends DataObjectSet {

	@Override
	protected DataObject getNewDataObject() {
		Set<Long> keyset = recordList.keySet();
		long uid = keyset.stream().max(Comparator.naturalOrder()).orElse(0L);
		DataObject newDataObject = new Transaction(++uid);
		return newDataObject;
	}
	
	public DataObject add(Account src, Account dst, double sum, String msg) throws Exception {
		
		if(src == null
				|| dst == null
				|| sum <= 0) {
			throw new Exception("Parameters for transaction are incorrect!");
		}
		
		DataObject newDataObject = getNewDataObject();
		newDataObject.setValue("sourceAccount", src.getUID());
		newDataObject.setValue("destinationAccount", dst.getUID());
		newDataObject.setValue("sum", sum);
		newDataObject.setValue("message", msg);
		
		src.setNewBalance(-sum);
		dst.setNewBalance(sum);
		
		this.recordList.put(newDataObject.getUID(), newDataObject);
		return newDataObject;
	}
	
}
