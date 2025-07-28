INSERT INTO employees (fio)
VALUES
('Иванов Иван Иванович');

INSERT INTO manufacture_bid_states (state, description)
VALUES
('CREATED', 'Создана'),
('IN_PROGRESS', 'В процессе'),
('TRANSIT_TO_STORE', 'В пути на склад'),
('COMPLETED', 'Завершена'),
('CANCELED', 'Отменена');
