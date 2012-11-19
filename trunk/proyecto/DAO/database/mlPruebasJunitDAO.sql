-- phpMyAdmin SQL Dump
-- version 3.4.5
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 19-11-2012 a las 00:24:25
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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2739 ;

--
-- Volcado de datos para la tabla `celdas`
--

INSERT INTO `celdas` (`idCelda`, `t_batalla_naval_idTBatallaNaval`, `xC`, `yC`, `id`, `estado`) VALUES
(1352, 15, 0, 0, 33, 'AGUA'),
(1353, 15, 0, 1, 0, 'AGUA'),
(1354, 15, 0, 2, 0, 'AGUA'),
(1355, 15, 0, 3, 0, 'AGUA'),
(1356, 15, 0, 4, 0, 'AGUA'),
(1357, 15, 0, 5, 0, 'AGUA'),
(1358, 15, 0, 6, 0, 'AGUA'),
(1359, 15, 0, 7, 0, 'AGUA'),
(1360, 15, 0, 8, 0, 'AGUA'),
(1361, 15, 0, 9, 0, 'AGUA'),
(1362, 15, 1, 0, 41, 'OCUPADO'),
(1363, 15, 1, 1, 0, 'AGUA'),
(1364, 15, 1, 2, 0, 'AGUA'),
(1365, 15, 1, 3, 0, 'AGUA'),
(1366, 15, 1, 4, 0, 'AGUA'),
(1367, 15, 1, 5, 0, 'AGUA'),
(1368, 15, 1, 6, 0, 'AGUA'),
(1369, 15, 1, 7, 0, 'AGUA'),
(1370, 15, 1, 8, 0, 'AGUA'),
(1371, 15, 1, 9, 0, 'AGUA'),
(1372, 15, 2, 0, 41, 'OCUPADO'),
(1373, 15, 2, 1, 0, 'AGUA'),
(1374, 15, 2, 2, 0, 'AGUA'),
(1375, 15, 2, 3, 0, 'AGUA'),
(1376, 15, 2, 4, 0, 'AGUA'),
(1377, 15, 2, 5, 0, 'AGUA'),
(1378, 15, 2, 6, 0, 'AGUA'),
(1379, 15, 2, 7, 0, 'AGUA'),
(1380, 15, 2, 8, 0, 'AGUA'),
(1381, 15, 2, 9, 0, 'AGUA'),
(1382, 15, 3, 0, 41, 'OCUPADO'),
(1383, 15, 3, 1, 0, 'AGUA'),
(1384, 15, 3, 2, 0, 'AGUA'),
(1385, 15, 3, 3, 0, 'AGUA'),
(1386, 15, 3, 4, 0, 'AGUA'),
(1387, 15, 3, 5, 0, 'AGUA'),
(1388, 15, 3, 6, 0, 'AGUA'),
(1389, 15, 3, 7, 0, 'AGUA'),
(1390, 15, 3, 8, 0, 'AGUA'),
(1391, 15, 3, 9, 0, 'AGUA'),
(1392, 15, 4, 0, 0, 'AGUA'),
(1393, 15, 4, 1, 0, 'AGUA'),
(1394, 15, 4, 2, 0, 'AGUA'),
(1395, 15, 4, 3, 0, 'AGUA'),
(1396, 15, 4, 4, 0, 'AGUA'),
(1397, 15, 4, 5, 0, 'AGUA'),
(1398, 15, 4, 6, 0, 'AGUA'),
(1399, 15, 4, 7, 0, 'AGUA'),
(1400, 15, 4, 8, 0, 'AGUA'),
(1401, 15, 4, 9, 0, 'AGUA'),
(1402, 15, 5, 0, 0, 'AGUA'),
(1403, 15, 5, 1, 0, 'AGUA'),
(1404, 15, 5, 2, 0, 'AGUA'),
(1405, 15, 5, 3, 0, 'AGUA'),
(1406, 15, 5, 4, 0, 'AGUA'),
(1407, 15, 5, 5, 0, 'AGUA'),
(1408, 15, 5, 6, 0, 'AGUA'),
(1409, 15, 5, 7, 0, 'AGUA'),
(1410, 15, 5, 8, 0, 'AGUA'),
(1411, 15, 5, 9, 0, 'AGUA'),
(1412, 15, 6, 0, 0, 'AGUA'),
(1413, 15, 6, 1, 0, 'AGUA'),
(1414, 15, 6, 2, 0, 'AGUA'),
(1415, 15, 6, 3, 0, 'AGUA'),
(1416, 15, 6, 4, 0, 'AGUA'),
(1417, 15, 6, 5, 0, 'AGUA'),
(1418, 15, 6, 6, 0, 'AGUA'),
(1419, 15, 6, 7, 0, 'AGUA'),
(1420, 15, 6, 8, 0, 'AGUA'),
(1421, 15, 6, 9, 0, 'AGUA'),
(1422, 15, 7, 0, 0, 'AGUA'),
(1423, 15, 7, 1, 0, 'AGUA'),
(1424, 15, 7, 2, 0, 'AGUA'),
(1425, 15, 7, 3, 0, 'AGUA'),
(1426, 15, 7, 4, 0, 'AGUA'),
(1427, 15, 7, 5, 0, 'AGUA'),
(1428, 15, 7, 6, 0, 'AGUA'),
(1429, 15, 7, 7, 0, 'AGUA'),
(1430, 15, 7, 8, 0, 'AGUA'),
(1431, 15, 7, 9, 0, 'AGUA'),
(1432, 15, 8, 0, 0, 'AGUA'),
(1433, 15, 8, 1, 0, 'AGUA'),
(1434, 15, 8, 2, 0, 'AGUA'),
(1435, 15, 8, 3, 0, 'AGUA'),
(1436, 15, 8, 4, 0, 'AGUA'),
(1437, 15, 8, 5, 0, 'AGUA'),
(1438, 15, 8, 6, 0, 'AGUA'),
(1439, 15, 8, 7, 0, 'AGUA'),
(1440, 15, 8, 8, 0, 'AGUA'),
(1441, 15, 8, 9, 0, 'AGUA'),
(1442, 15, 9, 0, 0, 'AGUA'),
(1443, 15, 9, 1, 0, 'AGUA'),
(1444, 15, 9, 2, 0, 'AGUA'),
(1445, 15, 9, 3, 0, 'AGUA'),
(1446, 15, 9, 4, 0, 'AGUA'),
(1447, 15, 9, 5, 0, 'AGUA'),
(1448, 15, 9, 6, 0, 'AGUA'),
(1449, 15, 9, 7, 0, 'AGUA'),
(1450, 15, 9, 8, 0, 'AGUA'),
(1451, 15, 9, 9, 0, 'AGUA'),
(1452, 16, 0, 0, 0, 'AGUA'),
(1453, 16, 0, 1, 0, 'AGUA'),
(1454, 16, 0, 2, 0, 'AGUA'),
(1455, 16, 0, 3, 0, 'AGUA'),
(1456, 16, 0, 4, 0, 'AGUA'),
(1457, 16, 0, 5, 0, 'AGUA'),
(1458, 16, 0, 6, 0, 'AGUA'),
(1459, 16, 0, 7, 0, 'AGUA'),
(1460, 16, 0, 8, 0, 'AGUA'),
(1461, 16, 0, 9, 0, 'AGUA'),
(1462, 16, 1, 0, 0, 'AGUA'),
(1463, 16, 1, 1, 0, 'AGUA'),
(1464, 16, 1, 2, 0, 'AGUA'),
(1465, 16, 1, 3, 0, 'AGUA'),
(1466, 16, 1, 4, 0, 'AGUA'),
(1467, 16, 1, 5, 0, 'AGUA'),
(1468, 16, 1, 6, 0, 'AGUA'),
(1469, 16, 1, 7, 0, 'AGUA'),
(1470, 16, 1, 8, 0, 'AGUA'),
(1471, 16, 1, 9, 0, 'AGUA'),
(1472, 16, 2, 0, 0, 'AGUA'),
(1473, 16, 2, 1, 0, 'AGUA'),
(1474, 16, 2, 2, 0, 'AGUA'),
(1475, 16, 2, 3, 0, 'AGUA'),
(1476, 16, 2, 4, 0, 'AGUA'),
(1477, 16, 2, 5, 0, 'AGUA'),
(1478, 16, 2, 6, 0, 'AGUA'),
(1479, 16, 2, 7, 0, 'AGUA'),
(1480, 16, 2, 8, 0, 'AGUA'),
(1481, 16, 2, 9, 0, 'AGUA'),
(1482, 16, 3, 0, 0, 'AGUA'),
(1483, 16, 3, 1, 0, 'AGUA'),
(1484, 16, 3, 2, 0, 'AGUA'),
(1485, 16, 3, 3, 0, 'AGUA'),
(1486, 16, 3, 4, 0, 'AGUA'),
(1487, 16, 3, 5, 0, 'AGUA'),
(1488, 16, 3, 6, 0, 'AGUA'),
(1489, 16, 3, 7, 0, 'AGUA'),
(1490, 16, 3, 8, 0, 'AGUA'),
(1491, 16, 3, 9, 0, 'AGUA'),
(1492, 16, 4, 0, 0, 'AGUA'),
(1493, 16, 4, 1, 0, 'AGUA'),
(1494, 16, 4, 2, 0, 'AGUA'),
(1495, 16, 4, 3, 0, 'AGUA'),
(1496, 16, 4, 4, 0, 'AGUA'),
(1497, 16, 4, 5, 0, 'AGUA'),
(1498, 16, 4, 6, 0, 'AGUA'),
(1499, 16, 4, 7, 0, 'AGUA'),
(1500, 16, 4, 8, 0, 'AGUA'),
(1501, 16, 4, 9, 0, 'AGUA'),
(1502, 16, 5, 0, 0, 'AGUA'),
(1503, 16, 5, 1, 0, 'AGUA'),
(1504, 16, 5, 2, 0, 'AGUA'),
(1505, 16, 5, 3, 0, 'AGUA'),
(1506, 16, 5, 4, 0, 'AGUA'),
(1507, 16, 5, 5, 0, 'AGUA'),
(1508, 16, 5, 6, 0, 'AGUA'),
(1509, 16, 5, 7, 0, 'AGUA'),
(1510, 16, 5, 8, 0, 'AGUA'),
(1511, 16, 5, 9, 0, 'AGUA'),
(1512, 16, 6, 0, 0, 'AGUA'),
(1513, 16, 6, 1, 0, 'AGUA'),
(1514, 16, 6, 2, 0, 'AGUA'),
(1515, 16, 6, 3, 0, 'AGUA'),
(1516, 16, 6, 4, 0, 'AGUA'),
(1517, 16, 6, 5, 0, 'AGUA'),
(1518, 16, 6, 6, 0, 'AGUA'),
(1519, 16, 6, 7, 0, 'AGUA'),
(1520, 16, 6, 8, 0, 'AGUA'),
(1521, 16, 6, 9, 0, 'AGUA'),
(1522, 16, 7, 0, 0, 'AGUA'),
(1523, 16, 7, 1, 0, 'AGUA'),
(1524, 16, 7, 2, 0, 'AGUA'),
(1525, 16, 7, 3, 0, 'AGUA'),
(1526, 16, 7, 4, 0, 'AGUA'),
(1527, 16, 7, 5, 0, 'AGUA'),
(1528, 16, 7, 6, 0, 'AGUA'),
(1529, 16, 7, 7, 0, 'AGUA'),
(1530, 16, 7, 8, 0, 'AGUA'),
(1531, 16, 7, 9, 0, 'AGUA'),
(1532, 16, 8, 0, 0, 'AGUA'),
(1533, 16, 8, 1, 0, 'AGUA'),
(1534, 16, 8, 2, 0, 'AGUA'),
(1535, 16, 8, 3, 0, 'AGUA'),
(1536, 16, 8, 4, 0, 'AGUA'),
(1537, 16, 8, 5, 0, 'AGUA'),
(1538, 16, 8, 6, 0, 'AGUA'),
(1539, 16, 8, 7, 0, 'AGUA'),
(1540, 16, 8, 8, 0, 'AGUA'),
(1541, 16, 8, 9, 0, 'AGUA'),
(1542, 16, 9, 0, 0, 'AGUA'),
(1543, 16, 9, 1, 0, 'AGUA'),
(1544, 16, 9, 2, 0, 'AGUA'),
(1545, 16, 9, 3, 0, 'AGUA'),
(1546, 16, 9, 4, 0, 'AGUA'),
(1547, 16, 9, 5, 0, 'AGUA'),
(1548, 16, 9, 6, 0, 'AGUA'),
(1549, 16, 9, 7, 0, 'AGUA'),
(1550, 16, 9, 8, 0, 'AGUA'),
(1551, 16, 9, 9, 0, 'AGUA'),
(1752, 17, 0, 0, 41, 'OCUPADO'),
(1753, 17, 0, 1, 0, 'AGUA'),
(1754, 17, 0, 2, 0, 'AGUA'),
(1755, 17, 0, 3, 0, 'AGUA'),
(1756, 17, 0, 4, 0, 'AGUA'),
(1757, 17, 0, 5, 0, 'AGUA'),
(1758, 17, 0, 6, 0, 'AGUA'),
(1759, 17, 0, 7, 0, 'AGUA'),
(1760, 17, 0, 8, 0, 'AGUA'),
(1761, 17, 0, 9, 0, 'AGUA'),
(1762, 17, 1, 0, 41, 'OCUPADO'),
(1763, 17, 1, 1, 0, 'AGUA'),
(1764, 17, 1, 2, 0, 'AGUA'),
(1765, 17, 1, 3, 0, 'AGUA'),
(1766, 17, 1, 4, 0, 'AGUA'),
(1767, 17, 1, 5, 0, 'AGUA'),
(1768, 17, 1, 6, 0, 'AGUA'),
(1769, 17, 1, 7, 0, 'AGUA'),
(1770, 17, 1, 8, 0, 'AGUA'),
(1771, 17, 1, 9, 0, 'AGUA'),
(1772, 17, 2, 0, 41, 'OCUPADO'),
(1773, 17, 2, 1, 0, 'AGUA'),
(1774, 17, 2, 2, 0, 'AGUA'),
(1775, 17, 2, 3, 0, 'AGUA'),
(1776, 17, 2, 4, 0, 'AGUA'),
(1777, 17, 2, 5, 0, 'AGUA'),
(1778, 17, 2, 6, 0, 'AGUA'),
(1779, 17, 2, 7, 0, 'AGUA'),
(1780, 17, 2, 8, 0, 'AGUA'),
(1781, 17, 2, 9, 0, 'AGUA'),
(1782, 17, 3, 0, 41, 'OCUPADO'),
(1783, 17, 3, 1, 0, 'AGUA'),
(1784, 17, 3, 2, 0, 'AGUA'),
(1785, 17, 3, 3, 0, 'AGUA'),
(1786, 17, 3, 4, 0, 'AGUA'),
(1787, 17, 3, 5, 0, 'AGUA'),
(1788, 17, 3, 6, 0, 'AGUA'),
(1789, 17, 3, 7, 0, 'AGUA'),
(1790, 17, 3, 8, 0, 'AGUA'),
(1791, 17, 3, 9, 0, 'AGUA'),
(1792, 17, 4, 0, 0, 'AGUA'),
(1793, 17, 4, 1, 0, 'AGUA'),
(1794, 17, 4, 2, 0, 'AGUA'),
(1795, 17, 4, 3, 0, 'AGUA'),
(1796, 17, 4, 4, 0, 'AGUA'),
(1797, 17, 4, 5, 0, 'AGUA'),
(1798, 17, 4, 6, 0, 'AGUA'),
(1799, 17, 4, 7, 0, 'AGUA'),
(1800, 17, 4, 8, 0, 'AGUA'),
(1801, 17, 4, 9, 0, 'AGUA'),
(1802, 17, 5, 0, 0, 'AGUA'),
(1803, 17, 5, 1, 0, 'AGUA'),
(1804, 17, 5, 2, 0, 'AGUA'),
(1805, 17, 5, 3, 0, 'AGUA'),
(1806, 17, 5, 4, 0, 'AGUA'),
(1807, 17, 5, 5, 0, 'AGUA'),
(1808, 17, 5, 6, 0, 'AGUA'),
(1809, 17, 5, 7, 0, 'AGUA'),
(1810, 17, 5, 8, 0, 'AGUA'),
(1811, 17, 5, 9, 0, 'AGUA'),
(1812, 17, 6, 0, 0, 'AGUA'),
(1813, 17, 6, 1, 0, 'AGUA'),
(1814, 17, 6, 2, 0, 'AGUA'),
(1815, 17, 6, 3, 0, 'AGUA'),
(1816, 17, 6, 4, 0, 'AGUA'),
(1817, 17, 6, 5, 0, 'AGUA'),
(1818, 17, 6, 6, 0, 'AGUA'),
(1819, 17, 6, 7, 0, 'AGUA'),
(1820, 17, 6, 8, 0, 'AGUA'),
(1821, 17, 6, 9, 0, 'AGUA'),
(1822, 17, 7, 0, 0, 'AGUA'),
(1823, 17, 7, 1, 0, 'AGUA'),
(1824, 17, 7, 2, 0, 'AGUA'),
(1825, 17, 7, 3, 0, 'AGUA'),
(1826, 17, 7, 4, 0, 'AGUA'),
(1827, 17, 7, 5, 0, 'AGUA'),
(1828, 17, 7, 6, 0, 'AGUA'),
(1829, 17, 7, 7, 0, 'AGUA'),
(1830, 17, 7, 8, 0, 'AGUA'),
(1831, 17, 7, 9, 0, 'AGUA'),
(1832, 17, 8, 0, 0, 'AGUA'),
(1833, 17, 8, 1, 0, 'AGUA'),
(1834, 17, 8, 2, 0, 'AGUA'),
(1835, 17, 8, 3, 0, 'AGUA'),
(1836, 17, 8, 4, 0, 'AGUA'),
(1837, 17, 8, 5, 0, 'AGUA'),
(1838, 17, 8, 6, 0, 'AGUA'),
(1839, 17, 8, 7, 0, 'AGUA'),
(1840, 17, 8, 8, 0, 'AGUA'),
(1841, 17, 8, 9, 0, 'AGUA'),
(1842, 17, 9, 0, 0, 'AGUA'),
(1843, 17, 9, 1, 0, 'AGUA'),
(1844, 17, 9, 2, 0, 'AGUA'),
(1845, 17, 9, 3, 0, 'AGUA'),
(1846, 17, 9, 4, 0, 'AGUA'),
(1847, 17, 9, 5, 0, 'AGUA'),
(1848, 17, 9, 6, 0, 'AGUA'),
(1849, 17, 9, 7, 0, 'AGUA'),
(1850, 17, 9, 8, 0, 'AGUA'),
(1851, 17, 9, 9, 0, 'AGUA'),
(1852, 18, 0, 0, 0, 'AGUA'),
(1853, 18, 0, 1, 0, 'AGUA'),
(1854, 18, 0, 2, 0, 'AGUA'),
(1855, 18, 0, 3, 0, 'AGUA'),
(1856, 18, 0, 4, 0, 'AGUA'),
(1857, 18, 0, 5, 0, 'AGUA'),
(1858, 18, 0, 6, 0, 'AGUA'),
(1859, 18, 0, 7, 0, 'AGUA'),
(1860, 18, 0, 8, 0, 'AGUA'),
(1861, 18, 0, 9, 0, 'AGUA'),
(1862, 18, 1, 0, 0, 'AGUA'),
(1863, 18, 1, 1, 0, 'AGUA'),
(1864, 18, 1, 2, 0, 'AGUA'),
(1865, 18, 1, 3, 0, 'AGUA'),
(1866, 18, 1, 4, 0, 'AGUA'),
(1867, 18, 1, 5, 0, 'AGUA'),
(1868, 18, 1, 6, 0, 'AGUA'),
(1869, 18, 1, 7, 0, 'AGUA'),
(1870, 18, 1, 8, 0, 'AGUA'),
(1871, 18, 1, 9, 0, 'AGUA'),
(1872, 18, 2, 0, 0, 'AGUA'),
(1873, 18, 2, 1, 0, 'AGUA'),
(1874, 18, 2, 2, 0, 'AGUA'),
(1875, 18, 2, 3, 0, 'AGUA'),
(1876, 18, 2, 4, 0, 'AGUA'),
(1877, 18, 2, 5, 0, 'AGUA'),
(1878, 18, 2, 6, 0, 'AGUA'),
(1879, 18, 2, 7, 0, 'AGUA'),
(1880, 18, 2, 8, 0, 'AGUA'),
(1881, 18, 2, 9, 0, 'AGUA'),
(1882, 18, 3, 0, 0, 'AGUA'),
(1883, 18, 3, 1, 0, 'AGUA'),
(1884, 18, 3, 2, 0, 'AGUA'),
(1885, 18, 3, 3, 0, 'AGUA'),
(1886, 18, 3, 4, 0, 'AGUA'),
(1887, 18, 3, 5, 0, 'AGUA'),
(1888, 18, 3, 6, 0, 'AGUA'),
(1889, 18, 3, 7, 0, 'AGUA'),
(1890, 18, 3, 8, 0, 'AGUA'),
(1891, 18, 3, 9, 0, 'AGUA'),
(1892, 18, 4, 0, 0, 'AGUA'),
(1893, 18, 4, 1, 0, 'AGUA'),
(1894, 18, 4, 2, 0, 'AGUA'),
(1895, 18, 4, 3, 0, 'AGUA'),
(1896, 18, 4, 4, 0, 'AGUA'),
(1897, 18, 4, 5, 0, 'AGUA'),
(1898, 18, 4, 6, 0, 'AGUA'),
(1899, 18, 4, 7, 0, 'AGUA'),
(1900, 18, 4, 8, 0, 'AGUA'),
(1901, 18, 4, 9, 0, 'AGUA'),
(1902, 18, 5, 0, 0, 'AGUA'),
(1903, 18, 5, 1, 0, 'AGUA'),
(1904, 18, 5, 2, 0, 'AGUA'),
(1905, 18, 5, 3, 0, 'AGUA'),
(1906, 18, 5, 4, 0, 'AGUA'),
(1907, 18, 5, 5, 0, 'AGUA'),
(1908, 18, 5, 6, 0, 'AGUA'),
(1909, 18, 5, 7, 0, 'AGUA'),
(1910, 18, 5, 8, 0, 'AGUA'),
(1911, 18, 5, 9, 0, 'AGUA'),
(1912, 18, 6, 0, 0, 'AGUA'),
(1913, 18, 6, 1, 0, 'AGUA'),
(1914, 18, 6, 2, 0, 'AGUA'),
(1915, 18, 6, 3, 0, 'AGUA'),
(1916, 18, 6, 4, 0, 'AGUA'),
(1917, 18, 6, 5, 0, 'AGUA'),
(1918, 18, 6, 6, 30, 'OCUPADO'),
(1919, 18, 6, 7, 30, 'OCUPADO'),
(1920, 18, 6, 8, 30, 'OCUPADO'),
(1921, 18, 6, 9, 30, 'OCUPADO'),
(1922, 18, 7, 0, 0, 'AGUA'),
(1923, 18, 7, 1, 0, 'AGUA'),
(1924, 18, 7, 2, 0, 'AGUA'),
(1925, 18, 7, 3, 0, 'AGUA'),
(1926, 18, 7, 4, 0, 'AGUA'),
(1927, 18, 7, 5, 0, 'AGUA'),
(1928, 18, 7, 6, 0, 'AGUA'),
(1929, 18, 7, 7, 0, 'AGUA'),
(1930, 18, 7, 8, 0, 'AGUA'),
(1931, 18, 7, 9, 0, 'AGUA'),
(1932, 18, 8, 0, 0, 'AGUA'),
(1933, 18, 8, 1, 0, 'AGUA'),
(1934, 18, 8, 2, 0, 'AGUA'),
(1935, 18, 8, 3, 0, 'AGUA'),
(1936, 18, 8, 4, 0, 'AGUA'),
(1937, 18, 8, 5, 0, 'AGUA'),
(1938, 18, 8, 6, 0, 'AGUA'),
(1939, 18, 8, 7, 0, 'AGUA'),
(1940, 18, 8, 8, 0, 'AGUA'),
(1941, 18, 8, 9, 0, 'AGUA'),
(1942, 18, 9, 0, 0, 'AGUA'),
(1943, 18, 9, 1, 0, 'AGUA'),
(1944, 18, 9, 2, 0, 'AGUA'),
(1945, 18, 9, 3, 0, 'AGUA'),
(1946, 18, 9, 4, 0, 'AGUA'),
(1947, 18, 9, 5, 0, 'AGUA'),
(1948, 18, 9, 6, 0, 'AGUA'),
(1949, 18, 9, 7, 0, 'AGUA'),
(1950, 18, 9, 8, 0, 'AGUA'),
(1951, 18, 9, 9, 0, 'AGUA'),
(2052, 19, 0, 0, 0, 'AGUA'),
(2053, 19, 0, 1, 0, 'AGUA'),
(2054, 19, 0, 2, 0, 'AGUA'),
(2055, 19, 0, 3, 0, 'AGUA'),
(2056, 19, 0, 4, 0, 'AGUA'),
(2057, 19, 0, 5, 0, 'AGUA'),
(2058, 19, 0, 6, 0, 'AGUA'),
(2059, 19, 0, 7, 0, 'AGUA'),
(2060, 19, 0, 8, 0, 'AGUA'),
(2061, 19, 0, 9, 0, 'AGUA'),
(2062, 19, 1, 0, 0, 'AGUA'),
(2063, 19, 1, 1, 0, 'AGUA'),
(2064, 19, 1, 2, 0, 'AGUA'),
(2065, 19, 1, 3, 0, 'AGUA'),
(2066, 19, 1, 4, 0, 'AGUA'),
(2067, 19, 1, 5, 0, 'AGUA'),
(2068, 19, 1, 6, 0, 'AGUA'),
(2069, 19, 1, 7, 0, 'AGUA'),
(2070, 19, 1, 8, 0, 'AGUA'),
(2071, 19, 1, 9, 0, 'AGUA'),
(2072, 19, 2, 0, 0, 'AGUA'),
(2073, 19, 2, 1, 0, 'AGUA'),
(2074, 19, 2, 2, 0, 'AGUA'),
(2075, 19, 2, 3, 0, 'AGUA'),
(2076, 19, 2, 4, 0, 'AGUA'),
(2077, 19, 2, 5, 0, 'AGUA'),
(2078, 19, 2, 6, 0, 'AGUA'),
(2079, 19, 2, 7, 0, 'AGUA'),
(2080, 19, 2, 8, 0, 'AGUA'),
(2081, 19, 2, 9, 0, 'AGUA'),
(2082, 19, 3, 0, 0, 'AGUA'),
(2083, 19, 3, 1, 0, 'AGUA'),
(2084, 19, 3, 2, 0, 'AGUA'),
(2085, 19, 3, 3, 0, 'AGUA'),
(2086, 19, 3, 4, 0, 'AGUA'),
(2087, 19, 3, 5, 0, 'AGUA'),
(2088, 19, 3, 6, 0, 'AGUA'),
(2089, 19, 3, 7, 0, 'AGUA'),
(2090, 19, 3, 8, 0, 'AGUA'),
(2091, 19, 3, 9, 0, 'AGUA'),
(2092, 19, 4, 0, 0, 'AGUA'),
(2093, 19, 4, 1, 0, 'AGUA'),
(2094, 19, 4, 2, 0, 'AGUA'),
(2095, 19, 4, 3, 0, 'AGUA'),
(2096, 19, 4, 4, 0, 'AGUA'),
(2097, 19, 4, 5, 0, 'AGUA'),
(2098, 19, 4, 6, 0, 'AGUA'),
(2099, 19, 4, 7, 0, 'AGUA'),
(2100, 19, 4, 8, 0, 'AGUA'),
(2101, 19, 4, 9, 0, 'AGUA'),
(2102, 19, 5, 0, 0, 'AGUA'),
(2103, 19, 5, 1, 0, 'AGUA'),
(2104, 19, 5, 2, 0, 'AGUA'),
(2105, 19, 5, 3, 0, 'AGUA'),
(2106, 19, 5, 4, 0, 'AGUA'),
(2107, 19, 5, 5, 0, 'AGUA'),
(2108, 19, 5, 6, 0, 'AGUA'),
(2109, 19, 5, 7, 0, 'AGUA'),
(2110, 19, 5, 8, 0, 'AGUA'),
(2111, 19, 5, 9, 0, 'AGUA'),
(2112, 19, 6, 0, 0, 'AGUA'),
(2113, 19, 6, 1, 0, 'AGUA'),
(2114, 19, 6, 2, 0, 'AGUA'),
(2115, 19, 6, 3, 0, 'AGUA'),
(2116, 19, 6, 4, 0, 'AGUA'),
(2117, 19, 6, 5, 0, 'AGUA'),
(2118, 19, 6, 6, 0, 'AGUA'),
(2119, 19, 6, 7, 0, 'AGUA'),
(2120, 19, 6, 8, 0, 'AGUA'),
(2121, 19, 6, 9, 0, 'AGUA'),
(2122, 19, 7, 0, 0, 'AGUA'),
(2123, 19, 7, 1, 0, 'AGUA'),
(2124, 19, 7, 2, 0, 'AGUA'),
(2125, 19, 7, 3, 0, 'AGUA'),
(2126, 19, 7, 4, 0, 'AGUA'),
(2127, 19, 7, 5, 0, 'AGUA'),
(2128, 19, 7, 6, 0, 'AGUA'),
(2129, 19, 7, 7, 0, 'AGUA'),
(2130, 19, 7, 8, 0, 'AGUA'),
(2131, 19, 7, 9, 0, 'AGUA'),
(2132, 19, 8, 0, 0, 'AGUA'),
(2133, 19, 8, 1, 0, 'AGUA'),
(2134, 19, 8, 2, 0, 'AGUA'),
(2135, 19, 8, 3, 0, 'AGUA'),
(2136, 19, 8, 4, 0, 'AGUA'),
(2137, 19, 8, 5, 0, 'AGUA'),
(2138, 19, 8, 6, 0, 'AGUA'),
(2139, 19, 8, 7, 0, 'AGUA'),
(2140, 19, 8, 8, 0, 'AGUA'),
(2141, 19, 8, 9, 0, 'AGUA'),
(2142, 19, 9, 0, 0, 'AGUA'),
(2143, 19, 9, 1, 0, 'AGUA'),
(2144, 19, 9, 2, 0, 'AGUA'),
(2145, 19, 9, 3, 0, 'AGUA'),
(2146, 19, 9, 4, 0, 'AGUA'),
(2147, 19, 9, 5, 0, 'AGUA'),
(2148, 19, 9, 6, 0, 'AGUA'),
(2149, 19, 9, 7, 0, 'AGUA'),
(2150, 19, 9, 8, 0, 'AGUA'),
(2151, 19, 9, 9, 0, 'AGUA'),
(2352, 21, 0, 0, 41, 'OCUPADO'),
(2353, 21, 0, 1, 0, 'AGUA'),
(2354, 21, 0, 2, 0, 'AGUA'),
(2355, 21, 0, 3, 0, 'AGUA'),
(2356, 21, 0, 4, 0, 'AGUA'),
(2357, 21, 0, 5, 0, 'AGUA'),
(2358, 21, 0, 6, 0, 'AGUA'),
(2359, 21, 0, 7, 0, 'AGUA'),
(2360, 21, 0, 8, 0, 'AGUA'),
(2361, 21, 0, 9, 0, 'AGUA'),
(2362, 21, 1, 0, 41, 'OCUPADO'),
(2363, 21, 1, 1, 0, 'AGUA'),
(2364, 21, 1, 2, 0, 'AGUA'),
(2365, 21, 1, 3, 0, 'AGUA'),
(2366, 21, 1, 4, 0, 'AGUA'),
(2367, 21, 1, 5, 0, 'AGUA'),
(2368, 21, 1, 6, 0, 'AGUA'),
(2369, 21, 1, 7, 0, 'AGUA'),
(2370, 21, 1, 8, 0, 'AGUA'),
(2371, 21, 1, 9, 0, 'AGUA'),
(2372, 21, 2, 0, 41, 'OCUPADO'),
(2373, 21, 2, 1, 0, 'AGUA'),
(2374, 21, 2, 2, 0, 'AGUA'),
(2375, 21, 2, 3, 0, 'AGUA'),
(2376, 21, 2, 4, 0, 'AGUA'),
(2377, 21, 2, 5, 0, 'AGUA'),
(2378, 21, 2, 6, 0, 'AGUA'),
(2379, 21, 2, 7, 0, 'AGUA'),
(2380, 21, 2, 8, 0, 'AGUA'),
(2381, 21, 2, 9, 0, 'AGUA'),
(2382, 21, 3, 0, 41, 'OCUPADO'),
(2383, 21, 3, 1, 0, 'AGUA'),
(2384, 21, 3, 2, 0, 'AGUA'),
(2385, 21, 3, 3, 0, 'AGUA'),
(2386, 21, 3, 4, 0, 'AGUA'),
(2387, 21, 3, 5, 0, 'AGUA'),
(2388, 21, 3, 6, 0, 'AGUA'),
(2389, 21, 3, 7, 0, 'AGUA'),
(2390, 21, 3, 8, 0, 'AGUA'),
(2391, 21, 3, 9, 0, 'AGUA'),
(2392, 21, 4, 0, 0, 'AGUA'),
(2393, 21, 4, 1, 0, 'AGUA'),
(2394, 21, 4, 2, 0, 'AGUA'),
(2395, 21, 4, 3, 0, 'AGUA'),
(2396, 21, 4, 4, 0, 'AGUA'),
(2397, 21, 4, 5, 0, 'AGUA'),
(2398, 21, 4, 6, 0, 'AGUA'),
(2399, 21, 4, 7, 0, 'AGUA'),
(2400, 21, 4, 8, 0, 'AGUA'),
(2401, 21, 4, 9, 0, 'AGUA'),
(2402, 21, 5, 0, 0, 'AGUA'),
(2403, 21, 5, 1, 0, 'AGUA'),
(2404, 21, 5, 2, 0, 'AGUA'),
(2405, 21, 5, 3, 0, 'AGUA'),
(2406, 21, 5, 4, 0, 'AGUA'),
(2407, 21, 5, 5, 0, 'AGUA'),
(2408, 21, 5, 6, 0, 'AGUA'),
(2409, 21, 5, 7, 0, 'AGUA'),
(2410, 21, 5, 8, 0, 'AGUA'),
(2411, 21, 5, 9, 0, 'AGUA'),
(2412, 21, 6, 0, 0, 'AGUA'),
(2413, 21, 6, 1, 0, 'AGUA'),
(2414, 21, 6, 2, 0, 'AGUA'),
(2415, 21, 6, 3, 0, 'AGUA'),
(2416, 21, 6, 4, 0, 'AGUA'),
(2417, 21, 6, 5, 0, 'AGUA'),
(2418, 21, 6, 6, 0, 'AGUA'),
(2419, 21, 6, 7, 0, 'AGUA'),
(2420, 21, 6, 8, 0, 'AGUA'),
(2421, 21, 6, 9, 0, 'AGUA'),
(2422, 21, 7, 0, 0, 'AGUA'),
(2423, 21, 7, 1, 0, 'AGUA'),
(2424, 21, 7, 2, 0, 'AGUA'),
(2425, 21, 7, 3, 0, 'AGUA'),
(2426, 21, 7, 4, 0, 'AGUA'),
(2427, 21, 7, 5, 0, 'AGUA'),
(2428, 21, 7, 6, 0, 'AGUA'),
(2429, 21, 7, 7, 0, 'AGUA'),
(2430, 21, 7, 8, 0, 'AGUA'),
(2431, 21, 7, 9, 0, 'AGUA'),
(2432, 21, 8, 0, 0, 'AGUA'),
(2433, 21, 8, 1, 0, 'AGUA'),
(2434, 21, 8, 2, 0, 'AGUA'),
(2435, 21, 8, 3, 0, 'AGUA'),
(2436, 21, 8, 4, 0, 'AGUA'),
(2437, 21, 8, 5, 0, 'AGUA'),
(2438, 21, 8, 6, 0, 'AGUA'),
(2439, 21, 8, 7, 0, 'AGUA'),
(2440, 21, 8, 8, 0, 'AGUA'),
(2441, 21, 8, 9, 0, 'AGUA'),
(2442, 21, 9, 0, 0, 'AGUA'),
(2443, 21, 9, 1, 0, 'AGUA'),
(2444, 21, 9, 2, 0, 'AGUA'),
(2445, 21, 9, 3, 0, 'AGUA'),
(2446, 21, 9, 4, 0, 'AGUA'),
(2447, 21, 9, 5, 0, 'AGUA'),
(2448, 21, 9, 6, 0, 'AGUA'),
(2449, 21, 9, 7, 0, 'AGUA'),
(2450, 21, 9, 8, 0, 'AGUA'),
(2451, 21, 9, 9, 0, 'AGUA'),
(2652, 20, 0, 0, 0, 'AGUA'),
(2653, 20, 0, 1, 0, 'AGUA'),
(2654, 20, 0, 2, 0, 'AGUA'),
(2655, 20, 0, 3, 0, 'AGUA'),
(2656, 20, 0, 4, 0, 'AGUA'),
(2657, 20, 0, 5, 0, 'AGUA'),
(2658, 20, 0, 6, 0, 'AGUA'),
(2659, 20, 0, 7, 0, 'AGUA'),
(2660, 20, 0, 8, 0, 'AGUA'),
(2661, 20, 0, 9, 41, 'OCUPADO'),
(2662, 20, 1, 0, 0, 'AGUA'),
(2663, 20, 1, 1, 0, 'AGUA'),
(2664, 20, 1, 2, 0, 'AGUA'),
(2665, 20, 1, 3, 0, 'AGUA'),
(2666, 20, 1, 4, 0, 'AGUA'),
(2667, 20, 1, 5, 0, 'AGUA'),
(2668, 20, 1, 6, 0, 'AGUA'),
(2669, 20, 1, 7, 0, 'AGUA'),
(2670, 20, 1, 8, 0, 'AGUA'),
(2671, 20, 1, 9, 41, 'OCUPADO'),
(2672, 20, 2, 0, 0, 'AGUA'),
(2673, 20, 2, 1, 0, 'AGUA'),
(2674, 20, 2, 2, 0, 'AGUA'),
(2675, 20, 2, 3, 0, 'AGUA'),
(2676, 20, 2, 4, 0, 'AGUA'),
(2677, 20, 2, 5, 0, 'AGUA'),
(2678, 20, 2, 6, 0, 'AGUA'),
(2679, 20, 2, 7, 0, 'AGUA'),
(2680, 20, 2, 8, 0, 'AGUA'),
(2681, 20, 2, 9, 41, 'OCUPADO'),
(2682, 20, 3, 0, 0, 'AGUA'),
(2683, 20, 3, 1, 0, 'AGUA'),
(2684, 20, 3, 2, 0, 'AGUA'),
(2685, 20, 3, 3, 0, 'AGUA'),
(2686, 20, 3, 4, 0, 'AGUA'),
(2687, 20, 3, 5, 0, 'AGUA'),
(2688, 20, 3, 6, 0, 'AGUA'),
(2689, 20, 3, 7, 0, 'AGUA'),
(2690, 20, 3, 8, 0, 'AGUA'),
(2691, 20, 3, 9, 41, 'OCUPADO'),
(2692, 20, 4, 0, 0, 'AGUA'),
(2693, 20, 4, 1, 0, 'AGUA'),
(2694, 20, 4, 2, 0, 'AGUA'),
(2695, 20, 4, 3, 0, 'AGUA'),
(2696, 20, 4, 4, 0, 'AGUA'),
(2697, 20, 4, 5, 0, 'AGUA'),
(2698, 20, 4, 6, 0, 'AGUA'),
(2699, 20, 4, 7, 0, 'AGUA'),
(2700, 20, 4, 8, 0, 'AGUA'),
(2701, 20, 4, 9, 0, 'AGUA'),
(2702, 20, 5, 0, 0, 'AGUA'),
(2703, 20, 5, 1, 0, 'AGUA'),
(2704, 20, 5, 2, 0, 'AGUA'),
(2705, 20, 5, 3, 0, 'AGUA'),
(2706, 20, 5, 4, 0, 'AGUA'),
(2707, 20, 5, 5, 0, 'AGUA'),
(2708, 20, 5, 6, 0, 'AGUA'),
(2709, 20, 5, 7, 0, 'AGUA'),
(2710, 20, 5, 8, 0, 'AGUA'),
(2711, 20, 5, 9, 0, 'AGUA'),
(2712, 20, 6, 0, 0, 'AGUA'),
(2713, 20, 6, 1, 0, 'AGUA'),
(2714, 20, 6, 2, 0, 'AGUA'),
(2715, 20, 6, 3, 0, 'AGUA'),
(2716, 20, 6, 4, 0, 'AGUA'),
(2717, 20, 6, 5, 0, 'AGUA'),
(2718, 20, 6, 6, 0, 'AGUA'),
(2719, 20, 6, 7, 0, 'AGUA'),
(2720, 20, 6, 8, 0, 'AGUA'),
(2721, 20, 6, 9, 0, 'AGUA'),
(2722, 20, 7, 0, 0, 'AGUA'),
(2723, 20, 7, 1, 0, 'AGUA'),
(2724, 20, 7, 2, 0, 'AGUA'),
(2725, 20, 7, 3, 0, 'AGUA'),
(2726, 20, 7, 4, 0, 'AGUA'),
(2727, 20, 7, 5, 0, 'AGUA'),
(2728, 20, 7, 6, 0, 'AGUA'),
(2729, 20, 7, 7, 0, 'AGUA'),
(2730, 20, 7, 8, 0, 'AGUA'),
(2731, 20, 7, 9, 0, 'AGUA'),
(2732, 20, 8, 0, 0, 'AGUA'),
(2733, 20, 8, 1, 0, 'AGUA'),
(2734, 20, 8, 2, 0, 'AGUA'),
(2735, 20, 8, 3, 0, 'AGUA'),
(2736, 20, 8, 4, 0, 'AGUA'),
(2737, 20, 8, 5, 0, 'AGUA'),
(2738, 20, 8, 6, 0, 'AGUA');

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=49 ;

