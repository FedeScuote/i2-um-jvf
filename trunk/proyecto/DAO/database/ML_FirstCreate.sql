CREATE TABLE desafios (
  idDesafio INT NOT NULL AUTO_INCREMENT,
  monto INT NULL,
  fechaHoraInicioD DATETIME NULL,
  estadoD VARCHAR(10) NULL,
  PRIMARY KEY(idDesafio)
);

CREATE TABLE juegos (
  idJuego INT NOT NULL AUTO_INCREMENT,
  juego VARCHAR(16) NULL,
  descripcion TEXT NULL,
  PRIMARY KEY(idJuego)
);

CREATE TABLE partidas (
  idPartida INT NOT NULL AUTO_INCREMENT,
  torneos_idTorneo INT NOT NULL,
  fechaHoraInicioP DATETIME NULL,
  estadoP VARCHAR(10) NULL,
  PRIMARY KEY(idPartida, torneos_idTorneo),
  INDEX Partidas_FKIndex1(torneos_idTorneo)
);

CREATE TABLE posiciones (
  idPosicion INT NOT NULL AUTO_INCREMENT,
  puesto INT NULL,
  PRIMARY KEY(idPosicion)
);

CREATE TABLE ranking (
  usuarios_idusuario INT NOT NULL,
  juegos_idJuego INT NOT NULL,
  usuarioRanking INT NULL,
  ganadas INT NULL,
  PRIMARY KEY(usuarios_idusuario, juegos_idJuego),
  INDEX usuarios_has_juegos_FKIndex1(usuarios_idusuario),
  INDEX usuarios_has_juegos_FKIndex2(juegos_idJuego)
);

CREATE TABLE torneos (
  idTorneo INT NOT NULL AUTO_INCREMENT,
  descripcion VARCHAR(50) NULL,
  pozo INT NULL,
  costoInscripcion INT NULL,
  fechaHoraInicioT DATETIME NULL,
  estadoT VARCHAR(10) NULL,
  PRIMARY KEY(idTorneo)
);

CREATE TABLE usuarios (
  idusuario INT NOT NULL AUTO_INCREMENT,
  usuario VARCHAR(16) NULL,
  clave VARCHAR(16) NULL,
  nivelPrivilegio INT NULL,
  virtual INT NULL,
  credito INT NULL,
  partidasGanadas INT NULL,
  nombre VARCHAR(16) NULL,
  apellido VARCHAR(16) NULL,
  pais VARCHAR(16) NULL,
  PRIMARY KEY(idusuario)
);

CREATE TABLE usuarios_has_juegos_desafios (
  juegos_idJuego INT NOT NULL,
  desafios_idDesafio INT NOT NULL,
  usuarios_idusuario INT NOT NULL,
  usuarioGanadorD INT NULL,
  PRIMARY KEY(juegos_idJuego, desafios_idDesafio, usuarios_idusuario),
  INDEX Juegos_has_Desafios_FKIndex1(juegos_idJuego),
  INDEX Juegos_has_Desafios_FKIndex2(desafios_idDesafio),
  INDEX Juegos_has_Desafios_FKIndex3(usuarios_idusuario)
);

CREATE TABLE usuarios_has_juegos_partidas (
  usuarios_idusuario INT NOT NULL,
  partidas_idPartida INT NOT NULL,
  juegos_idJuego INT NOT NULL,
  Partidas_Torneos_idTorneo INT NOT NULL,
  usuarioGanadorP INT NULL,
  PRIMARY KEY(usuarios_idusuario, partidas_idPartida, juegos_idJuego, Partidas_Torneos_idTorneo),
  INDEX usuarios_has_Partidas_FKIndex1(usuarios_idusuario),
  INDEX usuarios_has_Partidas_FKIndex2(partidas_idPartida),
  INDEX usuarios_has_Partidas_FKIndex3(juegos_idJuego)
);

CREATE TABLE usuarios_has_juegos_torneos (
  usuarios_idusuario INT NOT NULL,
  torneos_idTorneo INT NOT NULL,
  juegos_idJuego INT NOT NULL,
  PRIMARY KEY(usuarios_idusuario, torneos_idTorneo, juegos_idJuego),
  INDEX usuarios_has_torneos_FKIndex1(usuarios_idusuario),
  INDEX usuarios_has_torneos_FKIndex2(torneos_idTorneo),
  INDEX usuarios_has_torneos_FKIndex3(juegos_idJuego)
);

CREATE TABLE usuarios_has_torneos_posiciones (
  posiciones_idPosicion INT NOT NULL,
  usuarios_idusuario INT NOT NULL,
  torneos_idTorneo INT NOT NULL,
  PRIMARY KEY(posiciones_idPosicion, usuarios_idusuario, torneos_idTorneo),
  INDEX usuarios_has_Posiciones_FKIndex1(posiciones_idPosicion),
  INDEX usuarios_has_Posiciones_FKIndex2(usuarios_idusuario),
  INDEX usuarios_has_Torneos_FKIndex3(torneos_idTorneo)
);


