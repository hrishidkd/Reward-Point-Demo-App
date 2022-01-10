package com.app.demo.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Bills {

	@Id
	private int billId;
	private int userId;
	private String userName;
	private Date date;
	private float amount;

	public int getBillId() {
		return billId;
	}

	public void setBillId(int billId) {
		this.billId = billId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Bills [billId=" + billId + ", userId=" + userId + ", userName=" + userName + ", date=" + date
				+ ", amount=" + amount + "]";
	}

}
