create table users(
user_id serial primary key not null,
	username VARCHAR (100) not null,
	password VARCHAR (100) 
	);

create table product_types_master(
    product_type_id serial primary key not null,
	product_type_name VARCHAR (200) not null
	);

create table products (
     product_id serial not null,
     product_type_id INT not null,
	 product_name varchar(100) not null,
	 product_description varchar(255),
	 price double precision not null,
	 in_stock int not null,
	 
     primary key (product_id),
     foreign key (product_type_id)
     references product_types_master (product_type_id)
       
);

insert
	into
	users (username,
	password)
values('will.smith',
'smithw1'),
       ('tom.cruise',
'crtom11'),
       ('tom.hanks',
'hankt12'),
       ('al.pachino',
'al123'),
       ('brad.pitt ',
'brad321'),
       ('drew.barrymore',
'drebar121'),
       ('julia.roberts',
'julrob1!'),
       ('meryl.streep',
'stree123'),
       ('ann.hathway',
' hatann1'),
       ('danzel.washington',
'dwash333'), 
       ('angelina.jouli',
'angel111');

insert
	into
	product_types_master (product_type_name)
values('cellphones'),
       ('books'),
       ('sporting_goods'),
       ('toys'),
       ('music'),
       ('clothing'),
       ('cameras'),
       ('computers'),
       ('televisions');

insert
	into
	products (product_type_id,
	product_name,
	product_description,
	price,
	in_stock)
values('4',
'Marble Genius Marble Run Super Set',
'Brain Teaser Game',
'39.99',
10),
       ('3',
'BasketBall',
'WILSON Evolution Game Basketball' ,
'55.59',
10),
       ('1',
'Apple Iphone 13 Pro Max',
'Gold',
'1099.00' ,
10),
       ('9',
'LG OLED C1 Series 65 TV',
' Alexa Built-in 4k Smart TV ',
'1799.99 ',
10),
       ('2',
'Harry Potter Boxed Book Set',
'Hardcover Book Set with Custom Designed Dust Jackets | Author J.K. Rowling',
'324.99',
10),
       ('8',
'2021 Apple MacBook Pro',
'16-inch Apple M1 Pro chip with 10 CPU and core GPU-16GB RAM- 512GB SSD - Silver',
'2499.00',
10),
       ('5',
'Carhartt Men''s Rutland Hoodie',
'Carhartt Men''s Rutland Thermal Lined Zip Front Sweatshirt',
'79.98',
10),
       ('7',
'Nikon D7500 DSLR',
'20.9MP DSLR Digital Camera with AF-P DX NIKKOR 18-55mm f3.5-5.6G VR Lens + SanDisk 32GB Memory Card',
'1309.99',
10),
       ('6',
'Donnar Guitar',
'Black Acoustic Guitar',
'129.56',
10),
	   ('4',
' RC Car',
'Seckton Toys for 6-10 Year Old Boys',
'37.65',
10),
	   ('2',
'The Hobbit',
'Fiction book',
'42.97',
10),
	   ('5',
'Wool Coat',
'Anne Klein Women''s Classic Coat',
'129.98',
10),
		('1',
'Samsung Note',
'Samsung Galaxy Note 10+ 256GB+ Aura Glow +For GSM ',
'409.00',
10);