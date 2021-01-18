/**
 *
 * @author Md Siam Ansary
 */
package PersonTwo;

import Interface.CommunicationInterface;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.io.*;
import java.util.*;

public class PersonTwo {
    
    private double privateKeyPersonTwo = 0;
    private double publicKeyPersonTwo = 0;
    private double primeValue = 0;
    private double primitiveRoot = 0;
    private double sharedKey = 0;
    
    public static void main(String[] args) 
    {
        PersonTwo personTwo = new PersonTwo();
        personTwo.connectToPersonOne();
        
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
        
        privateKeyPersonTwo = (int) ((Math.random() * ((primitiveRoot-1) - 1)) + 1);
        System.out.println("Randomly selected private key for PersonTwo: " + privateKeyPersonTwo);
                
        publicKeyPersonTwo = ((Math.pow(primitiveRoot, privateKeyPersonTwo)) % primeValue);
        System.out.println("Public key for PersonTwo: " + publicKeyPersonTwo);
        
    }
    
    private void connectToPersonOne ()
    {
        try{
            Registry reg = LocateRegistry.getRegistry("127.0.0.1", 6566);
            CommunicationInterface rmi = (CommunicationInterface) reg.lookup("PersonOne");
            System.out.println("PersonTwo Connected...");
            
            while(true)
            {
                ppKeysValueCalculation();
                
                Scanner sc = new Scanner(System.in);
     
                rmi.send(String.valueOf(publicKeyPersonTwo));
                System.out.println("Public key sent from PersonTwo");
                
                double publicKeyPersonOne = Double.valueOf(rmi.receive()) ;
                System.out.println("Public key of PersonOne received");
                System.out.println("Public key of PersonOne : " + publicKeyPersonOne);
                
                sharedKey = ((Math.pow(publicKeyPersonOne, privateKeyPersonTwo)) % primeValue);
                System.out.println("Shared key calculated\n");
                
                System.out.println("\nShared key is : "+ sharedKey);
                break;
               
            }
           
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
        
    }
    
}
