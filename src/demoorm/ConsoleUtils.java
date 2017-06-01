package demoorm;

import java.util.*;

/**
 *
 * @author victor
 */
public class ConsoleUtils {

    private static Scanner in;

    static {
        in = new Scanner(System.in);
    }

    /**
     * Pide un entero por consola y se asegur de que esté entre dos valores.
     *
     * @param mensaje El mensaje que se muestra para pedir el entero
     * @param min El valor mínimo aceptado (incluído)
     * @param max El valor máximo aceptado (incluído)
     * @return El entero introducido por terminal ya validado
     */
    public static int pedirEntero(String mensaje, int min, int max) {
        int resultado = 0;
        boolean ok = false;
        do {
            System.out.format("%s [%d-%d]: ", mensaje, min, max);
            try {
                resultado = Integer.parseInt(in.nextLine());
                ok = resultado >= min && resultado <= max;
            } catch (Exception ex) {
                ok = false;
            }
            if (!ok) {
                System.out.format("Por favor, introduzca un valor en el rango entre %d y %d%n", min, max);
            }
        } while (!ok);
        return resultado;
    }

    /**
     * Pide una cadena por teclado
     *
     * @param mensaje El mensaje que se muestra antes
     * @return La cadena obtenida
     */
    public static String pedirCadena(String mensaje) {
        String resultado;
        System.out.format("%s: ", mensaje);
        resultado = in.nextLine();
        return resultado;
    }

    /**
     * Pide una confirmación por el teclado.
     * @param mensaje El mensaje que se emite
     * @return true si el usuario escribe "s" o "S" y false en
     *      cualquier otro caso
     */
    public static boolean pedirSiNo(String mensaje) {
        return (pedirCadena(mensaje + "(s/N)").toLowerCase().equals("s"));
    }

    /**
     * Escribe un menú con texto, numerando las opciones. En la posición 0
     * estará siempre la frase que representa volver o salir El método solicita
     * la opción al usuario y se asegura de que es correcta.
     *
     * Ej: menú("Salir", "Primera opción", "Otra opción", "Y la tercera")
     *
     * @param opciones Las leyendas de las opciones del menú
     * @param titulo El título del menú
     * @return la opción seleccionada.
     */
    public static int menu(String[] opciones, String titulo) {
        System.out.println(titulo);
        for (int i = 0; i < titulo.length(); i++) {
            System.out.print("=");
        }
        System.out.println();
        for (int i = 1; i < opciones.length; i++) {
            System.out.format(" %2d. %s%n", i, opciones[i]);
        }
        System.out.format("  0. %s%n", opciones[0]);
        int opcion = pedirEntero("  --Su opción", 0, opciones.length - 1);
        System.out.println();
        return opcion;
    }

    /**
     * Imprime n veces el carácter c
     *
     * @param c
     * @param n
     */
    public static void imprimirLinea(int n, char c) {
        for (int i = 0; i < n; i++) {
            System.out.print(c);
        }
    }

    /**
     * Imprime n veces el carácter c y luego un cambio de línea
     *
     * @param c
     * @param n
     */
    public static void imprimirLineaLn(int n, char c) {
        imprimirLinea(n, c);
        System.out.println();
    }

    /**
     * Imprime una caja con un mensaje con caracteres ASCII
     *
     * @param mensaje El mensaje dentro de la caja
     */
    public static void imprimirCaja(String mensaje) {
        System.out.print("+");
        imprimirLinea(mensaje.length() + 2, '-');
        System.out.println("+");
        System.out.print("|");
        System.out.format(" %s ", mensaje);
        System.out.println("|");
        System.out.print("+");
        imprimirLinea(mensaje.length() + 2, '-');
        System.out.println("+");
    }

    public static void main(String[] args) {
        // A modo de ejemplo
        imprimirCaja("Demo"); // Una caja
        // Un menú.
        System.out.format("La opción escogida es %d%n",
                menu(new String[]{"Salir", "Uno", "Dos"}, "Titulo")
        );
        // Una línea de 60 asteriscos
        imprimirLineaLn(60, '*');
    }

}
