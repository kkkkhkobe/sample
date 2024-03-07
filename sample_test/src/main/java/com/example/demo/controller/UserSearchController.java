package com.example.demo.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.demo.entity.UserEntity;
import com.example.demo.form.UserForm;
import com.example.demo.service.SearchService;

/**
 * クラス名 :SearchController
 * 
 * 作成日　:2024/02/27
 * 作成者　:塚田晃介
 * 最終更新日　:2024/02/27
 * 最終更新日　:塚田晃介
 * 
 * Copyright(C) AIOSL Technology All Rights Reserved.
 */

@Controller
public class UserSearchController {

	@Autowired
	SearchService repository;

	private Map<String, String> radioGender;

	Map<String, String> radioButton() {
		Map<String, String> radio = new LinkedHashMap<>();
		radio.put("男性", "男性");
		radio.put("女性", "女性");
		radio.put("", "未選択");
		return radio;
	}

	/**
	 * @param model Modelのインスタンス
	 * @param form UserFormのインスタンス
	 * @param result BindingResultのインスタンス
	 * @return 入力値が異常の場合、検索画面へエラーメッセージを表示
	 * @return 入力値が正常の場合、検索画面に結果を表示
	 */
	@GetMapping("/search")
	public String search1(Model model, @Validated @ModelAttribute UserForm form, BindingResult result) {
		radioGender = radioButton();

		model.addAttribute("radioGender", radioGender);
		model.addAttribute("search", form);

		if (result.hasErrors()) {
			List<String> errorList = new ArrayList<>();
			for (ObjectError error : result.getAllErrors()) {
				errorList.add(error.getDefaultMessage());
			}

			model.addAttribute("error", errorList);
			return "user";
		}
		List<UserEntity> list = repository.list(form);

		if (list.isEmpty()) {
			model.addAttribute("error", "・検索結果は0件です。");
			return "user";
		}

		model.addAttribute("data", list);
		model.addAttribute("table", true);
		return "user";
	}

}
