
package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;

import com.example.demo.form.UserInsertForm;
import com.example.demo.repository.UserRepository;

@SpringBootTest
class UserInsertControllerTest {

	@MockBean
	UserRepository repository;

	@Autowired
	UserInsertController controller;

	@Autowired
	JdbcTemplate template;

	MockMvc mock;

	UserInsertForm form = new UserInsertForm();

	@BeforeEach
	void setup() {
		mock = MockMvcBuilders.standaloneSetup(controller).build();

	}

	/**
	 * validationの検証
	 * modelの検証
	 * httpステータス検証
	 * 完了画面へ遷移の検証
	 * リポジトリの呼び出し回数
	 * @throws Exception
	 */

	@Test
	void testInsert1_1() throws Exception {
		form.setUser_name("Cくん");

		mock.perform(get("/insert1").param("user_name", "Cくん"))
				.andExpect(model().hasNoErrors())
				.andExpect(model().attribute("userInsertForm", form))
				.andExpect(status().isOk())
				.andExpect(view().name("message"));

		verify(repository, times(1)).insert(form);

	}

	/**
	 * 顧客名を異常値で検証
	 * modelの検証
	 * httpステータスの検証
	 * 顧客登録画面遷移の検証
	 * 該当するエラーメッセージの検証
	 */

	@Test
	void testInsert1_2() throws Exception {
		ResultActions results = mock.perform(get("/insert1").param("user_name", ""))
				.andExpect(model().attributeHasErrors("userInsertForm"))
				.andExpect(model().errorCount(1))
				.andExpect(model().attribute("insert", form))
				.andExpect(status().isOk())
				.andExpect(view().name("insert"));

		BindingResult bindResult = (BindingResult) results.andReturn().getModelAndView().getModel()
				.get(BindingResult.MODEL_KEY_PREFIX + "userInsertForm");
		String mes = bindResult.getFieldError().getDefaultMessage();
		assertEquals("・顧客名は必須項目です。", mes);

	}
	
	/**
	 * 年齢を異常値で検証
	 * modelの検証
	 * httpステータスの検証
	 * 顧客登録画面遷移の検証
	 * 該当するエラーメッセージの検証
	 * 
	 * @throws Exception
	 */

	@Test
	void testInsert1_3() throws Exception {
		form.setUser_name("a");
		form.setUser_age("aaa");

		ResultActions result = mock.perform(get("/insert1").param("user_name", "a").param("user_age", "aaa"))
				.andExpect(model().attributeHasErrors("userInsertForm"))
				.andExpect(model().errorCount(1))
				.andExpect(model().attribute("insert", form))
				.andExpect(status().isOk())
				.andExpect(view().name("insert"));

		BindingResult bindResult = (BindingResult) result.andReturn().getModelAndView().getModel()
				.get(BindingResult.MODEL_KEY_PREFIX + "userInsertForm");
		String message = bindResult.getFieldError().getDefaultMessage();
		assertEquals("・年齢は半角数字の入力をお願いします。", message);

	}

}
