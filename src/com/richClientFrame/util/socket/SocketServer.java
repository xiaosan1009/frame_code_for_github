package com.richClientFrame.util.socket;

import com.richClientFrame.util.ConstantsUtil;
import com.richClientFrame.util.LogUtil;

import org.apache.log4j.PropertyConfigurator;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer implements Runnable {
    
    private LogUtil log;
    
    private static final int PORT = 23010;
    
    // 307200 OR 230400//57600 76800
    private static final int DATA_LEN = 57600;
    // 14400;//28800;
    private static final int TT = 28800;
    // 图像YUV数组
    private byte[] yuv420sp = new byte[DATA_LEN];
    
    public SocketServer() {
        PropertyConfigurator.configure(
                "C:/Users/king/work/workspace/frame_workspace/web/smeltery/WebRoot/WEB-INF/xml/log4j.properties");
        log = new LogUtil(this.getClass());
        final Thread thread = new Thread(this);
        thread.start();
    }
    
    public void run() {
        try {
            final ServerSocket serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(PORT));
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
//                while (true) {
//                    log.info("InnerThread run");
//                    System.out.println("dis = " + reader.toString());
//                    for (int i = 0; i < DATA_LEN / TT; i++) {
//                        reader.read(yuv420sp, i * TT, TT);
//                    }
//                    SocketClient.sendDatas(socket, yuv420sp);
//                }
                while (socket.isConnected() && ((msg = reader.readUTF()) != null)) {
                    SocketClient.send(socket, msg);
                    log.info("来自客户机：" + msg);
                }
            } catch (EOFException e) {
                e.printStackTrace();
                log.info("the stream is over*********.");
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
    
    public static void main(String[] args) {
        new SocketServer();
    }

}
