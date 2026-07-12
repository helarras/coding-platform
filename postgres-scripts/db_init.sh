#!/usr/bin/env bash

set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
                 CREATE TABLE IF NOT EXISTS users (
                     id BIGSERIAL PRIMARY KEY,
                     username VARCHAR(255) NOT NULL UNIQUE,
                     email VARCHAR(255) NOT NULL UNIQUE,
                     password TEXT,
                     created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
                     updated_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP
                 );

                  CREATE TABLE IF NOT EXISTS exercises (
                      id BIGINT PRIMARY KEY,
                      author_id BIGSERIAL REFERENCES users (id),
                      title VARCHAR(255) NOT NULL,
                      description_markdown TEXT NOT NULL,
                      difficulty VARCHAR(255) NOT NULL,
                      starter_code TEXT
                  );

                  CREATE TABLE IF NOT EXISTS testcases (
                      id BIGINT PRIMARY KEY,
                      exercise_id BIGINT REFERENCES exercises (id),
                      input_data TEXT,
                      expected_output TEXT,
                      is_hidden BOOLEAN DEFAULT FALSE
                  );

                  CREATE TABLE IF NOT EXISTS submission (
                      id BIGINT PRIMARY KEY,
                      user_id BIGSERIAL REFERENCES users (id),
                      exercise_id BIGINT REFERENCES exercises (id),
                      submitted_code TEXT,
                      status VARCHAR(255),
                      timestamp TIMESTAMP
                  );
EOSQL