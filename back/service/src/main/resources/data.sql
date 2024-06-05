INSERT INTO ROLE(id, name) values (1, 'ADMIN');
INSERT INTO ROLE(id, name) values (2, 'WORKER');

INSERT INTO MY_USER(name, username, password) values ('Nada Zaric', 'admin', '$2a$12$SNAppbTdXwnOkjSjlpxNoOqCd6RQw4O7IabGuWH82a4wh9judY2SO');
INSERT INTO MY_USER_ROLES(user_id, roles_id) values (1, 1);

INSERT INTO MY_USER(name, username, password) values ('Ranka Milovanovic', 'ranka', '$2a$12$SNAppbTdXwnOkjSjlpxNoOqCd6RQw4O7IabGuWH82a4wh9judY2SO');
INSERT INTO MY_USER_ROLES(user_id, roles_id) values (2, 2);

-- INSERT INTO SOLDIER (full_name, jmbg, months, category, monthly_contribution) VALUES ('Pera Peric', '076436335357', 0, 0, 0.0);
-- INSERT INTO SOLDIER (full_name, jmbg, months, category, monthly_contribution) VALUES ('Marko Markovic', '076437565357', 0, 0, 0.0);
-- INSERT INTO SOLDIER (full_name, jmbg, months, category, monthly_contribution) VALUES ('Despot Despotovic', '4534646342345', 0, 0, 0.0)