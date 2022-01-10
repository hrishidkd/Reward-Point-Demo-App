package com.app.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.demo.model.Bills;

public interface BillsImpl extends JpaRepository<Bills, Integer> {

}
