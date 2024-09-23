-- Desabilitar a verificação de identidade
SET REFERENTIAL_INTEGRITY FALSE;

-- Inserir dados na tabela tb_users
INSERT INTO tb_users (id, created_at, updated_at, blocked, name, password, username) VALUES 
(1, '2024-09-22T23:06:39.9729189', '2024-09-22T23:06:39.9729403', FALSE, 'teste1', '12345', '734.245.300-50'),
(2, '2024-09-22T23:06:51.7766100', '2024-09-22T23:06:51.7766230', FALSE, 'teste2', '12345', '228.376.504-80');

-- Inserir dados na tabela tb_client
INSERT INTO tb_client (id, created_at, updated_at, account_code, balance, cpf, data_nascimento, email, nome, phone_number, user_id) VALUES 
(1, '2024-09-22T23:06:40.0285257', '2024-09-22T23:06:40.0285407', '0000000-0', 0.00, '734.245.300-50', '2000-07-04', '', 'teste1', '', 1),
(2, '2024-09-22T23:06:51.7864996', '2024-09-22T23:06:51.7865094', '0000000-0', 0.00, '228.376.504-80', '2000-07-04', '', 'teste2', '', 2);

-- Inserir dados na tabela tb_users_profiles
INSERT INTO tb_users_profiles (tb_users_id, roles) VALUES 
(1, 'CLIENT'),
(2, 'CLIENT');

-- Habilitar a verificação de identidade
SET REFERENTIAL_INTEGRITY TRUE;
