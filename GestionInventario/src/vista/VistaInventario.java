package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import modelo.Producto;
import dao.ProductoDAO;
import javax.swing.table.DefaultTableModel;

public class VistaInventario extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTable tablaProductos;
    private ProductoDAO productoDAO;
    private JButton btnAgregar;
    private JButton btnEliminar;
    private JButton btnModificar;

    public VistaInventario(ProductoDAO productoDAO) {
        this.productoDAO = productoDAO;

        setTitle("Inventario");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        tablaProductos = new JTable();
        tablaProductos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // Doble clic para editar o eliminar
                    int filaSeleccionada = tablaProductos.getSelectedRow();
                    if (filaSeleccionada != -1) {
                        // Obtén el producto seleccionado
                        Producto producto = obtenerProductoDeLaFila(filaSeleccionada);

                        // Abre un JFrame o JOptionPane para editar o eliminar
                        boolean eliminado = abrirDialogoDeEdicionOEliminacion(producto);

                        // Si el producto fue eliminado, elimínalo de la base de datos y de la tabla
                        if (eliminado) {
                            productoDAO.eliminarProducto(producto);
                            ((DefaultTableModel) tablaProductos.getModel()).removeRow(filaSeleccionada);
                        } else {
                            // Si el producto fue modificado, actualízalo en la base de datos y en la tabla
                            productoDAO.actualizarProducto(producto);
                            actualizarFilaDeLaTabla(filaSeleccionada, producto);
                        }
                    }
                }
            }
        });
        add(new JScrollPane(tablaProductos), BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        btnAgregar = new JButton("Agregar");
        btnEliminar = new JButton("Eliminar");
        btnModificar = new JButton("Modificar");

        btnAgregar.addActionListener(e -> agregarProducto());
        btnEliminar.addActionListener(e -> eliminarProducto());
        btnModificar.addActionListener(e -> modificarProducto());

        panelBotones.add(btnAgregar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnModificar);

        add(panelBotones, BorderLayout.SOUTH);
    }

    public JTable getTablaProductos() {
        return tablaProductos;
    }

    private Producto obtenerProductoDeLaFila(int fila) {
        DefaultTableModel modelo = (DefaultTableModel) tablaProductos.getModel();
        int id = (int) modelo.getValueAt(fila, 0);
        String nombre = (String) modelo.getValueAt(fila, 1);
        int cantidad = (int) modelo.getValueAt(fila, 2);

        Producto producto = new Producto();
        producto.setId(id);
        producto.setNombre(nombre);
        producto.setCantidad(cantidad);

        return producto;
    }

    private boolean abrirDialogoDeEdicionOEliminacion(Producto producto) {
        String[] opciones = {"Editar", "Eliminar"};
        int opcion = JOptionPane.showOptionDialog(null, "¿Qué quieres hacer con el producto?", "Elige una opción", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

        if (opcion == 0) { // Editar
            String nuevoNombre = JOptionPane.showInputDialog(null, "Introduce el nuevo nombre del producto", producto.getNombre());
            int nuevaCantidad = Integer.parseInt(JOptionPane.showInputDialog(null, "Introduce la nueva cantidad del producto", producto.getCantidad()));

            producto.setNombre(nuevoNombre);
            producto.setCantidad(nuevaCantidad);

            return false;
        } else if (opcion == 1) { // Eliminar
            return true;
        }

        return false;
    }

    private void actualizarFilaDeLaTabla(int fila, Producto producto) {
        DefaultTableModel modelo = (DefaultTableModel) tablaProductos.getModel();
        modelo.setValueAt(producto.getId(), fila, 0);
        modelo.setValueAt(producto.getNombre(), fila, 1);
        modelo.setValueAt(producto.getCantidad(), fila, 2);
    }

    private void agregarProducto() {
        String nombre = JOptionPane.showInputDialog(null, "Introduce el nombre del producto");
        int cantidad = Integer.parseInt(JOptionPane.showInputDialog(null, "Introduce la cantidad del producto"));

        Producto producto = new Producto();
        producto.setNombre(nombre);
        producto.setCantidad(cantidad);

        productoDAO.agregarProducto(producto);

        DefaultTableModel modelo = (DefaultTableModel) tablaProductos.getModel();
        modelo.addRow(new Object[]{producto.getId(), producto.getNombre(), producto.getCantidad()});
    }

    private void eliminarProducto() {
        int filaSeleccionada = tablaProductos.getSelectedRow();
        if (filaSeleccionada != -1) {
            Producto producto = obtenerProductoDeLaFila(filaSeleccionada);

            int confirmacion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de eliminar el producto?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
            if (confirmacion == JOptionPane.YES_OPTION) {
                productoDAO.eliminarProducto(producto);
                ((DefaultTableModel) tablaProductos.getModel()).removeRow(filaSeleccionada);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecciona un producto para eliminar.");
        }
    }

    private void modificarProducto() {
        int filaSeleccionada = tablaProductos.getSelectedRow();
        if (filaSeleccionada != -1) {
            Producto producto = obtenerProductoDeLaFila(filaSeleccionada);

            String nuevoNombre = JOptionPane.showInputDialog(null, "Introduce el nuevo nombre del producto", producto.getNombre());
            int nuevaCantidad = Integer.parseInt(JOptionPane.showInputDialog(null, "Introduce la nueva cantidad del producto", producto.getCantidad()));

            producto.setNombre(nuevoNombre);
            producto.setCantidad(nuevaCantidad);

            productoDAO.actualizarProducto(producto);
            actualizarFilaDeLaTabla(filaSeleccionada, producto);
        } else {
            JOptionPane.showMessageDialog(null, "Selecciona un producto para modificar.");
        }
    }
}
