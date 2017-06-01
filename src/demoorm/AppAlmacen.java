
package demoorm;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase Principal de una aplicación sencilla de control de almacén
 * de un negocio, utilizando una tabla en el SGBD-R h2 en modo embebido.
 * 
 * Esta clase utiliza objetos (el objeto {@link Producto} representa un
 * producto del almacén.
 * 
 * Un ORM (Mapeador Objeto-Relacional) representado por la clase
 * {@link Orm} convierte objetos en filas de tablas y
 * viceversa.
 * 
 * Salvo error u omisión, esta clase está completa y es funcional. También
 * la clase {@link Producto}
 * 
 * El ejercicio consiste en completar los métodos del ORM.
 * 
 * La clase auxiliar {@link ConsoleUtils} contiene métodos sencillos para
 * facilitar actividades comunes con el la consola
 * 
 * @author victor
 */
public class AppAlmacen {

    Orm orm;

// -------------- Métodos para --------------------------------------------    
// -------------- La resolución de los casos de uso -----------------------
    
// (La visibilidad de los métodos de esta case es poco relevante)    
    void menuInsertar() {
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

    void menuBorrar() {
        ConsoleUtils.imprimirCaja("BORRAR PRODUCTO");
        String ean = ConsoleUtils.pedirCadena("Código EAN a borrar");
        boolean ok = orm.borrarProducto(ean);
        if (ok) {
            System.out.println("Producto borrado");
        } else {
            System.out.println("No se pudo borrar. Verifique que el código EAN existe.");
        }
    }

    // ----------- Método para auxiliar a los dos listados del menú
    private void listarProductos(List<Producto> productos) {
        System.out.format("%10s %-20s %6s %6s%n", "EAN", "Nombre", "act.", "min.");
        ConsoleUtils.imprimirLineaLn(10 + 1 + 20 + 1 + 6 + 1 + 6, '-');
        for (Producto p : productos) {
            System.out.format("%10s %-20s %6s %6s%n", p.getEAN(),
                    p.getNombre(), p.getStockActual(), p.getStockMinimo());
        }
    }

    void menuListarBajoMinimos() {
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

    void menuModificar() {
        //--- Para modificar, primero se obtiene el producto por EAN---
        ConsoleUtils.imprimirCaja("Modificar producto");
        String ean = ConsoleUtils.pedirCadena("Código EAN");
        Producto p = orm.obtenerProducto(ean);
        if (p == null) {
            System.out.println("No se pudo encontrar el producto.");
            return;
        }
        
        //--- y Después se obtienen las modificaciones y se pasa el 
        //--- objeto al Orm para que lo modifique en la tabla
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


// -------------- El método principal con el menú --------------------------
    void run() {
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
