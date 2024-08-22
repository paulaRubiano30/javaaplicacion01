/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package conectar;

import java.util.Scanner;
/**
 *
 * @author lilia
 */
public class Mainsisbib {

    /**
     * @param args the command line arguments
     */
   public static void main(String[] args) {
        // TODO code application logic here
        ConeccionBD conexion = new ConeccionBD();
        Scanner scanner = new Scanner(System.in);
        int opcion;
        
        do{
           System.out.println("\nMenu de opciones:");
           System.out.println("1. Insertar autor");
           System.out.println("2. Consultar autor");
           System.out.println("3. Actualizar autor");
           System.out.println("4. Eliminar autor");
           System.out.println("5. Salir");
           System.out.println("Elegir una opción: ");
           opcion = scanner.nextInt();
           scanner.nextLine(); //paracrear una nueva linea despues del nextInt()
           
         switch (opcion){
             case 1:
                 conexion.insertar();
                 break;
             case 2:
                 conexion.consultar();
                 break;
             case 3:
                 System.out.println("ingresar el codigo del autor a actualizar");
                 int Cod_autor =scanner.nextInt();
                 scanner.nextLine();//para consumir la nueva linea
               
                 System.out.println("Ingrese el nuevo nombre del autor");
                 String nuevoNombre = scanner.nextLine();
               
                 System.out.println("Ingrese el nuevo apellido del autor");
                 String nuevoapellido = scanner.nextLine();
                 conexion.actualizar(Cod_autor, nuevoNombre, nuevoapellido);
               break;
             case 4:
                 System.out.println("Ingrese el codigo del autor a eliminar ");
                 
                 int Cod_autorEliminar = scanner.nextInt();
                 scanner.nextLine();
                 conexion.eliminar(Cod_autorEliminar);
                 break;
             case 5:
                 System.out.println("Saliendo ....");
                 break;
             default:
                 System.out.println("Opcion no valida, ingrese otra opcion");
            }  
           
        }while (opcion != 5);
        scanner.close();
    }
    
}
