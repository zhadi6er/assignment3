CREATE TABLE users(
	id serial PRIMARY KEY NOT NULL,
	username varchar(12) UNIQUE NOT NULL,
	password varchar(12) NOT NULL
)
CREATE TABLE categories(
	id serial PRIMARY KEY NOT NULL,
	name varchar(12) UNIQUE NOT NULL
)
CREATE TABLE items(
	id serial PRIMARY KEY NOT NULL,
	name varchar(12) UNIQUE NOT NULL,
	details text NOT NULL,
	price decimal NOT NULL,
	category_id int NOT NULL,
	FOREIGN KEY(category_id) REFERENCES categories(id)
)
INSERT INTO public.categories(id, name)
	VALUES (1, 'Sport'),(2, 'Devices');
INSERT INTO public.items(id, name, details, price, category_id)
	VALUES (1, 'Item 1', 'Details', 5.3, 1),
	(2, 'Item 2', 'Details', 3.3, 1),
	(3, 'Item 3', 'Details', 4.6, 2),
	(4, 'Item 4', 'Details', 6.7, 2);