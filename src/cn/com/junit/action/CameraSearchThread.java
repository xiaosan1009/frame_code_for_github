
package cn.com.junit.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * search cameras in LAN
 * 
 * @author wind
 */
public class CameraSearchThread extends Thread {

    public void run() {
        final byte[] MO_I = {
            77, 79, 95, 73,
        };
        final byte[] buffer = new byte[''];
        InetAddress addr = null;

        int camerasFound = 0;
        DatagramSocket s = null;
        int searchTimes = 2;

        Label_1: while (searchTimes-- > 0) {
            if (s != null) {
                s.close();
            }
            try {
                s = new DatagramSocket(0);
                s.setBroadcast(true);
                s.setSoTimeout(1000);
            } catch (SocketException e2) {
                e2.printStackTrace();
                // Log.e("Searching Camera......", e2.getMessage());
                break Label_1;
            }

            LibcMisc.memset(buffer, 0, 27);
            System.arraycopy(MO_I, 0, buffer, 0, MO_I.length);
            buffer[4] = 0;
            buffer[15] = 4;
            buffer[26] = 1;
            try {
                addr = InetAddress.getByName("255.255.255.255");
            } catch (UnknownHostException e1) {
                e1.printStackTrace();
                // Log.e("Searching Camera......", e1.getMessage());
                break Label_1;
            }

            for (int i = 0; i < buffer.length; i++) {
                System.out.println("buffer["+i+"] = " + buffer[i]);
            }
            DatagramPacket pkt = new DatagramPacket(buffer, 0, 27, addr, 10000);
            try {
                s.send(pkt);
                s.send(pkt);
            } catch (IOException e1) {
                e1.printStackTrace();
                // Log.e("Searching Camera......", e1.getMessage());
                break Label_1;
            }

            while (true) {
                pkt = new DatagramPacket(buffer, buffer.length);
                try {
                    s.receive(pkt);
                } catch (IOException e1) {
                    e1.printStackTrace();
                    // Log.e("Searching Camera......", e1.getMessage());
                    break;
                }

                final int ret = pkt.getLength();
                if (ret == 0) {
                    break;
                }
                if ((ret < 87) || (!new String(buffer, 0, 4).equals("MO_I"))) {
                    continue;
                }

                short vals = LibcMisc.get_short(buffer, 4);
                final int vali = LibcMisc.get_int(buffer, 15);
                if ((vals != 1) || (vali < 64)) {
                    continue;
                }

                String devId;

                try {
                    devId = new String(buffer, 23, 35, "ASCII");
                    if (devId.indexOf(0) != -1) {
                        devId = devId.substring(0, devId.indexOf(0));
                    }
//                    LOG.info("Camera Name = " + new String(buffer, 36, 56, "ASCII"));
                    System.out.println("Camera Name = " + new String(buffer, 36, 56, "ASCII"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    // Log.e("Searching Camera......", e.getMessage());
                    devId = null;
                    continue;
                }

                final byte[] temp = new byte[4];
                System.arraycopy(buffer, 57, temp, 0, 4);
                String devIp;
                try {
//                    LOG.info("temp = " + CommonUtil.binaryArray2Ipv4Address(temp));
                    addr = InetAddress.getByAddress(temp);
                    // dev_ip = addr.getHostName();
                    devIp = addr.getHostAddress();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                    // Log.e("Searching Camera......", e.getMessage());
                    devIp = null;
                    continue;
                }

//                vals = LibcMisc.get_short(buffer, 85);
//                vals = NetMisc.ntohs(vals);
//                final String devPort = String.valueOf(vals);
//                LOG.info("devPort = " + devPort);
//                IpCamera.handle_camera_searched(devId, devIp, devPort);
                camerasFound++;
                break;
            }

        }
        if (s != null) {
            s.close();
        }

//        IpCamera.handle_search_ended(camerasFound);
    }
    
}
