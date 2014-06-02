/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package afrinnovaelectric;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author princeyekaso
 */
public class MTNLiberiaCompressor {

    public byte[] wrap(byte[] msg) throws Exception {
        int len = msg.length;
        if (len > 65535) {
            throw new IllegalArgumentException("Exceeds 65535 bytes.");
        }
        byte firstByte = (byte) (len >>> 8);
        byte secondByte = (byte) len;
        ByteArrayOutputStream baos = new ByteArrayOutputStream(len + 2);
        baos.write(firstByte);
        baos.write(secondByte);
        baos.write(msg);
        return baos.toByteArray();
    }

    public byte[] unWrap(InputStream inputStream) throws Exception {
        int firstByte = inputStream.read();
        if (firstByte == -1) {
            throw new IOException("End of Stream while trying to read vli byte 1");
        }
        int firstByteValue = firstByte << 8;
        int secondByteValue = inputStream.read();
        if (secondByteValue == -1) {
            throw new IOException("End of Stream reading vli byte 2. ");
        }
        int len = firstByteValue + secondByteValue;
        byte[] message = new byte[len];
        int requestLen;
        int readLen;
        int currentIndex = 0;
        while (true) {
            requestLen = len - currentIndex;
            readLen = inputStream.read(message, currentIndex, requestLen);
            if (readLen == requestLen) {
                break; // Message is complete.
            }
// Either data was not yet available, or End of Stream.
            currentIndex += readLen;
            int nextByte = inputStream.read();
            if (nextByte == -1) {
                throw new IOException("End of Stream at " + currentIndex);
            }
            message[currentIndex++] = (byte) nextByte;
        }
        return message;
    }
}