--
-- Volcado de datos para la tabla `desafios`
--

INSERT INTO `desafios` (`idDesafio`, `monto`, `fechaHoraInicioD`, `estadoD`) VALUES
(37, 50, '2012-10-30 20:51:00', 'Finalizado'),
(39, 100, '2012-10-30 21:22:47', 'En curso'),
(41, 50, '2012-11-04 19:49:04', 'Finalizado'),
(47, 200, '2012-11-18 17:34:02', 'En hora'),
(48, 200, '2012-11-18 18:12:02', 'En hora');

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=52 ;

--
-- Volcado de datos para la tabla `disparos`
--

INSERT INTO `disparos` (`idDisparo`, `t_batalla_naval_idTBatallaNaval`, `idUsuarioD`, `resultadoD`, `xD`, `yD`) VALUES
(1, 15, 3, 'TOCADO', 4, 0),
(2, 15, 3, 'TOCADO', 2, 2),
(25, 15, 3, 'TOCADO', 2, 2),
(26, 15, 3, 'TOCADO', 2, 2),
(27, 15, 3, 'TOCADO', 2, 2),
(28, 15, 3, 'TOCADO', 2, 2),
(29, 15, 3, 'TOCADO', 2, 2),
(30, 15, 3, 'TOCADO', 2, 2),
(31, 15, 3, 'TOCADO', 2, 2),
(32, 15, 3, 'TOCADO', 2, 2),
(33, 15, 3, 'TOCADO', 2, 2),
(34, 15, 3, 'TOCADO', 2, 2),
(35, 15, 3, 'TOCADO', 2, 2),
(36, 15, 3, 'TOCADO', 2, 2),
(37, 15, 3, 'TOCADO', 2, 2),
(38, 15, 3, 'TOCADO', 2, 2),
(39, 15, 3, 'TOCADO', 2, 2),
(40, 15, 3, 'TOCADO', 2, 2),
(41, 15, 3, 'TOCADO', 2, 2),
(42, 15, 3, 'TOCADO', 2, 2),
(43, 15, 3, 'TOCADO', 2, 2),
(44, 15, 3, 'TOCADO', 2, 2),
(45, 15, 3, 'TOCADO', 2, 2),
(46, 15, 3, 'TOCADO', 2, 2),
(47, 15, 3, 'TOCADO', 2, 2),
(48, 15, 3, 'TOCADO', 2, 2),
(49, 15, 3, 'TOCADO', 2, 2),
(50, 15, 3, 'TOCADO', 2, 2),
(51, 15, 3, 'TOCADO', 2, 2);

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
(1, 1, 7),
(3, 1, 5),
(4, 1, 2),
(8, 1, 4),
(2, 1, 1),
(6, 1, 1);

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=19 ;

