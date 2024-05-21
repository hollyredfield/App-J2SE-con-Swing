# App-J2SE-con-Swing
📦 Sistema de Gestión de Inventario
Bienvenido al repositorio del Sistema de Gestión de Inventario. Este proyecto demuestra una aplicación robusta para la gestión de inventario, diseñada para optimizar las operaciones de un negocio de venta de productos. Construida utilizando Java Swing para una interfaz de escritorio intuitiva y JDBC con MySQL para un backend confiable, esta aplicación permite a los usuarios gestionar eficientemente su inventario de productos.

🚀 Características
Agregar Productos: Añade nuevos productos al inventario con facilidad.
Modificar Productos: Actualiza los detalles de los productos para mantener el inventario preciso.
Eliminar Productos: Elimina productos obsoletos o no deseados del inventario.
Generar Informes: Crea informes detallados del estado actual del inventario, proporcionando información completa.
🛠️ Tecnologías Utilizadas
Java SE con Swing: Para crear una aplicación de escritorio interactiva y fácil de usar.
Java EE con JDBC: Para establecer una conexión segura y eficiente entre la aplicación y la base de datos MySQL.
MySQL: Como el sistema de gestión de bases de datos relacional para almacenar y gestionar los datos del inventario.
📋 Requisitos
Java Development Kit (JDK) 8+
Servidor MySQL
Maven (para la gestión de dependencias)
🔧 Instrucciones de Configuración
Clonar el repositorio:

bash
Copy code
git clone https://github.com/hollyredfield/App-J2SE-con-Swing.git
cd App-J2SE-con-Swing
Configurar la base de datos:

Instala y arranca el servidor MySQL.

Crea una nueva base de datos llamada inventory_management_db.

Ejecuta el siguiente script SQL para crear las tablas necesarias e insertar datos de muestra:

sql
Copy code
-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: May 21, 2024 at 09:03 AM
-- Server version: 8.3.0
-- PHP Version: 8.2.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

-- Database: `inventory_management_db`
-- Table structure for table `productos`
DROP TABLE IF EXISTS `productos`;
CREATE TABLE IF NOT EXISTS `productos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) DEFAULT NULL,
  `cantidad` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table `productos`
INSERT INTO `productos` (`id`, `nombre`, `cantidad`) VALUES
(1, 'Iphone patatófono', 502),
(2, 'Samsung Galaxy S21', 300),
(3, 'MacBook Pro', 200),
(4, 'Dell XPS 15', 150),
(5, 'Sony PlayStation 5', 120),
(6, 'Microsoft Xbox Series X', 100),
(7, 'Samsung Galaxy S22', 1000),
(8, 'Huawei 12 Pro', 1500),
(9, 'Samsung Galaxy S23', 1200),
(10, 'Huawei 11 XT', 2000),
(11, 'Samsung Galaxy S24', 1500),
(12, 'Huawei 10 XT', 2000),
(13, 'iPhone 13', 30),
(14, 'Xiaomi Mi 11', 40),
(15, 'OnePlus 9', 50),
(16, 'Google Pixel 6', 60),
(17, 'Sony Xperia 5', 70),
(18, 'LG V60 ThinQ', 80),
(19, 'Motorola Edge', 90),
(20, 'Nokia 9 PureView', 100),
(21, 'Asus Zenfone 8', 110),
(22, 'ZTE Axon 30', 120),
(23, 'Realme GT', 130),
(24, 'Oppo Find X3', 140),
(25, 'Vivo X60 Pro', 150),
(26, 'Tecno Phantom X', 160),
(27, 'Infinix Zero X', 170);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
Configurar la aplicación:

Actualiza el archivo db.properties con tus credenciales de MySQL:
properties
Copy code
db.url=jdbc:mysql://localhost:3306/inventory_management_db
db.username=root
db.password=tucontraseña
Construir y ejecutar la aplicación:

bash
Copy code
mvn clean install
java -jar target/inventory-management-system.jar
🌟 Uso
Agregar un Producto: Navega a la sección "Agregar Producto", completa los detalles del producto y haz clic en "Agregar".
Modificar un Producto: Selecciona un producto de la lista, actualiza los campos necesarios y haz clic en "Guardar".
Eliminar un Producto: Selecciona un producto y haz clic en "Eliminar".
Generar Informes: Navega a la sección "Informes" y haz clic en "Generar Informe" para ver el estado actual del inventario.
📄 Licencia
Este proyecto está licenciado bajo la Licencia MIT. Consulta el archivo LICENSE para más detalles.

🤝 Contribuciones
¡Las contribuciones son bienvenidas! Por favor, haz un fork del repositorio y envía un pull request para cualquier mejora o corrección de errores.

👨‍💻 Autores
hollyredfield - Perfil de GitHub
¡Siéntete libre de explorar, usar y contribuir a este proyecto! Para cualquier pregunta o problema, abre un issue en GitHub.

¡Feliz programación! ✨