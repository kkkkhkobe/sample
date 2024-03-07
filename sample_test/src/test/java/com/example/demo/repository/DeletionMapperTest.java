package com.example.demo.repository;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.demo.entity.ItemEntity;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DeletionMapperTest {

	@Autowired
	DeletionMapper mapper;

	@Autowired
	JdbcTemplate template;

	/**
	 * 論理削除商品の一覧表示
	 */
	@Test
	void testFindAll() {
		List<ItemEntity> list = mapper.findAll();
		assertNotNull(list);
		assertEquals(1, list.size());
	}

	/**
	 * 論理削除商品の復元
	 */
	@Test
	void testUpdate1_1() {
		String id = "1";
		mapper.update(id);

		List<ItemEntity> list = mapper.findAll();

		assertEquals(0, list.size());
	}

	/**
	 * materialテーブルのmaterial_nameの変更
	 */
	@Test
	void testItem_update1_1() {
		Integer id = 1;
		String name = "塚田";
		mapper.item_update(id, name);

		String sql = "";
		sql += "SELECT material_name FROM material WHERE material_id = " + id;
		List<ItemEntity> list = template.query(sql, new BeanPropertyRowMapper<ItemEntity>(ItemEntity.class));

		assertEquals(name, list.get(0).getMaterial_name());
	}

	/**
	 * productテーブルの価格、在庫の変更
	 */
	@Test
	void testItem_update_stock1_1() {
		Integer id = 1;
		String price = "1500";
		String stock = "5";
		mapper.item_update_stock(id, price, stock);

		String sql = "";
		sql += "SELECT regular_price, material_stock FROM product WHERE material_id = " + id;
		List<ItemEntity> list = template.query(sql, new BeanPropertyRowMapper<ItemEntity>(ItemEntity.class));

		assertEquals(price, list.get(0).getRegular_price());
		assertEquals(stock, list.get(0).getMaterial_stock());
	}

	/**
	 * 論理削除の商品を物理削除
	 */
	@Test
	void testDelete1_1() {
		Integer id = 1;
		mapper.delete(id);

		String sql = "";
		sql += "SELECT m.material_id FROM material AS m "
				+ "RIGHT OUTER JOIN	product AS p ON m.material_id = p.material_id "
				+ "WHERE m.material_id = " + id;
		List<ItemEntity> list = template.query(sql, new BeanPropertyRowMapper<>(ItemEntity.class));
		assertThat(list.isEmpty());
	}

}
