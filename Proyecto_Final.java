import java.util.Scanner;

public class Proyecto_Final {
    
    public static String[][] registro = new String[10][10];
    public static Scanner scanner = new Scanner(System.in);

    public static void formularioDeRegistro(){
        int contador = 0;
        while (contador < registro.length && registro[contador][0] != null) {
            contador++;
        }
        if (contador < registro.length) {
            System.out.println("FORMULARIO DE REGISTRO");
            System.out.println("Para realizar su registro porfavor completar la siguiente información: ");
            scanner.nextLine();
            System.out.println("Tipo de identificación: ");
            registro[contador][0] = scanner.nextLine();
            System.out.println("Número de identificación: ");
            registro[contador][1] = scanner.nextLine();
            System.out.println("Nombres: ");
            registro[contador][2] = scanner.nextLine();
            System.out.println("Apellidos: ");
            registro[contador][3] = scanner.nextLine();
            System.out.println("Correo electrónico: ");
            registro[contador][4] = scanner.nextLine();
            System.out.println("Dirección de residencia: ");
            registro[contador][5] = scanner.nextLine();
            System.out.println("Ciudad de residencia: ");
            registro[contador][6] = scanner.nextLine();
            System.out.println("Teléfono de contacto: ");
            registro[contador][7] = scanner.nextLine();
            System.out.println("Contraseña: ");
            registro[contador][8] = scanner.nextLine();
            String contrasena = "";
            boolean confirmar = true;
            while (confirmar) {
                System.out.println("confirme la contraseña: "); 
                contrasena = scanner.nextLine();
                if(contrasena.equals(registro[contador][8])){
                    confirmar=false;
                }
            }
            registro[contador][9] = contrasena;
        } else {
            System.out.println("El registro está lleno. No se pueden agregar más registros.");
        }
    }

    public static boolean IniciarSesion(){
        scanner.nextLine();
        System.out.println("Ingrese su correo: ");
        String correo = scanner.nextLine();
        int contador = 0;
        while(contador < registro.length){
            if(correo.equals(registro[contador][4])){
                int verificar_contrasena = 0;
                while(verificar_contrasena < 3){
                    System.out.println("Ingrese su contraseña: ");
                    String contrasena = scanner.nextLine();
                    if(contrasena.equals(registro[contador][8])){
                        return true;

                    }else{
                        System.out.println("contraseña incorrecta");
                        verificar_contrasena = verificar_contrasena +1;
                    }

                }
                contador = contador + 1;
                
            }
            else{
                contador = contador + 1;
            }
        }
        return false;
        

    }

    public static void MostrarMenu() {
        boolean MostrarMenu = true;

        while(MostrarMenu){
            System.out.println("\n\nBienvenido a My Hotel...\n"+ "Mas que un lugar para descansar.");
            System.out.println("-----------------------------------------------------------\n\n");
            System.out.println("Ingrese la opción deseada");
            System.out.println("1. Registrarse como cliente");
            System.out.println("2. Iniciar Sesión");
            System.out.println("3. Salir\n\n");

            byte option = scanner.nextByte();

            switch(option){
                case 1:
                    formularioDeRegistro();
                    break;
                case 2:
                    System.out.println("Iniciar Sesión");
                    if(IniciarSesion()){
                        System.out.println("Se inicio sesion correctamente");
                    }else{
                        System.out.println("no se pudo iniciar sesion verifique los datos");
                    }
                    break;
                case 3:
                    MostrarMenu = false;
                    break;
                default:
                    System.out.println("Opción no válida, ingrese otra opcion");
                    break;
            }
        }
    }

    public static void main(String[] args) {
        MostrarMenu();
        scanner.close();
    }
    
}
