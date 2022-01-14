INSERT INTO account (id, email, password, github_id, github_username, name, nickname, picture, phone_number) VALUES (1, 'pkeugine@gmail.com', 'password', 'pkeugine', 'pkeugine', 'Eugine Park', 'PK', 'https://avatars.githubusercontent.com/u/48251668?v=4', '010-4067-0913');

INSERT INTO registration (id, client_id, client_secret, client_name, homepage_uri, redirect_uri) VALUES (1, '1', 'client_secret', 'client_name', 'http://localhost:8081', 'http://localhost:8081/login/authorize');

INSERT INTO code (id, value, registration_id, account_id) VALUES (1, 'asdf', '1', '1')
