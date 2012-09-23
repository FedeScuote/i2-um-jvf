-- phpMyAdmin SQL Dump
-- version 3.4.5
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 23-09-2012 a las 17:46:56
-- Versión del servidor: 5.5.16
-- Versión de PHP: 5.3.8

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `jvm`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `desafios`
--

CREATE TABLE IF NOT EXISTS `desafios` (
  `idDesafio` int(11) NOT NULL AUTO_INCREMENT,
  `monto` int(11) DEFAULT NULL,
  `fechaHoraInicioD` datetime DEFAULT NULL,
  PRIMARY KEY (`idDesafio`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Volcado de datos para la tabla `desafios`
--

INSERT INTO `desafios` (`idDesafio`, `monto`, `fechaHoraInicioD`) VALUES
(1, 200, '2012-09-03 13:00:00'),
(2, 250, '2012-09-12 15:00:00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `juegos`
--

CREATE TABLE IF NOT EXISTS `juegos` (
  `idJuego` int(11) NOT NULL AUTO_INCREMENT,
  `juego` varchar(16) DEFAULT NULL,
  `descripcion` text,
  PRIMARY KEY (`idJuego`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Volcado de datos para la tabla `juegos`
--

INSERT INTO `juegos` (`idJuego`, `juego`, `descripcion`) VALUES
(1, 'Batalla Naval', 'La Batalla naval, (juego de los barquitos o Hundir la flota, nombre con el que se comercializó en España el juego de mesa) deriva del nombre en inglés Battleship. Es un juego de lápiz y papel tradicional de adivinación que involucra a dos participantes.\r\nSe ha comercializado como juego de mesa en distintos formatos por varias marcas, el primero en sacarlo al mercado fue Milton Bradley Company en 1931, y se jugaba con lápiz y papel. En 2012 se ha estrenado una película basada, en el juego, denominada Battleship'),
(2, 'Ludo', 'Ludo (del latín, "yo juego"), también llamado Parkase, es una variación simplificada para niños del juego tradicional indio pachisi hecha en Inglaterra en 1896. Es muy similar a la adaptación occidental llamada parchís.'),
(3, 'Backgammon', 'El backgammon es uno de los juegos más antiguos de los que se tiene constancia. Los historiadores aún hoy discuten acerca de su origen, si bien todo parece apuntar a que ya existían juegos de características similares al backgammon hace 5000 años. Se cree que el antecesor del backgammon pudo ser el Juego Real de Ur, encontrado durante unas excavaciones en la tumba de un rey sumerio en la ciudad mesopotámica de Ur, en el actual Irak. Sin embargo, un descubrimiento posterior parece anticipar la fecha de nacimiento del backgammon alrededor de 100 o 200 años, al encontrarse una mesa de juego durante unas excavaciones arqueológicas en la ciudad de Shahr-i Sokhta, en el actual Irán.\r\nOtros atribuyen el origen del backgammon a un juego de mesa del Antiguo Egipto conocido como Senet del que se tienen referencias gracias a las pinturas encontradas en la pared de una tumba de la dinastía III (2650 a.C.) y a los juegos encontrados en tumbas como la del faraón Tutankamon.\r\nEn Roma existía un juego constituido por una mesa y tres dados conocido como Ludus Duodecim Scriptorum (juego de las doce líneas), que con el paso del tiempo fue conocido con el nombre de Tabula (mesa). Se extendió rápidamente por todas las clases sociales, encontrándose múltiples referencias de este fenómeno. Suetonio, en su Vidas de los Doce Césares, describe el interés obsesivo que tenía por este juego el emperador Claudio, el cual llevaba siempre consigo un juego para entretenerse cuando se encontraba de viaje. Es probable que las legiones romanas jugaran también un papel importante en la expansión del juego (de hecho, en Gran Bretaña se empezó a conocer como "tablas"), pero perdió poco a poco su popularidad con la caída del Imperio.\r\nEn Asia, se jugó antes del siglo IX una versión llamada Nard que difería de la tabula principalmente en el uso de sólo dos dados. En China, se extendió con el nombre de T''shu-Pu, mientras que en Japón fue llamado Sugoroku.\r\nEl juego experimentó un renacimiento en Europa durante las Cruzadas, cuando los soldados llegaron a aprender una versión conocida como tawla por los árabes (y takht-nard, o simplemente nard en persa). A pesar de los numerosos intentos por parte de la Iglesia para prohibirlo, al considerarse el componente de azar contrario a sus postulados, el juego experimentó una gran difusión durante la Edad Media gracias a numerosas versiones como: la tavola reale en Italia, las tablas reales en España, tavli en Grecia , tavla en Turquía, tric trac en Francia, Puff en Alemania, y backgammon o tables en Gran Bretaña.\r\nA pesar de la enorme popularidad que llegó a alcanzar el juego, no fue hasta 1743 cuando Edmon Hoyle publicó un breve tratado en el que se codificaron por primera vez las reglas del backgammon tal y como lo conocemos hoy en día.');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `partidas`
--

CREATE TABLE IF NOT EXISTS `partidas` (
  `idPartida` int(11) NOT NULL AUTO_INCREMENT,
  `torneos_idTorneo` int(11) NOT NULL,
  `fechaHoraInicioP` datetime DEFAULT NULL,
  PRIMARY KEY (`idPartida`,`torneos_idTorneo`),
  KEY `Partidas_FKIndex1` (`torneos_idTorneo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `posiciones`
--

CREATE TABLE IF NOT EXISTS `posiciones` (
  `idPosicion` int(11) NOT NULL AUTO_INCREMENT,
  `puesto` int(11) DEFAULT NULL,
  PRIMARY KEY (`idPosicion`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=56 ;

--
-- Volcado de datos para la tabla `posiciones`
--

INSERT INTO `posiciones` (`idPosicion`, `puesto`) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(8, 8),
(9, 9),
(10, 10),
(11, 11),
(12, 12),
(13, 13),
(14, 14),
(15, 15),
(16, 16),
(17, 17),
(18, 18),
(19, 19),
(20, 20),
(21, 21),
(22, 22),
(23, 23),
(24, 24),
(25, 25),
(26, 26),
(27, 27),
(28, 28),
(29, 29),
(30, 30),
(31, 31),
(32, 32),
(33, 33),
(34, 34),
(35, 35),
(36, 36),
(37, 37),
(38, 38),
(39, 39),
(40, 40),
(41, 41),
(42, 42),
(43, 43),
(44, 44),
(45, 45),
(46, 46),
(47, 47),
(48, 48),
(49, 49),
(50, 50),
(51, 51),
(52, 52),
(53, 53),
(54, 54),
(55, 55);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `torneos`
--

CREATE TABLE IF NOT EXISTS `torneos` (
  `idTorneo` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(50) DEFAULT NULL,
  `pozo` int(11) DEFAULT NULL,
  `costoInscripcion` int(11) DEFAULT NULL,
  `fechaHoraInicioT` datetime DEFAULT NULL,
  PRIMARY KEY (`idTorneo`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Volcado de datos para la tabla `torneos`
--

INSERT INTO `torneos` (`idTorneo`, `descripcion`, `pozo`, `costoInscripcion`, `fechaHoraInicioT`) VALUES
(1, 'Torneo Nacional Batalla Naval 2012', 50000, 50, '2012-09-11 09:00:00'),
(2, 'Torneo Apertura Batalla Naval 2012', 30000, 30, '2012-03-22 09:30:00'),
(3, 'Torneo Nacional Ludo 2012', 2000, 20, '2012-09-10 10:00:00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE IF NOT EXISTS `usuarios` (
  `idusuario` int(11) NOT NULL AUTO_INCREMENT,
  `usuario` varchar(16) DEFAULT NULL,
  `clave` varchar(16) DEFAULT NULL,
  `nivelPrivilegio` int(11) DEFAULT NULL,
  `virtual` int(11) DEFAULT NULL,
  `credito` int(11) DEFAULT NULL,
  `partidasGanadas` int(11) DEFAULT NULL,
  `nombre` varchar(16) DEFAULT NULL,
  `apellido` varchar(16) DEFAULT NULL,
  `pais` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`idusuario`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=11 ;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`idusuario`, `usuario`, `clave`, `nivelPrivilegio`, `virtual`, `credito`, `partidasGanadas`, `nombre`, `apellido`, `pais`) VALUES
(1, 'jhirata', 'jh', 1, 0, 200, 5, 'John', 'Hirata', 'Inglaterra'),
(2, 'vtuyare', 'vt', 1, 0, 200, 9, 'Vicente', 'Tuyare', 'Uruguay'),
(3, 'fscouteguazza', 'fs', 1, 0, 300, 4, 'Federico', 'Scuoteguazza', 'Inglaterra'),
(4, 'pvaztourem', 'pv', 2, 1, 400, 45, 'Patricia', 'Vaztourem', 'Uruguay'),
(5, 'ahirata', 'ah', 2, 1, 300, 69, 'Andrés', 'Hirata', 'Inglaterra'),
(6, 'dgonzalez', 'dg', 1, 0, 908, 78, 'Diego', 'Gonzalez', 'Inglaterra'),
(7, 'fkono', 'fk', 2, 1, 3000, 87, 'Fumie', 'Kono', 'Inglaterra'),
(8, 'thirata', 'th', 2, 0, 398, 98, 'Tomiyoshi', 'Hirata', 'Inglaterra'),
(9, 'jdiaz', 'jd', 2, 1, 500, 78, 'Javier', 'Diaz', 'Uruguay'),
(10, 'jrodriguez', 'jr', 2, 1, 300, 33, 'Javier', 'Rodriguez', 'Uruguay');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios_has_juegos_desafios`
--

CREATE TABLE IF NOT EXISTS `usuarios_has_juegos_desafios` (
  `juegos_idJuego` int(11) NOT NULL,
  `desafios_idDesafio` int(11) NOT NULL,
  `usuarios_idusuario` int(11) NOT NULL,
  `usuarioGanadorD` int(11) NOT NULL,
  PRIMARY KEY (`juegos_idJuego`,`desafios_idDesafio`,`usuarios_idusuario`),
  KEY `Juegos_has_Desafios_FKIndex1` (`juegos_idJuego`),
  KEY `Juegos_has_Desafios_FKIndex2` (`desafios_idDesafio`),
  KEY `Juegos_has_Desafios_FKIndex3` (`usuarios_idusuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios_has_juegos_partidas`
--

CREATE TABLE IF NOT EXISTS `usuarios_has_juegos_partidas` (
  `usuarios_idusuario` int(11) NOT NULL,
  `partidas_idPartida` int(11) NOT NULL,
  `juegos_idJuego` int(11) NOT NULL,
  `Partidas_Torneos_idTorneo` int(11) NOT NULL,
  `usuarioGanadorP` int(11) NOT NULL,
  PRIMARY KEY (`usuarios_idusuario`,`partidas_idPartida`,`juegos_idJuego`,`Partidas_Torneos_idTorneo`),
  KEY `usuarios_has_Partidas_FKIndex1` (`usuarios_idusuario`),
  KEY `usuarios_has_Partidas_FKIndex2` (`partidas_idPartida`),
  KEY `usuarios_has_Partidas_FKIndex3` (`juegos_idJuego`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios_has_juegos_torneos`
--

CREATE TABLE IF NOT EXISTS `usuarios_has_juegos_torneos` (
  `usuarios_idusuario` int(11) NOT NULL,
  `torneos_idTorneo` int(11) NOT NULL,
  `juegos_idJuego` int(11) NOT NULL,
  PRIMARY KEY (`usuarios_idusuario`,`torneos_idTorneo`,`juegos_idJuego`),
  KEY `usuarios_has_torneos_FKIndex1` (`usuarios_idusuario`),
  KEY `usuarios_has_torneos_FKIndex2` (`torneos_idTorneo`),
  KEY `usuarios_has_torneos_FKIndex3` (`juegos_idJuego`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios_has_torneos_posiciones`
--

CREATE TABLE IF NOT EXISTS `usuarios_has_torneos_posiciones` (
  `posiciones_idPosicion` int(11) NOT NULL,
  `usuarios_idusuario` int(11) NOT NULL,
  `torneos_idTorneo` int(11) NOT NULL,
  PRIMARY KEY (`posiciones_idPosicion`,`usuarios_idusuario`,`torneos_idTorneo`),
  KEY `usuarios_has_Posiciones_FKIndex1` (`posiciones_idPosicion`),
  KEY `usuarios_has_Posiciones_FKIndex2` (`usuarios_idusuario`),
  KEY `usuarios_has_Torneos_FKIndex3` (`torneos_idTorneo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
