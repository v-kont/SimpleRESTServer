package com.data;

public class Bank extends DataObject {

	Bank(Long uid) {
		super(uid);
	}
	
	@Override
	public void setValue(String attributename, Object value) throws Exception {
		String attr = attributename.trim().toLowerCase();
		String val = value.toString().trim().toLowerCase();
		
		if(attr.equalsIgnoreCase("code")
				&& !val.matches("[0-9]{9}")) {
			throw new Exception("Bank code is not valid: it can contains 9 numbers only");
		} else if(attr.equalsIgnoreCase("correspondent_account")
				&& !val.matches("[0-9]{20}")) {
			throw new Exception("Bank correspondent account is not valid: it can contains 20 numbers only");
		}
		
		super.setValue(attr, val);
	}
	
}
