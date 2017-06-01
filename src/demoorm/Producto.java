
package demoorm;

public class Producto {
    private String EAN;
    private String nombre;
    private int stockActual;
    private int stockMinimo;

    public Producto(String EAN, String nombre, int stockActual, int stockMinimo) {
        this.EAN = EAN;
        this.nombre = nombre;
        this.stockActual = stockActual;
        this.stockMinimo = stockMinimo;
    }

    public String getEAN() {
        return EAN;
    }

    public void setEAN(String EAN) {
        this.EAN = EAN;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getStockActual() {
        return stockActual;
    }

    public void setStockActual(int stockActual) {
        this.stockActual = stockActual;
    }

    public int getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(int stockMinimo) {
        this.stockMinimo = stockMinimo;
    }
    
}
