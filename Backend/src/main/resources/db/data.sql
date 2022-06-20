DELETE FROM t_merchant;
INSERT INTO t_merchant (phone, password, name, description, address, public_phone, public_address, business_hours,
    create_at, update_at)
VALUES
/* password1 */
('1000000000', '$2a$10$uvtW/5WhkZxbVFMQHVlk/uOnT3zdPI1JEDc3XYeQKSRA1rz0haZaW', 'name1', 'description1', 'address1', 'public phone1', 'public address1', 'business hour1',now(), now()),
/* password2 */
('1000000001', '$2a$10$kA4vgN8Xclmn7Sc8Wj6qtuCr5gAzjCvOhJftSwJ7TdoSrbccFYbCO', 'name2', 'description2', 'address2', 'public phone2', 'public address2', 'business hour2',now(), now()),
/* password3 */
('1000000002', '$2a$10$2YEd7joLuymmQKfDHZCVCus3X1.LXwNpjZpUFO2GtQ9uyCvvEgNdi', 'name3', 'description3', 'address3', 'public phone3', 'public address3', 'business hour3', now(), now());

DELETE FROM t_product;
INSERT INTO t_product (name, monthly, inventory, discount, price, merchant_id, create_at, update_at)
VALUES
('product1', 10, 100, 0, 1250, 1, now(), now()),
('product2', 5, 100, 10, 1000, 2, now(), now());

DELETE FROM t_category;
INSERT INTO t_category (name, merchant_id, create_at, update_at)
VALUES
('category1', 1, now(), now()),
('category2', 1, now(), now()),
('category3', 2, now(), now());

DELETE FROM t_product_to_category;

DELETE FROM t_order_delivery_method;

DELETE FROM t_order_status;

DELETE FROM t_client;
INSERT INTO t_client (create_at, update_at)
VALUES
(now(), now()),
(now(), now()),
(now(), now());