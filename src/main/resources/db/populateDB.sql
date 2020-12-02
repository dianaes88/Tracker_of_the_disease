DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (first_name, middle_name, last_name, position, password) VALUES
('Иван', 'Иванович', 'Иванов', 'врач', 'password1'),
('Зина', 'Плюс', 'Один', 'медсестра', 'password2');

INSERT INTO user_roles (role, user_id) VALUES
('ROLE_USER', 100000),
('ROLE_ADMIN', 100001);
