


CREATE TABLE drugs
(
    drugId          INT PRIMARY KEY,
    drugName        VARCHAR(50),
    drugCategory    VARCHAR(50),
    drugType        VARCHAR(50),
    expiryDate      DATE,
    dateCreated     DATE,
    manufactureDate DATE,
    quantity BIGINT
);



CREATE TABLE prescriptions (
    prescriptionId INT PRIMARY KEY,
    patientId INT,
    patientName VARCHAR(200),
    doctorId INT,
    doctorName VARCHAR(200),
    diagnosis TEXT,
    dateCreated DATE,
    isResolved BOOLEAN
);

CREATE TABLE prescription_drugs(
    prescriptionDrugId BIGINT AUTO_INCREMENT PRIMARY KEY,
    drugId INT,
    prescriptionId INT,
    quantityPrescribed BIGINT,

    CONSTRAINT fk_prescription
        FOREIGN KEY (prescriptionId) REFERENCES prescriptions(prescriptionId)
        ON DELETE CASCADE

);




