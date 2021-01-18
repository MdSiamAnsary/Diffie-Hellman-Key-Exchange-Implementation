/**
 *
 * @author Md Siam Ansary
 */

package Interface;

import java.rmi.*;

public interface CommunicationInterface extends Remote {
    
    public String receive() throws RemoteException;
    public void send(String str) throws RemoteException;
}
