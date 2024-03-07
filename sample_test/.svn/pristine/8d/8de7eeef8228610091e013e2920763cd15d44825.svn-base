package com.example.demo.form;

import jakarta.validation.constraints.Pattern;
import lombok.Data;
/*
 * クラス名 :UserForm
 * 
 * 作成日　:2024/02/28
 * 作成者　:塚田晃介
 * 最終更新日　:2024/02/28
 * 最終更新日　:塚田晃介
 * 
 * Copyright(C) AIOSL Technology All Rights Reserved.
 */

@Data
public class UserForm {

	@Pattern(regexp = "^[0-9]*$", message = "・IDは半角数字の入力をお願いします。")
	String user_id = "";

	String user_name = "";
	String user_address = "";

	@Pattern(regexp = "^[0-9]*$", message = "・年齢は半角数字の入力をお願いします。")
	String user_age = "";

	String department = "";
	
	String gender = "";
	
	String sort = "";

}
