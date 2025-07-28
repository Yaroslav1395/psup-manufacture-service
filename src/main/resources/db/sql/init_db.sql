--Та
CREATE TABLE employees (
    id BIGSERIAL  NOT NULL PRIMARY KEY,
    fio VARCHAR(255)
);

-- Таблица состояний заявок
CREATE TABLE manufacture_bid_states (
    id SERIAL PRIMARY KEY,
    state VARCHAR(255) NOT NULL UNIQUE,
    description VARCHAR(255) NOT NULL
);

-- Таблица заявок на производство
CREATE TABLE manufacture_bids (
    id BIGSERIAL PRIMARY KEY,
    product_id BIGINT NOT NULL,
    product_quantity INTEGER NOT NULL,
    manufacture_state_id INTEGER NOT NULL REFERENCES manufacture_bid_states(id),
    created_at TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP
);