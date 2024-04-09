INSERT INTO local_table (floor, name) VALUES ('Third Floor', 'PuppetShop');
INSERT INTO local_table (floor, name) VALUES ('Third Floor', 'DrugStore');
INSERT INTO local_table (floor, name) VALUES ('Third Floor', 'Adidas');
INSERT INTO local_table (floor, name) VALUES ('Second Floor', 'Nike');
INSERT INTO local_table (floor, name) VALUES ('First Floor', 'Puma');

INSERT INTO address (city) VALUES ('Buenos Aires');
INSERT INTO address (city) VALUES ('Bogota');
INSERT INTO address (city) VALUES ('Asuncion');


INSERT INTO product_table (brand, expired, name, price, id_local) VALUES ('KittenFF', false, 'Little Ones', 7.50, 1);
INSERT INTO product_table (brand, expired, name, price, id_local) VALUES ('Paracetamol', false, 'Aspirina', 2.10, 2);
INSERT INTO product_table (brand, expired, name, price, id_local) VALUES ('Puppie', false,'Bone', 4.50, 1);

INSERT INTO employee (name, salary, obj, skill, address_id) VALUES ('Juan', 11000, '2023-01-01', 'ESPANIOL', 1);
INSERT INTO employee (name, salary, obj, skill,address_id) VALUES ('Maria', 9000, '2020-05-01', 'CSNATURALES', 1);
INSERT INTO employee (name, salary, obj, skill, address_id) VALUES ('Flor', 5000, '2022-08-01', 'INGLES', 3);