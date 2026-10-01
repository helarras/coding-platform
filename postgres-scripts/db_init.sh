#!/usr/bin/env bash

set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
                 CREATE TABLE IF NOT EXISTS users (
                     id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                     username VARCHAR(255) NOT NULL UNIQUE,
                     email VARCHAR(255) NOT NULL UNIQUE,
                     password_hash TEXT,
                     created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
                     updated_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP
                 );

                    CREATE TABLE IF NOT EXISTS submissions (
                         id UUID PRIMARY KEY,
                         user_id UUID ,
                         problem_id UUID,
                         source_code TEXT,
                         status VARCHAR(50),
                         fail_reason TEXT,
                         created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP
                     );

                     CREATE TABLE IF NOT EXISTS problems (
                        id UUID PRIMARY KEY,
                        title VARCHAR(255) NOT NULL,
                        description TEXT NOT NULL,
                        status VARCHAR(30) NOT NULL,
                        difficulty VARCHAR(30) NOT NULL
                     );

                    CREATE TABLE IF NOT EXISTS problem_test_cases (
                       id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                       problem_id UUID REFERENCES problems (id) ON DELETE CASCADE,
                       input TEXT NOT NULL,
                       expected_output TEXT NOT NULL
                    );
EOSQL