package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;

import com.example.demo.form.UserForm;
import com.example.demo.service.SearchService;

@SpringBootTest
class UserSearchControllerTest {

	@MockBean
	SearchService service;

	@Autowired
	UserSearchController controller;

	MockMvc mock;

	UserForm form = new UserForm();

	@BeforeEach
	void setup() {
		mock = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	void testRadioButton() {
		Map<String, String> radio = controller.radioButton();

		assertTrue(radio.containsValue("男性"));
	}

	/**
	 * validationがない場合の検証
	 * modelの検証
	 * httpステータスの検証
  	 * 顧客検索画面遷移の検証
	 * サービスのlistメソッドの呼び出し回数
	 * @throws Exception
	 */

	@Test
	void testSearch1_1() throws Exception {
		form.setUser_id("1");

		mock.perform(get("/search").param("user_id", "1"))
				.andExpect(model().hasNoErrors())
				.andExpect(model().attribute("search", form))
				.andExpect(status().isOk())
				.andExpect(view().name("user"));

		verify(service, times(1)).list(form);

	}

	/**
	 * 入力フォーム「顧客ID」のエラー検証
	 * modelの検証
	 * エラー数の検証
	 * エラーの種類の検証
	 * httpステータスの検証
	 * 顧客検索画面遷移の検証
	 * idのエラーメッセージの検証
	 * @throws Exception
	 */
	@Test
	void testSearch1_2() throws Exception {
		ResultActions result = mock.perform(get("/search").param("user_id", "aaa"))
				.andExpect(model().attributeHasErrors("userForm"))
				.andExpect(model().errorCount(1))
				.andExpect(model().attributeHasFieldErrorCode("userForm", "user_id", "Pattern"))
				.andExpect(status().isOk())
				.andExpect(view().name("user"));

		BindingResult bindResult = (BindingResult) result.andReturn().getModelAndView().getModel()
				.get(BindingResult.MODEL_KEY_PREFIX + "userForm");
		String message = bindResult.getFieldError().getDefaultMessage();
		assertEquals("・IDは半角数字の入力をお願いします。", message);

	}

	/**
	 * 入力フォーム「年齢」のエラー検証
	 * modelの検証
	 * エラー数の検証
	 * エラーの種類の検証
	 * httpステータスの検証
	 * 顧客検索画面の検証
	 * 年齢のエラーメッセージの検証
	 * @throws Exception
	 */

	@Test
	void testSearch1_3() throws Exception {
		ResultActions result = mock.perform(get("/search").param("user_age", "aaa"))
				.andExpect(model().attributeHasErrors("userForm"))
				.andExpect(model().errorCount(1))
				.andExpect(model().attributeHasFieldErrorCode("userForm", "user_age", "Pattern"))
				.andExpect(status().isOk())
				.andExpect(view().name("user"));

		BindingResult bindResult = (BindingResult) result.andReturn().getModelAndView().getModel()
				.get(BindingResult.MODEL_KEY_PREFIX + "userForm");
		String message = bindResult.getFieldError().getDefaultMessage();
		assertEquals("・年齢は半角数字の入力をお願いします。", message);

	}

	/**
	 * 検索結果が0件の時の検証
	 * httpステータスの検証
	 * 顧客検索画面遷移の検証
	 * @throws Exception
	 */

	@Test
	void testSearch1_4() throws Exception {
		mock.perform(get("/search"))
				.andExpect(model().attribute("error", "・検索結果は0件です。"))
				.andExpect(status().isOk())
				.andExpect(view().name("user"));
	}

}
