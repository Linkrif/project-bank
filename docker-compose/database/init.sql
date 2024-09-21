IF NOT EXISTS (SELECT * FROM sys.databases WHERE name = 'ProjectBank')
BEGIN
  CREATE DATABASE ProjectBank;
END;
GO