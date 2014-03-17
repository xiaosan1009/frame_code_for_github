package com.richClientFrame.util.socket;

import com.richClientFrame.util.LogUtil;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Vector;


public class SocketClient {
    
    /**
     * log∂‘œÛ.
     */
    private static LogUtil log = new LogUtil(SocketClient.class);
    
    private static Vector<Socket> mClients = new Vector<Socket>();
    
    public static void add(Socket client) {
        mClients.add(client);
    }
    
    public static boolean contains(Socket client) {
        return mClients.contains(client);
    }
    
    public static int size() {
        return mClients.size();
    }
    
    public static void remove(Socket client) {
        mClients.remove(client);
    }
    
    public static void send(Socket client, String message) {
        DataOutputStream writer = null;
        try {
            for (int i = 0; i < mClients.size(); i++) {
                final Socket socket = mClients.get(i);
                String user = "other : ";
                if (client == socket) {
                    user = "me : ";
                    continue;
                }
                log.info("socket = " + socket);
                DataInputStream reader = new DataInputStream(socket.getInputStream());
                writer = new DataOutputStream(socket.getOutputStream());
                writer.writeUTF(message);
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void sendDatas(Socket client, byte[] yuv420sp) {
        DataOutputStream writer = null;
        try {
            for (int i = 0; i < mClients.size(); i++) {
                final Socket socket = mClients.get(i);
                if (client == socket) {
                }
                log.info("socket = " + socket);
                DataInputStream reader = new DataInputStream(socket.getInputStream());
                writer = new DataOutputStream(socket.getOutputStream());
                writer.write(yuv420sp, 0, 28800);
                writer.write(yuv420sp, 28800, 28800);
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void send(String message) {
        DataOutputStream writer = null;
        try {
            for (int i = 0; i < mClients.size(); i++) {
                final Socket socket = mClients.get(i);
                log.info("socket = " + socket);
                writer = new DataOutputStream(socket.getOutputStream());
                writer.writeUTF(message);
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
