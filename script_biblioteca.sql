USE biblioteca_db;

DROP TABLE IF EXISTS sesion;
DROP TABLE IF EXISTS permiso;
DROP TABLE IF EXISTS cuenta;
DROP TABLE IF EXISTS rol;

CREATE TABLE rol (
    idRol INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(30) NOT NULL UNIQUE,
    descripcion VARCHAR(200)
);

CREATE TABLE cuenta (
    idCuenta INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    correo VARCHAR(100) NOT NULL UNIQUE,
    contrasena CHAR(64) NOT NULL,
    idRol INT NOT NULL,
    estado ENUM('Activo', 'Inactivo') NOT NULL DEFAULT 'Activo',
    FOREIGN KEY (idRol) REFERENCES rol(idRol)
);

CREATE TABLE permiso (
    idPermiso INT AUTO_INCREMENT PRIMARY KEY,
    idRol INT NOT NULL,
    tabla VARCHAR(30) NOT NULL,
    puedeCrear BOOLEAN NOT NULL DEFAULT 0,
    puedeLeer BOOLEAN NOT NULL DEFAULT 0,
    puedeActualizar BOOLEAN NOT NULL DEFAULT 0,
    puedeEliminar BOOLEAN NOT NULL DEFAULT 0,
    UNIQUE (idRol, tabla),
    FOREIGN KEY (idRol) REFERENCES rol(idRol)
);

CREATE TABLE sesion (
    idSesion INT AUTO_INCREMENT PRIMARY KEY,
    idCuenta INT NOT NULL,
    fechaInicio DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    fechaCierre DATETIME NULL,
    FOREIGN KEY (idCuenta) REFERENCES cuenta(idCuenta)
);

INSERT INTO rol (nombre, descripcion) VALUES
('Administrador', 'Control total del sistema'),
('Bibliotecario', 'Operación diaria de la biblioteca'),
('Consulta', 'Solo lectura del catálogo');

INSERT INTO permiso (idRol, tabla, puedeCrear, puedeLeer, puedeActualizar, puedeEliminar) VALUES
(1, 'autor', 1, 1, 1, 1),
(1, 'bibliotecario', 1, 1, 1, 1),
(1, 'editorial', 1, 1, 1, 1),
(1, 'libro', 1, 1, 1, 1),
(1, 'multa', 1, 1, 1, 1),
(1, 'prestamo', 1, 1, 1, 1),
(1, 'revista', 1, 1, 1, 1),
(1, 'usuario', 1, 1, 1, 1),
(2, 'autor', 1, 1, 1, 0),
(2, 'bibliotecario', 0, 0, 0, 0),
(2, 'editorial', 1, 1, 1, 0),
(2, 'libro', 1, 1, 1, 0),
(2, 'multa', 1, 1, 1, 0),
(2, 'prestamo', 1, 1, 1, 0),
(2, 'revista', 1, 1, 1, 0),
(2, 'usuario', 1, 1, 1, 0),
(3, 'autor', 0, 1, 0, 0),
(3, 'bibliotecario', 0, 0, 0, 0),
(3, 'editorial', 0, 1, 0, 0),
(3, 'libro', 0, 1, 0, 0),
(3, 'multa', 0, 0, 0, 0),
(3, 'prestamo', 0, 0, 0, 0),
(3, 'revista', 0, 1, 0, 0),
(3, 'usuario', 0, 0, 0, 0);

INSERT INTO cuenta (nombre, correo, contrasena, idRol, estado) VALUES
('Administrador General', 'admin@biblioteca.com', SHA2('Admin123', 256), 1, 'Activo'),
('Bibliotecario Demo', 'bibliotecario@biblioteca.com', SHA2('Biblio123', 256), 2, 'Activo'),
('Consulta Demo', 'consulta@biblioteca.com', SHA2('Consulta123', 256), 3, 'Activo'),
('Cuenta Inactiva', 'inactiva@biblioteca.com', SHA2('Inactiva123', 256), 3, 'Inactivo');