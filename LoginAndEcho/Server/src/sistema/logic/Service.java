package sistema.logic;

import banamerprotocolo.logica.Usuario;
import java.util.ArrayList;
import java.util.List;
import sistema.data.UsuarioDao;

public class Service {
    
    // Singleton implementation
    private static Service theInstance;

    public static Service instance() {
        if (theInstance == null) {
            theInstance = new Service();
        }
        return theInstance;
    }

    // Service data
    UsuarioDao usuarioDao;

    // Service methods
    public Usuario login(Usuario u) throws Exception {
        return usuarioDao.login(u);
    }

    public List<Usuario> findAll() {
        return usuarioDao.findAll();
    }

    public String echo(Usuario u, String parameter) {
        return parameter + " " + u.getId();
    }

    public Service() {
        try {
            usuarioDao = new UsuarioDao();

        } catch (Exception e) {

        }
    }
    
}
