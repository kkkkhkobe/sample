package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.ItemEntity;
import com.example.demo.repository.DeletionMapper;

@Service
public class ItemService {
	
	@Autowired
	DeletionMapper mapper;
	
	public List<ItemEntity> find(){
		return mapper.findAll();
	}
	
	void update(String id) {
		mapper.update(id);
	}
	
	void item_update(Integer id, String name) {
		mapper.item_update(id, name);
	}
	void item_update_stock(Integer id, String price , String stock) {
		mapper.item_update_stock(id, price, stock);
	}
	
	void delete(Integer id) {
		mapper.delete(id);
	}

}