--
-- Volcado de datos para la tabla `t_batalla_naval`
--

INSERT INTO `t_batalla_naval` (`idTBatallaNaval`, `desafios_idDesafio`, `jugador`, `miTurno`, `barcosSubmarinos`, `barcosDestructores`, `barcosCruceros`, `barcosAcorazados`, `barcosSubmarinosColocados`, `barcosDestructoresColocados`, `barcosCrucerosColocados`, `barcosAcorazadosColocados`) VALUES
(11, 37, 'fscuoteguazza', 1, 0, 0, 0, 1, 0, 0, 0, 0),
(12, 37, 'jhirata', 0, 0, 0, 0, 1, 0, 0, 0, 0),
(15, 39, 'fscuoteguazza', 1, 0, 0, 0, 1, 0, 0, 0, 0),
(16, 39, 'dgonzalez', 0, 0, 0, 0, 1, 0, 0, 0, 1);

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=16 ;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`idusuario`, `usuario`, `clave`, `nivelPrivilegio`, `virtual`, `credito`, `partidasGanadas`, `nombre`, `apellido`, `pais`) VALUES
(1, 'jhirata', 'jh', 2, 0, 30200, 5, 'John', 'Hirata', 'Inglaterra'),
(2, 'vtuyare', 'vt', 2, 0, 30000, 9, 'Vicente', 'Tuyare', 'Uruguay'),
(3, 'fscuoteguazza', 'fs', 2, 1, 29850, 4, 'Federico', 'Scuoteguazza', 'Inglaterra'),
(4, 'pvaztourem', 'pv', 1, 1, 40050, 45, 'Patricia', 'Vaztourem', 'Uruguay'),
(5, 'ahirata', 'ah', 1, 1, 30000, 69, 'Andrés', 'Hirata', 'Inglaterra'),
(6, 'dgonzalez', 'dg', 1, 0, 9100, 78, 'Diego', 'Gonzalez', 'Inglaterra'),
(7, 'fkono', 'fk', 1, 1, 30000, 87, 'Fumie', 'Kono', 'Inglaterra'),
(8, 'thirata', 'th', 1, 0, 5000, 98, 'Tomiyoshi', 'Hirata', 'Inglaterra'),
(9, 'jdiaz', 'jd', 1, 1, 5000, 78, 'Javier', 'Diaz', 'Uruguay'),
(10, 'jrodriguez', 'jr', 1, 1, 30000, 33, 'Javier', 'Rodriguez', 'Uruguay'),
(11, 'lhirata', 'lh', 1, 0, 3000, 0, 'Lisa', 'Hirata', 'Uruguay'),
(15, 'jvf', 'jvf', 3, 0, 5000, 0, 'Propietario', 'Casino', 'Uruguay');

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
(1, 37, 1, 1),
(1, 37, 3, 1),
(1, 39, 3, 0),
(1, 39, 6, 0),
(1, 41, 1, 4),
(1, 41, 4, 4),
(1, 47, 1, 0),
(1, 48, 11, 0);

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
