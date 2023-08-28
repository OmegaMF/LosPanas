package com.manuf.lospanas;

public class Bebidas extends Producto implements comestibles, descuentos {
    boolean alcoholica;
    float graduacionAlc;
    boolean importado;

    public Bebidas(String identificador, String descripcion, int cantidadEnStock, float precio, float costo,
                     boolean disponibleParaVenta,boolean alcoholica, boolean importado){
        this.identificador=identificador;
        this.descripcion=descripcion;
        this.cantidadEnStock=cantidadEnStock;
        this.precio=precio;
        this.costo=costo;
        this.disponibleParaVenta=disponibleParaVenta;
        this.alcoholica=alcoholica;
        this.importado=importado;
    }

    public void SetDescuento(){ //No uso el SetDescuento porque mis productos son seteados desde el incio y el programa busca el mayor descuento posible que cumpla con los parámetros

    }

    public float GetDescuento(float precio, float costo){ //al momento de intentar aplicar esto no supe como hacer para que el programa detecte cual de los 3 tipos de "Producto" es, y que pueda acceder a la función correspondiente asi que apliqué los descuentos con un método alternativo
        float maxDescuento=0.85f;
        for(float i = maxDescuento; i < 1f; i+=0.01f){
            if((precio*i)>costo){
                return i;
            }
        }
        return 1f; //acá se comunicaría que el descuento no pudo ser aplicado
    }

    public float GetPrecioDescontado(float precio, float multiplicador){
        return precio*multiplicador;
    }

    public void SetFechaVencimiento(){

    }
    public void GetFechaVencimiento(){

    }
    
    public void SetCalorias(){

    }
    public int GetCalorias(){
        return 0;
    }
}
