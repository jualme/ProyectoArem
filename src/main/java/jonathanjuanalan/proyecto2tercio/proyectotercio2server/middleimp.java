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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author 2118270
 */
@Service
class middleimp implements middle {
    @Autowired
    private api api;
    
    public middleimp() {
    }
    
    @Override
    public String getResponse(String num) {
        return api.getResult(num);
    }
    
}
