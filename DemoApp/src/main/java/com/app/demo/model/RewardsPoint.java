package com.app.demo.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RewardsPoint {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int rewardId;
	private int userId;
	private float rewardPoints;
	private Date date;

	public RewardsPoint() {
		super();
	}

	public RewardsPoint(int userId, float rewardPoints, Date date) {
		super();
		this.userId = userId;
		this.rewardPoints = rewardPoints;
		this.date = date;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public float getRewardPoints() {
		return rewardPoints;
	}

	public void setRewardPoints(float rewardPoints) {
		this.rewardPoints = rewardPoints;
	}

	@Override
	public String toString() {
		return "RewardsPoint [userId=" + userId + ", rewardPoints=" + rewardPoints + "]";
	}

}
