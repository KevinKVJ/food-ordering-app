DELETE FROM t_merchant;
INSERT INTO t_merchant (phone, password, name, description, address, create_at, update_at)
VALUES
('1000000000', 'password1', 'name1', 'description1', 'address1', now(), now()),
('1000000001', 'password2', 'name2', 'description2', 'address2', now(), now()),
('1000000002', 'password3', 'name3', 'description3', 'address3', now(), now());