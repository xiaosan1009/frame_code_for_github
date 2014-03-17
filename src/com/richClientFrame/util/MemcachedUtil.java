package com.richClientFrame.util;
import com.danga.MemCached.MemcachedClient;
import com.danga.MemCached.SockIOPool;
import com.richClientFrame.exception.RichClientWebException;
import com.richClientFrame.info.ControlConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

/**
 * memcached���������..
 * @author king
 *
 */
public class MemcachedUtil {
    
    private static LogUtil sLog = new LogUtil(MemcachedUtil.class);
    
    private static MemcachedUtil sMe;

    private static MemcachedClient sMcc;

    private static int sSessionTimeout = 1800000;

    private static SockIOPool sPool;

    private static String sPoolName = "memsock";

    private boolean isNotConnection;

    private static Properties sProps;

    private static Object sLock = new Object();

    private static boolean sUseflag = true;
    
    private static String sPath;

    /**
     * �Ƿ�ʹ��memcached.
     * @param f �Ƿ�ʹ��memcached
     * @return �Ƿ�ʹ��memcached
     */
    public static boolean useMemcache(boolean f) {
        boolean retF = true;
        try {
            synchronized (sLock) {
                sUseflag = f;
            }
        } catch (Exception e) {
            retF = false;
        }
        return retF;
    }

    /**
     * ���ɵ�ģʵ��.
     * @return ��ģʵ��
     */
    public static synchronized MemcachedUtil getInstance() {
        if (sMe == null) {
            synchronized (sLock) {
                if (sMe == null) {
                    sMe = new MemcachedUtil();
                }
            }
        }
        return sMe;
    }

    /**
     * ���캯��.
     */
    public MemcachedUtil() {
        sLog.info("MemcachedUtil", "start");
        InputStream ins = null;
        try {
            if (CommonUtil.isEmpty(sPath)) {
                sUseflag = false;
                return;
            }
            if (sProps == null || sProps.isEmpty() || sPool == null) {
                sLog.info("MemcachedUtil���캯��", "path = " + sPath);
                ins = this.getClass().getClassLoader().getResourceAsStream(sPath);
                sProps = new Properties();
                sProps.load(ins);
                sLog.info("MemcachedUtil���캯��", "session_properties=session.properties |" + sProps);
                initSockIOPool(sProps);
                sSessionTimeout = InnerConvertUtil.getIntProperty(sProps, "sessionTimeout", 1800000);
            }
        } catch (Exception e) {
            sUseflag = false;
            e.printStackTrace();
        } finally {
            try {
                if (ins != null) {
                    ins.close();
                }
            } catch (IOException e) {
                sLog.error("MemcachedUtil���캯��", "close inputstream error!");
            }
        }
        sLog.info("MemcachedUtil", "end");
    }
    
