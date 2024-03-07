package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.entity.ItemEntity;
import com.example.demo.repository.ItemRepository;

/**
 * クラス名 :DeleteController
 * 
 * 作成日　:2024/02/27
 * 作成者　:塚田晃介
 * 最終更新日　:2024/02/27
 * 最終更新日　:塚田晃介
 * 
 * Copyright(C) AIOSL Technology All Rights Reserved.
 */

@Controller
public class ItemDeleteController {

	@Autowired
	ItemRepository repository;

	/**
	 * @param model Modelのインスタンス
	 * @param id 商品ID path変数
	 * @return delete.html画面遷移
	 */
	@GetMapping("/item_del/{id}")
	public String del(Model model, @PathVariable Integer id) {
		List<ItemEntity> list = repository.find(id);

		model.addAttribute("material_id", id);
		model.addAttribute("data", list);
		return "delete";
	}

	/**
	 * @param id 商品ID path変数
	 * @return message.html画面遷移
	 */
	@GetMapping("/del/{id}")
	public String del1(@PathVariable Integer id) {
		repository.update(id);

		return "message";
	}

}
