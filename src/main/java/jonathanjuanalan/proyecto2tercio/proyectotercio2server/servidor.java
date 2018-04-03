/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jonathanjuanalan.proyecto2tercio.proyectotercio2server;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
/**
 *
 * @author user
 */
public class servidor implements Runnable {
    private ServerSocket socket;
    private middle builder;
    public servidor (ServerSocket server, middle builder){
        socket=server;
        this.builder=builder;
    }
    @Override
    public void run() {
        try {
            while (true) {
                comienzoDeConection();
            }
        } catch (IOException ex) {
            Logger.getLogger(servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void comienzoDeConection()throws IOException {
        Socket clientSocket = null;
        try {           
            clientSocket = socket.accept();
        } catch (IOException e) {
            System.err.println("Accept failed.");
            e.printStackTrace();
            System.exit(1);
        }
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(  new InputStreamReader( clientSocket.getInputStream()));
        String inputLine, outputLine;
        
        while ((inputLine = in.readLine()) != null) {
            System.out.println("Received: " + inputLine);
            
            if (inputLine.startsWith("GET") || inputLine.startsWith("POST") ) {
                 String query = inputLine.split(" ")[1];
                if(query.equals("/") || query.equals("/index.html")){
                   Resource uri = new ClassPathXmlApplicationContext("applicationContext.xml").getResource("/index.html");
                    String output = "";
                    try {
                        InputStream is = uri.getInputStream();
                        BufferedReader br = new BufferedReader(new InputStreamReader(is));
                        
                        String line;
                        while ((line = br.readLine()) != null) {
                           output+=line;
                        } 
                        br.close();
                    } catch (IOException ex) {
                        Logger.getLogger(servidor.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    outputLine = "HTTP/1.1 200 OK\r\n"
                    + "Content-Type: text/html\r\n\r\n" + output;
                        out.println(outputLine);
                }

                else if(query.contains("request")){
                    outputLine = "HTTP/1.1 200 OK\r\n"
                    + "Content-Type: text/html\r\n\r\n" + builder.getResponse(query.split("/")[2]);
                        out.println(outputLine);
                }
            }
            if (!in.ready()) {
                break;
            }
            if (inputLine.equals("")) break;
        }
                
        out.close();
        in.close();
        clientSocket.close();
    }     
}
