package afrinnovaelectric.util;

import afrinnovaelectric.Constants;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import javax.net.ssl.SSLSocket;
import org.apache.log4j.Logger;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author princeyekaso
 */
public class ResponseQueue {

    private static ResponseQueue responseQueue = null;
    private ArrayBlockingQueue<ConnectionEntry> queue = new ArrayBlockingQueue<ConnectionEntry>(500000);
    private Logger logger = Logger.getLogger(ConnectionQueue.class);
    private boolean startThread = false;
    private int threadCount = 0;
    private Constants constant = new Constants();

    public ArrayBlockingQueue<ConnectionEntry> getQueue() {
        return queue;
    }

    public void addToQueue(ConnectionEntry entry) {
        this.queue.add(entry);
    }

    public void removeFromQueue(ConnectionEntry entry) {
        this.queue.remove(entry);
    }

    public ConnectionEntry pullFromQueue() throws InterruptedException {
        return this.queue.take();
    }

    private ResponseQueue() {
    }

    public static synchronized ResponseQueue getInstance() {

        if (null == responseQueue) {
            responseQueue = new ResponseQueue();
        }
        return responseQueue;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {

        throw new CloneNotSupportedException();

    }

    public String retrieveResponse(ConnectionEntry connectionEntry) {

        String response = "";
        // queue.
        logger.info("=======================THREAD==================" + threadCount);
        if (!startThread) {
            logger.info("Thread starting for the first time....");
            ++threadCount;
            if (threadCount >= 10) {
                startThread = true;
            }
            ResponseQueue.ConnectionTreeUpdater conUpdater = new ResponseQueue.ConnectionTreeUpdater();
            conUpdater.start();
        } else {
            logger.info("Thread already started......");
        }
        return response;

    }

    class ConnectionTreeUpdater extends Thread {

        Socket socket = null;

        @Override
        public void run() {
            while (queue.size() > 0) {
                logger.info("Running thread......" + queue.size());
                socket = ElectricityEngineManager.getInstance().getConnectionEngine(constant.IPAY_CLIENT).getConnection();
                if (socket != null) {
                    logger.info("SSLSocket is not null....");
                    try {
                        pullFromQueue().getAfrinnovaElectric().finalizeResponse(socket);
                    } catch (InterruptedException ex) {
                        logger.debug(ex.getMessage());
                    }

                } else {
                    logger.info("SSLSocket is null...");
                }
            }
            --threadCount;
            startThread = false;
        }
    }
}
