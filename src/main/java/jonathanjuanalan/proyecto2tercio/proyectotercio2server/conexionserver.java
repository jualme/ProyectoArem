/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jonathanjuanalan.proyecto2tercio.proyectotercio2server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

/**
 *
 * @author 2118270
 */
@Service
public class conexionserver implements api {        
    
    @Override
    public String getResult(String num){
        System.out.println("el numeroeroekiroskdfjoidshfslkjfsdkflj: "+num);
        URL rapi = null;
        String res = "";
        try {
            rapi = new URL("https://pure-escarpment-60633.herokuapp.com/cuadrado/"+num);
        } catch (MalformedURLException ex) {
            Logger.getLogger(conexionserver.class.getName()).log(Level.SEVERE, null, ex);
        }
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(rapi.openStream()))) {
            String inputLine = null;
            while ((inputLine = reader.readLine()) != null) {
                res += inputLine;
            }
            System.out.println(res);
        }catch (IOException x) {
            System.err.println(x);
        }
        System.out.println(res);
        return res;
    }
    
}
