
package cn.com.junit.action;

/**
 * ��Ŀ��� �� Web2.0��������. ����� �� Test ������ �� ������ �� ���� jϵ��ʽ �� xiaosan9528@163.com QQ ��
 * 26981791 ����ʱ�� �� Jul 24, 2013 10:30:48 AM
 * 
 * @author king
 */
public class Test {
    
    private final static InheritableThreadLocal<String> holder = new InheritableThreadLocal<String>();  

    /**
     * @Description:
     * @author king
     * @since Jul 24, 2013 10:30:48 AM
     * @version V1.0
     * @param args
     */

    public static void main(String[] args) {
//        System.out.println(toStringHex("50"));
//        CameraSetNetworkThread cameraSetNetworkThread = new CameraSetNetworkThread();
//        cameraSetNetworkThread.start();
//        CameraSearchThread cameraSearchThread = new CameraSearchThread();
//        cameraSearchThread.start();
//        System.out.println(Math.sin(30 * Math.PI * 2 / 360));
//        holder.set("aaa");  
//        System.out.println("begin=" + holder.get());  
//        Thread a = new Thread(){  
//           public void run() {  
//                System.out.println("thread-begin=" + holder.get());  
//                holder.set("vvvvvvvvvvvvv");  
//                System.out.println("thread-end=" + holder.get());  
//           }  
//        };  
//     
//        a.start();  
//        try {  
//           Thread.sleep(2000);  
//       } catch (InterruptedException e) {  
//           e.printStackTrace();  
//       }  
//        System.out.println("end=" + holder.get());
        char hex[] = {
                '0', '1', '2', '3', '4', '5', '6', '7', 
                '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
                };
                byte b = (byte) -15; 
                System.out.println("b = 0x" + hex[(b >> 4) & 0x0f] + hex[b & 0x0f]);
                System.out.println((b >> 4) & 0x0f);
    }

    public static String toStringHex(String s) {
        byte[] baKeyword = new byte[s.length() / 2];
        for (int i = 0; i < baKeyword.length; i++) {
            try {
                baKeyword[i] = (byte)(0xff & Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            s = new String(baKeyword, "utf-8");// UTF-16le:Not
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return s;
    }

}