    /**
     * ȡ��memcached��map����.
     * @param id memcached��map�����Ӧ��key
     * @return memcached��map����
     */
    public Map getMap(String id) {
        id = createKey(id);
        sLog.info("getMap", "start", "id = " + id, "useflag = " + sUseflag);

        Map map = null;
        try {
            if (sUseflag) {
                final long start = System.currentTimeMillis();
                map = (Map) getMemCachedClient().get(id);
                final long end = System.currentTimeMillis();
                sLog.info("getMap", null, "id=" + id, "map=" + map, "use time=" + (end - start));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        sLog.info("getMap", "end");
        return map;
    }
    
    /**
     * ȡ��memcached�Ķ���..
     * @param id memcached�Ķ����Ӧ��key
     * @return memcached�Ķ���
     */
    public Object get(String id) {
        id = createKey(id);
        sLog.info("get", "start", "id = " + id, "useflag = " + sUseflag);
        
        Object obj = null;
        try {
            if (sUseflag) {
                final long start = System.currentTimeMillis();
                obj = getMemCachedClient().get(id);
                final long end = System.currentTimeMillis();
                sLog.info("get", "id=" + id, "obj=" + obj, "use time=" + (end - start));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        sLog.info("get", "end");
        return obj;
    }

    /**
     * ����memcached�д洢�Ķ���.
     * @param id memcached�д洢�����key
     * @param obj memcached�д洢�����ֵ
     * @return ���ý��
     */
    public boolean set(String id, Object obj) {
        try {
            return set(id, obj, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    /**
     * ����memcached�д洢�Ķ���[������Чʱ��].
     * @param id memcached�д洢�����key
     * @param obj memcached�д洢�����ֵ
     * @param expiry memcached�д洢����Ĵ���ʱ��
     * @return memcached�д洢�Ķ���
     */
    public boolean set(String id, Object obj, long expiry) {
        try {
            return set(id, obj, new Date(expiry));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    /**
     * ����memcached�д洢�Ķ���[MAP].
     * @param id memcached�д洢�����key
     * @param valueMap memcached�д洢�����ֵ[MAP]
     * @return ���ý��
     */
    public boolean setMap(String id, Map valueMap) {
        try {
            return setMap(id, valueMap, new Date(sSessionTimeout));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    /**
     * ����memcached�д洢�Ķ���.
     * @param id memcached�д洢�����key
     * @param obj memcached�д洢�����ֵ
     * @param expiry memcached�д洢����Ĵ���ʱ��
     * @return ���ý��
     */
    public boolean set(String id, Object obj, Date expiry) {
        id = createKey(id);
        sLog.info("set", "start", "id = " + id, "obj = " + obj, "expiry = " + expiry);
        boolean flag = false;
        try {
            if (sUseflag) {
                if (expiry != null) {
                    flag = getMemCachedClient().set(id, obj, expiry);
                    sLog.info("set", "expiry", "flag = " + flag);
                } else {
                    flag = getMemCachedClient().set(id, obj);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        sLog.info("set", "end", "flag = " + flag);
        return flag;
    }
    
    /**
     * ����memcached�д洢�Ķ���[MAP].
     * @param id memcached�д洢�����key
     * @param valueMap memcached�д洢�����ֵ
     * @param expiry memcached�д洢����Ĵ���ʱ��
     * @return ���ý��
     */
    public boolean setMap(String id, Map valueMap, long expiry) {
        return setMap(id, valueMap, new Date(expiry));
    }
    
    /**
     * ����memcached�д洢�Ķ���[MAP].
     * @param id memcached�д洢�����key
     * @param valueMap memcached�д洢�����ֵ
     * @param expiry memcached�д洢����Ĵ���ʱ��
     * @return ���ý��
     */
    public boolean setMap(String id, Map valueMap, Date expiry) {
        id = createKey(id);
        sLog.info("setMap", "start", "id = " + id, "valueMap = " + valueMap, "expiry = " + expiry);
        boolean flag = false;
        try {
            if (sUseflag) {
                flag = getMemCachedClient().set(id, valueMap, expiry);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        sLog.info("setMap", "end", "flag = " + flag);
        return flag;
    }

    /**
     * ��memcached���Ƴ���Ӧkey��ֵ.
     * @param id memcached���Ƴ���Ӧkey
     * @return �Ƴ����
     */
    public boolean remove(String id) {
        try {
            id = createKey(id);
            if (!sUseflag) {
                return false;
            }
            return getMemCachedClient().delete(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * ȡ��memcached�ͻ��˵�״̬.
     * @return memcached�ͻ��˵�״̬
     */
    public Map stats() {
        try {
            return getMemCachedClient().stats();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * �ж�memcached�������Ƿ�����..
     * @return memcached�������Ƿ�����
     */
    public boolean isNotConnection() {
        return isNotConnection;
    }

    /**
     * ȡ��memcached�����ļ�·��.
     * @return memcached�����ļ�·��
     */
    public static String getPath() {
        return sPath;
    }

    /**
     * ����memcached�����ļ�·��.
     * @param path memcached�����ļ�·��
     */
    public static void setPath(String path) {
        sPath = path;
    }
    
    /**
     * ����memcached��Ӧ���̵�key
     * @param key memcached��key
     * @return memcached��Ӧ���̵�key
     */
    private String createKey(String key) {
        return ControlConfig.getInstance().getConfiguration().getMemcachedKey() 
            + ConstantsUtil.Str.UNDERLINE + key;
    }
    
    /**
     * ȡ��memcached�ͻ��˶���
     * @return memcached�ͻ��˶���
     */
    private static MemcachedClient getMemCachedClient() {
        sLog.info("getMemCachedClient", "start");
        if (sMcc == null) {
            sMcc = new MemcachedClient(sPoolName);
            sMcc.setPrimitiveAsString(true);
        }
        sLog.info("getMemCachedClient", "end");
        return sMcc;
    }
    
    /**
     * ��ʼ��memcached��������������.
     * @param props �����ļ�����
     */
    private static void initSockIOPool(Properties props) {
        sLog.info("initSockIOPool", "start", "props = " + props);
        try {
            final String serverlist = props.getProperty("serverlist");
            sPoolName = props.getProperty("poolname", "memsock");
            final String[] servers = serverlist.split(",");
            sPool = SockIOPool.getInstance(sPoolName);
            sLog.debug("SockIOPool = " + sPool);
            sPool.setServers(servers);
            sPool.setFailover(InnerConvertUtil.getBooleanProperty(props, "failover", true));
            sPool.setInitConn(InnerConvertUtil.getIntProperty(props, "initconn", 10));
            sPool.setMinConn(InnerConvertUtil.getIntProperty(props, "minconn", 5));
            sPool.setMaxConn(InnerConvertUtil.getIntProperty(props, "maxconn", 250));
            sPool.setMaintSleep(InnerConvertUtil.getIntProperty(props, "maintsleep", 30));
            sPool.setNagle(InnerConvertUtil.getBooleanProperty(props, "nagle", false));
            sPool.setSocketTO(InnerConvertUtil.getIntProperty(props, "socketTO", 3000));
            sPool.setAliveCheck(InnerConvertUtil.getBooleanProperty(props,"alivecheck", true));
            sPool.initialize();
        } catch (Exception e) {
            e.printStackTrace();
        }
        sLog.info("initSockIOPool", "end");
    }
    
    /* �ر�memcached��������������.
     * @see java.lang.Object#finalize()
     */
    @Override
    protected void finalize() {
        if (sPool != null) {
            sPool.shutDown();
        }
    }
    
    /**
     * memcached�ڲ�������.
     * @author king
     *
     */
    private static final class InnerConvertUtil {
        
        /**
         * ���캯��.
         */
        private InnerConvertUtil() {
        }
        
        /**
         * ȡ��memcached����.
         * @param props �����ļ�����
         * @param key ���Զ�Ӧ��key
         * @return memcached����
         */
        public static int getIntProperty(Properties props, String key) {
            return getIntProperty(props, key, 0);
        }

        /**
         * ȡ��memcached����.
         * @param props �����ļ�����
         * @param key ���Զ�Ӧ��key
         * @param defaultValue Ĭ��ֵ
         * @return memcached����
         */
        public static int getIntProperty(Properties props, String key, int defaultValue) {
            int result = defaultValue;
            if (props != null) {
                final String value = props.getProperty(key);
                try {
                    result = Integer.parseInt(value);
                } catch (Exception ex) {
                    sLog.error("InnerConvertUtil:getIntProperty", "get property fail:" + ex.getMessage());
                }
            }
            return result;
        }

        /**
         * ȡ��Long����������.
         * @param props �����ļ�����
         * @param key ���Զ�Ӧ��key
         * @return Long����������
         */
        public static long getLongProperty(Properties props, String key) {
            return getLongProperty(props, key, 0);
        }

        /**
         * ȡ��Long����������.
         * @param props �����ļ�����
         * @param key ���Զ�Ӧ��key
         * @param defaultValue Ĭ��ֵ
         * @return Long����������
         */
        public static long getLongProperty(Properties props, String key, long defaultValue) {
            long result = defaultValue;
            if (props != null) {
                final String value = props.getProperty(key);
                try {
                    result = Long.parseLong(value);
                } catch (Exception ex) {
                    sLog.error("get property fail:" + ex.getMessage());
                }
            }
            return result;
        }

        /**
         * ȡ��Boolean����������.
         * @param props �����ļ�����
         * @param key ���Զ�Ӧ��key
         * @return Boolean����������
         */
        public static boolean getBooleanProperty(Properties props, String key) {
            return getBooleanProperty(props, key, false);
        }

        /**
         * ȡ��Boolean����������.
         * @param props �����ļ�����
         * @param key ���Զ�Ӧ��key
         * @param defaultValue Ĭ��ֵ
         * @return Boolean����������
         */
        public static boolean getBooleanProperty(Properties props, String key, boolean defaultValue) {
            boolean result = defaultValue;
            if (props != null) {
                final String value = props.getProperty(key);
                try {
                    result = Boolean.parseBoolean(value);
                } catch (Exception ex) {
                    sLog.error("get property fail:" + ex.getMessage());
                }
            }
            return result;
        }

    }

}
