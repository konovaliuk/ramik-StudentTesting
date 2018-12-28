/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exeptions;

/**
 *
 * @author Denis
 */
public class ExeptionDataBase extends RuntimeException  {

    public ExeptionDataBase() {
    }
    
    public ExeptionDataBase(String message){
        super(message);
    }
}
