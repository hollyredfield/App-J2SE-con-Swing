package dao;

import modelo.Producto;
import conectaBD.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {
    public List<Producto> obtenerProductos() {
        List<Producto> productos = new ArrayList<>();
        try (Connection conn = Conexion.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Productos")) {

            while (rs.next()) {
                Producto producto = new Producto();
                producto.setId(rs.getInt("id"));
                producto.setNombre(rs.getString("nombre"));
                producto.setCantidad(rs.getInt("cantidad"));
                productos.add(producto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }

    public void agregarProducto(Producto producto) {
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO Productos (nombre, cantidad) VALUES (?, ?)")) {

            stmt.setString(1, producto.getNombre());
            stmt.setInt(2, producto.getCantidad());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarProducto(Producto producto) {
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement("UPDATE Productos SET nombre = ?, cantidad = ? WHERE id = ?")) {

            stmt.setString(1, producto.getNombre());
            stmt.setInt(2, producto.getCantidad());
            stmt.setInt(3, producto.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarProducto(Producto producto) {
        try (Connection conn = Conexion.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM Productos WHERE id = ?")) {

            stmt.setInt(1, producto.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
