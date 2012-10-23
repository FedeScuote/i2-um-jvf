﻿-- phpMyAdmin SQL Dump
-- version 3.4.5
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 23-10-2012 a las 18:19:18
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
-- Estructura de tabla para la tabla `celdas`
--

CREATE TABLE IF NOT EXISTS `celdas` (
  `idCelda` int(11) NOT NULL AUTO_INCREMENT,
  `t_batalla_naval_idTBatallaNaval` int(11) NOT NULL,
  `xC` int(11) NOT NULL,
  `yC` int(11) NOT NULL,
  `id` int(11) NOT NULL,
  `estado` varchar(16) NOT NULL,
  PRIMARY KEY (`idCelda`),
  KEY `celdas_FKIndex1` (`t_batalla_naval_idTBatallaNaval`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=604 ;

--
-- Volcado de datos para la tabla `celdas`
--

INSERT INTO `celdas` (`idCelda`, `t_batalla_naval_idTBatallaNaval`, `xC`, `yC`, `id`, `estado`) VALUES
(404, 7, 0, 0, 0, 'AGUA'),
(405, 7, 0, 1, 0, 'AGUA'),
(406, 7, 0, 2, 0, 'AGUA'),
(407, 7, 0, 3, 0, 'AGUA'),
(408, 7, 0, 4, 0, 'AGUA'),
(409, 7, 0, 5, 0, 'AGUA'),
(410, 7, 0, 6, 0, 'AGUA'),
(411, 7, 0, 7, 0, 'AGUA'),
(412, 7, 0, 8, 0, 'AGUA'),
(413, 7, 0, 9, 0, 'AGUA'),
(414, 7, 1, 0, 0, 'AGUA'),
(415, 7, 1, 1, 0, 'AGUA'),
(416, 7, 1, 2, 0, 'AGUA'),
(417, 7, 1, 3, 0, 'AGUA'),
(418, 7, 1, 4, 0, 'AGUA'),
(419, 7, 1, 5, 0, 'AGUA'),
(420, 7, 1, 6, 0, 'AGUA'),
(421, 7, 1, 7, 0, 'AGUA'),
(422, 7, 1, 8, 0, 'AGUA'),
(423, 7, 1, 9, 0, 'AGUA'),
(424, 7, 2, 0, 0, 'AGUA'),
(425, 7, 2, 1, 0, 'AGUA'),
(426, 7, 2, 2, 0, 'AGUA'),
(427, 7, 2, 3, 0, 'AGUA'),
(428, 7, 2, 4, 0, 'AGUA'),
(429, 7, 2, 5, 0, 'AGUA'),
(430, 7, 2, 6, 0, 'AGUA'),
(431, 7, 2, 7, 0, 'AGUA'),
(432, 7, 2, 8, 0, 'AGUA'),
(433, 7, 2, 9, 0, 'AGUA'),
(434, 7, 3, 0, 0, 'AGUA'),
(435, 7, 3, 1, 0, 'AGUA'),
(436, 7, 3, 2, 0, 'AGUA'),
(437, 7, 3, 3, 0, 'AGUA'),
(438, 7, 3, 4, 0, 'AGUA'),
(439, 7, 3, 5, 0, 'AGUA'),
(440, 7, 3, 6, 0, 'AGUA'),
(441, 7, 3, 7, 0, 'AGUA'),
(442, 7, 3, 8, 0, 'AGUA'),
(443, 7, 3, 9, 0, 'AGUA'),
(444, 7, 4, 0, 0, 'AGUA'),
(445, 7, 4, 1, 0, 'AGUA'),
(446, 7, 4, 2, 0, 'AGUA'),
(447, 7, 4, 3, 0, 'AGUA'),
(448, 7, 4, 4, 0, 'AGUA'),
(449, 7, 4, 5, 0, 'AGUA'),
(450, 7, 4, 6, 0, 'AGUA'),
(451, 7, 4, 7, 0, 'AGUA'),
(452, 7, 4, 8, 0, 'AGUA'),
(453, 7, 4, 9, 0, 'AGUA'),
(454, 7, 5, 0, 0, 'AGUA'),
(455, 7, 5, 1, 0, 'AGUA'),
(456, 7, 5, 2, 0, 'AGUA'),
(457, 7, 5, 3, 0, 'AGUA'),
(458, 7, 5, 4, 0, 'AGUA'),
(459, 7, 5, 5, 0, 'AGUA'),
(460, 7, 5, 6, 0, 'AGUA'),
(461, 7, 5, 7, 0, 'AGUA'),
(462, 7, 5, 8, 0, 'AGUA'),
(463, 7, 5, 9, 0, 'AGUA'),
(464, 7, 6, 0, 0, 'AGUA'),
(465, 7, 6, 1, 0, 'AGUA'),
(466, 7, 6, 2, 0, 'AGUA'),
(467, 7, 6, 3, 0, 'AGUA'),
(468, 7, 6, 4, 0, 'AGUA'),
(469, 7, 6, 5, 0, 'AGUA'),
(470, 7, 6, 6, 0, 'AGUA'),
(471, 7, 6, 7, 0, 'AGUA'),
(472, 7, 6, 8, 0, 'AGUA'),
(473, 7, 6, 9, 0, 'AGUA'),
(474, 7, 7, 0, 0, 'AGUA'),
(475, 7, 7, 1, 0, 'AGUA'),
(476, 7, 7, 2, 0, 'AGUA'),
(477, 7, 7, 3, 0, 'AGUA'),
(478, 7, 7, 4, 0, 'AGUA'),
(479, 7, 7, 5, 0, 'AGUA'),
(480, 7, 7, 6, 0, 'AGUA'),
(481, 7, 7, 7, 0, 'AGUA'),
(482, 7, 7, 8, 0, 'AGUA'),
(483, 7, 7, 9, 0, 'AGUA'),
(484, 7, 8, 0, 0, 'AGUA'),
(485, 7, 8, 1, 0, 'AGUA'),
(486, 7, 8, 2, 0, 'AGUA'),
(487, 7, 8, 3, 0, 'AGUA'),
(488, 7, 8, 4, 0, 'AGUA'),
(489, 7, 8, 5, 0, 'AGUA'),
(490, 7, 8, 6, 0, 'AGUA'),
(491, 7, 8, 7, 0, 'AGUA'),
(492, 7, 8, 8, 0, 'AGUA'),
(493, 7, 8, 9, 0, 'AGUA'),
(494, 7, 9, 0, 0, 'AGUA'),
(495, 7, 9, 1, 0, 'AGUA'),
(496, 7, 9, 2, 0, 'AGUA'),
(497, 7, 9, 3, 0, 'AGUA'),
(498, 7, 9, 4, 0, 'AGUA'),
(499, 7, 9, 5, 0, 'AGUA'),
(500, 7, 9, 6, 0, 'AGUA'),
(501, 7, 9, 7, 0, 'AGUA'),
(502, 7, 9, 8, 0, 'AGUA'),
(503, 7, 9, 9, 0, 'AGUA'),
(504, 6, 0, 0, 0, 'AGUA'),
(505, 6, 0, 1, 0, 'AGUA'),
(506, 6, 0, 2, 0, 'AGUA'),
(507, 6, 0, 3, 0, 'AGUA'),
(508, 6, 0, 4, 0, 'AGUA'),
(509, 6, 0, 5, 0, 'AGUA'),
(510, 6, 0, 6, 0, 'AGUA'),
(511, 6, 0, 7, 0, 'AGUA'),
(512, 6, 0, 8, 0, 'AGUA'),
(513, 6, 0, 9, 0, 'AGUA'),
(514, 6, 1, 0, 0, 'AGUA'),
(515, 6, 1, 1, 0, 'AGUA'),
(516, 6, 1, 2, 0, 'AGUA'),
(517, 6, 1, 3, 0, 'AGUA'),
(518, 6, 1, 4, 0, 'AGUA'),
(519, 6, 1, 5, 0, 'AGUA'),
(520, 6, 1, 6, 0, 'AGUA'),
(521, 6, 1, 7, 0, 'AGUA'),
(522, 6, 1, 8, 0, 'AGUA'),
(523, 6, 1, 9, 0, 'AGUA'),
(524, 6, 2, 0, 0, 'AGUA'),
(525, 6, 2, 1, 0, 'AGUA'),
(526, 6, 2, 2, 0, 'AGUA'),
(527, 6, 2, 3, 0, 'AGUA'),
(528, 6, 2, 4, 0, 'AGUA'),
(529, 6, 2, 5, 0, 'AGUA'),
(530, 6, 2, 6, 0, 'AGUA'),
(531, 6, 2, 7, 0, 'AGUA'),
(532, 6, 2, 8, 0, 'AGUA'),
(533, 6, 2, 9, 0, 'AGUA'),
(534, 6, 3, 0, 0, 'AGUA'),
(535, 6, 3, 1, 0, 'AGUA'),
(536, 6, 3, 2, 0, 'AGUA'),
(537, 6, 3, 3, 0, 'AGUA'),
(538, 6, 3, 4, 0, 'AGUA'),
(539, 6, 3, 5, 0, 'AGUA'),
(540, 6, 3, 6, 0, 'AGUA'),
(541, 6, 3, 7, 0, 'AGUA'),
(542, 6, 3, 8, 0, 'AGUA'),
(543, 6, 3, 9, 0, 'AGUA'),
(544, 6, 4, 0, 0, 'AGUA'),
(545, 6, 4, 1, 0, 'AGUA'),
(546, 6, 4, 2, 0, 'AGUA'),
(547, 6, 4, 3, 0, 'AGUA'),
(548, 6, 4, 4, 0, 'AGUA'),
(549, 6, 4, 5, 0, 'AGUA'),
(550, 6, 4, 6, 0, 'AGUA'),
(551, 6, 4, 7, 0, 'AGUA'),
(552, 6, 4, 8, 0, 'AGUA'),
(553, 6, 4, 9, 0, 'AGUA'),
(554, 6, 5, 0, 0, 'AGUA'),
(555, 6, 5, 1, 0, 'AGUA'),
(556, 6, 5, 2, 0, 'AGUA'),
(557, 6, 5, 3, 0, 'AGUA'),
(558, 6, 5, 4, 0, 'AGUA'),
(559, 6, 5, 5, 0, 'AGUA'),
(560, 6, 5, 6, 0, 'AGUA'),
(561, 6, 5, 7, 0, 'AGUA'),
(562, 6, 5, 8, 0, 'AGUA'),
(563, 6, 5, 9, 0, 'AGUA'),
(564, 6, 6, 0, 0, 'AGUA'),
(565, 6, 6, 1, 0, 'AGUA'),
(566, 6, 6, 2, 0, 'AGUA'),
(567, 6, 6, 3, 0, 'AGUA'),
(568, 6, 6, 4, 0, 'AGUA'),
(569, 6, 6, 5, 0, 'AGUA'),
(570, 6, 6, 6, 0, 'AGUA'),
(571, 6, 6, 7, 0, 'AGUA'),
(572, 6, 6, 8, 0, 'AGUA'),
(573, 6, 6, 9, 0, 'AGUA'),
(574, 6, 7, 0, 0, 'AGUA'),
(575, 6, 7, 1, 0, 'AGUA'),
(576, 6, 7, 2, 0, 'AGUA'),
(577, 6, 7, 3, 0, 'AGUA'),
(578, 6, 7, 4, 0, 'AGUA'),
(579, 6, 7, 5, 0, 'AGUA'),
(580, 6, 7, 6, 0, 'AGUA'),
(581, 6, 7, 7, 0, 'AGUA'),
(582, 6, 7, 8, 0, 'AGUA'),
(583, 6, 7, 9, 0, 'AGUA'),
(584, 6, 8, 0, 0, 'AGUA'),
(585, 6, 8, 1, 0, 'AGUA'),
(586, 6, 8, 2, 0, 'AGUA'),
(587, 6, 8, 3, 0, 'AGUA'),
(588, 6, 8, 4, 0, 'AGUA'),
(589, 6, 8, 5, 0, 'AGUA'),
(590, 6, 8, 6, 0, 'AGUA'),
(591, 6, 8, 7, 0, 'AGUA'),
(592, 6, 8, 8, 0, 'AGUA'),
(593, 6, 8, 9, 0, 'AGUA'),
(594, 6, 9, 0, 0, 'AGUA'),
(595, 6, 9, 1, 0, 'AGUA'),
(596, 6, 9, 2, 0, 'AGUA'),
(597, 6, 9, 3, 0, 'AGUA'),
(598, 6, 9, 4, 0, 'AGUA'),
(599, 6, 9, 5, 0, 'AGUA'),
(600, 6, 9, 6, 0, 'AGUA'),
(601, 6, 9, 7, 0, 'AGUA'),
(602, 6, 9, 8, 0, 'AGUA'),
(603, 6, 9, 9, 0, 'AGUA');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `desafios`
--

CREATE TABLE IF NOT EXISTS `desafios` (
  `idDesafio` int(11) NOT NULL AUTO_INCREMENT,
  `monto` int(11) DEFAULT NULL,
  `fechaHoraInicioD` datetime DEFAULT NULL,
  `estadoD` varchar(10) NOT NULL,
  PRIMARY KEY (`idDesafio`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=33 ;

--
-- Volcado de datos para la tabla `desafios`
--

INSERT INTO `desafios` (`idDesafio`, `monto`, `fechaHoraInicioD`, `estadoD`) VALUES
(1, 200, '2012-09-03 13:00:00', 'Finalizado'),
(2, 250, '2012-09-12 15:00:00', 'Finalizado'),
(3, 400, '2012-09-03 14:00:00', 'Finalizado'),
(4, 300, '2012-09-20 14:00:00', 'Finalizado'),
(5, 500, '2012-09-12 19:00:00', 'Finalizado'),
(6, 453, '2012-09-26 19:00:00', 'Finalizado'),
(7, 40, '2012-09-12 18:00:00', 'Finalizado'),
(8, 300, '2012-08-13 14:00:00', 'Finalizado'),
(9, 30, '2012-09-14 16:00:00', 'Finalizado'),
(10, 400, '2012-09-19 17:00:00', 'Finalizado'),
(11, 22, '2012-09-16 15:00:00', 'Finalizado'),
(12, 100, '2012-09-15 13:00:00', 'Finalizado'),
(13, 800, '2012-09-07 18:00:00', 'Finalizado'),
(14, 900, '2012-09-17 12:00:00', 'Finalizado'),
(15, 1000, '2012-09-19 12:00:00', 'Finalizado'),
(16, 1050, '2012-09-08 18:00:00', 'Finalizado'),
(17, 500, '2012-10-24 18:00:00', 'En curso'),
(18, 1500, '2012-10-16 15:00:00', 'En hora'),
(19, 30, '2012-10-31 16:00:00', 'En curso'),
(20, 5000, '2012-11-13 13:00:00', 'En curso'),
(21, 450, '2012-12-19 00:00:00', 'En hora'),
(22, 300, '2012-12-14 00:00:00', 'En hora'),
(23, 250, '2012-01-24 17:00:00', 'En hora'),
(31, 1600, '2012-10-23 13:19:54', 'En hora'),
(32, 1600, '2012-10-23 13:21:09', 'En hora');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `disparos`
--

CREATE TABLE IF NOT EXISTS `disparos` (
  `idDisparo` int(11) NOT NULL AUTO_INCREMENT,
  `t_batalla_naval_idTBatallaNaval` int(11) NOT NULL,
  `idUsuarioD` int(11) NOT NULL,
  `resultadoD` varchar(16) NOT NULL,
  `xD` int(11) NOT NULL,
  `yD` int(11) NOT NULL,
  PRIMARY KEY (`idDisparo`),
  KEY `disparos_FKIndex1` (`t_batalla_naval_idTBatallaNaval`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;

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
  `estadoP` varchar(10) NOT NULL,
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
-- Estructura de tabla para la tabla `ranking`
--

CREATE TABLE IF NOT EXISTS `ranking` (
  `usuarios_idUsuario` int(11) NOT NULL,
  `juegos_idJuego` int(11) NOT NULL,
  `ganadas` int(11) NOT NULL,
  KEY `usuarios_has_juegos_FKIndex1` (`usuarios_idUsuario`),
  KEY `usuarios_has_juegos_FKIndex2` (`juegos_idJuego`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `ranking`
--

INSERT INTO `ranking` (`usuarios_idUsuario`, `juegos_idJuego`, `ganadas`) VALUES
(6, 2, 1),
(3, 2, 3),
(8, 2, 2),
(1, 1, 1),
(3, 1, 1),
(4, 1, 1);

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
  `estadoT` varchar(10) NOT NULL,
  PRIMARY KEY (`idTorneo`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Volcado de datos para la tabla `torneos`
--

INSERT INTO `torneos` (`idTorneo`, `descripcion`, `pozo`, `costoInscripcion`, `fechaHoraInicioT`, `estadoT`) VALUES
(1, 'Torneo Nacional Batalla Naval 2012', 50000, 50, '2012-10-11 09:00:00', 'En hora'),
(2, 'Torneo Apertura Batalla Naval 2012', 30000, 30, '2012-09-23 09:30:00', 'Atrasado'),
(3, 'Torneo Nacional Ludo 2012', 2000, 20, '2012-09-22 10:00:00', 'En curso');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `t_batalla_naval`
--

CREATE TABLE IF NOT EXISTS `t_batalla_naval` (
  `idTBatallaNaval` int(11) NOT NULL AUTO_INCREMENT,
  `desafios_idDesafio` int(11) NOT NULL,
  `jugador` varchar(16) NOT NULL,
  `miTurno` tinyint(1) NOT NULL,
  `barcosSubmarinos` int(11) NOT NULL,
  `barcosDestructores` int(11) NOT NULL,
  `barcosCruceros` int(11) NOT NULL,
  `barcosAcorazados` int(11) NOT NULL,
  `barcosSubmarinosColocados` int(11) NOT NULL,
  `barcosDestructoresColocados` int(11) NOT NULL,
  `barcosCrucerosColocados` int(11) NOT NULL,
  `barcosAcorazadosColocados` int(11) NOT NULL,
  PRIMARY KEY (`idTBatallaNaval`,`jugador`),
  KEY `desafios_idDesafio` (`desafios_idDesafio`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Volcado de datos para la tabla `t_batalla_naval`
--

INSERT INTO `t_batalla_naval` (`idTBatallaNaval`, `desafios_idDesafio`, `jugador`, `miTurno`, `barcosSubmarinos`, `barcosDestructores`, `barcosCruceros`, `barcosAcorazados`, `barcosSubmarinosColocados`, `barcosDestructoresColocados`, `barcosCrucerosColocados`, `barcosAcorazadosColocados`) VALUES
(6, 20, 'vtuyare', 1, 2, 2, 2, 2, 2, 2, 2, 2),
(7, 20, 'jhirata', 1, 2, 2, 2, 2, 2, 2, 2, 2);

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

--
-- Volcado de datos para la tabla `usuarios_has_juegos_desafios`
--

INSERT INTO `usuarios_has_juegos_desafios` (`juegos_idJuego`, `desafios_idDesafio`, `usuarios_idusuario`, `usuarioGanadorD`) VALUES
(1, 1, 1, 1),
(1, 1, 2, 1),
(1, 2, 3, 3),
(1, 2, 4, 3),
(1, 3, 4, 4),
(1, 3, 5, 4),
(1, 17, 4, 0),
(1, 17, 8, 0),
(1, 18, 5, 0),
(1, 19, 1, 2),
(1, 19, 2, 2),
(1, 20, 1, 0),
(1, 21, 2, 0),
(1, 22, 3, 0),
(1, 31, 7, 0),
(1, 32, 9, 0),
(2, 4, 6, 6),
(2, 4, 7, 6),
(2, 5, 3, 3),
(2, 5, 8, 3),
(2, 6, 8, 8),
(2, 6, 9, 8),
(2, 7, 3, 3),
(2, 7, 4, 3),
(2, 8, 3, 3),
(2, 8, 7, 3),
(2, 9, 8, 8),
(2, 9, 9, 8);

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
