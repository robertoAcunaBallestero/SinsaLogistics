CREATE DATABASE proyectosinsalogistic;
USE proyectosinsalogistic;

-- TABLAS 
CREATE TABLE Cliente (
  id_cliente INT AUTO_INCREMENT PRIMARY KEY,
  nombre     VARCHAR(100) NOT NULL,
  correo     VARCHAR(120) NOT NULL,
  direccion  VARCHAR(200) NOT NULL);

CREATE TABLE Asesor (
  id_asesor INT AUTO_INCREMENT PRIMARY KEY,
  nombre    VARCHAR(100) NOT NULL,
  telefono  VARCHAR(45)  NOT NULL,
  correo    VARCHAR(120) NOT NULL);

CREATE TABLE Proveedor (
  id_proveedor INT AUTO_INCREMENT PRIMARY KEY,
  nombre       VARCHAR(100) NOT NULL,
  telefono     INT NOT NULL,
  correo       VARCHAR(120) NOT NULL,
  direccion    VARCHAR(200) NOT NULL);


CREATE TABLE Material (
  id_material  INT AUTO_INCREMENT PRIMARY KEY,
  nombre       VARCHAR(100) NOT NULL,
  precio       INT NOT NULL,
  stock        INT NOT NULL,
  descripcion  VARCHAR(255) NOT NULL,
  id_proveedor INT NULL,
  CONSTRAINT fk_material_proveedor
    FOREIGN KEY (id_proveedor) REFERENCES Proveedor(id_proveedor));

CREATE TABLE Cotizacion (
  id_cotizacion INT AUTO_INCREMENT PRIMARY KEY,
  fecha         DATE NOT NULL,
  estado        VARCHAR(45) NOT NULL,
  total         INT NOT NULL,
  id_cliente    INT NOT NULL,
  CONSTRAINT fk_cotizacion_cliente
    FOREIGN KEY (id_cliente) REFERENCES Cliente(id_cliente));

CREATE TABLE Detalle_cotizacion (
  id_detalle      INT AUTO_INCREMENT PRIMARY KEY,
  cantidad        INT NOT NULL,
  precio_unitario INT NOT NULL,
  subtotal        INT NOT NULL,
  id_cotizacion   INT NOT NULL,
  id_material     INT NOT NULL,
  CONSTRAINT fk_detalle_cotizacion
    FOREIGN KEY (id_cotizacion) REFERENCES Cotizacion(id_cotizacion),
  CONSTRAINT fk_detalle_material
    FOREIGN KEY (id_material) REFERENCES Material(id_material));

CREATE TABLE Movimiento_inventario (
  id_movimiento   INT AUTO_INCREMENT PRIMARY KEY,
  tipo_movimiento VARCHAR(45) NOT NULL,
  cantidad        INT NOT NULL,
  fecha           DATE NOT NULL,
  id_asesor       INT NOT NULL,
  id_material     INT NOT NULL,
  CONSTRAINT fk_mov_asesor
    FOREIGN KEY (id_asesor) REFERENCES Asesor(id_asesor),
  CONSTRAINT fk_mov_material
    FOREIGN KEY (id_material) REFERENCES Material(id_material));

CREATE TABLE Cliente_asesor (
  id_cliente_asesor INT AUTO_INCREMENT PRIMARY KEY,
  id_cliente        INT NOT NULL,
  id_asesor         INT NOT NULL,
  CONSTRAINT fk_cliente_asesor_cliente
    FOREIGN KEY (id_cliente) REFERENCES Cliente(id_cliente),
  CONSTRAINT fk_cliente_asesor_asesor
    FOREIGN KEY (id_asesor) REFERENCES Asesor(id_asesor));

CREATE TABLE Material_proveedor (
  id_material_proveedor INT AUTO_INCREMENT PRIMARY KEY,
  id_material           INT NOT NULL,
  id_proveedor          INT NOT NULL,
  CONSTRAINT fk_matprov_material
    FOREIGN KEY (id_material) REFERENCES Material(id_material),
  CONSTRAINT fk_matprov_proveedor
    FOREIGN KEY (id_proveedor) REFERENCES Proveedor(id_proveedor));

INSERT INTO rol (nombre) VALUES ('ADMIN');
INSERT INTO rol (nombre) VALUES ('CLIENTE');

INSERT INTO usuario (username, password, activo) 
VALUES ('admin', '$2a$10$encryptedPassword', 1);


