package com.richClientFrame.util.socket;

import com.richClientFrame.info.ControlConfig;
import com.richClientFrame.util.LogUtil;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketProtocol implements Runnable {
    
    private static LogUtil sLog = new LogUtil(SocketProtocol.class);

    private static final String POLICY_FILE = "<?xml version=\"1.0\"?>\n" 
        + "<!DOCTYPE cross-domain-policy SYSTEM \"http://www.adobe.com/xml/dtds/cross-domain-policy.dtd\">\n" 
        + "<cross-domain-policy> \n" 
        + " <allow-access-from domain=\"*\" to-ports=\"20340\"/> \n" 
        + "</cross-domain-policy>";

    private static final String POLICY_REQUEST = "<policy-file-request/>";
    
    public SocketProtocol() {
        final Thread thread = new Thread(this);
        thread.start();
    }
    
    public void run() {
        try {
            final int securityPort = ControlConfig.getInstance().getConfiguration().getSocketSecurity();
            final ServerSocket serverSocket = new ServerSocket();
            sLog.info("SocketProtocol run", null, "securityPort = " + securityPort);
            serverSocket.bind(new InetSocketAddress(securityPort));
            while (true) {
                sLog.info("SocketProtocol run");
                System.out.println("run");
                final Socket socket = serverSocket.accept();
                final BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                final char[] by = new char[22];
                br.read(by,0,22);
                final String request = new String(by);
                sLog.info("head = " + request + "");
                if (POLICY_REQUEST.equals(request)) {
                    final DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                    dataOutputStream.write(POLICY_FILE.getBytes());
                    dataOutputStream.flush();
                    dataOutputStream.close();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
