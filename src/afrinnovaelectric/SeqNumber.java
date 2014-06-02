/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package afrinnovaelectric;

import java.text.DecimalFormat;

/**
 *
 * @author princeyekaso
 */
public class SeqNumber {

    private int seqNum = 0;
    DecimalFormat decimalFormat = new DecimalFormat("00000");
    private static SeqNumber seqNumber = null;

    private SeqNumber() {
        seqNum = 0;
    }

    public static synchronized SeqNumber getInstance() {
        if (null == seqNumber) {
            seqNumber = new SeqNumber();
        }
        return seqNumber;
    }

    public synchronized String nextSequence() {
        seqNum++;
        if (seqNum == 99999) {
            seqNum = 0;
        }
        return decimalFormat.format(seqNum);
    }
}
