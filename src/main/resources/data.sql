
INSERT INTO soccer_manager.teams (name, country, budget)
VALUES ('Thunderbolts', 'Germany', 3000000),
       ('Crimson Falcons', 'Brazil', 3000000),
       ('Golden Eagles', 'USA', 3000000);


INSERT INTO soccer_manager.users (email, password, team_id)
VALUES ('user1@example.com', '$2a$10$LItYRB.kFQut3En14b2S2.fpPmmWhgO/HEGGn7jVKMIFnyTovj6ua', 1),
       ('user2@example.com', '$2a$10$jmVC71toD2UOUbe/vPCrRuC41iJpOU2X9x85DT87F/wHDHvTg3DE.', 2),
       ('user3@example.com', '$2a$10$qpjWRDlpjbKOe5u0zm4K5O8SCtQRH/bYD/aIK5pPbJOKFTtc7CcUW', 3);


INSERT INTO soccer_manager.players (first_name, last_name, age, position, market_value, team_id)
VALUES ('John', 'Smith', 24, 'GOALKEEPER', 500000, 1),
       ('Paul', 'Jones', 26, 'GOALKEEPER', 500000, 1),
       ('Mike', 'Brown', 23, 'GOALKEEPER', 500000, 1),
       ('Chris', 'Davis', 29, 'DEFENDER', 500000, 1),
       ('Tom', 'Clark', 22, 'DEFENDER', 500000, 1),
       ('James', 'Taylor', 30, 'DEFENDER', 500000, 1),
       ('Peter', 'Evans', 27, 'DEFENDER', 500000, 1),
       ('Henry', 'Thomas', 25, 'DEFENDER', 500000, 1),
       ('Jack', 'Wilson', 28, 'MIDFIELDER', 500000, 1),
       ('Oliver', 'Moore', 21, 'MIDFIELDER', 500000, 1),
       ('Harry', 'Martin', 26, 'MIDFIELDER', 500000, 1),
       ('George', 'Hall', 29, 'MIDFIELDER', 500000, 1),
       ('Charlie', 'King', 24, 'MIDFIELDER', 500000, 1),
       ('Oscar', 'Wright', 23, 'MIDFIELDER', 500000, 1),
       ('Theo', 'Adams', 28, 'ATTACKER', 500000, 1),
       ('Finn', 'Baker', 25, 'ATTACKER', 500000, 1),
       ('Lucas', 'Green', 27, 'ATTACKER', 500000, 1),
       ('Hugo', 'Carter', 26, 'ATTACKER', 500000, 1),
       ('Archie', 'Young', 22, 'ATTACKER', 500000, 1),
       ('Leo', 'Turner', 30, 'ATTACKER', 500000, 1);


INSERT INTO soccer_manager.players (first_name, last_name, age, position, market_value, team_id)
VALUES ('Liam', 'Hill', 23, 'GOALKEEPER', 500000, 2),
       ('Noah', 'Ward', 29, 'GOALKEEPER', 500000, 2),
       ('Ethan', 'Scott', 24, 'GOALKEEPER', 500000, 2),
       ('Logan', 'Harris', 26, 'DEFENDER', 500000, 2),
       ('Mason', 'Walker', 22, 'DEFENDER', 500000, 2),
       ('Lucas', 'Roberts', 28, 'DEFENDER', 500000, 2),
       ('Elijah', 'Phillips', 25, 'DEFENDER', 500000, 2),
       ('William', 'Campbell', 30, 'DEFENDER', 500000, 2),
       ('James', 'Parker', 23, 'MIDFIELDER', 500000, 2),
       ('Alexander', 'Wood', 24, 'MIDFIELDER', 500000, 2),
       ('Sebastian', 'Morris', 27, 'MIDFIELDER', 500000, 2),
       ('Benjamin', 'Cooper', 26, 'MIDFIELDER', 500000, 2),
       ('Michael', 'Foster', 25, 'MIDFIELDER', 500000, 2),
       ('Daniel', 'Bailey', 29, 'MIDFIELDER', 500000, 2),
       ('Matthew', 'Rogers', 23, 'ATTACKER', 500000, 2),
       ('Aiden', 'Gray', 28, 'ATTACKER', 500000, 2),
       ('Joseph', 'Reed', 26, 'ATTACKER', 500000, 2),
       ('Samuel', 'Howard', 22, 'ATTACKER', 500000, 2),
       ('David', 'Ward', 30, 'ATTACKER', 500000, 2),
       ('Andrew', 'Carter', 27, 'ATTACKER', 500000, 2);

INSERT INTO soccer_manager.players (first_name, last_name, age, position, market_value, team_id)
VALUES
    ('Oliver', 'Bennett', 24, 'GOALKEEPER', 500000, 3),
    ('Henry', 'Jenkins', 25, 'GOALKEEPER', 500000, 3),
    ('Jack', 'Peterson', 28, 'GOALKEEPER', 500000, 3),
    ('Jacob', 'Long', 23, 'DEFENDER', 500000, 3),
    ('Thomas', 'Murphy', 29, 'DEFENDER', 500000, 3),
    ('Charles', 'Bell', 26, 'DEFENDER', 500000, 3),
    ('Christopher', 'Kelly', 22, 'DEFENDER', 500000, 3),
    ('Anthony', 'Sanders', 30, 'DEFENDER', 500000, 3),
    ('Jonathan', 'Ramirez', 27, 'MIDFIELDER', 500000, 3),
    ('Joshua', 'Fisher', 24, 'MIDFIELDER', 500000, 3),
    ('Ryan', 'Ross', 23, 'MIDFIELDER', 500000, 3),
    ('Nicholas', 'Powell', 28, 'MIDFIELDER', 500000, 3),
    ('Isaac', 'Ward', 25, 'MIDFIELDER', 500000, 3),
    ('Owen', 'Stewart', 26, 'MIDFIELDER', 500000, 3),
    ('Caleb', 'Cruz', 23, 'ATTACKER', 500000, 3),
    ('Nathan', 'Ortiz', 22, 'ATTACKER', 500000, 3),
    ('Luke', 'Reynolds', 27, 'ATTACKER', 500000, 3),
    ('Gabriel', 'Perry', 28, 'ATTACKER', 500000, 3),
    ('Wyatt', 'Hughes', 29, 'ATTACKER', 500000, 3),
    ('Isaiah', 'Coleman', 30, 'ATTACKER', 500000, 3);