/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.lab1;


import com.mycompany.model.MainProgramLoop;
import java.io.IOException;


/**Class with main method
 *@version 1.0
 * @author AgataPietrzycka
 */
public class Main {
    public static void main(String args[]){
        
      MainProgramLoop  loop = new MainProgramLoop();
      try{
          loop.mainProgmaLoop();
      }catch(IOException e){
          System.out.print("Msg " + e.getMessage());
      }
    }
}
