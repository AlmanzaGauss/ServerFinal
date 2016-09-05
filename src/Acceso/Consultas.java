
/*
Esta clase es la encargada de realizar las consultas a la base de datos y retornar los datos correspondientes
 */
package Acceso;

import Acceso.Conexion;//Importamos la clase Conexion para pode realizar consultas a la base en caso de que el
//estado de la conexion sea true
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author aalmanza
 */


public class Consultas{
    Conexion cx= new Conexion();//Se hace una instancia de la clase Conexion.
    Connection cn= cx.Conectar();//Se establece la conexion con la DB
    private ResultSet resultado;//Variable donde se guardan los datos obtenidos de la consulta
    
    /*Constructor*/
    public Consultas()
    {
    //Conectar();
    }
    
    /*Funcion que se encraga de recibir la consulta a realizar 
    y retornar los datos correspondientes*/
    public ResultSet FillRows(String query)
    {
        try {
            //getStmt();
            //resultado=stmt.executeQuery("SELECT * FROM Modules");
            Statement st=cn.createStatement();
            //resultado=st.executeQuery("SELECT * FROM Modules");
            resultado=st.executeQuery(query);
            return resultado;
        } catch (Exception ex) {
            System.out.println("Aqui error");
           System.err.println("SQLExeption"+ex.getMessage());
                   return null;
        }
        
    }
    
      public int UpdateRows(String query)
    {
        try {
            //getStmt();
            //resultado=stmt.executeQuery("SELECT * FROM Modules");
            Statement st=cn.createStatement();
            //resultado=st.executeQuery("SELECT * FROM Modules");
            //resultado=st.executeQuery(query);
             int updateCount = st.executeUpdate(query);
            return updateCount;
        } catch (Exception ex) {
            System.out.println("Aqui error");
           System.err.println("SQLExeption"+ex.getMessage());
                   return 0;
        }
        
    }
    
}
