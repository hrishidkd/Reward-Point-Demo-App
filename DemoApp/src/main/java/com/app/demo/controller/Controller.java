package com.app.demo.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.demo.dao.BillsImpl;
import com.app.demo.dao.RewardsImpl;
import com.app.demo.dao.UserImpl;
import com.app.demo.model.Bills;
import com.app.demo.model.RewardsPoint;
import com.app.demo.model.User;

@RestController
public class Controller {

	private static final Logger logger = LoggerFactory.getLogger(Controller.class);

	@Autowired
	UserImpl userRepo;
	@Autowired
	RewardsImpl rewardsRepo;
	@Autowired
	BillsImpl billRepo;

	@RequestMapping("/AddBill")
	@PostMapping
	public String addNewBill(Bills bill, HttpServletResponse response) {

		logger.info("Inside addNewBill with obj: " + bill.toString());
		if (billRepo.existsById(bill.getBillId())) {
			response.setStatus(512, "Duplicate Id");
			logger.info("Duplicate Bill Id.");
			return "Error... Duplicate Bill Id.";

		}
		try {
			if (userRepo.existsById(bill.getUserId())) {
				billRepo.save(bill);
			} else {
				addUser(new User(bill.getUserId(), bill.getUserName()));
				billRepo.save(bill);
			}
			updateRewardPoint(bill.getAmount(), bill.getUserId(), bill.getDate());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			response.setStatus(512, "Error while saving Bill.");
			return "Error while saving Bill.";
		}
		return "Bill added Successfully.";
	}

	@RequestMapping("/getAllPointsForUser")
	@GetMapping
	public float getAllRewardPointForUser(@RequestParam("userId") int userId, HttpServletResponse response) {
		float rewardPoint = 0;
		logger.info("Inside getAllRewardPointForUser with userId: " + userId);
		try {
			rewardPoint = rewardsRepo.getAllRewardPointsById(userId);
		} catch (Exception e) {
			response.setStatus(512, e.getMessage());
			logger.error(e.getMessage());
			e.printStackTrace();

		}
		return rewardPoint;
	}

	@RequestMapping("/getAllPointsForUserByMonth")
	@GetMapping
	public float getAllRewardPointForUserByMonth(int userId, int year, int month, HttpServletResponse response) {
		float rewardPoint = 0;
		logger.info("Inside getAllRewardPointForUserByMonth with user Id : " + userId + " year: " + year + " month: "
				+ month);
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.set(year, month-1, 1);
			java.util.Date utilDate = calendar.getTime();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String stringDate = dateFormat.format(utilDate);
			final java.sql.Date startDate = java.sql.Date.valueOf(stringDate);
			System.out.println("start date : " + startDate);
		
			if (month == 12) {
				year++;
				month = 0;
			}
			calendar.set(year, month, 1);
			utilDate = calendar.getTime();
			stringDate = dateFormat.format(utilDate);
			final java.sql.Date endDate = java.sql.Date.valueOf(stringDate);
			
			System.out.println("End date : " + endDate);
			rewardPoint = rewardsRepo.getAllRewardsPointByMonthAndYear(userId, startDate, endDate);
			response.setStatus(200, "Status OK.");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			response.setStatus(500, "Internal Server Error.");
		}

		return rewardPoint;
	}

	public void updateRewardPoint(float amount, int userId, Date date) {
		logger.info("Inside updateRewardPoint with amount : " + amount + " userId: " + userId + " date: " + date);
		float reward = 0;
		if (amount < 100 && amount > 50) {
			reward = ((amount - 50) * 1);
		} else {
			reward = ((amount - 100) * 2) + 50;
		}

		logger.info("Calculated reward points : " + reward);
		rewardsRepo.save(new RewardsPoint(userId, reward, date));
		System.out.println(rewardsRepo.getAllRewardPointsById(userId));
	}

	public void addUser(User user) {
		logger.info("Inside addUser with user obj: " + user);
		try {
			userRepo.save(user);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
	}
}
