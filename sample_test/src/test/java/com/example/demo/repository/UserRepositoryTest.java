package com.example.demo.repository;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.UserEntity;
import com.example.demo.form.UserForm;
import com.example.demo.form.UserInsertForm;

@SpringBootTest
@Transactional
class UserRepositoryTest {

	@Autowired
	UserRepository repository;

	@Autowired
	JdbcTemplate template;

	UserForm form = new UserForm();
	
	//顧客の全件検索

	@Test
	void testSearch1_1() {
		List<UserEntity> list = repository.search(form);

		assertEquals(2, list.size());
	}
	
	//顧客のiD指定の条件検索

	@Test
	void testSearch1_2() {
		form.setUser_id("1");
		List<UserEntity> list = repository.search(form);

		assertEquals(1, list.size());
		assertEquals(form.getUser_id(), list.get(0).getUser_id());
	}
	
	//顧客のid検索で条件に該当しない

	@Test
	void testSearch1_3() {
		form.setUser_id("500");
		List<UserEntity> list = repository.search(form);

		assertThat(list.isEmpty());
	}
	
	//顧客新規登録の確認テスト

	@Test
	void testInsert1_1() {
		UserInsertForm form = new UserInsertForm();
		form.setUser_name("aaa");
		repository.insert(form);
		int actual = JdbcTestUtils.countRowsInTable(template, "user_table");

		assertEquals(3, actual);
	}

}
