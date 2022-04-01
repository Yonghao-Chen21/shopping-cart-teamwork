CREATE TABLE users(
user_id serial PRIMARY KEY NOT NULL,
	username VARCHAR ( 100 )  NOT NULL,
	password VARCHAR ( 100 ) 
	);
	
	CREATE TABLE product_types_master(
    product_type_id serial PRIMARY KEY NOT NULL,
	product_type_name VARCHAR ( 200 )  NOT NULL
	);
	
	CREATE TABLE products (
     product_id serial  NOT NULL,
     product_type_id INT NOT NULL,
	 product_name varchar(100) NOT NULL,
	 product_description varchar(255),
	 price double precision NOT NULL,
	 in_stock boolean NOT NULL,
	 
     PRIMARY KEY (product_id),
     FOREIGN KEY (product_type_id)
     REFERENCES product_types_master (product_type_id)
       
);

INSERT INTO users (username,password)
VALUES('will.smith', 'smithw1'),
       ('tom.cruise','crtom11'),
       ('tom.hanks', 'hankt12'),
       ('al.pachino','al123'),
       ('brad.pitt ','brad321'),
       ('drew.barrymore','drebar121'),
       ('julia.roberts','julrob1!'),
       ('meryl.streep','stree123'),
       ('ann.hathway',' hatann1'),
       ('danzel.washington','dwash333'), 
       ('angelina.jouli','angel111');
 
       
       INSERT INTO product_types_master (product_type_name)
VALUES('cellphones'),
       ('books'),
       ('sporting_goods'),
       ('toys'),
       ('music'),
       ('clothing'),
       ('cameras'),
       ('computers'),
       ('televisions');
       
       INSERT INTO products (product_type_id,product_name, product_description,price,in_stock)
       VALUES('4','Marble Genius Marble Run Super Set','Brain Teaser Game','39.99','Yes'),
       ('3','BasketBall','WILSON Evolution Game Basketball' ,'55.59','Yes'),
       ('1','Apple Iphone 13 Pro Max','Gold','1099.00' ,'Yes'),
       ('9','LG OLED C1 Series 65 TV',' Alexa Built-in 4k Smart TV ','1799.99 ','No'),
       ('2','Harry Potter Boxed Book Set','Hardcover Book Set with Custom Designed Dust Jackets | Author J.K. Rowling','324.99','Yes'),
       ('8','2021 Apple MacBook Pro','16-inch Apple M1 Pro chip with 10 CPU and core GPU-16GB RAM- 512GB SSD - Silver','2499.00','Yes'),
       ('5','Carhartt Men''s Rutland Hoodie','Carhartt Men''s Rutland Thermal Lined Zip Front Sweatshirt','79.98','Yes'),
       ('7','Nikon D7500 DSLR','20.9MP DSLR Digital Camera with AF-P DX NIKKOR 18-55mm f3.5-5.6G VR Lens + SanDisk 32GB Memory Card','1309.99','Yes'),
       ('6','Donnar Guitar','Black Acoustic Guitar','129.56','No'),
	   ('4',' RC Car','Seckton Toys for 6-10 Year Old Boys','37.65','Yes'),
	   ('2','The Hobbit','Fiction book','42.97','No'),
	   ('5','Wool Coat','Anne Klein Women''s Classic Coat','129.98','Yes'),
		('1','Samsung Note','Samsung Galaxy Note 10+ 256GB+ Aura Glow +For GSM ','409.00','Yes');
	
