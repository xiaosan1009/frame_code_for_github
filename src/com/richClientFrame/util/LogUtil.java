package com.richClientFrame.util;

import com.richClientFrame.exception.RichClientWebException;
import com.richClientFrame.info.ControlConfig;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 项目名称 ： smeltery
 * 类名称 ： LogUtil
 * 类描述 ： 日志管理类.
 * 创建人 ： 金雷
 * 联系方式 ： xiaosan9528@163.com
 * QQ ： 26981791
 * 创建时间 ： 2010.03.19
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
     * 构造函数.
     * @param clazz 调用日志的类对象
     */
    public LogUtil(Class<?> clazz) {
        log = LogFactory.getLog(clazz);
        mClazz = clazz;
    }
    
    /**
     * 设置类对象.
     * @param clazz 调用日志的类对象
     */
    public void setClass(Class<?> clazz) {
        log = LogFactory.getLog(clazz);
        mClazz = clazz;
    }
    
    /**
     * 构造函数.
     * @param clazz 调用日志的类对象
     * @param isFrame 是否为共通日志
     */
    public LogUtil(Class<?> clazz, boolean isFrame) {
        log = LogFactory.getLog(clazz);
        mClazz = clazz;
        this.mIsFrame = isFrame;
    }
    
    /**
     * 调试方法.
     * @param obj 输出日志的值
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
     * 调试方法.
     * @param method 方法名
     * @param message 输出日志的值
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
     * 调试方法.
     * @param method 方法名
     * @param message 输出日志的值
     * @param param 参数
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
     * 调试方法【引擎】.
     * @param obj 输出日志的值
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
     * 调试方法【引擎】.
     * @param method 方法名
     * @param message 输出日志的值
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
     * 调试方法【引擎】.
     * @param method 方法名
     * @param message 输出日志的值
     * @param param 参数
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
     * 信息方法.
     * @param obj 输出日志的值
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
     * 信息方法.
     * @param method 方法名
     * @param message 输出日志的值
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
     * 信息方法.
     * @param method 方法名
     * @param message 输出日志的值
     * @param param 参数
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
     * 信息方法【引擎】.
     * @param obj 输出日志的值
     * @throws RichClientWebException RichClientWebException
     */
    private void info_f(Object obj) throws RichClientWebException {
        if (!executeFrameLog(INFO_LOG_TYPE)) {
            return;
        }
        log.info(createFrameLogContent(obj));
    }

    /**
     * 信息方法【引擎】.
     * @param method 方法名
     * @param message 输出日志的值
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
     * 信息方法【引擎】.
     * @param method 方法名
     * @param message 输出日志的值
     * @param param 参数
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
     * 异常方法.
     * @param obj 输出日志的值
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
     * 异常方法.
     * @param obj 输出日志的值
     * @param ef 错误信息
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
     * 异常方法.
     * @param method 方法名
     * @param message 输出日志的值
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
     * 异常方法.
     * @param method 方法名
     * @param message 输出日志的值
     * @param ef 错误信息
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
     * 异常方法.
     * @param method 方法名
     * @param message 输出日志的值
     * @param param 参数
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
     * 异常方法.
     * @param method 方法名
     * @param message 输出日志的值
     * @param param 参数
     * @param ef 错误信息
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
     * 异常方法【引擎】.
     * @param obj 输出日志的值
     * @throws RichClientWebException RichClientWebException
     */
    private void fatal_f(Object obj) throws RichClientWebException {
        if (!executeFrameLog(FATAL_LOG_TYPE)) {
            return;
        }
        log.fatal(createFrameLogContent(obj));
    }
    
    /**
     * 异常方法【引擎】.
     * @param obj 输出日志的值
     * @param ef 错误信息
     * @throws RichClientWebException RichClientWebException
     */
    private void fatal_f(String obj, Throwable ef) throws RichClientWebException {
        if (!executeFrameLog(FATAL_LOG_TYPE)) {
            return;
        }
        log.fatal(createFrameLogContent(obj), ef);
    }
    
    /**
     * 异常方法【引擎】.
     * @param method 方法名
     * @param message 输出日志的值
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
     * 异常方法【引擎】.
     * @param method 方法名
     * @param message 输出日志的值
     * @param ef 错误信息
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
     * 异常方法【引擎】.
     * @param method 方法名
     * @param message 输出日志的值
     * @param param 参数
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
     * 异常方法【引擎】.
     * @param method 方法名
     * @param message 输出日志的值
     * @param param 参数
     * @param ef 错误信息
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
     * 错误方法.
     * @param obj 输出日志的值
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
     * 错误方法.
     * @param obj 输出日志的值
     * @param ef 错误信息
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
     * 错误方法.
     * @param obj 输出日志的值
     * @param ef 错误信息
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
     * 错误方法.
     * @param method 方法名
     * @param message 输出日志的值
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
     * 错误方法.
     * @param method 方法名
     * @param message 输出日志的值
     * @param ef 错误信息
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
     * 错误方法.
     * @param method 方法名
     * @param message 输出日志的值
     * @param param 参数
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
     * 错误方法.
     * @param method 方法名
     * @param message 输出日志的值
     * @param param 参数
     * @param ef 错误信息
     */
    public void error(Throwable ef, String method, Object message, String... param) {
        try {
            // 是否打印日志的判断
            if (!executeLog(ERROR_LOG_TYPE)) {
                return;
            }
            if (mIsFrame) {
                // 错误方法【引擎】
                error_f(ef, method, message, param);
            } else {
                // 创建日志参数信息
                final StringBuffer paramBuff = makeLogParams(param);
                // 创建日志过程信息
                message = makeLogStepInfo(message);
                // 日志打印
                log.error(createLogContent(method, message, paramBuff), ef);
            }
        } catch (RichClientWebException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * 错误方法【引擎】.
     * @param obj 输出日志的值
     * @throws RichClientWebException RichClientWebException
     */
    private void error_f(Object obj) throws RichClientWebException {
        if (!executeFrameLog(ERROR_LOG_TYPE)) {
            return;
        }
        log.error(createFrameLogContent(obj));
    }
    
    /**
     * 错误方法【引擎】.
     * @param obj 输出日志的值
     * @param ef 错误信息
     * @throws RichClientWebException RichClientWebException
     */
    private void error_f(String obj, Throwable ef) throws RichClientWebException {
        if (!executeFrameLog(ERROR_LOG_TYPE)) {
            return;
        }
        log.error(createFrameLogContent(obj), ef);
    }
    
    /**
     * 错误方法【引擎】.
     * @param obj 输出日志的值
     * @param ef 错误信息
     * @throws RichClientWebException RichClientWebException
     */
    private void error_f(Object obj, Throwable ef) throws RichClientWebException {
        if (!executeFrameLog(ERROR_LOG_TYPE)) {
            return;
        }
        log.error(createFrameLogContent(obj), ef);
    }
    
    /**
     * 错误方法【引擎】.
     * @param method 方法名
     * @param message 输出日志的值
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
     * 错误方法【引擎】.
     * @param method 方法名
     * @param message 输出日志的值
     * @param ef 错误信息
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
     * 错误方法【引擎】.
     * @param method 方法名
     * @param message 输出日志的值
     * @param param 参数
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
     * 错误方法【引擎】.
     * @param method 方法名
     * @param message 输出日志的值
     * @param param 参数
     * @param ef 错误信息
     * @throws RichClientWebException RichClientWebException
     */
    private void error_f(Throwable ef, String method, Object message, String... param) throws RichClientWebException {
        // 是否打印引擎日志的判断
        if (!executeFrameLog(ERROR_LOG_TYPE)) {
            return;
        }
        // 创建引擎日志参数信息
        final StringBuffer paramBuff = makeFrameLogParams(param);
        // 创建日志过程信息
        message = makeLogStepInfo(message);
        // 日志打印
        log.error(createFrameLogContent(method, message, paramBuff), ef);
    }

    /**
     * @Description: 创建日志过程信息
     * @author king
     * @since Nov 30, 2012 9:13:26 AM 
     * @version V1.0
     * @param message 日志过程信息
     * @return 日志过程信息
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
     * @Description: 创建日志参数信息
     * @author king
     * @since Nov 30, 2012 9:11:49 AM 
     * @version V1.0
     * @param param 参数
     * @return 日志参数信息
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
     * @Description: 创建引擎日志参数信息
     * @author king
     * @since Nov 30, 2012 9:11:49 AM 
     * @version V1.0
     * @param param 参数
     * @return 日志参数信息
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
     * 创建日志头信息.
     * @return 日志头信息
     */
    private String makeLogHeader() {
        if (mClazz == null) {
            return ConstantsUtil.Str.EMPTY;
        }
        return ConstantsUtil.Log.LEFT_SIGN + mClazz.getSimpleName() + ConstantsUtil.Log.RIGHT_SIGN;
    }
    
    /**
     * 创建日志时间信息.
     * @param log 日志信息
     * @throws RichClientWebException RichClientWebException
     * @return 日志时间信息
     */
    private String setLogTimeInfo(String log) throws RichClientWebException {
        if (CommonUtil.isEmpty(log)) {
            return setLogTimeInfo();
        }
        log += setLogTimeInfo();
        return log;
    }
    
    /**
     * 创建日志时间信息.
     * @throws RichClientWebException RichClientWebException
     * @return 日志时间信息
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
    * @Description: 是否打印日志的判断
    * @author king
    * @since Nov 1, 2012 2:25:44 PM 
    * 
    * @param type 日志类型
    * @return 是否打印日志
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
     * @Description: 生成日志内容
     * @author king
     * @since Nov 30, 2012 9:22:37 AM 
     * @version V1.0
     * @param obj 方法名
     * @return 日志内容
     * @throws RichClientWebException RichClientWebException
     */
    private String createLogContent(Object obj) throws RichClientWebException {
        return createLogContent(obj, null);
    }
    
    /**
     * @Description: 生成日志内容
     * @author king
     * @since Nov 30, 2012 9:22:37 AM 
     * @version V1.0
     * @param method 方法名
     * @param message 过程信息
     * @return 日志内容
     * @throws RichClientWebException RichClientWebException
     */
    private String createLogContent(Object method, Object message) throws RichClientWebException {
        return createLogContent(method, message, new StringBuffer());
    }
    
    /**
     * @Description: 生成日志内容
     * @author king
     * @since Nov 30, 2012 9:22:37 AM 
     * @version V1.0
     * @param method 方法名
     * @param message 过程信息
     * @param paramBuff 参数信息
     * @return 日志内容
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
     * @Description: 是否打印引擎日志的判断
     * @author king
     * @since Nov 1, 2012 2:25:44 PM 
     * 
     * @param type 日志类型
     * @return 是否打印日志
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
     * @Description: 生成引擎日志内容
     * @author king
     * @since Nov 30, 2012 9:22:37 AM 
     * @version V1.0
     * @param obj 方法名
     * @return 引擎日志内容
     * @throws RichClientWebException RichClientWebException
     */
    private String createFrameLogContent(Object obj) throws RichClientWebException {
        return createFrameLogContent(obj, null);
    }
    
    /**
     * @Description: 生成引擎日志内容
     * @author king
     * @since Nov 30, 2012 9:22:37 AM 
     * @version V1.0
     * @param method 方法名
     * @param message 过程信息
     * @return 引擎日志内容
     * @throws RichClientWebException RichClientWebException
     */
    private String createFrameLogContent(Object method, Object message)
        throws RichClientWebException {
        return createFrameLogContent(method, message, new StringBuffer());
    }
    
    /**
     * @Description: 生成引擎日志内容
     * @author king
     * @since Nov 30, 2012 9:22:37 AM 
     * @version V1.0
     * @param method 方法名
     * @param message 过程信息
     * @param paramBuff 参数信息
     * @return 引擎日志内容
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
