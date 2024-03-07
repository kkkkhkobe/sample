package com.example.demo.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.entity.ItemEntity;
import com.example.demo.repository.ItemRepository;

@SpringBootTest
class ItemDeleteControllerTest {

	@MockBean
	ItemRepository repository;

	@Autowired
	ItemDeleteController controller;

	MockMvc mock;

	@BeforeEach
	void setup() {
		mock = MockMvcBuilders.standaloneSetup(controller).build();
	}

	/**
	 * @throws Exception
	 * URLパス変数の検証
	 * 削除商品一覧画面遷移の検証
	 * modelの検証
	 * リポジトリのfindメソッド呼び出し回数
	 */
	@Test
	void testDel1_1() throws Exception {
		Integer id = 1;
		List<ItemEntity> list = repository.find(id);

		mock.perform(get("/item_del/{id}", id))
				.andExpect(model().attribute("material_id", id))
				.andExpect(model().attribute("data", list))
				.andExpect(status().isOk())
				.andExpect(view().name("delete"));

		verify(repository, times(2)).find(id);
	}

	/**
	 * @throws Exception
	 * httpステータスの検証
	 * 完了画面遷移の検証
	 * リポジトリのupdateメソッドの呼び出し回数
	 */
	@Test
	void testDel1_1_1() throws Exception {
		Integer id = 1;
		mock.perform(get("/del/{id}", id))
				.andExpect(status().isOk())
				.andExpect(view().name("message"));

		verify(repository, times(1)).update(id);
	}

}
