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

import com.example.demo.form.ItemRegisterForm;
import com.example.demo.repository.DeletioinRepository;

@SpringBootTest
class ItemModControllerTest {

	@MockBean
	DeletioinRepository service;

	@Autowired
	ItemModController controller;

	MockMvc mock;

	ItemRegisterForm form = new ItemRegisterForm();

	@BeforeEach
	void setup() {
		mock = MockMvcBuilders.standaloneSetup(controller).build();
	}

	/**
	 * URLパス変数の検証
	 * httpステータスの検証
	 * 商品修正画面遷移の検証
	 * @throws Exception
	 */

	@Test
	void testmod1_1() throws Exception {
		Integer id = 1;
		form.setMaterial_name("Dくん");

		mock.perform(get("/item_mod/{id}", id).param("material_name", "Dくん"))
				.andExpect(model().attribute("form", form))
				.andExpect(status().isOk())
				.andExpect(view().name("mod"));
	}

	/**
	 * URLパス変数の検証
	 * httpステータスの検証
	 * 完了画面遷移の検証
	 * サービスクラスの各メソッドの呼び出し回数
	 * @throws Exception
	 */

	@Test
	void testMod1_2() throws Exception {
		String id = "1";
		form.setMaterial_name("Dくん");
		form.setRegular_price("500");

		mock.perform(get("/item_mod/mod_item/{id}", id)
				.param("material_name", "Dくん")
				.param("regular_price", "500"))
				.andExpect(status().isOk())
				.andExpect(view().name("message"));

		verify(service, times(1)).item_update(id, form);
		verify(service, times(1)).item_update_stock(id, form);
	}

	/**
	 * 商品名を異常値で検証
	 * エラー数の検証
	 * URLパス変数の検証
	 * httpステータスの検証
	 * 完了画面遷移の検証
	 * 商品名のエラーメッセージ検証
	 * @throws Exception
	 */

	@Test
	void testMod1_3() throws Exception {
		String id = "1";
		form.setMaterial_name("");

		ResultActions result = mock.perform(get("/item_mod/mod_item/{id}", id)
				.param("material_name", ""))
				.andExpect(model().attributeHasErrors("itemRegisterForm"))
				.andExpect(model().errorCount(1))
				.andExpect(status().isOk())
				.andExpect(view().name("mod"));

		BindingResult bindResult = (BindingResult) result.andReturn().getModelAndView().getModel()
				.get(BindingResult.MODEL_KEY_PREFIX + "itemRegisterForm");
		String message = bindResult.getFieldError().getDefaultMessage();
		assertEquals("・商品名は必須項目です。", message);

	}

	/**
	 * 在庫数を異常値で検証
	 * エラー数の検証
	 * URLパス変数の検証
	 * httpステータスの検証
	 * 完了画面遷移の検証
	 * 在庫数のエラーメッセージ検証
	 * @throws Exception
	 */

	@Test
	void testMod1_4() throws Exception {
		String id = "1";
		form.setMaterial_name("a");
		form.setMaterial_stock("aaa");

		ResultActions result = mock.perform(get("/item_mod/mod_item/{id}", id).param("material_name", "a")
				.param("material_stock", "aaa"))
				.andExpect(model().attributeHasErrors("itemRegisterForm"))
				.andExpect(model().errorCount(1))
				.andExpect(status().isOk())
				.andExpect(view().name("mod"));

		BindingResult bindResult = (BindingResult) result.andReturn().getModelAndView().getModel()
				.get(BindingResult.MODEL_KEY_PREFIX + "itemRegisterForm");
		String message = bindResult.getFieldError().getDefaultMessage();
		assertEquals("・在庫数は半角数字の入力をお願いします。", message);

	}

	/**
	 * 販売価格を異常値で検証
	 * エラー数の検証
	 * URLパス変数の検証
	 * httpステータスの検証
	 * 完了画面遷移の検証
	 * 販売価格のエラーメッセージ検証
	 * @throws Exception
	 */

	@Test
	void testMod1_5() throws Exception {
		String id = "1";
		form.setMaterial_name("a");
		form.setRegular_price("aaa");

		ResultActions result = mock.perform(get("/item_mod/mod_item/{id}", id).param("material_name", "a")
				.param("regular_price", "aaa"))
				.andExpect(model().attributeHasErrors("itemRegisterForm"))
				.andExpect(model().errorCount(1))
				.andExpect(status().isOk())
				.andExpect(view().name("mod"));

		BindingResult bindResult = (BindingResult) result.andReturn().getModelAndView().getModel()
				.get(BindingResult.MODEL_KEY_PREFIX + "itemRegisterForm");
		String message = bindResult.getFieldError().getDefaultMessage();
		assertEquals("・価格は半角数字の入力をお願いします。", message);

	}

}
