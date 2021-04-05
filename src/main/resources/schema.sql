CREATE TABLE USER (user_id int PRIMARY KEY AUTO_INCREMENT, username VARCHAR(200) NOT NULL, password VARCHAR(300) NOT NULL);

CREATE TABLE ISSUE (issue_id int PRIMARY KEY AUTO_INCREMENT, title VARCHAR(200) NOT NULL, description VARCHAR(1000) NOT NULL,
                    date_created TIMESTAMP NOT NULL, user_opened int NOT NULL, assigned_user int NOT NULL, editor_user int NOT NULL,
                    date_last_edited TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, FOREIGN KEY (assigned_user) REFERENCES USER(user_id),
                    FOREIGN KEY (user_opened) REFERENCES USER(user_id) ON UPDATE CASCADE ON DELETE RESTRICT);