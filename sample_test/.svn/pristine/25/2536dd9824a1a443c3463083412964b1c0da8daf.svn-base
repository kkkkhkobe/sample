package com.example.demo.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.repository.DeletioinRepository;

@SpringBootTest
@Transactional
class ItemDeleteMybatisControllerTest {

	@MockBean
	DeletioinRepository service;

	@Autowired
	ItemDeleteMybatisController controller;

	MockMvc mock;

	@BeforeEach
	void setup() {
		mock = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	/**
	 * httpステータスの検証
	 * 削除商品一覧画面遷移の検証
	 * サービスのdeleteメソッド呼び出し回数
	 * @throws Exception
	 */

	@Test
	void testDele1_1() throws Exception {
		Integer id = 1;
		mock.perform(get("/delete/{id}", id))
				.andExpect(status().isOk())
				.andExpect(view().name("message"));
		
		verify(service, times(1)).delete(id);
	}

}
