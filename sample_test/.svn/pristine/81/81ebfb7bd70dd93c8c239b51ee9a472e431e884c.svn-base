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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;

import com.example.demo.form.ItemForm;
import com.example.demo.repository.ItemRepository;

@SpringBootTest
class ItemControllerTest {

	@MockBean
	ItemRepository repository;

	@Autowired
	ItemController controller;

	ItemForm form = new ItemForm();

	MockMvc mock;

	@BeforeEach
	void setup() {
		mock = MockMvcBuilders.standaloneSetup(controller).build();
	}

	/**
	 * 条件検索の検証
	 * 入力値の検証
	 * httpステータスの検証
	 * 検索結果の検証
	 * リポジトリのsearchメソッドの呼び出し回数
	 * @throws Exception
	 */
	@Test
	void testSearch1_1() throws Exception {
		form.setMaterial_id("1");

		mock.perform(get("/result").param("material_id", "1"))
				.andExpect(model().hasNoErrors())
				.andExpect(model().attribute("itemForm", form))
				.andExpect(status().isOk())
				.andExpect(view().name("item"));

		verify(repository, times(1)).search(form);
	}

	/**
	 * 商品idの異常値の検証
	 * 該当エラー数の確認
	 * modelの検証
	 * httpステータスの検証
	 * 画面遷移の検証
	 * 該当するエラーメッセージの検証
	 * @throws Exception
	 */
	@Test
	void testSearch1_2() throws Exception {
		form.setMaterial_id("aaa");

		ResultActions result = mock.perform(get("/result").param("material_id", "aaa"))
				.andExpect(model().attributeHasErrors("itemForm"))
				.andExpect(model().errorCount(1))
				.andExpect(model().attribute("form", form))
				.andExpect(status().isOk())
				.andExpect(view().name("item"));

		BindingResult bindResult = (BindingResult) result.andReturn().getModelAndView().getModel()
				.get(BindingResult.MODEL_KEY_PREFIX + "itemForm");
		String mes = bindResult.getFieldError().getDefaultMessage();
		assertEquals("・IDは半角数字の入力をお願いします。", mes);
	}

	/**
	 * 検索結果0件
	 * validationの検証
	 * model自作のエラーメッセージの検証
	 * 画面遷移の検証
	 * リポジトリのsearchメソッドの呼び出し回数
	 * @throws Exception
	 */

	@Test
	void testSearch1_3() throws Exception {
		form.setMaterial_id("100");

		mock.perform(get("/result").param("material_id", "100"))
				.andExpect(model().hasNoErrors())
				.andExpect(model().attribute("error", "・検索結果は0件です。"))
				.andExpect(status().isOk())
				.andExpect(view().name("item"));

	}

}
