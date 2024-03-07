package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.form.ItemRegisterForm;
import com.example.demo.repository.DeletioinRepository;

/**
 * クラス名 :ModController
 * 
 * 作成日　:2024/02/27
 * 作成者　:塚田晃介
 * 最終更新日　:2024/02/27
 * 最終更新日　:塚田晃介
 * 
 * Copyright(C) AIOSL Technology All Rights Reserved.
 */

@Controller
public class ItemModController {

	@Autowired
	DeletioinRepository repository;

	/**
	 * @param model　Modelのインスタンス
	 * @param id 商品IDのURLパス
	 * @param form ItemFormのインスタンス
	 * @return 商品修正画面へ遷移
	 */
	@GetMapping("/item_mod/{id}")
	public String mod(Model model, @PathVariable String id, ItemRegisterForm form) {
		model.addAttribute("mod", id);
		model.addAttribute("form", form);
		return "mod";
	}

	/**
	 * @param model　Modelのインスタンス
	 * @param id 商品IDのURLパス
	 * @param form ItemFormのインスタンス
	 * @return 完了画面へ遷移
	 */
	@GetMapping("/item_mod/mod_item/{id}")
	public String mod1(Model model, @PathVariable String id, @Validated @ModelAttribute ItemRegisterForm form,
			BindingResult result) {

		if (result.hasErrors()) {
			List<String> errorList = new ArrayList<String>();
			for (ObjectError error : result.getAllErrors()) {
				errorList.add(error.getDefaultMessage());
			}
			model.addAttribute("form", form);

			model.addAttribute("error", errorList);
			return "mod";
		}

		repository.item_update(id, form);
		repository.item_update_stock(id, form);

		return "message";

	}

}
