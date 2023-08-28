package com.manuf.lospanas;
//import java.util.Dictionary;
import java.util.ArrayList;
import java.util.List;

public class Tienda {  
    String nombre;
    int maxProductosEnStock;
    float saldoEnCaja;
    //Dictionary<String,Producto> productosHabilitados; //define si están o no habilitados para la venta / el string es el identificador. No lo utilizo porque lo hice de una manera que usando la lista de elementos totales, mira cuales están habilitados y cuales no, y solo muestra los que sí los están
    List<Producto> productos=new ArrayList<>();

    public Tienda(String nombre, int maxProductosEnStock, float saldoEnCaja){
        this.nombre=nombre;
        this.maxProductosEnStock=maxProductosEnStock;
        this.saldoEnCaja=saldoEnCaja;
    }

    public void agregarObjeto(Producto producto){
        productos.add(producto);
    }
}
