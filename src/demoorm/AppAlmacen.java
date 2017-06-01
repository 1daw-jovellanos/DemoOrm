/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demoorm;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author victor
 */
public class AppAlmacen {

    Orm orm;

    private void menuInsertar() {
        ConsoleUtils.imprimirCaja("INSERTAR NUEVO PRODUCTO");
        String ean = ConsoleUtils.pedirCadena("-Código EAN");
        String nombre = ConsoleUtils.pedirCadena("-Nombre producto");
        int stockActual = ConsoleUtils.pedirEntero("-Stock actual", 0, 100000);
        int stockMinimo = ConsoleUtils.pedirEntero("-Stock Mínimo", 0, 100000);
        Producto p = new Producto(ean, nombre, stockActual, stockMinimo);
        boolean ok = orm.insertarProducto(p);
        if (ok) {
            System.out.println("Producto insertado");
        } else {
            System.out.println("No se pudo insertar. Verifique que el código EAN no existe ya.");
        }
    }

    private void menuBorrar() {
        ConsoleUtils.imprimirCaja("BORRAR PRODUCTO");
        String ean = ConsoleUtils.pedirCadena("Código EAN a borrar");
        boolean ok = orm.borrarProducto(ean);
        if (ok) {
            System.out.println("Producto borrado");
        } else {
            System.out.println("No se pudo borrar. Verifique que el código EAN existe.");
        }
    }

    private void listarProductos(List<Producto> productos) {
        System.out.format("%10s %-20s %6s %6s%n", "EAN", "Nombre", "act.", "min.");
        ConsoleUtils.imprimirLineaLn(10 + 1 + 20 + 1 + 6 + 1 + 6, '-');
        for (Producto p : productos) {
            System.out.format("%10s %-20s %6s %6s%n", p.getEAN(),
                    p.getNombre(), p.getStockActual(), p.getStockMinimo());
        }
    }

    private void menuListarBajoMinimos() {
        ConsoleUtils.imprimirCaja("Listar productos bajo mínimos");
        List<Producto> listaProductos = orm.obtenerProductosBajoMinimos();
        listarProductos(listaProductos);
    }

    private void menuListarPorNombre() {
        ConsoleUtils.imprimirCaja("Buscar por nombre");
        String cadenaDeBusqueda = ConsoleUtils.pedirCadena("Cadena a buscar");
        List<Producto> listaProductos = orm.obtenerProductosPorNombre(cadenaDeBusqueda);
        listarProductos(listaProductos);
    }

    private void menuModificar() {
        ConsoleUtils.imprimirCaja("Modificar producto");
        String ean = ConsoleUtils.pedirCadena("Código EAN");
        Producto p = orm.obtenerProducto(ean);
        if (p == null) {
            System.out.println("No se pudo encontrar el producto.");
            return;
        }
        if (ConsoleUtils.pedirSiNo("Modificar nombre [" + p.getNombre() + "]")) {
            p.setNombre(ConsoleUtils.pedirCadena("Nuevo nombre"));
        }
        if (ConsoleUtils.pedirSiNo("Modificar stock actual [" + p.getStockActual() + "]")) {
            p.setStockActual(ConsoleUtils.pedirEntero("Nuevo stock actual", 0, 100000));
        }
        if (ConsoleUtils.pedirSiNo("Modificar stock minimo [" + p.getStockMinimo() + "]")) {
            p.setStockMinimo(ConsoleUtils.pedirEntero("Nuevo stock minimo", 0, 100000));
        }
        boolean ok = orm.modificarProducto(p);
        if (ok) {
            System.out.println("Producto modificado");
        } else {
            System.out.println("No se pudo modificar por causas desconocidas.");
        }
    }

    public void run() {
        boolean driverOk = true;
        try {
            orm = new Orm();
        } catch (Exception ex) {
            driverOk = false;
        }
        if (!driverOk) {
            System.out.println("No se pudo inicializar la BD.");
            System.out.println("Imposible continuar.");
            return;
        }

        int opcion;
        do {
            System.out.println();
            opcion = ConsoleUtils.menu(
                    new String[]{
                        "Salir",
                        "Insertar producto",
                        "Borrar producto por código",
                        "Listar productos bajo mínimos",
                        "Listar productos por nombre",
                        "Modificar un producto",},
                    "Menú Principal"
            );
            switch (opcion) {
                case 1:
                    menuInsertar();
                    break;
                case 2:
                    menuBorrar();
                    break;
                case 3:
                    menuListarBajoMinimos();
                    break;
                case 4:
                    menuListarPorNombre();
                    break;
                case 5:
                    menuModificar();
                    break;
            }
        } while (opcion != 0);

    }

    public static void main(String[] args) {
        new AppAlmacen().run();
    }

}
