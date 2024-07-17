-- User table
CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

-- Wallet table
CREATE TABLE IF NOT EXISTS wallet (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    usdt_balance DOUBLE NOT NULL,
    btc_balance DOUBLE NOT NULL,
    eth_balance DOUBLE NOT NULL,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Trade table
CREATE TABLE IF NOT EXISTS trade (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    crypto_pair VARCHAR(10) NOT NULL,
    type VARCHAR(4) NOT NULL,
    price DOUBLE NOT NULL,
    amount DOUBLE NOT NULL,
    timestamp TIMESTAMP NOT NULL,
    CONSTRAINT fk_trade_user FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Aggregated Price table
CREATE TABLE IF NOT EXISTS aggregated_price (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    crypto_pair VARCHAR(10) NOT NULL,
    bid_price DOUBLE NOT NULL,
    ask_price DOUBLE NOT NULL,
    timestamp TIMESTAMP NOT NULL
);
