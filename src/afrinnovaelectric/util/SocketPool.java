///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package afrinnovaelectric.util;
//
//import java.io.Closeable;
//import java.io.IOException;
//import java.net.Socket;
//import java.util.NoSuchElementException;
//import org.apache.commons.pool2.ObjectPool;
//import org.apache.commons.pool2.PooledObject;
//import org.apache.commons.pool2.PooledObjectFactory;
//import org.apache.commons.pool2.impl.GenericObjectPool;
//
//
///**
// *
// * @author modupealadeojebi
// */
//public class SocketPool implements Closeable {
// 
//    private ObjectPool pool; 
// 
//  public SocketPool(final String host, final int port, int max) { 
//    pool = new GenericObjectPool(new PooledObjectFactory<Socket>() {
// 
////      public void destroyObject(Object obj) throws Exception { 
////        if (obj instanceof Socket) { 
////          ((Socket) obj).close(); 
////        } 
////      } 
//// 
////      public boolean validateObject(Object obj) { 
////        if (obj instanceof Socket) { 
////          return ((Socket) obj).isConnected(); 
////        } 
////        return false; 
////      } 
// 
////      public Object makeObject() throws Exception { 
////        return new Socket(host, port); 
////      } 
//// 
////      public void activateObject(Object obj) throws Exception { 
////        // do nothing 
////      } 
//// 
////      public void passivateObject(Object obj) throws Exception { 
////        // do nothing 
////      } 
//
//        @Override
//        public PooledObject makeObject() throws Exception {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        }
//
//        @Override
//        public void destroyObject(PooledObject po) throws Exception {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        }
//
//        @Override
//        public boolean validateObject(PooledObject po) {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        }
//
//        @Override
//        public void activateObject(PooledObject po) throws Exception {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        }
//
//        @Override
//        public void passivateObject(PooledObject po) throws Exception {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        }
//    }, max); 
//  } 
// 
//  public Socket get() throws Exception, NoSuchElementException, 
//      IllegalStateException { 
//    return (Socket) pool.borrowObject(); 
//  } 
// 
//  public void put(Socket socket) throws Exception { 
//    pool.returnObject(socket); 
//  } 
// 
//  public void remove(Socket socket) throws Exception { 
//    pool.invalidateObject(socket); 
//  } 
// 
//  public void close() throws IOException { 
//    try { 
//      pool.clear(); 
//    } catch (Exception e) { 
//      throw new IOException(e.getMessage()); 
//    } 
//  } 
//    
//}
