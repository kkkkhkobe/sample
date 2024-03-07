package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.UserEntity;
import com.example.demo.form.UserForm;
import com.example.demo.repository.UserRepository;
/*
 * クラス名 :SearchService
 * 
 * 作成日　:2024/02/28
 * 作成者　:塚田晃介
 * 最終更新日　:2024/02/28
 * 最終更新日　:塚田晃介
 * 
 * Copyright(C) AIOSL Technology All Rights Reserved.
 */

@Service
public class SearchService {
	
	@Autowired
	UserRepository repository;

	public List<UserEntity> list(UserForm form) {
		List<UserEntity> list = repository.search(form);
		return list;
	}

}
