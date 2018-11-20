package com.data;

public class DataStore {
	
	private static DataStore _instance = null;
	private BankSet bankList;
	private AccountSet accountList;
	private TransactionSet transactionList;
	
	DataStore() throws Exception{
		bankList = new BankSet();
		accountList = new AccountSet();
		transactionList = new TransactionSet();
		fillData();
	}
	
	public static synchronized DataStore getInstance() throws Exception {
        if (_instance == null)
            _instance = new DataStore();
        return _instance;
}

	private void fillData() throws Exception {
		fillBankList();
		fillAccountList();
	}
	
	private void fillBankList() throws Exception {
		DataObject newBank1 = this.bankList.add();
		newBank1.setValue("name", "SberBank");
		newBank1.setValue("code", "123456789");
		newBank1.setValue("correspondent_account", "12345678901011121314");
		
		DataObject newBank2 = this.bankList.add();
		newBank2.setValue("name", "CitiBank");
		newBank2.setValue("code", "987654321");
		newBank2.setValue("correspondent_account", "14131211100987654321");
	}
	
	private void fillAccountList() throws Exception {
		DataObject account1 = this.accountList.add();
		account1.setValue("number", "123456789010111213141");
		account1.setValue("cardnum", "4277600104642371");
		account1.setValue("lastname", "Ivanov");
		account1.setValue("firstname", "Ivan");
		account1.setValue("middlename", "Ivanovich");
		account1.setValue("inn", "123456789010");
		account1.setValue("address", "SPb, Kalinina st., 10");
		account1.setValue("bankid", this.bankList.findByName("SberBank").getUID());
		account1.setValue("phone", "123456780");
		account1.setValue("balance", 1000L);
		
		DataObject account2 = this.accountList.add();
		account2.setValue("number", "123456789010111213142");
		account2.setValue("cardnum", "4277600104642372");
		account2.setValue("lastname", "Petrov");
		account2.setValue("firstname", "Petr");
		account2.setValue("middlename", "Petrovich");
		account2.setValue("inn", "123456789011");
		account2.setValue("address", "SPb, Kalinina st., 11");
		account2.setValue("bankid", this.bankList.findByName("CitiBank").getUID());
		account2.setValue("phone", "123456781");
		account2.setValue("balance", 1000L);
		
		DataObject account3 = this.accountList.add();
		account3.setValue("number", "123456789010111213143");
		account3.setValue("cardnum", "4277600104642373");
		account3.setValue("lastname", "Sergeev");
		account3.setValue("firstname", "Sergey");
		account3.setValue("middlename", "Sergeevich");
		account3.setValue("inn", "123456789012");
		account3.setValue("address", "SPb, Kalinina st., 12");
		account3.setValue("bankid", this.bankList.findByName("SberBank").getUID());
		account3.setValue("phone", "123456782");
		account3.setValue("balance", 1000L);
	}
	
	public DataObject findAccountByCard(String cardNumber) throws Exception {
		return this.accountList.findByCard(cardNumber);
	}
	
	public DataObject findAccountByPhone(String phoneNumber) throws Exception {
		return this.accountList.findByPhone(phoneNumber);
	}
	
	public DataObject findBankByName(String name) throws Exception {
		return this.bankList.findByName(name);
	}
	
	public DataObject createNewTransaction(Account src, Account dst, double sum, String msg) throws Exception {
		 return this.transactionList.add(src, dst, sum, msg);
	}
	
	public String queryData() {
		String valueList = "";
		valueList = valueList.concat("{\"bankList\":" + this.bankList.queryData());
		valueList = valueList.concat(", \"accountList\":" + this.accountList.queryData());
		valueList = valueList.concat(", \"transactionList\":" + this.transactionList.queryData());
		return valueList.concat("}");
	}
	
}
