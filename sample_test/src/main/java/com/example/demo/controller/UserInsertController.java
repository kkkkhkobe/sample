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

import com.example.demo.form.UserInsertForm;
import com.example.demo.repository.UserRepository;

/**
 * クラス名 :InsertController
 * 
 * 作成日　:2024/02/27
 * 作成者　:塚田晃介
 * 最終更新日　:2024/02/27
 * 最終更新日　:塚田晃介
 * 
 * Copyright(C) AIOSL Technology All Rights Reserved.
 */

@Controller
public class UserInsertController {

	@Autowired
	UserRepository repository;

	private Map<String, String> radioGender;

	public Map<String, String> initRadioGender() {
		Map<String, String> radio = new LinkedHashMap<>();
		radio.put("男性", "男性");
		radio.put("女性", "女性");
		return radio;
	}

	/**
	 * @param model Modelのインスタンス
	 * @param form UserInsertFormのフォーム
	 * @param result BindingResultのインスタンス
	 * @return 入力値エラーの場合、登録画面へ遷移
	 * @return 入力値が正常の場合、完了画面へ遷移
	 */
	@GetMapping("/insert1")
	public String insert1(Model model, @Validated @ModelAttribute UserInsertForm form,
			BindingResult result) {
		radioGender = initRadioGender();

		if (result.hasErrors()) {
			List<String> errorList = new ArrayList<String>();
			for (ObjectError error : result.getAllErrors()) {
				errorList.add(error.getDefaultMessage());
			}
			model.addAttribute("radioGender", radioGender);
			model.addAttribute("error", errorList);
			model.addAttribute("insert", form);
			return "insert";
		}
		model.addAttribute("radioGender", radioGender);
		repository.insert(form);
		model.addAttribute("insert", form);
		return "message";
	}

}
