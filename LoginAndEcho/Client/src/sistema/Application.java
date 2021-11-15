package sistema;

import banamerprotocolo.comunicacion.Protocolo;
import banamerprotocolo.logica.Usuario;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Application {

    public static void main(String[] args) throws IOException {
        ObjectInputStream in;
        ObjectOutputStream out;
        Socket s;
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        Usuario usuario;
        Usuario respuesta = null;
        String mensaje = "";
        String eco = "";

       // try {
            s = new Socket(Protocolo.SERVER, Protocolo.PORT);
            out = new ObjectOutputStream(s.getOutputStream());
            in = new ObjectInputStream(s.getInputStream());

            //LOGIN
            System.out.print("USUARIO: ");
            usuario = new Usuario(Integer.valueOf(input.readLine()), input.readLine());
            out.writeObject(usuario);
            out.flush();
            try {
                respuesta = (Usuario) in.readObject(); // esta esperando la respuesta del servidor
            } catch (ClassNotFoundException ex) {
            }
            if (respuesta.getNombre().equals("ERROR")) {
                System.out.println("Usuario incorrecto");
                System.exit(0);
            } else {
                // CICLO ECO
                while (true) {
                    System.out.print("MENSAJE: ");
                    mensaje = input.readLine();
                    if (mensaje.isEmpty()) { // aqui sierra el socket
                        s.close();
                        System.exit(0);
                    }
                    out.writeObject(mensaje);
                    out.flush();
                    try {
                        eco = (String) in.readObject();
                    } catch (ClassNotFoundException ex) {
                    }
                    System.out.println("RESPUESTA: " + eco);
                }
            }
     /*   } catch (IOException ex) {
            System.out.println("Error de comunicacion");
            System.out.println(ex.getMessage());
            System.exit(0);
        }*/

    }

}
