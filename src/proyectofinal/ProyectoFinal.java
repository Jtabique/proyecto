
package proyectofinal;

import java.util.Scanner;


public class ProyectoFinal {
    //Variables globales
    public static int ventas;
    public static double proVendidos;
    public static double totVendido;
    public static int ventEfect;
    public static int ventTarj;
    public static int ventTrans;
    public static double mayor;
    public static double menor;

    
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        // Menu Principal
        //Variables
        int op = 0;
        int salir = 0;
        int confir;
        
        do{
        //Ingreso de datos 
        System.out.println("ingrese la opcion: ");
        System.out.println("1 para Registrar una venta");
        System.out.println("2 para Consultar caja");
        System.out.println("3 para Mostrar estadisticas");
        System.out.println("4 para Salir");
        
        op = teclado.nextInt();
        
        switch(op){
            case 1: //Registrar ventas 
                   registroVenta();
                   salir = 0;
                   break;
            case 2: //Consultar caja
                consultarCaja();
                salir = 0;
                break;
            case 3: //Mostrar estadisticas
                estadisticas();
                salir = 0;
                break;
            case 4: //Salir 
                System.out.println("¿Quiere salir del menu?");
                System.out.println("Ingrese: 1 para si y \n" +
                                   "cualquier otro numero para no");
            confir = teclado.nextInt();
                
                if (confir == 1) {
                    salir = 1;
                }else{
                    salir = 0;
                }
                
                break;
            default:
                System.out.println("Opcion Invalida");
                salir = 0;
                break;

                
                
        }
        }while(salir != 1);
        
        
    }
    // Parte de registro ventas 
    public static void registroVenta(){
        Scanner teclado = new Scanner(System.in);
        System.out.println("===== REGISTRAR VENTA =====");
        // Variables 
        double cantPro;
        double codigo;
        String nombre;
        double precio;
        double cantidad;
        int volver = 0;

        int i = 0;
        double subProducto; 
        double subTotal = 0;
        double descuento;
        double montoDes;
        double iva;
        double total;
        double pago;
        double cambio = 0;
        double falta;
        double efectivo =0;
        double pagTotal;
        int opPago = 0;
        String tipo = "";
        
        int confirmaTransf = 0;
        int p;
        
        
        // Primer ciclo
        do{
            System.out.println("¿Cuantos productos tendra la compra?");
            cantPro = teclado.nextDouble();
            if (cantPro <= 0) {
                System.out.println("La cantidad debe ser mayor a cero");  
            }
        }while(cantPro <= 0);
        
        for (i = 0; i < cantPro; i++){
            do{
            System.out.println("Ingrese el codigo del producto.");
            codigo = teclado.nextDouble();
            
            System.out.println("Ingrese el nombre del producto.");
            nombre = teclado.next();
            
            System.out.println("Ingrese el precio del producto.");
            precio = teclado.nextDouble();
            
            System.out.println("Ingrese la cantidad del producto.");
            cantidad = teclado.nextDouble();
            
            if (precio > 0) {
                if (cantidad > 0) {
                    subProducto = precio * cantidad;
                    subTotal = subTotal + subProducto;
                    
                }else{
                    System.out.println("La cantidad del producto debe ser mayor a cero");
                }
                
            }else{
                System.out.println("El precio del producto debe ser mayor a cero");
            }
            
             }while(precio <=0 || cantidad <= 0);
            
   
        }
        
        //Descuentos
        if (subTotal < 200) {
            descuento = 0;
            
        }else if(subTotal >= 200 && subTotal <= 499.99){
            descuento = subTotal * 0.05;
        
        }else if(subTotal >= 500 && subTotal <= 999.99){
            descuento = subTotal * 0.08;
        }else{
            descuento = subTotal * 0.10;
        }//aqui puedes añadir otro descuento
        
        montoDes = subTotal - descuento;
        iva =montoDes * 0.12;
        total = montoDes + iva;
        
        //Forma de pago
        
        do {
            System.out.println("Ingrese su forma de pago:\n" +
                               "ingrese \n"
                             + "1 para Efectivo,\n" +
                               "2 para Tarjeta y\n" +
                               "3 para Transferencia.");
            opPago = teclado.nextInt();
            switch (opPago){
                case 1: //Efectivo
                    System.out.println("---- El total a pagar es de: Q" + total + " en efectivo ----");
                    pagTotal = total;
                    System.out.println("Ingrese el pago: ");
                    
                    do {
                        pago = teclado.nextDouble();
                        if (pago > 0) {
                            if (pago >= pagTotal) {
                                cambio = pago - pagTotal;
                                tipo = "Efectivo";
                                efectivo = efectivo + pago;
                                ventEfect = ventEfect + 1;
                                
                            }else{
                                efectivo = efectivo + pago;
                                falta = pagTotal - pago;
                                pagTotal = falta;
                                System.out.println("Aun necesita pagar: Q" + falta);
                                
                            }
                            
                        }else{
                            System.out.println("El pago debe ser mayor a cero");
                        
                        } 
                    } while (efectivo < total);
                    break;
                    
                case 2://Tarjeta
                    System.out.println("---- El total a pagar es de: Q" + total + " a traves de su tarjeta ----");
                    System.out.println("Pase su tarjeta");

                    // Efecto visual: imprime 8 puntos, uno cada medio segundo,
                    // para simular que la tarjeta se esta procesando
                    for (p = 0; p < 8; p++) {
                        System.out.print(".");
                        System.out.flush(); // muestra el punto de inmediato, sin esperar al salto de linea

                        // Thread.sleep pausa el programa 500 milisegundos (medio segundo).
                        // Java obliga a usar try/catch porque la pausa podria interrumpirse.
                        try {
                            Thread.sleep(500); //aqui se cambian los segundo
                        } catch (InterruptedException e) {
                        // No se hace nada si se interrumpe, el programa continua normal
                        }
                    
                    }

                    System.out.println(); // baja a la siguiente linea despues de los puntos
                    System.out.println("Cobro realizado");

                    tipo = "Tarjeta";
                    ventTarj = ventTarj + 1;
                    
                    break;
                case 3://Transferencia
                    System.out.println("---- El total a transferir es de: Q" + total + " ----");
                    System.out.println("Banco: Banco Industrial");
                    System.out.println("No de Cuenta: 12134123 \n"
                                     + "Tipo: Monetaria");
                    // Referencia simulada (como la boleta de un banco): "TR-" + numero de transferencia
                    System.out.println("Referencia: TR-" + (ventTrans + 1));

                    // Espera hasta que el cliente confirme que ya hizo la transferencia
                    
                    while (confirmaTransf != 1) {
                        System.out.println("Ingrese 1 cuando haya realizado la transferencia");
                        confirmaTransf = teclado.nextInt();
                    }

                    // Efecto visual de verificacion: 6 puntos, uno cada medio segundo
                    System.out.print("Verificando transferencia");
                    for (p = 0; p < 6; p++) {
                        System.out.print(".");
                        System.out.flush();
                        try { // intenta pausar medio segundo
                            Thread.sleep(500); // Aqui se cambia el tiempo
                        } catch (InterruptedException e) {
                        // No se hace nada, el programa continua normal
                        }
                    }
                    System.out.println();
                    System.out.println("Transferencia recibida. Cobro realizado");

                    tipo = "Transferencia";
                    ventTrans = ventTrans + 1;
                    
                    break;
                default:
                System.out.println("Opcion invalida");
                break;
                
            
            }
            
        } while (opPago < 1 || opPago > 3);
        
        if (opPago == 1) {
            System.out.println("-----Resumen de venta-----\n"
                             + "Cantidad de productos vendidos: " + cantPro + "\n"
                             + "Sub total: Q" + subTotal + "\n"
                             + "Descuento aplicado: Q" + descuento + "\n"
                             + "Iva aplicado: Q" + iva + "\n"
                             + "Total: Q" + total + "\n"
                             + "Forma de pago: " + tipo + "\n"
                             + "Efectivo recibido: Q" + efectivo + "\n"
                             + "Cambio: Q" + cambio + "\n"
                             + "-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-");
            
        }else {
            System.out.println("-----Resumen de venta-----\n"
                             + "Cantidad de productos vendidos: " + cantPro + "\n"
                             + "Sub total: Q" + subTotal + "\n"
                             + "Descuento aplicado: Q" + descuento + "\n"
                             + "Iva aplicado: Q" + iva + "\n"
                             + "Total: Q" + total + "\n"
                             + "Forma de pago: " + tipo + "\n"
                             + "-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-");
        
        }
        
        //Para Consultar caja
        ventas = ventas + 1;
        proVendidos = proVendidos + cantPro;
        totVendido = totVendido + total;
        
        //Para estadisticas
        if (ventas == 1) {
            mayor = total;
            menor = total;
            
        }else if(total > mayor){
            mayor = total;
        }else if(total < menor){
            menor = total;
        }
        
        
        while (volver != 1) {
        System.out.println("Ingrese 1 para regresar al menu");
        volver = teclado.nextInt();
        }

        
        
    }
    
    public static void consultarCaja(){
        int volver = 0;
        Scanner teclado = new Scanner(System.in);
        System.out.println("===== CONSULTA DE CAJA ===== \n"
                + "============================ \n"
                + "Ventas realizadas: " + ventas + "\n"
                + "Productos vendidos: " + proVendidos + "\n"
                + "Total vendido: Q" + totVendido + "\n"
                + "Cantidad de ventas en efectivo realizadas: " + ventEfect + "\n"
                + "Cantidad de ventas con tarjeta realizadas: " + ventTarj + "\n"
                + "Cantidad de ventas por transferencias: " + ventTrans + "\n"
                + "-_-_-_-_-_-_-_-_-_-_-_-_-_-_-");
         while (volver != 1) {
        System.out.println("Ingrese 1 para regresar al menu");
        volver = teclado.nextInt();
        }

    
    }
    
    public static void estadisticas(){
        Scanner teclado = new Scanner(System.in);
        
        double promedio;
        int volver = 0;
        
        System.out.println("===== ESTADISTICAS =====");
        if (ventas == 0) {
            System.out.println("No existen ventas registradas.\n"
                         + "========================");
              
        }else{
            promedio = totVendido / ventas;
            System.out.println("Numero total de ventas: " + ventas + "\n"
                             + "Cantidad total de productos vendidos: " + proVendidos + "\n"
                             + "Total de dinero vendido: Q" + totVendido + "\n"
                             + "Promedio de dinero por venta: Q" + promedio + "\n"
                             + "--- FORMAS DE PAGO --- \n"
                             + "Cantidad de ventas en efectivo realizadas: " + ventEfect + "\n"
                             + "Cantidad de ventas con tarjeta realizadas: " + ventTarj + "\n"
                             + "Cantidad de ventas por transferencias: " + ventTrans + "\n"
                             + "---------------------------- \n"
                             + "Venta de mayor valor: Q" + mayor + "\n"
                             + "Venta de menor valor: Q" + menor + "\n"
                             + "-_-_-_-_-_-_-_-_-_-_-_-_-_-_-");
        
        }
         while (volver != 1) {
        System.out.println("Ingrese 1 para regresar al menu");
        volver = teclado.nextInt();
        }

    
    }
    
    
 
    
}
