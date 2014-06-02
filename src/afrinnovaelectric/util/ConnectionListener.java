/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package afrinnovaelectric.util;

import javax.net.ssl.SSLSocket;

/**
 *
 * @author princeyekaso
 */
public interface ConnectionListener {

    public boolean processConnection(SSLSocket socket);
}
