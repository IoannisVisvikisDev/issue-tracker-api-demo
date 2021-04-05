INSERT INTO USER (username, password) VALUES ('UserOne', 'FirstPassword!');
INSERT INTO USER (username, password) VALUES ('UserTwo', 'SecondPassword@');

INSERT INTO ISSUE (title, description, date_created, user_opened, assigned_user, editor_user, date_last_edited)
            VALUES ('Fix Me', 'Fix bug in sandbox', NOW(), 1, 2, 1, NOW());
INSERT INTO ISSUE (title, description, date_created, user_opened, assigned_user, editor_user, date_last_edited)
            VALUES ('Fix Me Too', 'Fix bug in production', NOW(), 2, 1, 2, NOW());