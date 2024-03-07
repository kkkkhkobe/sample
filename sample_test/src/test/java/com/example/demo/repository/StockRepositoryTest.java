package com.example.demo.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.form.ItemRegisterForm;

@SpringBootTest
@Transactional
class StockRepositoryTest {

	@Autowired
	StockRepository repository;

	@Autowired
	JdbcTemplate template;

	//productテーブル登録のチェック

	@Test
	void testInsert1_1() {
		String name = "aaa";
		String text = "";

		String sql = "";
		sql += "INSERT INTO material (material_name, material_text) "
				+ "VALUES( ?, ?) ";
		template.update(sql, name, text);

		ItemRegisterForm form = new ItemRegisterForm();
		form.setMaterial_stock("5");
		form.setRegular_price("1500");
		repository.insert(form);

		int result = JdbcTestUtils.countRowsInTable(template, "product");

		assertEquals(4, result);

	}

}
