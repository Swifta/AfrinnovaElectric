/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package afrinnovaelectric.util;

import org.json.me.JSONException;
import org.json.me.JSONObject;

/**
 *
 * @author Opeyemi
 */
public class Message {

    private int messageID;
    private String message;
    private String destination;

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getMessageID() {
        return messageID;
    }

    public void setMessageID(int messageID) {
        this.messageID = messageID;
    }

    public Message fromJson(String json) throws JSONException {
        JSONObject js = new JSONObject(json);
        this.setMessage(js.getString("message"));
        this.setDestination(js.getString("destination"));
        return this;
    }

    public String toJson(Message message) throws JSONException {
        String json = null;

        JSONObject js = new JSONObject();

        js.put("message", message.getMessage());
        js.put("destination", message.getDestination());
        js.put("messageid", message.getMessageID());

        json = js.toString();

        return json;
    }
}
