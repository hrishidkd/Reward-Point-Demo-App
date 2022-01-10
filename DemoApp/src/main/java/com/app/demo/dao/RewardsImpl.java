package com.app.demo.dao;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.demo.model.RewardsPoint;

public interface RewardsImpl extends JpaRepository<RewardsPoint, Integer> {

	@Query("Select SUM(rewardPoints) from RewardsPoint rp where rp.userId=:id")
	float getAllRewardPointsById(@Param("id") int id);

	@Query("SELECT SUM(rewardPoints) from RewardsPoint rp where rp.userId=:id and date >= :startDate and date < :endDate")
	float getAllRewardsPointByMonthAndYear(@Param("id") int id, @Param("startDate") Date startDate,
			@Param("endDate") Date endDate);
}
