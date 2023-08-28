package com.manuf.lospanas;
import java.util.Scanner;

import com.manuf.lospanas.Envasados.Material;
import com.manuf.lospanas.Limpieza.TiposDeAplicacion;

public class App {
    public static void main(String[] args) throws Exception {
       
        Scanner sc = new Scanner(System.in);
        int intUnidades=0;
        String codigoachequear="";
        int stockTotal=0;

        Tienda tienda= new Tienda("LosPanas", 999, 1000);        
        CargarDefaults(tienda);
        
        System.out.println("Bienvenido a la tienda Los Panas!");

        MenuPrincipal(tienda, sc, intUnidades, codigoachequear, stockTotal);
        

        sc.close();

    }

    static void CargarDefaults(Tienda tienda){
        tienda.productos.add(new Bebidas("AC001", "Botella de agua de 1,5lts", 25, 6, 5, true, false, false));
        tienda.productos.add(new Bebidas("AC002", "Lata de Coca-Cola de 354ml", 7, 3.6f, 3, true, false, false));
        tienda.productos.add(new Bebidas("AC003", "Cerveza Corona de 330ml", 15, 12, 10, true, true, true ));
        tienda.productos.add(new Envasados("AB001", "Lata de Atún", 30, 14.4f, 12, true, Material.LATA, false));
        tienda.productos.add(new Envasados("AB002", "Paquete de fideos", 60, 4.8f, 4, true, Material.PLASTICO, false));
        tienda.productos.add(new Envasados("AB003", "Paquete de arroz de 1kg", 60, 5.64f, 4.7f, true, Material.PLASTICO, false));
        tienda.productos.add(new Limpieza("AZ001", "Trapo de piso", 20, 8.4f,7, true, TiposDeAplicacion.PISOS));
        tienda.productos.add(new Limpieza("AZ002", "Jabón en polvo 1kg", 20, 6,5, true, TiposDeAplicacion.ROPA));
        tienda.productos.add(new Limpieza("AZ003", "Esponja", 20, 3.6f,3, true, TiposDeAplicacion.COCINA));

    }

    static void MenuPrincipal(Tienda tienda, Scanner sc, int intUnidades, String codigoachequear, int stockTotal){
        System.out.println("Qué desea hacer?");
        System.out.println("1-Comprar un producto para la tienda.");
        System.out.println("2-Vender un producto desde la tienda.");
        int respuesta=(Integer.parseInt(sc.nextLine()));
        switch(respuesta){
            case 1:
                AgregarProductos(tienda, sc, intUnidades, codigoachequear, stockTotal);
                break;
            default:
                VenderProductos(tienda, sc, intUnidades, codigoachequear, stockTotal);
                break;

        }
    }

    static void AgregarProductos(Tienda tienda, Scanner sc, int intUnidades, String codigoachequear, int stockTotal){
        System.out.println("Introduzca el código del producto que desea agregar a la tienda." );
            stockTotal=0;
        for (Producto producto : tienda.productos) {
            System.out.println(producto.identificador + "-" + producto.descripcion + " - ($"+producto.costo+") - Stock: "+producto.cantidadEnStock);

            stockTotal+=producto.cantidadEnStock;
        }
        System.out.println("Saldo actual: $"+tienda.saldoEnCaja+"  |  "+ "Stock actual: "+ stockTotal+"/"+tienda.maxProductosEnStock);
        codigoachequear=sc.nextLine();
        int contador=0;
        for (Producto producto : tienda.productos) {            
            if(codigoachequear.equals(producto.identificador)){
                System.out.println("Cuantas unidades del producto desea agregar?");
                intUnidades=Integer.parseInt(sc.nextLine());
                float chequeoPrecio=tienda.saldoEnCaja-(producto.costo*intUnidades);
                int chequeoStock= tienda.maxProductosEnStock-stockTotal-intUnidades;
                if(chequeoStock<0){
                    System.out.println("No hay suficiente espacio en el stock para almacenar los productos. Faltan "+ (chequeoStock-2*chequeoStock));
                    VolverAlMenu(tienda, sc, intUnidades, codigoachequear, stockTotal);
                }else{
                    if(chequeoPrecio<0){
                    System.out.println("No hay suficiente saldo para efectuar la compra. Faltan $"+ (chequeoPrecio-2*chequeoPrecio));
                    VolverAlMenu(tienda, sc, intUnidades, codigoachequear, stockTotal);
                }else{
                    producto.cantidadEnStock+=intUnidades;
                    stockTotal+=intUnidades;
                    tienda.saldoEnCaja=chequeoPrecio;
                //System.out.println("DESPUES DE SUMAR "+producto.cantidadEnStock);
                System.out.println("Saldo Actual: $"+tienda.saldoEnCaja);
                System.out.println("Stock actual: "+ stockTotal+"/"+tienda.maxProductosEnStock);
                VolverAlMenu(tienda, sc, intUnidades, codigoachequear, stockTotal);

                return;
                }
                }
                
                
            }else{
                contador++;
                if(contador==tienda.productos.size()){
                    System.out.println("El código no pertenece a un producto en el catálogo");
                    VolverAlMenu(tienda, sc, intUnidades, codigoachequear, stockTotal);
                }
            }
        }
    }

