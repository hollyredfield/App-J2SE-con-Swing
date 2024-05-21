package controlador;

import modelo.Producto;
import modelo.Inventario;
import dao.ProductoDAO;
import vista.VistaInventario;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Aplicacion {

    public static void main(String[] args) {
        // Crear una instancia de ProductoDAO
        ProductoDAO productoDAO = new ProductoDAO();

        // Obtener la lista de productos de la base de datos
        java.util.List<Producto> productos = productoDAO.obtenerProductos();

        // Crear una instancia de Inventario y establecer la lista de productos
        Inventario inventario = new Inventario();
        inventario.setProductos(productos);

        // Crear una instancia de VistaInventario
        VistaInventario vistaInventario = new VistaInventario(productoDAO);

        // Crear un modelo de tabla y establecerlo en la tabla de la vista
        DefaultTableModel modelo = new DefaultTableModel(new Object[]{"ID", "Nombre", "Cantidad"}, 0);
        vistaInventario.getTablaProductos().setModel(modelo);

        // Llenar la tabla con los datos del inventario
        for (Producto producto : inventario.getProductos()) {
            modelo.addRow(new Object[]{producto.getId(), producto.getNombre(), producto.getCantidad()});
        }

        // Mostrar la vista
        vistaInventario.setVisible(true);
    }
}