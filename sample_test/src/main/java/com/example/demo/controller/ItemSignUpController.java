package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.repository.DeletioinRepository;

/**
 * クラス名 :SignController
 * 
 * 作成日　:2024/02/27
 * 作成者　:塚田晃介
 * 最終更新日　:2024/02/27
 * 最終更新日　:塚田晃介
 * 
 * Copyright(C) AIOSL Technology All Rights Reserved.
 */

@Controller
public class ItemSignUpController {
	
	@Autowired
	DeletioinRepository repository;
	
	/**
	 * @param id 商品IDのURLパラム変数
	 * @return 完了画面へ遷移
	 */
	@GetMapping("/sign/{id}")
	public String sign(@PathVariable String id) {
		repository.update(id);
		return "message";
	}

}
