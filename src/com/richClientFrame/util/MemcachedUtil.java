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
 * memcached对象控制类..
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
     * 是否使用memcached.
     * @param f 是否使用memcached
     * @return 是否使用memcached
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
     * 生成单模实例.
     * @return 单模实例
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
     * 构造函数.
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
                sLog.info("MemcachedUtil构造函数", "path = " + sPath);
                ins = this.getClass().getClassLoader().getResourceAsStream(sPath);
                sProps = new Properties();
                sProps.load(ins);
                sLog.info("MemcachedUtil构造函数", "session_properties=session.properties |" + sProps);
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
                sLog.error("MemcachedUtil构造函数", "close inputstream error!");
            }
        }
        sLog.info("MemcachedUtil", "end");
    }
    
    /**
     * 取得memcached的map对象.
     * @param id memcached的map对象对应的key
     * @return memcached的map对象
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
     * 取得memcached的对象..
     * @param id memcached的对象对应的key
     * @return memcached的对象
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
     * 设置memcached中存储的对象.
     * @param id memcached中存储对象的key
     * @param obj memcached中存储对象的值
     * @return 设置结果
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
     * 设置memcached中存储的对象[设置生效时间].
     * @param id memcached中存储对象的key
     * @param obj memcached中存储对象的值
     * @param expiry memcached中存储对象的存续时间
     * @return memcached中存储的对象
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
     * 设置memcached中存储的对象[MAP].
     * @param id memcached中存储对象的key
     * @param valueMap memcached中存储对象的值[MAP]
     * @return 设置结果
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
     * 设置memcached中存储的对象.
     * @param id memcached中存储对象的key
     * @param obj memcached中存储对象的值
     * @param expiry memcached中存储对象的存续时间
     * @return 设置结果
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
     * 设置memcached中存储的对象[MAP].
     * @param id memcached中存储对象的key
     * @param valueMap memcached中存储对象的值
     * @param expiry memcached中存储对象的存续时间
     * @return 设置结果
     */
    public boolean setMap(String id, Map valueMap, long expiry) {
        return setMap(id, valueMap, new Date(expiry));
    }
    
    /**
     * 设置memcached中存储的对象[MAP].
     * @param id memcached中存储对象的key
     * @param valueMap memcached中存储对象的值
     * @param expiry memcached中存储对象的存续时间
     * @return 设置结果
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
     * 在memcached中移除对应key的值.
     * @param id memcached中移除对应key
     * @return 移除结果
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
     * 取得memcached客户端的状态.
     * @return memcached客户端的状态
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
     * 判断memcached服务器是否连接..
     * @return memcached服务器是否连接
     */
    public boolean isNotConnection() {
        return isNotConnection;
    }

    /**
     * 取得memcached配置文件路径.
     * @return memcached配置文件路径
     */
    public static String getPath() {
        return sPath;
    }

    /**
     * 设置memcached配置文件路径.
     * @param path memcached配置文件路径
     */
    public static void setPath(String path) {
        sPath = path;
    }
    
    /**
     * 生成memcached对应工程的key
     * @param key memcached的key
     * @return memcached对应工程的key
     */
    private String createKey(String key) {
        return ControlConfig.getInstance().getConfiguration().getMemcachedKey() 
            + ConstantsUtil.Str.UNDERLINE + key;
    }
    
    /**
     * 取得memcached客户端对象
     * @return memcached客户端对象
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
     * 初始化memcached服务器交互对象.
     * @param props 配置文件对象
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
    
    /* 关闭memcached服务器交互对象.
     * @see java.lang.Object#finalize()
     */
    @Override
    protected void finalize() {
        if (sPool != null) {
            sPool.shutDown();
        }
    }
    
    /**
     * memcached内部常量类.
     * @author king
     *
     */
    private static final class InnerConvertUtil {
        
        /**
         * 构造函数.
         */
        private InnerConvertUtil() {
        }
        
        /**
         * 取得memcached属性.
         * @param props 配置文件对象
         * @param key 属性对应的key
         * @return memcached属性
         */
        public static int getIntProperty(Properties props, String key) {
            return getIntProperty(props, key, 0);
        }

        /**
         * 取得memcached属性.
         * @param props 配置文件对象
         * @param key 属性对应的key
         * @param defaultValue 默认值
         * @return memcached属性
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
         * 取得Long型属性数据.
         * @param props 配置文件对象
         * @param key 属性对应的key
         * @return Long型属性数据
         */
        public static long getLongProperty(Properties props, String key) {
            return getLongProperty(props, key, 0);
        }

        /**
         * 取得Long型属性数据.
         * @param props 配置文件对象
         * @param key 属性对应的key
         * @param defaultValue 默认值
         * @return Long型属性数据
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
         * 取得Boolean型属性数据.
         * @param props 配置文件对象
         * @param key 属性对应的key
         * @return Boolean型属性数据
         */
        public static boolean getBooleanProperty(Properties props, String key) {
            return getBooleanProperty(props, key, false);
        }

        /**
         * 取得Boolean型属性数据.
         * @param props 配置文件对象
         * @param key 属性对应的key
         * @param defaultValue 默认值
         * @return Boolean型属性数据
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
