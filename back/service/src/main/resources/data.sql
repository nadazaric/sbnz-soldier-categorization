INSERT INTO ROLE(id, name) VALUES (1, 'ADMIN');
INSERT INTO ROLE(id, name) VALUES (2, 'WORKER');

INSERT INTO MY_USER(name, username, password) VALUES ('Nada Zaric', 'admin', '$2a$12$SNAppbTdXwnOkjSjlpxNoOqCd6RQw4O7IabGuWH82a4wh9judY2SO');
INSERT INTO MY_USER_ROLES(user_id, roles_id) VALUES (1, 1);

INSERT INTO MY_USER(name, username, password) VALUES ('Ranka Milovanovic', 'ranka', '$2a$12$SNAppbTdXwnOkjSjlpxNoOqCd6RQw4O7IabGuWH82a4wh9judY2SO');
INSERT INTO MY_USER_ROLES(user_id, roles_id) VALUES (2, 2);

INSERT INTO UNIT(name, parent_id, type) VALUES ('Armija', null, 0); 
INSERT INTO UNIT(name, parent_id, type) VALUES ('I Brigada', 1, 0);
INSERT INTO UNIT(name, parent_id, type) VALUES ('II Brigada', 1, 0);
INSERT INTO UNIT(name, parent_id, type) VALUES ('I Bataljon', 2, 0);
INSERT INTO UNIT(name, parent_id, type) VALUES ('II Bataljon', 2, 0);
INSERT INTO UNIT(name, parent_id, type) VALUES ('III Bataljon', 3, 0);
INSERT INTO UNIT(name, parent_id, type) VALUES ('IV Bataljon', 3, 0);
INSERT INTO UNIT(name, parent_id, type) VALUES ('I Jedinica', 4, 1);
INSERT INTO UNIT(name, parent_id, type) VALUES ('II Jedinica', 4, 1);
INSERT INTO UNIT(name, parent_id, type) VALUES ('III Jedinica', 5, 1);
INSERT INTO UNIT(name, parent_id, type) VALUES ('IV Jedinica', 5, 1);
INSERT INTO UNIT(name, parent_id, type) VALUES ('V Jedinica', 6, 1);
INSERT INTO UNIT(name, parent_id, type) VALUES ('VI Jedinica', 6, 1);
INSERT INTO UNIT(name, parent_id, type) VALUES ('VII Jedinica', 7, 1);
INSERT INTO UNIT(name, parent_id, type) VALUES ('VIII Jedinica', 7, 1);

INSERT INTO SPA_COMPETITION(name, year, is_done, position_number) VALUES ('Konkurs za 2023 godinu', '2023', true, 2);
INSERT INTO COMPETITOR(full_name, jmbg, status, dead_family_member, injury_type, score) VALUES ('Milovan Djuric', '1', 2, 1, 1, 20);
INSERT INTO COMPETITOR(full_name, jmbg, status, dead_family_member, injury_type, score) VALUES ('Marko Peric', '2', 2, 0, 1, 25);
INSERT INTO COMPETITOR(full_name, jmbg, status, dead_family_member, injury_type, score) VALUES ('Nada Zaric', '3', 2, 0, 2, 30);
INSERT INTO SPA_COMPETITION_COMPETITORS(spa_competition_id, competitors_id) VALUES (1, 1);
INSERT INTO SPA_COMPETITION_COMPETITORS(spa_competition_id, competitors_id) VALUES (1, 2);
INSERT INTO SPA_COMPETITION_COMPETITORS(spa_competition_id, competitors_id) VALUES (1, 3);
INSERT INTO SPA_COMPETITION_SELECTED_COMPETITORS(spa_competition_id, selected_competitors_id) VALUES (1, 2);
INSERT INTO SPA_COMPETITION_SELECTED_COMPETITORS(spa_competition_id, selected_competitors_id) VALUES (1, 3);

-- INSERT INTO SOLDIER (full_name, jmbg, months, category, monthly_contribution) VALUES ('Pera Peric', '076436335357', 0, 0, 0.0);
-- INSERT INTO SOLDIER (full_name, jmbg, months, category, monthly_contribution) VALUES ('Marko Markovic', '076437565357', 0, 0, 0.0);
-- INSERT INTO SOLDIER (full_name, jmbg, months, category, monthly_contribution) VALUES ('Despot Despotovic', '4534646342345', 0, 0, 0.0)