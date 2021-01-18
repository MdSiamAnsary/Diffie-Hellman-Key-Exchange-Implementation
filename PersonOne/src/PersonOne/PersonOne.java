/**
 *
 * @author Md Siam Ansary
 */

package PersonOne;

import Interface.CommunicationInterface;
import PersonOne.InterfaceImpl;
import java.rmi.*;
import java.io.*;
import java.util.logging.*;
import java.net.*;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.*;
import java.util.Scanner;

public class PersonOne {
    
    public static void main(String[] args) throws RemoteException, NotBoundException 
    {
       
        PersonOne personOne = new PersonOne();
        personOne.getConnection();

    }
     
    private void getConnection()
    {
        
        try{
            InterfaceImpl obj = new InterfaceImpl();
            
            Registry reg = LocateRegistry.createRegistry(6566);
            
            reg.rebind("PersonOne", obj);
            
            System.out.println("PersonOne is Ready...");
           
        }
        catch(Exception ex)
        {
            
        }
    }
    
}
