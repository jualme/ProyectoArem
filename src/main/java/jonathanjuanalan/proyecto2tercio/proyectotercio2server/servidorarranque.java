/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jonathanjuanalan.proyecto2tercio.proyectotercio2server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author user
 */
public class servidorarranque {
    public static void main(String[] args) {
         
        ServerSocket serverSocket = null;
        Integer port = null;
        try {
            port = 8080;            
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.err.println("Could not listen on port:" + port);
            System.exit(1);
        }
        
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        middle medio = ac.getBean(middleimp.class); //The error is here :v
        
        
        ExecutorService exService = Executors.newFixedThreadPool(3);
        for (int x = 0; x < 3; x++) {
            Runnable server = new servidor(serverSocket, medio);
            exService.execute(server);
        } 
    }
}
    

