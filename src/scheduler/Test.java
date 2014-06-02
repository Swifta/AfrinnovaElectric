/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduler;

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author princeyekaso
 */
public class Test {

    public static void main(String[] args) {
     /*   Thread thread = new Thread() {
            public void run() {
                getValues();
            }
        };
        Thread thread2 = new Thread() {
            public void run() {
                getValue();
            }
        };
        Thread thread3 = new Thread() {
            public void run() {
                getValu();
            }
        };
        thread.start();
        thread2.start();
        thread3.start();*/DateUtilities du = new DateUtilities();
         System.out.println("Help me"+du.formatDateToString(new Date(), "yyyy-mm-dd hh:mm:ss Z"));
          System.out.println(new Date().toGMTString());
    }

    public static void getValues() {
        int i = 0;
        while (i < 10000) {
           
            i++;
        }
    }

    public static void getValue() {
        int i = 0;
        while (i < 10000) {
            System.out.println("Help them"+new Timestamp(new Date().getTime()));
            i++;
        }
    }

    public static void getValu() {
        int i = 0;
        while (i < 10000) {
            System.out.println("Help him"+new Timestamp(new Date().getTime()));
            i++;
        }
    }
}
