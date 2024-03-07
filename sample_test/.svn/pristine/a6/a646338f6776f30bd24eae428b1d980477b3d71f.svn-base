package com.example.demo.controller;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.ItemEntity;
import com.example.demo.form.ItemForm;
import com.example.demo.form.UserForm;
import com.example.demo.repository.DeletioinRepository;

@SpringBootTest
@Transactional
class MenuControllerTest {

	@MockBean
	DeletioinRepository repository;

	@Autowired
	MenuController controller;

	MockMvc mockMvc;

	@BeforeEach
	void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	/**
	 * 画面遷移の検証
	 * httpステータスの検証
	 * @throws Exception
	 */

	@Test
	void testMenu1_1() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/"))
				.andExpect(status().isOk())
				.andExpect(view().name("test"));
	}

	/**
	 * modelの検証
	 * 顧客検索画面遷移の検証
	 * httpステータスの検証
	 * @throws Exception
	 */

	@Test
	void testSearch1_1() throws Exception {
		UserForm form = new UserForm();

		mockMvc.perform(get("/user"))
				.andExpect(model().attribute("search", form))
				.andExpect(status().isOk())
				.andExpect(view().name("user.html"));
	}

	/**
	 * modelの検証
	 * 顧客登録画面遷移の検証
	 * httpステータスの検証
	 * @throws Exception
	 */

	@Test
	void testInsert1_1() throws Exception {
		UserForm form = new UserForm();

		mockMvc.perform(get("/insert"))
				.andExpect(model().attribute("insert", form))
				.andExpect(status().isOk())
				.andExpect(view().name("insert.html"));

	}

	/**
	 * modelの検証
	 * 商品検索画面遷移の検証
	 * httpステータスの検証
	 * @throws Exception
	 */

	@Test
	void testItem1_1() throws Exception {
		ItemForm form = new ItemForm();

		mockMvc.perform(get("/item"))
				.andExpect(model().attribute("form", form))
				.andExpect(status().isOk())
				.andExpect(view().name("item.html"));

	}

	/**
	 * modelの検証
	 * 商品登録画面遷移の検証
	 * httpステータス　正常テスト
	 * @throws Exception
	 */

	@Test
	void testRegi1_1() throws Exception {
		ItemForm form = new ItemForm();

		mockMvc.perform(get("/item_regi"))
				.andExpect(model().attribute("form", form))
				.andExpect(status().isOk())
				.andExpect(view().name("register"));

	}

	/**
	 * modelの検証
	 * 削除商品一覧画面遷移　正常テスト
	 * @throws Exception
	 */

	@Test
	void testDel1_1() throws Exception {
		mockMvc.perform(get("/item_delete"))
				.andExpect(status().isOk())
				.andExpect(view().name("deletion"));

	}

	/**
	 * modelの検証
	 * 削除商品一覧の画面遷移の検証
	 * @throws Exception
	 */

	@Test
	void testDel1_2() throws Exception {
		List<ItemEntity> list = repository.search();
		assertThat(list.isEmpty());

		mockMvc.perform(get("/item_delete"))
				.andExpect(model().attribute("delete_item", true))
				.andExpect(status().isOk())
				.andExpect(view().name("deletion"));

	}

}
