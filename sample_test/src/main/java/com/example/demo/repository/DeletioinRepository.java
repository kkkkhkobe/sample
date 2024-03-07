package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.ItemEntity;
import com.example.demo.form.ItemRegisterForm;

@Repository
public class DeletioinRepository {

	@Autowired
	JdbcTemplate template;

	public List<ItemEntity> search() {
		String sql = "";
		sql += "SELECT m.material_id, m.material_name, p.regular_price, p.material_stock "
				+ "		FROM material AS m RIGHT OUTER JOIN product AS p ON "
				+ "		m.material_id = p.material_id WHERE deleted_flg = 1 ";

		List<ItemEntity> list = template.query(sql, new BeanPropertyRowMapper<ItemEntity>(ItemEntity.class));
		return list;
	}

	public void update(String id) {
		String sql = "";
		sql += "UPDATE product SET deleted_flg = 0 WHERE material_id = ?";

		template.update(sql, id);
	}

	public void item_update(String id, ItemRegisterForm form) {
		String sql = "";
		sql += "UPDATE material SET material_name = ? WHERE material_id = ?";
		template.update(sql, form.getMaterial_name(), id);
	}

	public void item_update_stock(String id, ItemRegisterForm form) {
		String sql = "";
		sql += "UPDATE product SET regular_price = ? , material_stock = ? WHERE material_id = ?";

		template.update(sql, form.getRegular_price(), form.getMaterial_stock(), id);
	}

	public void delete(Integer id) {
		String sql = "";
		sql += "DELETE material,product FROM material RIGHT OUTER JOIN product "
				+ "ON material.material_id = product.material_id WHERE material.material_id = ?";

		template.update(sql, id);
	}

}
