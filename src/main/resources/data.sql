-- Initial user
INSERT INTO users (email, password) VALUES ('jq@gmail.com', 'jq123');

-- Initial wallet balance for the user
INSERT INTO wallet (user_id, usdt_balance, btc_balance, eth_balance) VALUES (1, 50000.0, 0.0, 0.0);

