package com.richClientFrame.util;

import com.richClientFrame.exception.RichClientWebException;
import com.richClientFrame.info.ControlConfig;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * ��Ŀ���� �� smeltery
 * ������ �� LogUtil
 * ������ �� ��־������.
 * ������ �� ����
 * ��ϵ��ʽ �� xiaosan9528@163.com
 * QQ �� 26981791
 * ����ʱ�� �� 2010.03.19
 * @author king
 */
public class LogUtil {
    
    private Log log;
    
    private boolean mIsFrame;
    
    private Class<?> mClazz;
    
    private static final int DEBUG_LOG_TYPE = 0;
    
    private static final int INFO_LOG_TYPE = 1;
    
    private static final int FATAL_LOG_TYPE = 2;
    
    private static final int ERROR_LOG_TYPE = 3;
    
    /**
     * ���캯��.
     * @param clazz ������־�������
     */
    public LogUtil(Class<?> clazz) {
        log = LogFactory.getLog(clazz);
        mClazz = clazz;
    }
    
    /**
     * ���������.
     * @param clazz ������־�������
     */
    public void setClass(Class<?> clazz) {
        log = LogFactory.getLog(clazz);
        mClazz = clazz;
    }
    
    /**
     * ���캯��.
     * @param clazz ������־�������
     * @param isFrame �Ƿ�Ϊ��ͨ��־
     */
    public LogUtil(Class<?> clazz, boolean isFrame) {
        log = LogFactory.getLog(clazz);
        mClazz = clazz;
        this.mIsFrame = isFrame;
    }
    
