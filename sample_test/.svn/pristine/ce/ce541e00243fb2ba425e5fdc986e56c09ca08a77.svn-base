package com.example.demo.repository;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.ItemEntity;
import com.example.demo.form.ItemForm;
import com.example.demo.form.ItemRegisterForm;

@SpringBootTest
@Transactional
class DeletioinRepositoryTest {

	@Autowired
	DeletioinRepository repository;

	@Autowired
	JdbcTemplate template;

	ItemForm form = new ItemForm();

	ItemRegisterForm form1 = new ItemRegisterForm();

	/**
	 * 論理削除している商品一覧
	 */
	@Test
	void testSearch1_1() {
		List<ItemEntity> list = repository.search();
		assertNotNull(list);
		assertEquals(1, list.size());
	}

	/**
	 * 論理削除している商品を再表示
	 */

	@Test
	void testUpdate1_1() {
		String id = "1";
		repository.update(id);

		String sql = "";
		sql += "SELECT material_id, deleted_flg FROM product WHERE material_id = " + id;
		List<ItemEntity> list = template.query(sql, new BeanPropertyRowMapper<ItemEntity>(ItemEntity.class));
		assertEquals(id, list.get(0).getMaterial_id());
		assertEquals("0", list.get(0).getDeleted_flg());

	}

	/**
	 * materialテーブルのmaterial_nameの変更
	 */
	@Test
	void testItem_update1_1() {

		String id = "2";
		form1.setMaterial_name("aaa");
		repository.item_update(id, form1);

		String sql = "";
		sql += "SELECT material_id, material_name FROM material WHERE material_id = " + id;
		List<ItemEntity> list = template.query(sql, new BeanPropertyRowMapper<>(ItemEntity.class));

		assertEquals(form1.getMaterial_name(), list.get(0).getMaterial_name());

	}

	/**
	 * productテーブルの価格、在庫の変更
	 */
	@Test
	void testItem_update_stock1_1() {
		String id = "2";
		form1.setRegular_price("1000");
		form1.setMaterial_stock("5");

		repository.item_update_stock(id, form1);

		String sql = "";
		sql += "SELECT regular_price, material_stock FROM product "
				+ "WHERE material_id = " + id;
		List<ItemEntity> list = template.query(sql, new BeanPropertyRowMapper<>(ItemEntity.class));
		assertEquals(form1.getMaterial_stock(), list.get(0).getMaterial_stock());
		assertEquals(form1.getRegular_price(), list.get(0).getRegular_price());

	}

	/**
	 * 論理削除の商品を物理削除
	 */
	@Test
	void testDelete1_1() {
		Integer id = 1;
		repository.delete(id);

		String sql = "";
		sql += "SELECT * FROM material WHERE material_id = " + id;
		List<ItemEntity> list = template.query(sql, new BeanPropertyRowMapper<>(ItemEntity.class));

		assertThat(list.isEmpty());
	}

}
