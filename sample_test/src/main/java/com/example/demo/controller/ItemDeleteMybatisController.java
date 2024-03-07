package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.repository.DeletioinRepository;

/**
 * クラス名 :DeleteMybatisController
 * 
 * 作成日　:2024/02/27
 * 作成者　:塚田晃介
 * 最終更新日　:2024/02/27
 * 最終更新日　:塚田晃介
 * 
 * Copyright(C) AIOSL Technology All Rights Reserved.
 */

@Controller
public class ItemDeleteMybatisController {

	@Autowired
	DeletioinRepository repository;

	/**
	 * @param id 商品IDのURLパス
	 * @return 操作完了のHTMLファイルへ遷移
	 */
	@GetMapping("/delete/{id}")
	public String dele(@PathVariable Integer id) {
		repository.delete(id);

		return "message";
	}

}