    /**
     * ���Է���.
     * @param obj �����־��ֵ
     */
    public void debug(Object obj) {
        try {
            if (!executeLog(DEBUG_LOG_TYPE)) {
                return;
            }
            if (mIsFrame) {
                debug_f(obj);
            } else {
                try {
                    log.info(createLogContent(obj));
                } catch (RichClientWebException e) {
                    e.printStackTrace();
                }
            }
        } catch (RichClientWebException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * ���Է���.
     * @param method ������
     * @param message �����־��ֵ
     */
    public void debug(String method, Object message) {
        try {
            if (!executeLog(DEBUG_LOG_TYPE)) {
                return;
            }
            if (mIsFrame) {
                debug_f(method, message);
            } else {
                message = makeLogStepInfo(message);
                log.info(createLogContent(method, message));
            }
        } catch (RichClientWebException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * ���Է���.
     * @param method ������
     * @param message �����־��ֵ
     * @param param ����
     */
    public void debug(String method, Object message, String... param) {
        try {
            if (!executeLog(DEBUG_LOG_TYPE)) {
                return;
            }
            if (mIsFrame) {
                debug_f(method, message, param);
            } else {
                final StringBuffer paramBuff = makeLogParams(param);
                message = makeLogStepInfo(message);
                log.info(createLogContent(method, message, paramBuff));
            }
        } catch (RichClientWebException e1) {
            e1.printStackTrace();
        }
    }
    
    /**
     * ���Է��������桿.
     * @param obj �����־��ֵ
     * @throws RichClientWebException RichClientWebException
     */
    private void debug_f(Object obj) throws RichClientWebException {
        if (!executeFrameLog(DEBUG_LOG_TYPE)) {
            return;
        }
        try {
            log.info(createFrameLogContent(obj));
        } catch (RichClientWebException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * ���Է��������桿.
     * @param method ������
     * @param message �����־��ֵ
     * @throws RichClientWebException RichClientWebException
     */
    private void debug_f(String method, Object message) throws RichClientWebException {
        if (!executeFrameLog(DEBUG_LOG_TYPE)) {
            return;
        }
        message = makeLogStepInfo(message);
        log.info(createFrameLogContent(method, message));
    }
    
    /**
     * ���Է��������桿.
     * @param method ������
     * @param message �����־��ֵ
     * @param param ����
     * @throws RichClientWebException RichClientWebException
     */
    private void debug_f(String method, Object message, String... param) throws RichClientWebException {
        if (!executeFrameLog(DEBUG_LOG_TYPE)) {
            return;
        }
        final StringBuffer paramBuff = makeFrameLogParams(param);
        message = makeLogStepInfo(message);
        log.info(createFrameLogContent(method, message, paramBuff));
    }
    
    /**
     * ��Ϣ����.
     * @param obj �����־��ֵ
     */
    public void info(Object obj) {
        try {
            if (!executeLog(INFO_LOG_TYPE)) {
                return;
            }
            if (mIsFrame) {
                info_f(obj);
            } else {
                try {
                    log.info(createLogContent(obj));
                } catch (RichClientWebException e) {
                    e.printStackTrace();
                }
            }
        } catch (RichClientWebException e1) {
            e1.printStackTrace();
        }
    }
    
    /**
     * ��Ϣ����.
     * @param method ������
     * @param message �����־��ֵ
     */
    public void info(String method, Object message) {
        try {
            if (!executeLog(INFO_LOG_TYPE)) {
                return;
            }
            if (mIsFrame) {
                info_f(method, message);
            } else {
                message = makeLogStepInfo(message);
                log.info(createLogContent(method, message));
            }
        } catch (RichClientWebException e1) {
            e1.printStackTrace();
        }
    }
    
    /**
     * ��Ϣ����.
     * @param method ������
     * @param message �����־��ֵ
     * @param param ����
     */
    public void info(String method, Object message, String... param) {
        try {
            if (!executeLog(INFO_LOG_TYPE)) {
                return;
            }
            if (mIsFrame) {
                info_f(method, message, param);
            } else {
                final StringBuffer paramBuff = makeLogParams(param);
                message = makeLogStepInfo(message);
                log.info(createLogContent(method, message, paramBuff));
            }
        } catch (RichClientWebException e1) {
            e1.printStackTrace();
        }
    }
    
    /**
     * ��Ϣ���������桿.
     * @param obj �����־��ֵ
     * @throws RichClientWebException RichClientWebException
     */
    private void info_f(Object obj) throws RichClientWebException {
        if (!executeFrameLog(INFO_LOG_TYPE)) {
            return;
        }
        log.info(createFrameLogContent(obj));
    }

    /**
     * ��Ϣ���������桿.
     * @param method ������
     * @param message �����־��ֵ
     * @throws RichClientWebException RichClientWebException
     */
    private void info_f(String method, Object message) throws RichClientWebException {
        if (!executeFrameLog(INFO_LOG_TYPE)) {
            return;
        }
        message = makeLogStepInfo(message);
        log.info(createFrameLogContent(method, message));
    }
    
    /**
     * ��Ϣ���������桿.
     * @param method ������
     * @param message �����־��ֵ
     * @param param ����
     * @throws RichClientWebException RichClientWebException
     */
    private void info_f(String method, Object message, String... param) throws RichClientWebException {
        if (!executeFrameLog(INFO_LOG_TYPE)) {
            return;
        }
        final StringBuffer paramBuff = makeFrameLogParams(param);
        message = makeLogStepInfo(message);
        log.info(createFrameLogContent(method, message, paramBuff));
    }
    
    /**
     * �쳣����.
     * @param obj �����־��ֵ
     */
    public void fatal(Object obj) {
        try {
            if (!executeLog(FATAL_LOG_TYPE)) {
                return;
            }
            if (mIsFrame) {
                fatal_f(obj);
            } else {
                try {
                    log.fatal(createLogContent(obj));
                } catch (RichClientWebException e) {
                    e.printStackTrace();
                }
            }
        } catch (RichClientWebException e1) {
            e1.printStackTrace();
        }
    }
    
    /**
     * �쳣����.
     * @param obj �����־��ֵ
     * @param ef ������Ϣ
     */
    public void fatal(String obj, Throwable ef) {
        try {
            if (!executeLog(FATAL_LOG_TYPE)) {
                return;
            }
            if (mIsFrame) {
                fatal_f(obj, ef);
            } else {
                try {
                    log.fatal(createLogContent(obj), ef);
                } catch (RichClientWebException e) {
                    e.printStackTrace();
                }
            }
        } catch (RichClientWebException e1) {
            e1.printStackTrace();
        }
    }
    
    /**
     * �쳣����.
     * @param method ������
     * @param message �����־��ֵ
     */
    public void fatal(String method, Object message) {
        try {
            if (!executeLog(FATAL_LOG_TYPE)) {
                return;
            }
            if (mIsFrame) {
                fatal_f(method, message);
            } else {
                message = makeLogStepInfo(message);
                log.fatal(createLogContent(method, message));
            }
        } catch (RichClientWebException e1) {
            e1.printStackTrace();
        }
    }
    
    /**
     * �쳣����.
     * @param method ������
     * @param message �����־��ֵ
     * @param ef ������Ϣ
     */
    public void fatal(String method, Object message, Throwable ef) {
        try {
            if (!executeLog(FATAL_LOG_TYPE)) {
                return;
            }
            if (mIsFrame) {
                fatal_f(method, message, ef);
            } else {
                message = makeLogStepInfo(message);
                log.fatal(createLogContent(method, message), ef);
            }
        } catch (RichClientWebException e1) {
            e1.printStackTrace();
        }
    }
    
    /**
     * �쳣����.
     * @param method ������
     * @param message �����־��ֵ
     * @param param ����
     */
    public void fatal(String method, Object message, String... param) {
        try {
            if (!executeLog(FATAL_LOG_TYPE)) {
                return;
            }
            if (mIsFrame) {
                fatal_f(method, message, param);
            } else {
                final StringBuffer paramBuff = makeLogParams(param);
                message = makeLogStepInfo(message);
                log.fatal(createLogContent(method, message, paramBuff));
            }
        } catch (RichClientWebException e1) {
            e1.printStackTrace();
        }
    }
    
    /**
     * �쳣����.
     * @param method ������
     * @param message �����־��ֵ
     * @param param ����
     * @param ef ������Ϣ
     */
    public void fatal(Throwable ef, String method, Object message, String... param) {
        try {
            if (!executeLog(FATAL_LOG_TYPE)) {
                return;
            }
            if (mIsFrame) {
                fatal_f(ef, method, message, param);
            } else {
                final StringBuffer paramBuff = makeLogParams(param);
                message = makeLogStepInfo(message);
                log.fatal(createLogContent(method, message, paramBuff), ef);
            }
        } catch (RichClientWebException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * �쳣���������桿.
     * @param obj �����־��ֵ
     * @throws RichClientWebException RichClientWebException
     */
    private void fatal_f(Object obj) throws RichClientWebException {
        if (!executeFrameLog(FATAL_LOG_TYPE)) {
            return;
        }
        log.fatal(createFrameLogContent(obj));
    }
    
    /**
     * �쳣���������桿.
     * @param obj �����־��ֵ
     * @param ef ������Ϣ
     * @throws RichClientWebException RichClientWebException
     */
    private void fatal_f(String obj, Throwable ef) throws RichClientWebException {
        if (!executeFrameLog(FATAL_LOG_TYPE)) {
            return;
        }
        log.fatal(createFrameLogContent(obj), ef);
    }
    
    /**
     * �쳣���������桿.
     * @param method ������
     * @param message �����־��ֵ
     * @throws RichClientWebException RichClientWebException
     */
    private void fatal_f(String method, Object message) throws RichClientWebException {
        if (!executeFrameLog(FATAL_LOG_TYPE)) {
            return;
        }
        message = makeLogStepInfo(message);
        log.fatal(createFrameLogContent(method, message));
    }
    
    /**
     * �쳣���������桿.
     * @param method ������
     * @param message �����־��ֵ
     * @param ef ������Ϣ
     * @throws RichClientWebException RichClientWebException
     */
    private void fatal_f(String method, Object message, Throwable ef) throws RichClientWebException {
        if (!executeFrameLog(FATAL_LOG_TYPE)) {
            return;
        }
        message = makeLogStepInfo(message);
        log.fatal(createFrameLogContent(method, message), ef);
    }

    /**
     * �쳣���������桿.
     * @param method ������
     * @param message �����־��ֵ
     * @param param ����
     * @throws RichClientWebException RichClientWebException
     */
    private void fatal_f(String method, Object message, String... param) throws RichClientWebException {
        if (!executeFrameLog(FATAL_LOG_TYPE)) {
            return;
        }
        final StringBuffer paramBuff = makeFrameLogParams(param);
        message = makeLogStepInfo(message);
        log.fatal(createFrameLogContent(method, message, paramBuff));
    }
    
    /**
     * �쳣���������桿.
     * @param method ������
     * @param message �����־��ֵ
     * @param param ����
     * @param ef ������Ϣ
     * @throws RichClientWebException RichClientWebException
     */
    private void fatal_f(Throwable ef, String method, Object message, String... param) throws RichClientWebException {
        if (!executeFrameLog(FATAL_LOG_TYPE)) {
            return;
        }
        final StringBuffer paramBuff = makeFrameLogParams(param);
        message = makeLogStepInfo(message);
        log.fatal(createFrameLogContent(method, message, paramBuff), ef);
    }
    
    /**
     * ���󷽷�.
     * @param obj �����־��ֵ
     */
    public void error(Object obj) {
        try {
            if (!executeLog(ERROR_LOG_TYPE)) {
                return;
            }
            if (mIsFrame) {
                error_f(obj);
            } else {
                log.error(createLogContent(obj));
            }
        } catch (RichClientWebException e1) {
            e1.printStackTrace();
        }
    }
    
    /**
     * ���󷽷�.
     * @param obj �����־��ֵ
     * @param ef ������Ϣ
     */
    public void error(String obj, Throwable ef) {
        try {
            if (!executeLog(ERROR_LOG_TYPE)) {
                return;
            }
            if (mIsFrame) {
                error_f(obj, ef);
            } else {
                log.error(createLogContent(obj), ef);
            }
        } catch (RichClientWebException e1) {
            e1.printStackTrace();
        }
    }
    
    /**
     * ���󷽷�.
     * @param obj �����־��ֵ
     * @param ef ������Ϣ
     */
    public void error(Object obj, Throwable ef) {
        try {
            if (!executeLog(ERROR_LOG_TYPE)) {
                return;
            }
            if (mIsFrame) {
                error_f(obj, ef);
            } else {
                try {
                    log.error(createLogContent(obj), ef);
                } catch (RichClientWebException e) {
                    e.printStackTrace();
                }
            }
        } catch (RichClientWebException e1) {
            e1.printStackTrace();
        }
    }
    
    /**
     * ���󷽷�.
     * @param method ������
     * @param message �����־��ֵ
     */
    public void error(String method, Object message) {
        try {
            if (!executeLog(ERROR_LOG_TYPE)) {
                return;
            }
            if (mIsFrame) {
                error_f(method, message);
            } else {
                message = makeLogStepInfo(message);
                log.error(createLogContent(method, message));
            }
        } catch (RichClientWebException e1) {
            e1.printStackTrace();
        }
    }
    
    /**
     * ���󷽷�.
     * @param method ������
     * @param message �����־��ֵ
     * @param ef ������Ϣ
     */
    public void error(String method, Object message, Throwable ef) {
        try {
            if (!executeLog(ERROR_LOG_TYPE)) {
                return;
            }
            if (mIsFrame) {
                error_f(method, message, ef);
            } else {
                message = makeLogStepInfo(message);
                log.error(createLogContent(method, message), ef);
            }
        } catch (RichClientWebException e1) {
            e1.printStackTrace();
        }
    }
    
    /**
     * ���󷽷�.
     * @param method ������
     * @param message �����־��ֵ
     * @param param ����
     */
    public void error(String method, Object message, String... param) {
        try {
            if (!executeLog(ERROR_LOG_TYPE)) {
                return;
            }
            if (mIsFrame) {
                error_f(method, message, param);
            } else {
                final StringBuffer paramBuff = makeLogParams(param);
                message = makeLogStepInfo(message);
                log.error(createLogContent(method, message, paramBuff));
            }
        } catch (RichClientWebException e1) {
            e1.printStackTrace();
        }
    }
    
    /**
     * ���󷽷�.
     * @param method ������
     * @param message �����־��ֵ
     * @param param ����
     * @param ef ������Ϣ
     */
    public void error(Throwable ef, String method, Object message, String... param) {
        try {
            // �Ƿ��ӡ��־���ж�
            if (!executeLog(ERROR_LOG_TYPE)) {
                return;
            }
            if (mIsFrame) {
                // ���󷽷������桿
                error_f(ef, method, message, param);
            } else {
                // ������־������Ϣ
                final StringBuffer paramBuff = makeLogParams(param);
                // ������־������Ϣ
                message = makeLogStepInfo(message);
                // ��־��ӡ
                log.error(createLogContent(method, message, paramBuff), ef);
            }
        } catch (RichClientWebException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * ���󷽷������桿.
     * @param obj �����־��ֵ
     * @throws RichClientWebException RichClientWebException
     */
    private void error_f(Object obj) throws RichClientWebException {
        if (!executeFrameLog(ERROR_LOG_TYPE)) {
            return;
        }
        log.error(createFrameLogContent(obj));
    }
    
    /**
     * ���󷽷������桿.
     * @param obj �����־��ֵ
     * @param ef ������Ϣ
     * @throws RichClientWebException RichClientWebException
     */
    private void error_f(String obj, Throwable ef) throws RichClientWebException {
        if (!executeFrameLog(ERROR_LOG_TYPE)) {
            return;
        }
        log.error(createFrameLogContent(obj), ef);
    }
    
    /**
     * ���󷽷������桿.
     * @param obj �����־��ֵ
     * @param ef ������Ϣ
     * @throws RichClientWebException RichClientWebException
     */
    private void error_f(Object obj, Throwable ef) throws RichClientWebException {
        if (!executeFrameLog(ERROR_LOG_TYPE)) {
            return;
        }
        log.error(createFrameLogContent(obj), ef);
    }
    
    /**
     * ���󷽷������桿.
     * @param method ������
     * @param message �����־��ֵ
     * @throws RichClientWebException RichClientWebException
     */
    private void error_f(String method, Object message) throws RichClientWebException {
        if (!executeFrameLog(ERROR_LOG_TYPE)) {
            return;
        }
        message = makeLogStepInfo(message);
        log.error(createFrameLogContent(method, message));
    }
    
    /**
     * ���󷽷������桿.
     * @param method ������
     * @param message �����־��ֵ
     * @param ef ������Ϣ
     * @throws RichClientWebException RichClientWebException
     */
    private void error_f(String method, Object message, Throwable ef) throws RichClientWebException {
        if (!executeFrameLog(ERROR_LOG_TYPE)) {
            return;
        }
        message = makeLogStepInfo(message);
        log.error(createFrameLogContent(method, message), ef);
    }
    
    /**
     * ���󷽷������桿.
     * @param method ������
     * @param message �����־��ֵ
     * @param param ����
     * @throws RichClientWebException RichClientWebException
     */
    private void error_f(String method, Object message, String... param) throws RichClientWebException {
        if (!executeFrameLog(ERROR_LOG_TYPE)) {
            return;
        }
        final StringBuffer paramBuff = makeFrameLogParams(param);
        message = makeLogStepInfo(message);
        log.error(createFrameLogContent(method, message, paramBuff));
    }
    
    /**
     * ���󷽷������桿.
     * @param method ������
     * @param message �����־��ֵ
     * @param param ����
     * @param ef ������Ϣ
     * @throws RichClientWebException RichClientWebException
     */
    private void error_f(Throwable ef, String method, Object message, String... param) throws RichClientWebException {
        // �Ƿ��ӡ������־���ж�
        if (!executeFrameLog(ERROR_LOG_TYPE)) {
            return;
        }
        // ����������־������Ϣ
        final StringBuffer paramBuff = makeFrameLogParams(param);
        // ������־������Ϣ
        message = makeLogStepInfo(message);
        // ��־��ӡ
        log.error(createFrameLogContent(method, message, paramBuff), ef);
    }

    /**
     * @Description: ������־������Ϣ
     * @author king
     * @since Nov 30, 2012 9:13:26 AM 
     * @version V1.0
     * @param message ��־������Ϣ
     * @return ��־������Ϣ
     */
    private Object makeLogStepInfo(Object message) {
        if (CommonUtil.isNotEmpty(message)) {
            message = "***" + CommonUtil.toString(message) + "*** ";
        } else {
            message = CommonUtil.toString(message);
        }
        return message;
    }
    
    /**
     * @Description: ������־������Ϣ
     * @author king
     * @since Nov 30, 2012 9:11:49 AM 
     * @version V1.0
     * @param param ����
     * @return ��־������Ϣ
     */
    private StringBuffer makeLogParams(String... param) {
        final StringBuffer paramBuff = new StringBuffer();
        if (param == null) {
            return paramBuff;
        }
        for (int i = 0; i < param.length; i++) {
            paramBuff.append(ConstantsUtil.Log.LEFT_SIGN + param[i] + ConstantsUtil.Log.RIGHT_SIGN);
        }
        return paramBuff;
    }

    /**
     * @Description: ����������־������Ϣ
     * @author king
     * @since Nov 30, 2012 9:11:49 AM 
     * @version V1.0
     * @param param ����
     * @return ��־������Ϣ
     */
    private StringBuffer makeFrameLogParams(String... param) {
        final StringBuffer paramBuff = new StringBuffer();
        if (param == null) {
            return paramBuff;
        }
        for (int i = 0; i < param.length; i++) {
            paramBuff.append(ConstantsUtil.Log.FRAME_LEFT_SIGN + param[i] + ConstantsUtil.Log.FRAME_RIGHT_SIGN);
        }
        return paramBuff;
    }
    
    /**
     * ������־ͷ��Ϣ.
     * @return ��־ͷ��Ϣ
     */
    private String makeLogHeader() {
        if (mClazz == null) {
            return ConstantsUtil.Str.EMPTY;
        }
        return ConstantsUtil.Log.LEFT_SIGN + mClazz.getSimpleName() + ConstantsUtil.Log.RIGHT_SIGN;
    }
    
    /**
     * ������־ʱ����Ϣ.
     * @param log ��־��Ϣ
     * @throws RichClientWebException RichClientWebException
     * @return ��־ʱ����Ϣ
     */
    private String setLogTimeInfo(String log) throws RichClientWebException {
        if (CommonUtil.isEmpty(log)) {
            return setLogTimeInfo();
        }
        log += setLogTimeInfo();
        return log;
    }
    
    /**
     * ������־ʱ����Ϣ.
     * @throws RichClientWebException RichClientWebException
     * @return ��־ʱ����Ϣ
     */
    private String setLogTimeInfo() throws RichClientWebException {
        if (ControlConfig.getInstance() == null
                || ControlConfig.getInstance().getConfiguration().doLogTime()) {
            return (ConstantsUtil.Log.LEFT_SIGN 
                + DateUtils.getNowTime(DateUtils.FORMAT_HH_MM_SS) 
                + ConstantsUtil.Log.RIGHT_SIGN);
        }
        return ConstantsUtil.Str.EMPTY;
    }
    
    /** 
    * @Description: �Ƿ��ӡ��־���ж�
    * @author king
    * @since Nov 1, 2012 2:25:44 PM 
    * 
    * @param type ��־����
    * @return �Ƿ��ӡ��־
    * @throws RichClientWebException RichClientWebException
    */
    private boolean executeLog(int type) throws RichClientWebException {
        if (ControlConfig.getInstance() == null) {
            return true;
        }
        if (!ControlConfig.getInstance().getConfiguration().doLog()) {
            return false;
        }
        final int level = ControlConfig.getInstance().getConfiguration().getLogLevel();
        if (level > type) {
            return false;
        }
//        switch (type) {
//            case DEBUG_LOG_TYPE:
//                break;
//            case INFO_LOG_TYPE:
//                if (level > 1) {
//                    return false;
//                }
//                break;
//            case FATAL_LOG_TYPE:
//                if (level > 2) {
//                    return false;
//                }
//                break;
//            case ERROR_LOG_TYPE:
//                if (level > 3) {
//                    return false;
//                }
//                break;
//
//            default:
//                break;
//        }
        return true;
    }
    
    /**
     * @Description: ������־����
     * @author king
     * @since Nov 30, 2012 9:22:37 AM 
     * @version V1.0
     * @param obj ������
     * @return ��־����
     * @throws RichClientWebException RichClientWebException
     */
    private String createLogContent(Object obj) throws RichClientWebException {
        return createLogContent(obj, null);
    }
    
    /**
     * @Description: ������־����
     * @author king
     * @since Nov 30, 2012 9:22:37 AM 
     * @version V1.0
     * @param method ������
     * @param message ������Ϣ
     * @return ��־����
     * @throws RichClientWebException RichClientWebException
     */
    private String createLogContent(Object method, Object message) throws RichClientWebException {
        return createLogContent(method, message, new StringBuffer());
    }
    
    /**
     * @Description: ������־����
     * @author king
     * @since Nov 30, 2012 9:22:37 AM 
     * @version V1.0
     * @param method ������
     * @param message ������Ϣ
     * @param paramBuff ������Ϣ
     * @return ��־����
     * @throws RichClientWebException RichClientWebException
     */
    private String createLogContent(Object method, Object message, final StringBuffer paramBuff)
        throws RichClientWebException {
        return makeLogHeader() + ConstantsUtil.Log.LEFT_SIGN 
                + method 
                + ConstantsUtil.Log.RIGHT_SIGN 
                + CommonUtil.toString(message) 
                + setLogTimeInfo(paramBuff.toString());
    }
    
    /** 
     * @Description: �Ƿ��ӡ������־���ж�
     * @author king
     * @since Nov 1, 2012 2:25:44 PM 
     * 
     * @param type ��־����
     * @return �Ƿ��ӡ��־
     * @throws RichClientWebException RichClientWebException
     */
    private boolean executeFrameLog(int type) throws RichClientWebException {
        if (ControlConfig.getInstance() == null) {
            return true;
        }
        if (!ControlConfig.getInstance().getConfiguration().doFrameLog()) {
            return false;
        }
        return true;
    }
    
    /**
     * @Description: ����������־����
     * @author king
     * @since Nov 30, 2012 9:22:37 AM 
     * @version V1.0
     * @param obj ������
     * @return ������־����
     * @throws RichClientWebException RichClientWebException
     */
    private String createFrameLogContent(Object obj) throws RichClientWebException {
        return createFrameLogContent(obj, null);
    }
    
    /**
     * @Description: ����������־����
     * @author king
     * @since Nov 30, 2012 9:22:37 AM 
     * @version V1.0
     * @param method ������
     * @param message ������Ϣ
     * @return ������־����
     * @throws RichClientWebException RichClientWebException
     */
    private String createFrameLogContent(Object method, Object message)
        throws RichClientWebException {
        return createFrameLogContent(method, message, new StringBuffer());
    }
    
    /**
     * @Description: ����������־����
     * @author king
     * @since Nov 30, 2012 9:22:37 AM 
     * @version V1.0
     * @param method ������
     * @param message ������Ϣ
     * @param paramBuff ������Ϣ
     * @return ������־����
     * @throws RichClientWebException RichClientWebException
     */
    private String createFrameLogContent(Object method, Object message, final StringBuffer paramBuff)
        throws RichClientWebException {
        return ConstantsUtil.Log.FRAME_SIGN 
            + makeLogHeader() + ConstantsUtil.Log.FRAME_LEFT_SIGN 
            + method 
            + ConstantsUtil.Log.FRAME_RIGHT_SIGN 
            + CommonUtil.toString(message) 
            + setLogTimeInfo(paramBuff.toString());
    }

}
