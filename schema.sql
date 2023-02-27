-- Run as
-- sudo -u postgres psql -f schema.sql
CREATE DATABASE pynance;
CREATE USER pynance WITH PASSWORD 'localdev';
GRANT ALL PRIVILEGES ON DATABASE pynance TO pynance;