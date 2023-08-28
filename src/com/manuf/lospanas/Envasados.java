package com.manuf.lospanas;

public class Envasados extends Producto implements comestibles, descuentos{

    enum Material{
        PLASTICO,
        VIDRIO,
        LATA
    }
    boolean importado;
    public Material material;

    public Envasados(String identificador, String descripcion, int cantidadEnStock, float precio,
                     float costo, boolean disponibleParaVenta, Material material, boolean importado){
        this.identificador=identificador;
        this.descripcion=descripcion;
        this.cantidadEnStock=cantidadEnStock;
        this.precio=precio;
        this.costo=costo;
        this.disponibleParaVenta=disponibleParaVenta;
        this.material=material;
        this.importado=importado;
    }

    public void SetDescuento(){

    }

    public float GetDescuento(float precio, float costo){ //al momento de intentar aplicar esto no supe como hacer para que el programa detecte cual de los 3 tipos de "Producto" es, y que pueda acceder a la función correspondiente asi que apliqué los descuentos con un método alternativo pero lo dejo hecho para que se vea
        float maxDescuento=0.80f;
        for(float i = maxDescuento; i < 1f; i+=0.01f){
            if((precio*i)>costo){
                return i;
            }
        }
        return 1f;
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
