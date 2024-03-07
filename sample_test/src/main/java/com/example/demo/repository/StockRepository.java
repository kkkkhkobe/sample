package com.example.demo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.form.ItemRegisterForm;
/*
 * クラス名 :StockRepository
 * 
 * 作成日　:2024/02/28
 * 作成者　:塚田晃介
 * 最終更新日　:2024/02/28
 * 最終更新日　:塚田晃介
 * 
 * Copyright(C) AIOSL Technology All Rights Reserved.
 */

@Repository
public class StockRepository {

	@Autowired
	JdbcTemplate template;

	public void insert(ItemRegisterForm form) {
		String sql = "";
		sql += "INSERT INTO product (material_id, material_stock, regular_price) "
				+ "SELECT MAX(material.material_id), ?, ? FROM material";
		template.update(sql, form.getMaterial_stock(), form.getRegular_price());

	}
}
