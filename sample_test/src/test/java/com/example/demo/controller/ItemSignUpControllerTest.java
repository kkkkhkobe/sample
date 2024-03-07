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

import com.example.demo.repository.DeletioinRepository;

@SpringBootTest
class ItemSignUpControllerTest {

	@MockBean
	DeletioinRepository service;

	@Autowired
	ItemSignUpController controller;

	MockMvc mock;

	@BeforeEach
	void setup() {
		mock = MockMvcBuilders.standaloneSetup(controller).build();
	}

	/**
	 * URLパス変数の検証
	 * httpステータスの検証
	 * 完了画面遷移の検証
	 * サービスのupdateメソッドの呼び出し回数
	 * @throws Exception
	 */
	@Test
	void testSign1_1() throws Exception {
		String id = "1";
		
		mock.perform(get("/sign/{id}", id))
				.andExpect(status().isOk())
				.andExpect(view().name("message"));
		
		verify(service, times(1)).update(id);
	}

}
