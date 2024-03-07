package com.example.demo.form;

import jakarta.validation.constraints.Pattern;
import lombok.Data;
/*
 * クラス名 :ItemForm
 * 
 * 作成日　:2024/02/28
 * 作成者　:塚田晃介
 * 最終更新日　:2024/02/28
 * 最終更新日　:塚田晃介
 * 
 * Copyright(C) AIOSL Technology All Rights Reserved.
 */

@Data
public class ItemForm {

	@Pattern(regexp = "^[0-9]*$", message = "・IDは半角数字の入力をお願いします。")
	String material_id;
	
	String material_name;
	String material_text;
	
	@Pattern(regexp = "^[0-9]*$", message = "・価格は半角数字の入力をお願いします。")
	String regular_price;
	
	@Pattern(regexp = "^[0-9]*$", message = "・在庫数は半角数字の入力をお願いします。")
	String material_stock;
	

}
