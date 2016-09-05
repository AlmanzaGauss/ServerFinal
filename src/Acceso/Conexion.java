/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Acceso;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author falag
 */
public class Conexion {
    protected Connection con;//Se declara una variable de tipo Connection para realizar la conexion a DB
    protected Statement stmt;//Variable Statement para verificar el estado de la Conexion.
    private String serverName="173.11.142.60";//Variable donde se guarda el dns del servidor o ip
    //private String serverName="173.11.142.59";
    //private String serverName="localhost";
    private String portNumber="3306";//Puerto donde se encuentra la base de datos(Default de MySQL)
    private String dataBaseName="TMS";//Nombre de la base de datos a usar
    //private String url="jdbc:mysql://173.11.142.59:3306/"+dataBaseName;//URL de la base datos haciendo uso de la libreria
    private String url="jdbc:mysql://173.11.142.60:3306/"+dataBaseName;//URL de la base datos haciendo uso de la libreria
    //private String url="jdbc:mysql://localhost/"+dataBaseName;//URL de la base datos haciendo uso de la libreria
    //private String url="jdbc:mysql://localhost:3306/TMS";//URL de la base datos haciendo uso de la libreria
    
    
    //private String userName="mitvdev";//Nombre de usuario de la base de datos
    private String userName="admin";//Nombre de usuario de la base de datos
    
    private String password="miradev8";//Contraseña para acceder a la base de datos
    
    private String errString;//Variable para cachar el error en caso de que lo haya.
    
    public Conexion()
{
}
    /*Funcion que se encragar de obtener la url y retornarla como un String*/
    private String getConnectionUrl()
    {
    return url;
    }
    
    public Connection Conectar()
    {
        con=null;//Variable de tipo Connecion inicializada
        try {
            //Class.forName("com.mysql.jdbc.Driver");//Se indica que libreria se usara.
            con=DriverManager.getConnection(getConnectionUrl(),userName,password);//Se guarda en con el estatus de la conexion
            System.out.println("Conectado");
        } catch (Exception e) {
            errString="Error Mientras se conecta a la base de datos";
            System.out.println(e);
            System.out.println(errString);
            return null;
        } 
    return con;
    }
        
    public void Desconectar()
    {
        try {
            stmt.close();
            con.close();
        } catch (SQLException e) {
           errString="Error Mientras se cerraba la Conexion a la base";
        }
    }
            
    public Statement getStmt()
    {
        return this.stmt;
    }
    
}
