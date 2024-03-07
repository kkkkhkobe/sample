package com.example.demo.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entity.ItemEntity;
import com.example.demo.form.ItemForm;
import com.example.demo.form.UserForm;
import com.example.demo.repository.DeletioinRepository;
import com.example.demo.repository.UserRepository;

/**
 * クラス名 :UserController
 * 
 * 作成日 :2024/02/27
 * 作成者 :塚田晃介
 * 最終更新日 :2024/02/27
 * 最終更新日 :塚田晃介
 * 
 * Copyright(C) AIOSL Technology All Rights Reserved.
 */

@Controller
public class MenuController {

	@Autowired
	UserRepository repository;

	@Autowired
	DeletioinRepository delerepo;

	private Map<String, String> radioGender;

	private Map<String, String> radioButton() {
		Map<String, String> radio = new LinkedHashMap<>();
		radio.put("男性", "男性");
		radio.put("女性", "女性");
		radio.put("", "未選択");
		return radio;
	}

	public Map<String, String> initRadioGender() {
		Map<String, String> radio = new LinkedHashMap<>();
		radio.put("男性", "男性");
		radio.put("女性", "女性");
		return radio;
	}

	/**
	 * @return メニュー画面を表示
	 */
	@GetMapping("/")
	public String menu() {
		return "test";
	}

	/**
	 * @param model Modelのインスタンス
	 * @param form UserFormのインスタンス
	 * @return 顧客検索画面へ遷移
	 */
	@GetMapping("/user")
	public String search(Model model, UserForm form) {
		model.addAttribute("search", form);

		radioGender = radioButton();
		model.addAttribute("radioGender", radioGender);

		return "user.html";
	}

	/**
	 * @param model Modelのインスタンス
	 * @param form UserFormのインスタンス
	 * @return 顧客登録への画面遷移
	 */
	@GetMapping("/insert")
	public String insert(Model model, UserForm form) {
		model.addAttribute("insert", form);

		radioGender = initRadioGender();
		model.addAttribute("radioGender", radioGender);
		model.addAttribute("insert", form);
		return "insert.html";
	}

	/**
	 * @param model Modelのインスタンス
	 * @param form ItemFormのインスタンス
	 * @return 商品検索画面へ遷移
	 */
	@GetMapping("/item")
	public String item(Model model, ItemForm form) {
		model.addAttribute("form", form);
		return "item.html";
	}

	/**
	 * @param model Modelのインスタンス
	 * @param form ItemFormのインスタンス
	 * @return 商品登録画面へ遷移
	 */
	@GetMapping("/item_regi")
	public String regi(Model model, ItemForm form) {
		model.addAttribute("form", form);
		return "register";
	}

	/**
	 * @param model Modelのインスタンス
	 * @return 削除商品一覧画面へ画面遷移
	 */
	@GetMapping("/item_delete")
	public String del(Model model) {
		List<ItemEntity> list = delerepo.search();

		if (CollectionUtils.isEmpty(list)) {
			model.addAttribute("delete_item", true);
			return "deletion";
		}
		model.addAttribute("data", list);
		model.addAttribute("table", true);
		return "deletion";
	}

}
