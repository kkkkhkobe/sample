package com.example.demo.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.UserEntity;
import com.example.demo.form.UserForm;

@SpringBootTest
class SearchServiceTest {

	@Autowired
	SearchService service;

	//ID検索の該当する場合

	@Test
	void testList1_1() {
		UserForm form = new UserForm();
		form.setUser_id("1");

		List<UserEntity> list = service.list(form);

		assertEquals("1", list.get(0).getUser_id());
	}

	//名前検索の該当する場合

	@Test
	void testList1_2() {
		UserForm form = new UserForm();
		form.setUser_name("塚田");

		List<UserEntity> list = service.list(form);

		assertEquals("塚田", list.get(0).getUser_name());
	}

	//住所検索の該当する場合

	@Test
	void testList1_3() {
		UserForm form = new UserForm();
		form.setUser_address("兵庫");

		List<UserEntity> list = service.list(form);

		assertEquals("兵庫", list.get(0).getUser_address());
	}

	//年齢検索の該当する場合

	@Test
	void testList1_4() {
		UserForm form = new UserForm();
		form.setUser_age("28");

		List<UserEntity> list = service.list(form);

		assertEquals("28", list.get(0).getUser_age());
	}

	//性別検索の該当する場合

	@Test
	void testList1_5() {
		UserForm form = new UserForm();
		form.setGender("男性");

		List<UserEntity> list = service.list(form);

		assertEquals("男性", list.get(0).getGender());
	}

	//ID検索結果が無い場合

	@Test
	void testList2_1() {
		UserForm form = new UserForm();
		form.setUser_id("5");

		List<UserEntity> list = service.list(form);

		assertThat(list.isEmpty());
	}

}
