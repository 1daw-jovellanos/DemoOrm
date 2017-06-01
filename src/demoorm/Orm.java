package demoorm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Esta clase es el ORM (Mapeador Objeto-Relacional) para la app de almacén.
 *
 * Todas las operaciones de BD están aquí. Sin embargo, fuera del ORM, los
 * productos se manejan con objetos, y varios productos con una colección.
 *
 * @author Tú
 * @version
 */
public class Orm {

    private static String JDBC_CONNECTION = "jdbc:h2:./almacen";
    private static String JDBC_USER = "sa";
    private static String JDBC_PASS = "";

    /**
     * El constructor. Trata de cargar el driver
     * Averigua si la BD existe, y si no es así, la crea.
     * @throws SQLException Si no se puede cargar el driver o no se puede crear
     *  la BD.
     */
    public Orm() throws SQLException {
        try {
            Class.forName("org.h2.Driver");
        } catch (Exception ex) {
            String errMsg = "No se pudo cargar el driver de h2.";
            System.err.println(errMsg);
            throw new SQLException(errMsg);
        }
        if (!existeBD()) {
            crearBD();
        }
    }

    private boolean existeBD() {
        boolean existe = true;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(
                    JDBC_CONNECTION + ";IFEXISTS=TRUE",
                    JDBC_USER, JDBC_PASS);
        } catch (SQLException ex) {
            existe = false;
            System.err.println("No existe la BD.");
        } finally {
            try {
                conn.close();
            } catch (Exception ex) {
            }
        }
        return existe;
    }

    /**
     * Intenta crear la BD.
     * H2 crea automáticamente la BD cuando no existe, pero vacía.
     * Ejecutamos el script almacen.sql.
     * 
     * @throws SQLException Cuando no se puede crear la BD y su tabla.
     */
    private void crearBD() throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        System.err.println("Creando BD y tabla.");
        try {
            conn = DriverManager.getConnection(
                    JDBC_CONNECTION,
                    JDBC_USER, JDBC_PASS);
            pstmt = conn.prepareStatement("runscript from 'almacen.sql'");
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            throw ex; // Se relanza la excepción 
        } finally {
            try {
                pstmt.close();
            } catch (Exception ex) {
            }
            try {
                conn.close();
            } catch (Exception ex) {
            }
        }

    }

    public boolean insertarProducto(Producto p) {
        // POR HACER:
        // Conectar a la BD e insertar los datos del producto p.
        // Devolver true si la inserción se pudo realizar.
        // false si el SGBD no admite la inserción

        // Si el SGBD devuelve excepción por error sintáctico en SQL 
        // debe ser subsanado.
        return false;
    }

    boolean borrarProducto(String ean) {
        // POR HACER:
        // Conectar a la BD y borrar la fila cuya pk sea el código ean
        // pasado como parámetro.

        // Devolver true si se borró la fila
        // devolver false si no se borró o se produjo algún error de BD.
        // Si el SGBD devuelve excepción por error sintáctico en SQL 
        // debe ser subsanado.
        return false;
    }

    List<Producto> obtenerProductosBajoMinimos() {
        List<Producto> resultado = new ArrayList<Producto>();
        // POR HACER:
        // Conectar a la BD y obtener las filas de la tabla cuyo
        // stock actual sea inferior al stock mínimo

        // Crear un objeto de la clase producto por cada fila
        // devuelta de la BD.
        // Se devuelve la lista vacía si hay excepción
        return resultado;
    }

    List<Producto> obtenerProductosPorNombre(String cadenaDeBusqueda) {
        List<Producto> resultado = new ArrayList<Producto>();
        // POR HACER:
        // Conectar a la BD y obtener las filas de la tabla cuyo
        // nombre CONTENGA la cadena de búsqueda, en mayúsculas o minúsculas,
        // con el operador LIKE

        // H2 dispone de una funcion llamada UPPER(...) que pasa a 
        // mayúsculas un texto.
        // Ej... Para buscar un producto que contenga la cadena "patata"
        // ... WHERE UPPER(nombre) LIKE ?
        // y se establece el placeholder a "%patata%"
        // Crear un objeto de la clase producto por cada fila
        // devuelta de la BD.
        // Se devuelve la lista vacía si hay excepción
        return resultado;
    }

    Producto obtenerProducto(String ean) {
        Producto resultado = null;
        // POR HACER:
        // Conectar a la BD y obtener la fila correspondiente al producto
        // cuyo ean corresponda al pasado como parámetro.

        // Ten en cuenta que obtendremos 1 fila o quizá 0, porque el ean
        // es la pk
        // H2 dispone de una funcion llamada UPPER(...) que pasa a 
        // Crear un objeto de la clase producto si se devuelve un fila
        // Se devuelve null si no se obtiene ninguna fila o hay
        // cualquier excepción
        return resultado;
    }

    boolean modificarProducto(Producto p) {
        // POR HACER:
        // Conectar a la BD y modificar los campos
        // nombre, stockActual y stockMinimo con los valores que
        // vienen en el objeto referenciado por p
        // para aquella fila cuyo ean sea el del objeto
        // referenciado por p

        // Devuelve true si la modificación afectó a una fila
        // false si no afectó a ninguna fila o se produjo alguna
        // excepcion
        return false;
    }

}
