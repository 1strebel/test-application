INSERT INTO APP_USER (username, password) VALUES
                       ('admin', '$2a$10$4Am31HlTGMy0/RD8XWTC3.bTz.UStJJZUF06UTJdO8T39vjeFwxnW'),
                       ('user1', '$2a$10$6XJp.uKjyQdZrCLqjt6f6.SMknHydg4/amC5y5eDnd0tYxQWyaf/q'),
                       ('user2', '$2a$10$T68riq8fe7k8KPk35p2yCOoxQmOk3.kuNXamXXtgJRqsbHutkuMD2'),
                       ('user3', '$2a$10$t8JR299vjtCmvRNbFHtNIuH9CFWrW1hXnCB7tokHLaimgC5yRXq/e');

INSERT INTO ROLE (name) VALUES
                        ('ROLE_ADMIN'),
                        ('ROLE_FRONTEND'),
                        ('ROLE_BACKEND');

INSERT INTO USER_ROLE (user_id, role_id) VALUES
((SELECT id FROM APP_USER WHERE username = 'admin'), (SELECT id FROM ROLE WHERE name = 'ROLE_ADMIN')),
((SELECT id FROM APP_USER WHERE username = 'user1'), (SELECT id FROM ROLE WHERE name = 'ROLE_FRONTEND')),
((SELECT id FROM APP_USER WHERE username = 'user2'), (SELECT id FROM ROLE WHERE name = 'ROLE_FRONTEND')),
((SELECT id FROM APP_USER WHERE username = 'user2'), (SELECT id FROM ROLE WHERE name = 'ROLE_BACKEND')),
((SELECT id FROM APP_USER WHERE username = 'user3'), (SELECT id FROM ROLE WHERE name = 'ROLE_BACKEND'));
