
package cn.com.junit.action;

import com.richClientFrame.util.CommonUtil;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * @ClassName: CameraSetNetworkThread
 * @Description:
 * @author king
 * @since 2013-7-18 下午03:54:16
 */
public class CameraSetNetworkThread extends Thread {
    
    public static final String SET_NETWORK_FINISHED = "SET_NETWORK_FINISHED";

    private static final byte[] MO_I = {
        77, 79, 95, 73,
    };
    
    /**
     * @param videoBean videoBean
     */
    public CameraSetNetworkThread() {
    }

    /**
     * @ClassName: CameraSetNetworkThread
     * @Description:
     * @author king
     * @since 2013-7-18 下午03:54:16
     */
    public void run() {
        final byte[] buffer = new byte[''];
        InetAddress addr = null;

        DatagramSocket s = null;
        int executeTimes = 1;

        Label_1: while (executeTimes-- > 0) {
            if (s != null) {
                s.close();
            }
            try {
                s = new DatagramSocket(0);
                s.setBroadcast(true);
                s.setSoTimeout(60000);
            } catch (SocketException e2) {
                e2.printStackTrace();
                // Log.e("Searching Camera......", e2.getMessage());
                System.out.println("Searching Camera......" + e2.getMessage());
                break Label_1;
            }

            LibcMisc.memset(buffer, 0, 87);
            System.arraycopy(MO_I, 0, buffer, 0, MO_I.length);
            buffer[4] = 2;
            buffer[15] = 64;
            buffer[26] = 1;
            final byte[] cameraId = "78A5DD08BEE1".getBytes();
            System.arraycopy(cameraId, 0, buffer, 27, cameraId.length);
            final byte[] userId = "admin".getBytes();
            System.arraycopy(userId, 0, buffer, 40, userId.length);
            final byte[] password = "123456".getBytes();
            System.arraycopy(password, 0, buffer, 53, password.length);
            final byte[] ip = CommonUtil.ipv4Address2BinaryArray("192.168.1.198");
            System.arraycopy(ip, 0, buffer, 66, ip.length);
            final byte[] mask = CommonUtil.ipv4Address2BinaryArray("255.255.255.0");
            System.arraycopy(mask, 0, buffer, 70, mask.length);
            final byte[] gateway = CommonUtil.ipv4Address2BinaryArray("192.168.1.1");
            System.arraycopy(gateway, 0, buffer, 74, gateway.length);
            final byte[] dns = CommonUtil.ipv4Address2BinaryArray("192.168.1.1");
            System.arraycopy(dns, 0, buffer, 78, dns.length);
            buffer[83] = (byte)Integer.parseInt("80");
            try {
                addr = InetAddress.getByName("255.255.255.255");
            } catch (UnknownHostException e1) {
                e1.printStackTrace();
                // Log.e("Searching Camera......", e1.getMessage());
                System.out.println("Searching Camera......" + e1.getMessage());
                break Label_1;
            }

//            for (int i = 0; i < buffer.length; i++) {
//                LOG.info("buffer["+i+"] = " + buffer[i]);
//            }
            DatagramPacket pkt = new DatagramPacket(buffer, 0, 87, addr, 10000);
            try {
                s.send(pkt);
//                s.send(pkt);
            } catch (IOException e1) {
                e1.printStackTrace();
                break Label_1;
            }

            while (true) {
                pkt = new DatagramPacket(buffer, buffer.length);
                try {
                    s.receive(pkt);
                } catch (IOException e1) {
//                    e1.printStackTrace();
//                    LOG.error("exception ==> " + e1.getMessage());
                    System.out.println("exception ==> " + e1.getMessage());
                    break;
                }

                final int ret = pkt.getLength();
                if (ret == 0) {
                    break;
                }
                if ((ret < 24) || (!new String(buffer, 0, 4).equals("MO_I"))) {
                    continue;
                }

                final short operationCode = LibcMisc.get_short(buffer, 4);
                final int textLength = LibcMisc.get_int(buffer, 15);
                if ((operationCode != 3) || (textLength < 1)) {
                    continue;
                }

                try {
                    final String result = new String(buffer, 23, 24, "ASCII");
//                    LOG.info("result = " + result);
                    System.out.println("result = " + result);
//                    setNetworkFinished(result);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    // Log.e("Searching Camera......", e.getMessage());
                    continue;
                }

                break;
            }

        }
        if (s != null) {
            s.close();
        }

    }
    
    /** 
    * @Description: 
    * @author king
    * @since 2013-7-18 下午05:18:55 
    * 
    * @param result result
    */
//    private void setNetworkFinished(String result) {
//        final NSNotificationCenter nc = NSNotificationCenter.defaultCenter();
//        final Map userinfo = new HashMap();
//        userinfo.put("result", result);
//        nc.postNotification(SET_NETWORK_FINISHED, null, userinfo);
//    }

}
