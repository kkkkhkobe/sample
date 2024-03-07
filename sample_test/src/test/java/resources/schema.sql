CREATE TABLE if not exists material
(
   material_id int (10) unsigned NOT NULL AUTO_INCREMENT,
   material_name varchar (100) NOT NULL,
   material_text varchar (100) NOT NULL,
   PRIMARY KEY (material_id)
);
CREATE TABLE if not exists product
(
   material_id int (10) unsigned NOT NULL,
   regular_price varchar (10) NOT NULL,
   material_stock char (5) NOT NULL,
   deleted_flg char (1) DEFAULT '0',
   PRIMARY KEY (material_id)
);
insert into material
(
   material_id,
   material_name,
   material_text
)
values
(
   null,
   'aaa',
   'ccc'
),

(
   null,
   'aaa',
   'ccc'
);
