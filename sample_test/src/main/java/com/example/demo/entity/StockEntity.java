package com.example.demo.entity;

import lombok.Data;
/*
 * クラス名 :StockEntity
 * 
 * 作成日　:2024/02/28
 * 作成者　:塚田晃介
 * 最終更新日　:2024/02/28
 * 最終更新日　:塚田晃介
 * 
 * Copyright(C) AIOSL Technology All Rights Reserved.
 */

@Data
public class StockEntity {

	Integer material_id;
	String regular_price;
	String material_stock;
	String deleted_flg;

}
