package com.manuf.lospanas;

public interface descuentos {
    public void SetDescuento();
    public float GetDescuento(float precio, float costo);

    public float GetPrecioDescontado(float precio, float multiplicador);
}
