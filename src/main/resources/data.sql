

INSERT INTO privilege (privilege_id, name, description) VALUES (1, 'PRIVILEGE_USER_READ', 'description for privilege user read');

INSERT INTO role (role_id, name, description) VALUES (1, 'ROLE_USER', 'description for role user');

INSERT INTO role_privileges (role_role_id, privileges_privilege_id) VALUES (1, 1);

