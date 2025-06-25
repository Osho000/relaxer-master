CREATE TABLE IF NOT EXISTS users_schema.t_roles(
    id SERIAL PRIMARY KEY,
    c_name VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS users_schema.t_credentials(
    id SERIAL PRIMARY KEY,
    c_username VARCHAR(50) UNIQUE NOT NULL,
    c_password VARCHAR(255) NOT NULL,
    c_user_id INTEGER,
    c_role_id INTEGER,
    FOREIGN KEY (c_user_id) REFERENCES users_schema.t_users(id),
    FOREIGN KEY (c_role_id) REFERENCES users_schema.t_roles(id)
);