CREATE TABLE drugs (
                       drug_id INT PRIMARY KEY,
                       drug_name VARCHAR(50),
                       drug_category VARCHAR(50),
                       drug_type VARCHAR(50),
                       expiry_date TIMESTAMP,
                       date_created TIMESTAMP,
                       quantity BIGINT
                   );