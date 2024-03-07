package com.example.demo.repository;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.ItemEntity;
import com.example.demo.form.ItemForm;
import com.example.demo.form.ItemRegisterForm;

@SpringBootTest
@Transactional
class ItemRepositoryTest {

	@Autowired
	ItemRepository repository;

	@Autowired
	JdbcTemplate template;

	ItemForm form = new ItemForm();

	//全検索数

	@Test
	void testSearch1_1() {
		List<ItemEntity> list = repository.search(form);

		assertEquals(2, list.size());
	}

	//ID検索

	@Test
	void testSearch1_2() {
		form.setMaterial_id("2");
		List<ItemEntity> list = repository.search(form);

		assertEquals(form.getMaterial_id(), list.get(0).getMaterial_id());
	}

	//該当しない検索条件

	@Test
	void testSearch1_3() {
		form.setMaterial_id("塚田");
		List<ItemEntity> list = repository.search(form);

		assertThat(list.isEmpty());
	}

	//ID検索

	@Test
	void testFind1_1() {
		Integer id = 1;
		List<ItemEntity> list = repository.find(id);

		assertEquals(1, list.size());
	}

	//登録後、一行増加

	@Test
	void testInsert1_1() {
		ItemRegisterForm form = new ItemRegisterForm();
		form.setMaterial_name("轟金剛");
		form.setMaterial_text("");
		repository.insert(form);
		int actual = JdbcTestUtils.countRowsInTable(template, "material");

		assertEquals(4, actual);
	}

	//指定したIDのデリートフラグが変更されているか

	@Test
	void testUpdate1_1() {
		Integer id = 1;
		String flg = "1";
		repository.update(id);

		String sql = "SELECT product.deleted_flg FROM product WHERE material_id = ";
		sql += id;
		List<ItemEntity> list = template.query(sql, new BeanPropertyRowMapper<ItemEntity>(ItemEntity.class));

		assertEquals(flg, list.get(0).getDeleted_flg());
	}
}
