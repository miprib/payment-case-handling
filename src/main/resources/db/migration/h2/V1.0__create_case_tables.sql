CREATE SCHEMA payment;

CREATE SEQUENCE case_sequence START WITH 11 INCREMENT BY 1; -- Starting with 11 because we have 'PCH_10' as the last test data insert

CREATE TABLE payment.payment_case
(
	id 			VARCHAR(25) 	NOT NULL,
	country		CHAR(2)			NOT NULL,
	amount		DECIMAL(19,2)	NOT NULL,
	currency	CHAR(3)			NOT NULL,
	payment_id	BIGINT			NOT NULL,
	
	CONSTRAINT PK__employee__id PRIMARY KEY (id)
);

CREATE TABLE payment.case_resolution
(
	payment_case_id 	VARCHAR(25)	NOT NULL,
	resolution			VARCHAR(25)	NOT NULL,
	
	CONSTRAINT PK__case_resolution__id PRIMARY KEY (payment_case_id),
	CONSTRAINT FK__case_resolution__payment_case FOREIGN KEY (payment_case_id) REFERENCES payment.payment_case(id)
);

INSERT INTO payment.payment_case(id, country, amount, currency, payment_id) VALUES
('PCH_1', 'DK', 100, 'EUR', 1),
('PCH_2', 'DK', 100, 'EUR', 2),
('PCH_3', 'FI', 100, 'EUR', 3),
('PCH_4', 'FI', 100, 'EUR', 4),
('PCH_5', 'NO', 100, 'EUR', 5),
('PCH_6', 'NO', 100, 'EUR', 6),
('PCH_7', 'SE', 100, 'EUR', 7),
('PCH_8', 'SE', 100, 'EUR', 8),
('PCH_9', 'SE', 100, 'EUR', 9),
('PCH_10', 'SE', 100, 'EUR', 10);

INSERT INTO payment.case_resolution(payment_case_id, resolution) VALUES
('PCH_1', 'ACCEPTED'),
('PCH_3', 'REJECTED'),
('PCH_5', 'ACCEPTED'),
('PCH_7', 'REJECTED'),
('PCH_8', 'ACCEPTED');