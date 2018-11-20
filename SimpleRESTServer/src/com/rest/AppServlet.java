package com.rest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.data.Account;
import com.data.DataObject;
import com.data.DataStore;

/**
 * Servlet implementation class AppServlet
 */
@WebServlet("/AppServlet")
public class AppServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private DataStore dataStore;
	private boolean error;
	private String answer;
	
	AppServlet(){
		this.answer = "";
		
		try {
			dataStore = DataStore.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.error = false;
		this.answer = "";
		
		String action = request.getParameter("action");
		String srccard = request.getParameter("srccard");
		String srcphone = request.getParameter("srcphone");
		String dstcard = request.getParameter("dstcard");
		String dstphone = request.getParameter("dstphone");
		String sum = request.getParameter("sum");
		String msg = request.getParameter("msg");
		
		if(action != null) {
			String curaction = action.trim();
			if(curaction.equalsIgnoreCase("transfer")) {
				makeTransfer(srccard, srcphone, dstcard, dstphone, sum, msg);
			} else if(curaction.equalsIgnoreCase("query")) {
				this.answer = queryData();
			} else {
				this.error = true;
				this.answer = "Action is not set";
			}
		} else {
			this.error = true;
			this.answer = "Action is not set";
		}
		
		String reply = "";
		if(action.trim().equalsIgnoreCase("query")) {
			reply = this.answer;
		} else {
			reply = "{\"error\":" + (this.error?"1":"0") +",\"result\":\"" + (this.answer.isEmpty()?"OK":this.answer) + "\"}";
		}
		
		setResult(response, reply);
	}
	
	private void makeTransfer(String srccard, String srcphone, String dstcard, String dstphone, String sum, String message) {
		double value_sum = 0;
		if(!this.error) {
			try {
				value_sum = Double.parseDouble(sum);
			} catch (Exception ex) {
				this.error = true;
				this.answer = "Sum is invalid";
			}
		}
		
		DataObject srcAccount = null;
		DataObject dstAccount = null;
		String srccardVal = srccard != null?srccard.trim():"";
		String srcphoneVal = srcphone != null?srcphone.trim():"";
		String dstcardVal = dstcard != null?dstcard.trim():"";
		String dstphoneVal = dstphone != null?dstphone.trim():"";
		
		if(!this.error) {
			try {
				if(!srccardVal.isEmpty()) {
					srcAccount = this.dataStore.findAccountByCard(srccardVal);
				}
				
				if(srcAccount == null && !srcphoneVal.isEmpty()) {
					srcAccount = this.dataStore.findAccountByPhone(srcphoneVal);
				}
				
				if(srcAccount == null) {
					this.error = true;
					this.answer = "Source account is not found!";
				}
			} catch(Exception e) {
				this.error = true;
				this.answer = e.getMessage();
			}
		}
		
		if(!this.error) {
			try {
				if(!dstcardVal.isEmpty()) {
					dstAccount = this.dataStore.findAccountByCard(dstcardVal);
				}
				
				if(dstAccount == null && !dstphoneVal.isEmpty()) {
					dstAccount = this.dataStore.findAccountByPhone(dstphoneVal);
				}
				
				if(dstAccount == null) {
					this.error = true;
					this.answer = "Destination account is not found!";
				}
			} catch(Exception e) {
				this.error = true;
				this.answer = e.getMessage();
			}
		}
		
		try {
			if(!this.error) {
				this.dataStore.createNewTransaction((Account)srcAccount, (Account)dstAccount, value_sum, message);
			}
		}catch(Exception e) {
			this.error = true;
			this.answer = e.getMessage();
		}
		
	}
	
	private String queryData() {
		return this.dataStore.queryData();
	}
	
	protected void setResult(HttpServletResponse response, String reply) throws UnsupportedEncodingException, IOException {
		response.getOutputStream().write(reply.getBytes("UTF-8"));
		response.setContentType("application/json; charset=UTF-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setStatus(HttpServletResponse.SC_OK);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
