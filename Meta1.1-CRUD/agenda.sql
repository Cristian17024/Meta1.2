 CREATE DATABASE agenda;

USE agenda;

CREATE TABLE Personas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    direccion VARCHAR(200)
);

CREATE TABLE Telefonos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    personaId INT NOT NULL,
    telefono VARCHAR(20) NOT NULL,
    FOREIGN KEY (personaId) REFERENCES Personas(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

INSERT INTO Personas (nombre, direccion)
VALUES ('John Doe', 'Calle Falsa 123');

INSERT INTO Telefonos (personaId, telefono)
VALUES (1, '555-7654321');

INSERT INTO Telefonos (personaId, telefono)
VALUES (1, '666-1234567');