package com.data;

public class Account extends DataObject {
	
	Account(Long uid){
		super(uid);
	}
	
	public void setNewBalance(double diff) throws Exception {
		
		if(diff < 0 && this.getDoubleValue("balance") < Math.abs(diff)) {
			throw new Exception("Not enough money!");
		}
		
		double newBalance = this.getDoubleValue("balance") + diff;
		this.setValue("balance", newBalance);
		
	}

}