    static void VenderProductos(Tienda tienda, Scanner sc, int intUnidades, String codigoachequear, int stockTotal){
        System.out.println("Ingrese el código del producto que se vende");
        stockTotal=0;
        for (Producto producto : tienda.productos) {
            if(producto.cantidadEnStock<=0){
                producto.disponibleParaVenta=false;
            }else{
                producto.disponibleParaVenta=true;
            }
            if(producto.disponibleParaVenta){
            System.out.println(producto.identificador + "-" + producto.descripcion + " - ($"+producto.precio+") - Stock: "+producto.cantidadEnStock);
            stockTotal+=producto.cantidadEnStock;
            }else{
                System.out.println(producto.identificador+"- EL PRODUCTO '"+producto.descripcion+"' NO SE ENCUENTRA DISPONIBLE");
            }

        }
        codigoachequear=sc.nextLine();
        int contador=0;
        for (Producto producto : tienda.productos) {            
            if(codigoachequear.equals(producto.identificador)){
                System.out.println("Cuantas unidades del producto se están vendiendo? (máximo 10)");
                intUnidades=Integer.parseInt(sc.nextLine());
                if(intUnidades>0 && intUnidades<=10){
                    int chequeoStock= producto.cantidadEnStock-intUnidades;
                    if(chequeoStock<0){
                    System.out.println("No hay suficiente stock del producto solicitado. Solo se venderán "+producto.cantidadEnStock);
                    intUnidades=producto.cantidadEnStock;
                    }
                    producto.cantidadEnStock-=intUnidades;
                    stockTotal-=intUnidades;
                    tienda.saldoEnCaja+=producto.precio*intUnidades*AplicarDescuento(producto);
                    if(AplicarDescuento(producto)!=1){
                      System.out.println("Venta exitosa de "+intUnidades+" x "+ producto.descripcion+
                      " ($"+(producto.precio*intUnidades*AplicarDescuento(producto))+") (Precio sin descuento: $"+producto.precio*intUnidades+")");  
                    }else{
                        System.out.println("Venta exitosa de "+intUnidades+" x "+ producto.descripcion+" ($"+(producto.precio*intUnidades)+")");  
                    }
                    

                    System.out.println("Saldo Actual: $"+tienda.saldoEnCaja);
                    System.out.println("Stock actual: "+ stockTotal+"/"+tienda.maxProductosEnStock);
                    VolverAlMenu(tienda, sc, intUnidades, codigoachequear, stockTotal);
                    
                }else{
                    System.out.println("Cantidad inválida.");
                    MenuPrincipal(tienda, sc, intUnidades, codigoachequear, stockTotal);
                }
                
            }
            else{
            contador++;
            if(contador==tienda.productos.size()){
                System.out.println("El código no pertenece a un producto en el catálogo");
                 VolverAlMenu(tienda, sc, intUnidades, codigoachequear, stockTotal);
                }
            }
        }
    }

    static float AplicarDescuento(Producto producto){
        switch(producto.getClass().getSimpleName()){
            case "Bebidas":
                return 0.85f;
            case "Envasados":
                return 0.8f;
            case "Limpieza":
                return 0.75f;
            default: return 1;
        }
    }


    static void VolverAlMenu(Tienda tienda, Scanner sc, int intUnidades, String codigoachequear, int stockTotal){
                System.out.println(" ");
                System.out.println("---------------------------");
                System.out.println(" ");
                MenuPrincipal(tienda, sc, intUnidades, codigoachequear, stockTotal);
    }
}


//Validación de identificador, esto lo hice al principio pero lo saqué porque decidí que tenía mas sentido dejar los artículos cargados por defecto. Pero si se quisiera agregar nuevos artículos en runtime, hice esta validación para el identificador alfanumérico

/*System.out.println("Por favor seleccione una acción: ");
        System.out.println("1-Agregar nueva bebida.");
        System.out.println("2-Agregar nuevo envasado.");
        System.out.println("3-Agregar nuevo producto de limpieza.");

        int tipoSeleccionado=Integer.parseInt(sc.nextLine());

        if(tipoSeleccionado >= 1 && tipoSeleccionado <=3){
            System.out.println("Ingrese el código del producto a ser ingresado: ");
                    codigoachequear=sc.nextLine();
                    sc.close();
                    switch(tipoSeleccionado){
                        case 1: if(codigoachequear.matches("AC\\d{3}")){
                            System.out.println("Código válido");}else{             en vez de decir "Código válido", pasaría a pedir el resto de inputs y usaría el "tienda.productos.Add()" que se encuentra en la función CargarDefaults();
                                System.out.println("Código Invalido");
                            }
                            break;
                        case 2: if(codigoachequear.matches("AB\\d{3}")){
                            System.out.println("Código válido");}else{
                                System.out.println("Código Invalido");
                            }
                            break;
                        case 3: if(codigoachequear.matches("AZ\\d{3}")){
                            System.out.println("Código válido");}else{
                                System.out.println("Código Invalido");
                            }
                            break;            
        }
        }else{
            System.out.println("Numero inválido");
            return;
        } */