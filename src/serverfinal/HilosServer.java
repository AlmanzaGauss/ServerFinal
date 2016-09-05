/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverfinal;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import Acceso.Consultas;
import java.sql.SQLException;



/**
 *
 * @author falag
 */
public class HilosServer extends Thread{
    DatagramPacket peticion;
    DatagramSocket miSocket;
    String macaddr;
    String pruebilla;
    Consultas consu= new Consultas();
    public HilosServer()
    {
    
    }
    
    public HilosServer(String Mac,DatagramSocket socket,DatagramPacket paquete)
    {
        this.peticion=paquete;
        this.miSocket=socket;
        this.macaddr=Mac;
    }
    
    public void SendMessage(String mensaje,InetAddress addr, int port)
    {
         //String resp= new Date().toString();
         //String resp=mensaje.getBytes();
         
              DatagramPacket paqueteEnvio= new DatagramPacket(mensaje.getBytes(),mensaje.length(),addr,port);
            System.out.println("Enviando"+new String(paqueteEnvio.getData()));
        try {
            miSocket.send(paqueteEnvio);
        } catch (IOException ex) {
            Logger.getLogger(HilosServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void run()
    {
        System.out.println("MAC Adrr:"+macaddr);
        System.out.println("ip:"+peticion.getAddress());
         
         System.out.println("Canonical"+peticion.getAddress().getCanonicalHostName());
         System.out.println("SocketAddress"+peticion.getSocketAddress());
         System.out.println("Algo"+peticion.getAddress().getHostAddress());
         System.out.println("Mas"+peticion.getAddress().getHostName());
         
         System.out.println("Puerto:"+peticion.getPort());
         System.out.println("Mensaje:"+new String(peticion.getData(),0,peticion.getLength()));
              //InetAddress ip=peticion.getAddress();
              
              
              /*Aqui manadamos al Cliente*/
              
             // String resp= new Date().toString();
            InetAddress addr= peticion.getAddress();
            int port=peticion.getPort();
            
            while(true)
            {
                //Checar si hay algun mensaje para esa caja Opciones
                String ip_r=peticion.getAddress().getHostAddress();
                int port_r=peticion.getPort();
                
                 System.out.println("Algo"+peticion.getAddress().getHostAddress());
                 System.out.println("Puerto:"+peticion.getPort());
                 System.out.println("MAC Adrr:"+macaddr);
                // System.out.println("Mensaje:"+new String(peticion.getData(),0,peticion.getLength()));
          ResultSet check_db;
          ResultSet index;
          
          //Primero obtenemos 
         String check_mac="Select MessagingSTBReboot_ID,MessagingSTBReboot_RequestMessage from MessagingSTBReboot where MessagingSTBReboot_MacAdd='"+macaddr+"' and MessagingSTBReboot_ResponseMessage=0";
         String countmac="SELECT COUNT(*) from MessagingSTBReboot where MessagingSTBReboot_MacAdd='"+macaddr+"' and MessagingSTBReboot_ResponseMessage=0";
         
         //String check_mensajes="Select Mensaje from Pruebas_Envio_Mensajes where Ip_Publica='"+ip_r+"' and Puerto="+port_r+" and bandera=0";
         //String count="SELECT COUNT(*) from Pruebas_Envio_Mensajes where Ip_Publica='"+ip_r+"' and Puerto="+port_r+" and bandera=0";
         int elements=0;
         index=consu.FillRows(countmac);
            try {
                while(index.next())
                {
                    elements=index.getInt(1);
                    
                }  } catch (SQLException ex) {
                Logger.getLogger(HilosServer.class.getName()).log(Level.SEVERE, null, ex);
            }
          String recopilacion="";
            if(elements!=0)
            {
         check_db=consu.FillRows(check_mac);
        
            try {
                while(check_db.next())
                {
                    String mensajito=check_db.getString("MessagingSTBReboot_RequestMessage");
                    String id_mensaje=check_db.getString("MessagingSTBReboot_ID");
                    
                    //recopilacion+=check_db.getString("MessagingSTBReboot_RequestMessage");
                    recopilacion+=id_mensaje+","+mensajito+"|";
                    System.out.println("Rec:"+recopilacion);

                }  } catch (SQLException ex) {
                Logger.getLogger(HilosServer.class.getName()).log(Level.SEVERE, null, ex);
            }
            } //fin del if de !=0
                 else
            {
                recopilacion="";
                recopilacion="1";
            }
                SendMessage(recopilacion,addr, port);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(HilosServer.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
            
             //Timer timer = new Timer();
           // timer.schedule(message.run(), 0, 5000);
            
           /* DatagramPacket paqueteEnvio= new DatagramPacket(resp.getBytes(),resp.length(),addr,port);
            System.out.println("Enviando"+new String(paqueteEnvio.getData()));
        try {
            miSocket.send(paqueteEnvio);
        } catch (IOException ex) {
            Logger.getLogger(HilosServer.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }
    
}
