package com.richClientFrame.util.socket;

import com.richClientFrame.info.ControlConfig;
import com.richClientFrame.util.ConstantsUtil;
import com.richClientFrame.util.LogUtil;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketThread implements Runnable {
    
    private LogUtil log;
    
    public SocketThread() {
        log = new LogUtil(this.getClass());
        final Thread thread = new Thread(this);
        thread.start();
    }
    
    public void run() {
        try {
            final ServerSocket serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(
                    ControlConfig.getInstance().getConfiguration().getSocketPort()));
            while (true) {
                log.info("SocketThread run");
                final Socket socket = serverSocket.accept();
                if (!SocketClient.contains(socket)) {
                    SocketClient.add(socket);
                }
                log.info("size = " + SocketClient.size());
                new InnerThread(socket);
                log.info("SocketThread end");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private class InnerThread implements Runnable {
        
        private Socket socket;
        private DataInputStream reader;
        private DataOutputStream writer;

        
        public InnerThread(Socket s) {
            socket = s;
            final Thread thread = new Thread(this);
            thread.start();
        }

        public void run() {
            
            try {
                reader = new DataInputStream(socket.getInputStream());
                writer = new DataOutputStream(socket.getOutputStream());
                String msg = ConstantsUtil.Str.EMPTY;
                log.info("socket.isConnected() = " + socket.isConnected());
                while (socket.isConnected() && ((msg = reader.readUTF()) != null)) {
                    log.info("InnerThread run");
                    SocketClient.send(socket, msg);
                    log.info("来自客户机：" + msg);
                }
            } catch (EOFException e) {
                e.printStackTrace();
                log.info("the stream is over.");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    SocketClient.remove(socket);
                    if (reader != null) {
                        reader.close();
                    }
                    if (writer != null) {
                        writer.close();
                    }
                    if (socket != null) {
                        socket.close();
                    }
                    reader = null;
                    writer = null;
                    socket = null;
                    log.info("客户机离开");
                    log.info("当前客户机的连接数：" + SocketClient.size());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
