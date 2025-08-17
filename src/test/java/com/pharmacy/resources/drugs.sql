CREATE TABLE drugs (
                       drug_id INT PRIMARY KEY,
                       drug_name VARCHAR(50),
                       drug_category VARCHAR(50),
                       drug_type VARCHAR(50),
                       expiry_date DATE,
                       date_created DATE,
                       manufacture_date DATE,
                       quantity BIGINT
                   );