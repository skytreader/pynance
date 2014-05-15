CREATE TABLE IF NOT EXISTS users(
    userid INTEGER AUTO_INCREMENT,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    total_budget DECIMAL(8, 2) DEFAULT 0.0,
    create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_updater INTEGER NOT NULL,
    last_update TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    can_read BOOLEAN NOT NULL DEFAULT TRUE,
    can_write BOOLEAN NOT NULL DEFAULT FALSE,
    can_exec BOOLEAN NOT NULL DEFAULT FALSE,
    PRIMARY KEY (userid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO users (username, password, last_updater, can_read, can_write, can_exec)
VALUES ('root', 'root', 0, 1, 1, 1);

CREATE TABLE IF NOT EXISTS expenses(
    expense_id INTEGER AUTO_INCREMENT,
    userid INTEGER,
    expense_desc VARCHAR(255) NOT NULL,
    expense_cost DECIMAL(8, 2) NOT NULL,
    is_planned BOOLEAN DEFAULT FALSE,
    expense_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_updater INTEGER NOT NULL,
    last_update TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (expense_id),
    FOREIGN KEY (last_updater) REFERENCES users (userid),
    FOREIGN KEY (userid) REFERENCES users (userid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS expense_breakdown(
    breakdown_id INTEGER AUTO_INCREMENT,
    expense_id INTEGER,
    expense_desc VARCHAR(255) NOT NULL,
    expense_cost DECIMAL(8, 2) NOT NULL,
    create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_updater INTEGER NOT NULL,
    last_update TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (breakdown_id),
    FOREIGN KEY (expense_id) REFERENCES expenses (expense_id),
    FOREIGN KEY (last_updater) REFERENCES users (userid)
) ENGINE = INNODB DEFAULT CHARSET = utf8;

CREATE TABLE IF NOT EXISTS expense_projections(
    projection_id INTEGER AUTO_INCREMENT,
    userid INTEGER NOT NULL,
    expense_desc VARCHAR(255) NOT NULL,
    expense_cost DECIMAL(8, 2) NOT NULL,
    expense_expected_in INTEGER NOT NULL,
    recurrence_factor INTEGER DEFAULT 0,
    expense_fulfilled INTEGER,
    create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_updater INTEGER NOT NULL,
    last_update TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (projection_id),
    FOREIGN KEY (last_updater) REFERENCES users (userid),
    FOREIGN KEY (userid) REFERENCES users (userid),
    FOREIGN KEY (expense_fulfilled) REFERENCES expenses (expense_id)
) ENGINE = INNODB DEFAULT CHARSET = utf8;

CREATE TABLE IF NOT EXISTS history(
    history_id INTEGER AUTO_INCREMENT,
    userid INTEGER,
    snapshot_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    current_budget DECIMAL(8, 2) NOT NULL,
    period_expenses DECIMAL(8, 2) NOT NULL,
    create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_updater INTEGER NOT NULL,
    last_update TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (history_id),
    FOREIGN KEY (last_updater) REFERENCES users (userid),
    FOREIGN KEY (userid) REFERENCES users (userid)
) ENGINE = INNODB DEFAULT CHARSET = utf8;
