package ua.goptsii.packet;

import lombok.Data;

import java.nio.ByteBuffer;
import java.util.Arrays;

@Data
public class Message {

    private Integer cType;
    private Integer bUserId;

    /* easy to save message in byte array for encoding and etc. */
    private byte [] message;

    public static final int MAX_MESSAGE_SIZE = 255;
    public static final int BYTES_WITHOUT_MESSAGE = Integer.BYTES * 2;
    public static final int BYTES_MAX_SIZE = BYTES_WITHOUT_MESSAGE + MAX_MESSAGE_SIZE;

    public Message(int cType, int bUserId, byte[] message) {
        this.cType = cType;
        this.bUserId = bUserId;
        this.message = message;
    }

    public Message(int cType, int bUserId, String message) {
        this.cType = cType;
        this.bUserId = bUserId;
        this.message = message.getBytes();
    }

    public byte[] getMessageForPacket(){
        //ByteBuffer - easily to work with memory
        return ByteBuffer.allocate(getMessageBytesLength())
                .putInt(cType)
                .putInt(bUserId)
                .put(message).array();
    }

    public byte[] getMessage() {
        return message;
    }

    public void encode(){
        message = MyCipher.encode(message);
    }

    public void decode(){
        message = MyCipher.decode(message);
    }

    public int getTextMessageBytesLength(){
        return message.length;
    }

    public int getMessageBytesLength() {
        return  BYTES_WITHOUT_MESSAGE + getTextMessageBytesLength();
    }
    public String getMessageText(){
        return new String (getMessage());
    }

    @Override
    public String toString() {
        return "Message{" +
                "cType=" + cType +
                ", bUserId=" + bUserId +
                ", message=" + getMessageText() +
                '}';
    }
}
