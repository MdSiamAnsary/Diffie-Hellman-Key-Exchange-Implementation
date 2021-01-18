/**
 *
 * @author Md Siam Ansary
 */

package PersonOne;

import java.rmi.*;
import java.io.*;
import java.util.*;
import java.net.*;
import java.rmi.server.UnicastRemoteObject;
import Interface.CommunicationInterface;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InterfaceImpl extends UnicastRemoteObject implements CommunicationInterface
{
    String str = "";
    
    private double privateKeyPersonOne = 0;
    private double publicKeyPersonOne = 0;
    private double primeValue = 0;
    private double primitiveRoot = 0;
    private double sharedKey = 0;
    
    public InterfaceImpl() throws RemoteException
    {
        super();
    }
    
    public void send(String str1) throws RemoteException
    {
        str = str1;
        
        try 
        {
            ppKeysValueCalculation();
        } 
        catch (FileNotFoundException ex) 
        {
            Logger.getLogger(InterfaceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Public key from PersonTwo received");
        double publicKeyPersonTwo = Double.valueOf(str1);
        System.out.println("Public key of PersonTwo : " + publicKeyPersonTwo);
        
        String str2 = String.valueOf(publicKeyPersonOne);
        str = str2;
        System.out.println("Public key of PersonOne sent");
        
        sharedKey = ((Math.pow(publicKeyPersonTwo, privateKeyPersonOne)) % primeValue);
        System.out.println("Shared key calculated\n");
                
        System.out.println("\nShared key is : "+ sharedKey);
       
    }
    
    public String receive() throws RemoteException
    {
        return str;   
    }
    
    private void ppKeysValueCalculation() throws FileNotFoundException
    {
        String filepath = "../textfile.txt";
        File myObj = new File(filepath);
        Scanner myReader = new Scanner(myObj);
        
        while (myReader.hasNextLine()) 
        {
            String data = myReader.nextLine();
            
            if(primeValue == 0.0)
            {
                primeValue = Double.valueOf(data);
            }
            else
            {
                primitiveRoot = Double.valueOf(data);
            }          
        }
        
        myReader.close();
        
        System.out.println("Prime value : " + primeValue + " Primitive root : " + primitiveRoot);
        
        privateKeyPersonOne = (int) ((Math.random() * ((primitiveRoot-1) - 1)) + 1);
        System.out.println("Randomly selected private key for PersonOne: " + privateKeyPersonOne);
                
        publicKeyPersonOne = ((Math.pow(primitiveRoot, privateKeyPersonOne)) % primeValue);
        System.out.println("Public key for PersonOne: " + publicKeyPersonOne);
        
    }
    
}
