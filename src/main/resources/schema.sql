CREATE SCHEMA IF NOT EXISTS soccer_manager;

CREATE TABLE soccer_manager.users
(
    id       SERIAL PRIMARY KEY,
    email    VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    team_id  INT UNIQUE,
    FOREIGN KEY (team_id) REFERENCES soccer_manager.teams (id) ON DELETE SET NULL
);

CREATE TABLE soccer_manager.teams
(
    id         SERIAL PRIMARY KEY,
    name       VARCHAR(100)   NOT NULL UNIQUE,
    country    VARCHAR(100)   NOT NULL,
    budget     NUMERIC(15, 2) NOT NULL,
    team_value NUMERIC(15, 2)
);

CREATE TABLE soccer_manager.players
(
    id           SERIAL PRIMARY KEY,
    first_name   VARCHAR(100)   NOT NULL,
    last_name    VARCHAR(100)   NOT NULL,
    age          INT            NOT NULL,
    position     VARCHAR(20)    NOT NULL,
    status       VARCHAR(20),
    market_value NUMERIC(15, 2) NOT NULL,
    team_id      INT,
    FOREIGN KEY (team_id) REFERENCES soccer_manager.teams (id) ON DELETE SET NULL
);

CREATE OR REPLACE FUNCTION update_team_value()
    RETURNS TRIGGER AS
$$
BEGIN
    IF TG_OP = 'UPDATE' THEN
        -- Update the old team's value
        UPDATE soccer_manager.teams
        SET team_value = (SELECT COALESCE(SUM(market_value), 0)
                          FROM soccer_manager.players
                          WHERE team_id = OLD.team_id)
        WHERE id = OLD.team_id;

        -- Update the new team's value
        UPDATE soccer_manager.teams
        SET team_value = (SELECT COALESCE(SUM(market_value), 0)
                          FROM soccer_manager.players
                          WHERE team_id = NEW.team_id)
        WHERE id = NEW.team_id;
    END IF;

    -- Handle INSERT
    IF TG_OP = 'INSERT' THEN
        UPDATE soccer_manager.teams
        SET team_value = (SELECT COALESCE(SUM(market_value), 0)
                          FROM soccer_manager.players
                          WHERE team_id = NEW.team_id)
        WHERE id = NEW.team_id;
    END IF;

    -- Handle DELETE
    IF TG_OP = 'DELETE' THEN
        UPDATE soccer_manager.teams
        SET team_value = (SELECT COALESCE(SUM(market_value), 0)
                          FROM soccer_manager.players
                          WHERE team_id = OLD.team_id)
        WHERE id = OLD.team_id;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_update_team_value
    AFTER INSERT OR UPDATE OR DELETE
    ON soccer_manager.players
    FOR EACH ROW
EXECUTE FUNCTION update_team_value();


DROP TABLE IF EXISTS soccer_manager.players CASCADE;

DROP TABLE IF EXISTS soccer_manager.teams CASCADE;

DROP TABLE IF EXISTS soccer_manager.users CASCADE;

DROP TRIGGER IF EXISTS trg_update_team_value ON soccer_manager.players;

DROP SCHEMA IF EXISTS soccer_manager CASCADE;

