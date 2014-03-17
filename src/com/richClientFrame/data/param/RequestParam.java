
package com.richClientFrame.data.param;

import com.richClientFrame.dao.TableRowMap;
import com.richClientFrame.data.param.RequestParamFace.IActionStep;
import com.richClientFrame.data.param.RequestParamFace.IChecker;
import com.richClientFrame.data.param.RequestParamFace.ICmb;
import com.richClientFrame.data.param.RequestParamFace.ICondition;
import com.richClientFrame.data.param.RequestParamFace.IDataSource;
import com.richClientFrame.data.param.RequestParamFace.IExcel;
import com.richClientFrame.data.param.RequestParamFace.IExternal;
import com.richClientFrame.data.param.RequestParamFace.IFormat;
import com.richClientFrame.data.param.RequestParamFace.IItem;
import com.richClientFrame.data.param.RequestParamFace.IList;
import com.richClientFrame.data.param.RequestParamFace.IParam;
import com.richClientFrame.data.param.RequestParamFace.IResult;
import com.richClientFrame.data.param.RequestParamFace.ISingle;
import com.richClientFrame.data.param.RequestParamFace.IWhether;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 项目名称 ： Web2.0开发引擎. 类名称 ： RequestParam 类描述 ： request.xml参数管理类. 创建人 ： 金雷 联系方式 ：
 * xiaosan9528@163.com QQ ： 26981791 创建时间 ： 2011.09.07
 * 
 * @author king
 */
public class RequestParam implements ICmb, IItem, IDataSource, IExcel {

    private String dispCode;

    private String handlerClass;

    private String methodCode;

    private String logout;

    private String request;

    private String forward;

    private String check;
    
    private String describe;

    private boolean inputStream;

    private String checkIndex;

    private String jspName;

    private String initJspName;

    private String client;

    private Servlet servlet;

    private Method method;

    private Complex complex;

    private List list;

    private Dimension dimension;

    private Items items;
    
    private Mails mails;

    private Updates updates;

    private Upload upload;

    private java.util.List<Cmb> cmbs = new ArrayList<Cmb>();

    private Map<String, Cmb> cmbMaps = new HashMap<String, Cmb>();

    private java.util.List<Style> styles = new ArrayList<Style>();

    private java.util.List<CellStyle> cellStyles = new ArrayList<CellStyle>();

    private Map<String, Style> stylesMap = new HashMap<String, Style>();

    private Map<String, CellStyle> cellStylesMap = new HashMap<String, CellStyle>();

    private DataSource dataSource;

    private Excel excel;

    public class Method {
        private String name;

        private int Type;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getType() {
            return Type;
        }

        public void setType(int type) {
            Type = type;
        }
    }

    public class Servlet {
        private String method;

        public String getMethod() {
            return method;
        }

        public void setMethod(String method) {
            this.method = method;
        }
    }

    public class Complex implements IItem, IExcel, IList {
        private Items items;

        private Lists lists;

        private Excel excel;

        private String pageSize;

        public Lists getLists() {
            return lists;
        }

        public void setLists(Lists lists) {
            this.lists = lists;
        }

        public Items getItems() {
            return items;
        }

        public void setItems(Items items) {
            this.items = items;
        }

        public Excel getExcel() {
            return excel;
        }

        public void setExcel(Excel excel) {
            this.excel = excel;
        }

        public String getPageSize() {
            return pageSize;
        }

        public void setPageSize(String pageSize) {
            this.pageSize = pageSize;
        }
    }

    public class Items {

        private String id;

        private java.util.List<Item> items = new ArrayList<Item>();

        public java.util.List<Item> getItems() {
            return items;
        }

        public void setItems(java.util.List<Item> items) {
            this.items = items;
        }

        public void addItem(Item item) {
            items.add(item);
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
    
    public class Mails {
        
        private String id;
        
        private java.util.List<Mail> mails = new ArrayList<Mail>();
        
        public java.util.List<Mail> getMails() {
            return mails;
        }
        
        public void setMails(java.util.List<Mail> items) {
            this.mails = items;
        }
        
        public void addMail(Mail item) {
            mails.add(item);
        }
        
        public String getId() {
            return id;
        }
        
        public void setId(String id) {
            this.id = id;
        }
    }
    
    public class Mail implements IParam, IResult, ICondition {
        private String index;

        private String url;
        
        private String mailServerHost;
        
        private String mailServerPort;
        
        private String userName;
        
        private String password;
        
        private boolean validate;
        
        private String fromAddress;
        
        private String toAddress;
        
        private String subject;
        
        private String content;
        
        private boolean resultConstant;

        private boolean resultAppoint;
        
        private String conditionId;
        
        private java.util.List<Param> params = new ArrayList<Param>();
        
        private java.util.List<Result> results = new ArrayList<Result>();
        
        private java.util.List<Condition> conditions = new ArrayList<Condition>();

        public String getIndex() {
            return index;
        }

        public void setIndex(String index) {
            this.index = index;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
        
        public void addParam(Param param) {
            params.add(param);
        }

        public java.util.List<Param> getParams() {
            return params;
        }

        public void setParams(java.util.List<Param> params) {
            this.params = params;
        }
        
        public java.util.List<Result> getResults() {
            return results;
        }

        public void setResults(java.util.List<Result> results) {
            this.results = results;
        }

        public void addResult(Result result) {
            results.add(result);
        }
        
        public boolean isResultConstant() {
            return resultConstant;
        }

        public void setResultConstant(boolean flg) {
            resultConstant = flg;
        }
        
        public boolean isResultAppoint() {
            return resultAppoint;
        }

        public void setResultAppoint(boolean resultAppoint) {
            this.resultAppoint = resultAppoint;
        }
        
        public void addCondition(Condition condition) {
            conditions.add(condition);
        }

        public java.util.List<Condition> getConditions() {
            return conditions;
        }

        public void setConditions(java.util.List<Condition> conditions) {
            this.conditions = conditions;
        }

        public void setConditionId(String id) {
            this.conditionId = id;
        }

        public String getConditionId() {
            return conditionId;
        }

        public String getMailServerHost() {
            return mailServerHost;
        }

        public void setMailServerHost(String mailServerHost) {
            this.mailServerHost = mailServerHost;
        }

        public String getMailServerPort() {
            return mailServerPort;
        }

        public void setMailServerPort(String mailServerPort) {
            this.mailServerPort = mailServerPort;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public boolean isValidate() {
            return validate;
        }

        public void setValidate(boolean validate) {
            this.validate = validate;
        }

        public String getFromAddress() {
            return fromAddress;
        }

        public void setFromAddress(String fromAddress) {
            this.fromAddress = fromAddress;
        }

        public String getToAddress() {
            return toAddress;
        }

        public void setToAddress(String toAddress) {
            this.toAddress = toAddress;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

    public class Upload implements IActionStep, IChecker, IExcel, ISingle {
        private String index;

        private String sql;

        private String source;

        private String path;

        private String fileName;
        
        private String dataType;

        private boolean ftp;

        private boolean useFileName;

        private boolean initialize;

        private java.util.List<Param> params = new ArrayList<Param>();

        private java.util.List<Result> results = new ArrayList<Result>();

        private java.util.List<Checker> checks = new ArrayList<Checker>();

        private boolean resultConstant;

        private boolean resultAppoint;

        private Excel excel;

        private boolean list;

        private String targetId;

        public boolean isList() {
            return list;
        }

        public void setList(boolean list) {
            this.list = list;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public boolean isFtp() {
            return ftp;
        }

        public void setFtp(boolean ftp) {
            this.ftp = ftp;
        }

        public boolean isInitialize() {
            return initialize;
        }

        public void setInitialize(boolean initialize) {
            this.initialize = initialize;
        }

        public java.util.List<Result> getResults() {
            return results;
        }

        public void setResults(java.util.List<Result> results) {
            this.results = results;
        }

        public void addResult(Result result) {
            results.add(result);
        }

        public String getSql() {
            return sql;
        }

        public void setSql(String sql) {
            this.sql = sql;
        }

        public void addCheck(Checker check) {
            checks.add(check);
        }

        public java.util.List<Checker> getChecks() {
            return checks;
        }

        public void setChecks(java.util.List<Checker> checks) {
            this.checks = checks;
        }

        public String getIndex() {
            return index;
        }

        public boolean isResultConstant() {
            return resultConstant;
        }

        public void setResultConstant(boolean flg) {
            resultConstant = flg;
        }

        public void addParam(Param param) {
            params.add(param);
        }

        public java.util.List<Param> getParams() {
            return params;
        }

        public void setParams(java.util.List<Param> params) {
            this.params = params;
        }

        public boolean isResultAppoint() {
            return resultAppoint;
        }

        public void setResultAppoint(boolean resultAppoint) {
            this.resultAppoint = resultAppoint;
        }

        public void setIndex(String index) {
            this.index = index;
        }

        public Excel getExcel() {
            return excel;
        }

        public void setExcel(Excel excel) {
            this.excel = excel;
        }

        public boolean isUseFileName() {
            return useFileName;
        }

        public void setUseFileName(boolean useFileName) {
            this.useFileName = useFileName;
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getTargetId() {
            return targetId;
        }

        public void setTargetId(String targetId) {
            this.targetId = targetId;
        }

        public String getSelectedKey() {
            // TODO Auto-generated method stub
            return null;
        }

        public String getDataType() {
            return dataType;
        }

        public void setDataType(String dataType) {
            this.dataType = dataType;
        }
    }

    public class External implements IActionStep {
        private String method;

        private String index;

        private String source;

        private java.util.List<Result> results = new ArrayList<Result>();

        private boolean resultConstant;

        private boolean resultAppoint;

        private java.util.List<Param> params = new ArrayList<Param>();

        public String getMethod() {
            return method;
        }

        public void setMethod(String method) {
            this.method = method;
        }

        public java.util.List<Result> getResults() {
            return results;
        }

        public void setResults(java.util.List<Result> results) {
            this.results = results;
        }

        public void addResult(Result result) {
            results.add(result);
        }

        public String getIndex() {
            return index;
        }

        public void setIndex(String index) {
            this.index = index;
        }

        public boolean isResultConstant() {
            return resultConstant;
        }

        public void setResultConstant(boolean flg) {
            resultConstant = flg;
        }

        public String getSql() {
            return null;
        }

        public void addParam(Param param) {
            params.add(param);
        }

        public java.util.List<Param> getParams() {
            return params;
        }

        public void setParams(java.util.List<Param> params) {
            this.params = params;
        }

        public boolean isResultAppoint() {
            return resultAppoint;
        }

        public void setResultAppoint(boolean resultAppoint) {
            this.resultAppoint = resultAppoint;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }
    }

    public class Updates implements IParam {
        private String id;

        private String success;

        private String error;

        private java.util.List<Param> params = new ArrayList<Param>();

        private java.util.List<Update> updates = new ArrayList<Update>();

        public String getSuccess() {
            return success;
        }

        public void setSuccess(String success) {
            this.success = success;
        }

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }

        public java.util.List<Update> getUpdates() {
            return updates;
        }

        public void setUpdates(java.util.List<Update> updates) {
            this.updates = updates;
        }

        public void addUpdate(Update update) {
            updates.add(update);
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void addParam(Param param) {
            params.add(param);
        }

        public java.util.List<Param> getParams() {
            return params;
        }

        public void setParams(java.util.List<Param> params) {
            this.params = params;
        }
    }

    public class Item implements IActionStep, IChecker, IExternal, IExcel, ICondition, ISingle {
        private String sql;

        private String source;

        private String index;

        private boolean list;

        private String targetId;

        /**
         * 获取下拉框初始值的key
         */
        private String selectedKey;

        private String conditionId;

        private java.util.List<Param> params = new ArrayList<Param>();

        private java.util.List<Result> results = new ArrayList<Result>();

        private java.util.List<Checker> checks = new ArrayList<Checker>();

        private java.util.List<External> externals = new ArrayList<External>();

        private java.util.List<Condition> conditions = new ArrayList<Condition>();

        private boolean resultConstant;

        private boolean resultAppoint;

        private Excel excel;

        public Excel getExcel() {
            return excel;
        }

        public void setExcel(Excel excel) {
            this.excel = excel;
        }

        public java.util.List<External> getExternals() {
            return externals;
        }

        public void setExternals(java.util.List<External> externals) {
            this.externals = externals;
        }

        public void addExternal(External external) {
            externals.add(external);
        }

        public String getSql() {
            return sql;
        }

        public void setSql(String sql) {
            this.sql = sql;
        }

        public String getIndex() {
            return index;
        }

        public void setIndex(String index) {
            this.index = index;
        }

        public java.util.List<Result> getResults() {
            return results;
        }

        public void setResults(java.util.List<Result> results) {
            this.results = results;
        }

        public void addResult(Result result) {
            results.add(result);
        }

        public java.util.List<Checker> getChecks() {
            return checks;
        }

        public void setChecks(java.util.List<Checker> checks) {
            this.checks = checks;
        }

        public void addCheck(Checker check) {
            checks.add(check);
        }

        public boolean isList() {
            return list;
        }

        public void setList(boolean list) {
            this.list = list;
        }

        public String getTargetId() {
            return targetId;
        }

        public void setTargetId(String targetId) {
            this.targetId = targetId;
        }

        /**
         * 判断是否是固定值【true：固定值】.
         * 
         * @return 是否是固定值
         */
        public boolean isResultConstant() {
            return resultConstant;
        }

        public void setResultConstant(boolean flg) {
            resultConstant = flg;
        }

        public void addParam(Param param) {
            params.add(param);
        }

        public java.util.List<Param> getParams() {
            return params;
        }

        public void setParams(java.util.List<Param> params) {
            this.params = params;
        }

        public boolean isResultAppoint() {
            return resultAppoint;
        }

        public void setResultAppoint(boolean resultAppoint) {
            this.resultAppoint = resultAppoint;
        }

        /**
         * @Description: 取得获取下拉框初始值的key
         * @author king
         * @since Jul 17, 2013 4:26:03 PM 
         * @version V1.0
         * @return 获取下拉框初始值的key
         */
        public String getSelectedKey() {
            return selectedKey;
        }

        /**
         * @Description: 设置获取下拉框初始值的key
         * @author king
         * @since Jul 17, 2013 4:26:19 PM 
         * @version V1.0
         * @param selectedKey 获取下拉框初始值的key
         */
        public void setSelectedKey(String selectedKey) {
            this.selectedKey = selectedKey;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public void addCondition(Condition condition) {
            conditions.add(condition);
        }

        public java.util.List<Condition> getConditions() {
            return conditions;
        }

        public void setConditions(java.util.List<Condition> conditions) {
            this.conditions = conditions;
        }

        public void setConditionId(String id) {
            this.conditionId = id;
        }

        public String getConditionId() {
            return conditionId;
        }
    }

    public class Update implements IActionStep, IChecker, ICondition, IExternal {
        private String sql;

        private String source;

        private String conditionId;

        private String index;

        private boolean result;

        private java.util.List<Condition> conditions = new ArrayList<Condition>();

        private java.util.List<Param> params = new ArrayList<Param>();

        private java.util.List<Result> results = new ArrayList<Result>();

        private java.util.List<Checker> checks = new ArrayList<Checker>();

        private java.util.List<External> externals = new ArrayList<External>();

        private boolean resultConstant;

        private boolean resultAppoint;

        public String getSql() {
            return sql;
        }

        public void setSql(String sql) {
            this.sql = sql;
        }

        public String getIndex() {
            return index;
        }

        public void setIndex(String index) {
            this.index = index;
        }

        public java.util.List<Result> getResults() {
            return results;
        }

        public void setResults(java.util.List<Result> results) {
            this.results = results;
        }

        public void addResult(Result result) {
            results.add(result);
        }

        public java.util.List<Checker> getChecks() {
            return checks;
        }

        public void setChecks(java.util.List<Checker> checks) {
            this.checks = checks;
        }

        public void addCheck(Checker check) {
            checks.add(check);
        }

        public boolean hasResult() {
            return result;
        }

        public void setResult(boolean result) {
            this.result = result;
        }

        public boolean isResultConstant() {
            return resultConstant;
        }

        public void setResultConstant(boolean flg) {
            resultConstant = flg;
        }

        public void addParam(Param param) {
            params.add(param);
        }

        public java.util.List<Param> getParams() {
            return params;
        }

        public void setParams(java.util.List<Param> params) {
            this.params = params;
        }

        public void addCondition(Condition condition) {
            conditions.add(condition);
        }

        public java.util.List<Condition> getConditions() {
            return conditions;
        }

        public void setConditions(java.util.List<Condition> conditions) {
            this.conditions = conditions;
        }

        public void setConditionId(String id) {
            this.conditionId = id;
        }

        public String getConditionId() {
            return conditionId;
        }

        public boolean isResultAppoint() {
            return resultAppoint;
        }

        public void setResultAppoint(boolean resultAppoint) {
            this.resultAppoint = resultAppoint;
        }

        public java.util.List<External> getExternals() {
            return externals;
        }

        public void setExternals(java.util.List<External> externals) {
            this.externals = externals;
        }

        public void addExternal(External external) {
            externals.add(external);
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }
    }

    public class Compare implements IFormat {

        private String value;

        private java.util.List<Format> formats = new ArrayList<Format>();

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public void addFormat(Format format) {
            formats.add(format);
        }

        public java.util.List<Format> getFormats() {
            return formats;
        }

        public void setFormats(java.util.List<Format> formats) {
            this.formats = formats;
        }
    }

    public class Format {
        private String method;

        private String id;

        private String params;

        public String getMethod() {
            return method;
        }

        public void setMethod(String method) {
            this.method = method;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getParams() {
            return params;
        }

        public void setParams(String params) {
            this.params = params;
        }
    }

    public class Condition implements IActionStep {
        private java.util.List<Param> params = new ArrayList<Param>();

        private String operator;

        private String sql;

        private String source;

        private String index;

        private Compare compareSource;

        private Compare compareTarget;

        private boolean resultAppoint;

        public Compare getCompareSource() {
            return compareSource;
        }

        public void setCompareSource(Compare value) {
            this.compareSource = value;
        }

        public String getOperator() {
            return operator;
        }

        public void setOperator(String operator) {
            this.operator = operator;
        }

        public Compare getCompareTarget() {
            return compareTarget;
        }

        public void setCompareTarget(Compare value) {
            this.compareTarget = value;
        }

        public String getSql() {
            return sql;
        }

        public void setSql(String sql) {
            this.sql = sql;
        }

        public void addParam(Param param) {
            params.add(param);
        }

        public java.util.List<Param> getParams() {
            return params;
        }

        public void setParams(java.util.List<Param> params) {
            this.params = params;
        }

        public void addResult(Result result) {

        }

        public String getIndex() {
            return index;
        }

        public boolean isResultConstant() {
            return false;
        }

        public void setResultConstant(boolean flg) {
        }

        public void setIndex(String index) {
            this.index = index;
        }

        public boolean isResultAppoint() {
            return resultAppoint;
        }

        public void setResultAppoint(boolean resultAppoint) {
            this.resultAppoint = resultAppoint;
        }

        public java.util.List<Result> getResults() {
            return null;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }
    }

    public class Display implements ICondition {
        private String value;

        private String conditionId;

        private java.util.List<Condition> conditions = new ArrayList<Condition>();

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public java.util.List<Condition> getConditions() {
            return conditions;
        }

        public void setConditions(java.util.List<Condition> conditions) {
            this.conditions = conditions;
        }

        public void addCondition(Condition condition) {
            conditions.add(condition);
        }

        public void setConditionId(String id) {
            this.conditionId = id;
        }

        public String getConditionId() {
            return conditionId;
        }
    }

    public class TextColor implements ICondition {
        private String value;

        private String conditionId;

        private java.util.List<Condition> conditions = new ArrayList<Condition>();

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public java.util.List<Condition> getConditions() {
            return conditions;
        }

        public void setConditions(java.util.List<Condition> conditions) {
            this.conditions = conditions;
        }

        public void addCondition(Condition condition) {
            conditions.add(condition);
        }

        public void setConditionId(String id) {
            this.conditionId = id;
        }

        public String getConditionId() {
            return conditionId;
        }
    }

    public class BackgroundColor implements ICondition {
        private String value;

        private String conditionId;

        private java.util.List<Condition> conditions = new ArrayList<Condition>();

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public java.util.List<Condition> getConditions() {
            return conditions;
        }

        public void setConditions(java.util.List<Condition> conditions) {
            this.conditions = conditions;
        }

        public void addCondition(Condition condition) {
            conditions.add(condition);
        }

        public void setConditionId(String id) {
            this.conditionId = id;
        }

        public String getConditionId() {
            return conditionId;
        }
    }

    public class Style implements ICondition {
        private String property;

        private String conditionId;

        private java.util.List<Display> displays = new ArrayList<Display>();

        private java.util.List<TextColor> textColors = new ArrayList<TextColor>();

        private java.util.List<BackgroundColor> backgroundColors = new ArrayList<BackgroundColor>();

        private java.util.List<Condition> conditions = new ArrayList<Condition>();

        public String getProperty() {
            return property;
        }

        public void setProperty(String property) {
            this.property = property;
        }

        public java.util.List<Condition> getConditions() {
            return conditions;
        }

        public void setConditions(java.util.List<Condition> conditions) {
            this.conditions = conditions;
        }

        public void addCondition(Condition condition) {
            conditions.add(condition);
        }

        public java.util.List<Display> getDisplays() {
            return displays;
        }

        public void setDisplays(java.util.List<Display> displays) {
            this.displays = displays;
        }

        public void addDisplay(Display display) {
            this.displays.add(display);
        }

        public java.util.List<TextColor> getTextColors() {
            return textColors;
        }

        public void setTextColors(java.util.List<TextColor> textColors) {
            this.textColors = textColors;
        }

        public void addTextColor(TextColor textColor) {
            this.textColors.add(textColor);
        }

        public java.util.List<BackgroundColor> getBackgroundColors() {
            return backgroundColors;
        }

        public void setBackgroundColors(java.util.List<BackgroundColor> backgroundColors) {
            this.backgroundColors = backgroundColors;
        }

        public void addBackgroundColor(BackgroundColor backgroundColor) {
            this.backgroundColors.add(backgroundColor);
        }

        public void setConditionId(String id) {
            this.conditionId = id;
        }

        public String getConditionId() {
            return conditionId;
        }
    }

    public class Property {
        private String name;

        private String value;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public class DataSource {
        private String target;

        private String method;

        public String getTarget() {
            return target;
        }

        public void setTarget(String target) {
            this.target = target;
        }

        public String getMethod() {
            return method;
        }

        public void setMethod(String method) {
            this.method = method;
        }

    }

    public class Result implements ICondition, IFormat {
        private String conditionId;

        private String targetId;

        private String operator;

        private String value;

        private boolean list;

        private java.util.List<Condition> conditions = new ArrayList<Condition>();

        private java.util.List<Format> formats = new ArrayList<Format>();

        public String getTargetId() {
            return targetId;
        }

        public void setTargetId(String property) {
            this.targetId = property;
        }

        public String getOperator() {
            return operator;
        }

        public void setOperator(String operator) {
            this.operator = operator;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public java.util.List<Condition> getConditions() {
            return conditions;
        }

        public void setConditions(java.util.List<Condition> conditions) {
            this.conditions = conditions;
        }

        public void addCondition(Condition condition) {
            conditions.add(condition);
        }

        public boolean isList() {
            return list;
        }

        public void setList(boolean list) {
            this.list = list;
        }

        public void setConditionId(String id) {
            this.conditionId = id;
        }

        public String getConditionId() {
            return conditionId;
        }

        public void addFormat(Format format) {
            formats.add(format);
        }

        public java.util.List<Format> getFormats() {
            return formats;
        }

        public void setFormats(java.util.List<Format> formats) {
            this.formats = formats;
        }
    }

    public class Param implements IFormat, ICondition {
        private String conditionId;

        private String key;

        private String value;

        private boolean list;
        
        private boolean sessionAllowNull;

        private boolean keep;

        private java.util.List<Condition> conditions = new ArrayList<Condition>();

        private java.util.List<Format> formats = new ArrayList<Format>();

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public boolean isList() {
            return list;
        }

        public void setList(boolean list) {
            this.list = list;
        }

        public java.util.List<Condition> getConditions() {
            return conditions;
        }

        public void setConditions(java.util.List<Condition> conditions) {
            this.conditions = conditions;
        }

        public void addCondition(Condition condition) {
            conditions.add(condition);
        }

        public void addFormat(Format format) {
            formats.add(format);
        }

        public java.util.List<Format> getFormats() {
            return formats;
        }

        public void setFormats(java.util.List<Format> formats) {
            this.formats = formats;
        }

        public void setConditionId(String id) {
            this.conditionId = id;
        }

        public String getConditionId() {
            return conditionId;
        }

        public boolean isKeep() {
            return keep;
        }

        public void setKeep(boolean keep) {
            this.keep = keep;
        }

        public boolean isSessionAllowNull() {
            return sessionAllowNull;
        }

        public void setSessionAllowNull(boolean sessionAllowNull) {
            this.sessionAllowNull = sessionAllowNull;
        }
    }

    public class Lists {
        private java.util.List<List> lists = new ArrayList<List>();

        public java.util.List<List> getLists() {
            return lists;
        }

        public void setLists(java.util.List<List> lists) {
            this.lists = lists;
        }

        public void addList(List list) {
            lists.add(list);
        }

    }

    public class List implements IActionStep, IExternal, IChecker, ICmb, IExcel, IList {

        private String sql;

        private String source;

        private String targetId;

        private String pageSize;

        private String index;

        private java.util.List<Checker> checks = new ArrayList<Checker>();

        private java.util.List<Param> params = new ArrayList<Param>();

        private java.util.List<Result> results = new ArrayList<Result>();

        private java.util.List<External> externals = new ArrayList<External>();

        private java.util.List<Cmb> cmbs = new ArrayList<Cmb>();

        private boolean resultConstant;

        private boolean resultAppoint;

        private boolean initialize;

        private Excel excel;

        public java.util.List<External> getExternals() {
            return externals;
        }

        public void setExternals(java.util.List<External> externals) {
            this.externals = externals;
        }

        public void addExternal(External external) {
            externals.add(external);
        }

        public String getSql() {
            return sql;
        }

        public void setSql(String sql) {
            this.sql = sql;
        }

        public String getTargetId() {
            return targetId;
        }

        public void setTargetId(String targetId) {
            this.targetId = targetId;
        }

        public String getPageSize() {
            return pageSize;
        }

        public void setPageSize(String pageSize) {
            this.pageSize = pageSize;
        }

        public java.util.List<Result> getResults() {
            return results;
        }

        public void setResults(java.util.List<Result> results) {
            this.results = results;
        }

        public void addResult(Result result) {
            results.add(result);
        }

        public String getIndex() {
            return index;
        }

        public void setIndex(String index) {
            this.index = index;
        }

        public java.util.List<Checker> getChecks() {
            return checks;
        }

        public void setChecks(java.util.List<Checker> checks) {
            this.checks = checks;
        }

        public void addCheck(Checker check) {
            checks.add(check);
        }

        public boolean isResultConstant() {
            return resultConstant;
        }

        public void setResultConstant(boolean flg) {
            resultConstant = flg;
        }

        public java.util.List<Cmb> getCmbs() {
            return cmbs;
        }

        public void setCmbs(java.util.List<Cmb> cmbs) {
            this.cmbs = cmbs;
        }

        public void addCmb(Cmb cmb) {
            cmbs.add(cmb);
        }

        public Excel getExcel() {
            return excel;
        }

        public void setExcel(Excel excel) {
            this.excel = excel;
        }

        public void addParam(Param param) {
            params.add(param);
        }

        public java.util.List<Param> getParams() {
            return params;
        }

        public void setParams(java.util.List<Param> params) {
            this.params = params;
        }

        public boolean isResultAppoint() {
            return resultAppoint;
        }

        public void setResultAppoint(boolean resultAppoint) {
            this.resultAppoint = resultAppoint;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public boolean isInitialize() {
            return initialize;
        }

        public void setInitialize(boolean initialize) {
            this.initialize = initialize;
        }
    }

    public class Dimension implements IActionStep, IList {

        private String sql;

        private String source;

        private String targetId;

        private String pageSize;

        private String index;

        private List list;

        private java.util.List<Param> params = new ArrayList<Param>();

        private java.util.List<Result> results = new ArrayList<Result>();

        private boolean resultConstant;

        private boolean resultAppoint;

        public String getSql() {
            return sql;
        }

        public void setSql(String sql) {
            this.sql = sql;
        }

        public String getTargetId() {
            return targetId;
        }

        public void setTargetId(String targetId) {
            this.targetId = targetId;
        }

        public String getPageSize() {
            return pageSize;
        }

        public void setPageSize(String pageSize) {
            this.pageSize = pageSize;
        }

        public java.util.List<Result> getResults() {
            return results;
        }

        public void setResults(java.util.List<Result> results) {
            this.results = results;
        }

        public void addResult(Result result) {
            results.add(result);
        }

        public String getIndex() {
            return index;
        }

        public void setIndex(String index) {
            this.index = index;
        }

        public List getList() {
            return list;
        }

        public void setList(List list) {
            this.list = list;
        }

        public boolean isResultConstant() {
            return resultConstant;
        }

        public void setResultConstant(boolean flg) {
            resultConstant = flg;
        }

        public void addParam(Param param) {
            params.add(param);
        }

        public java.util.List<Param> getParams() {
            return params;
        }

        public void setParams(java.util.List<Param> params) {
            this.params = params;
        }

        public boolean isResultAppoint() {
            return resultAppoint;
        }

        public void setResultAppoint(boolean resultAppoint) {
            this.resultAppoint = resultAppoint;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }
    }

    public class Taber {
        private String targetId;

        private String message;

        public String getTargetId() {
            return targetId;
        }

        public void setTargetId(String targetId) {
            this.targetId = targetId;
        }

        public String getMessage() {
            if ("NOTHING_INPUT".equals(message)) {
                message = "1001";
            } else if ("INPUT_ERROR".equals(message)) {
                message = "1002";
            } else if ("RANGE_ERROR".equals(message)) {
                message = "1003";
            } else if ("OVER_LENGTH".equals(message)) {
                message = "1004";
            } else if ("INPUT_INVALID".equals(message)) {
                message = "1005";
            } else if ("NUM_ONLY_ERROR".equals(message)) {
                message = "1008";
            } else if ("TIME_DISORDER".equals(message)) {
                message = "1010";
            } else if ("OVERLAP".equals(message)) {
                message = "1011";
            } else if ("NUM_DIS_ORDER".equals(message)) {
                message = "1012";
            }
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    public class Checker implements IActionStep, ICondition, IExternal, IWhether {
        private String conditionId;

        private String sql;

        private String source;

        private String index;

        private String resCode;

        private java.util.List<Param> params = new ArrayList<Param>();

        private java.util.List<Taber> tabs = new ArrayList<Taber>();

        private java.util.List<Condition> conditions = new ArrayList<Condition>();

        private java.util.List<Condition> whethers = new ArrayList<Condition>();

        private java.util.List<External> externals = new ArrayList<External>();

        private boolean resultConstant;

        private boolean resultAppoint;

        public java.util.List<Taber> getTabs() {
            return tabs;
        }

        public void setTabs(java.util.List<Taber> tabs) {
            this.tabs = tabs;
        }

        public String getSql() {
            return sql;
        }

        public void setSql(String sql) {
            this.sql = sql;
        }

        public String getIndex() {
            return index;
        }

        public void setIndex(String index) {
            this.index = index;
        }

        public void addResult(Result result) {
        }

        public void addTab(Taber tab) {
            tabs.add(tab);
        }

        public void addCondition(Condition condition) {
            conditions.add(condition);
        }

        public java.util.List<Condition> getConditions() {
            return conditions;
        }

        public void setConditions(java.util.List<Condition> conditions) {
            this.conditions = conditions;
        }

        public String getResCode() {
            return resCode;
        }

        public void setResCode(String resCode) {
            this.resCode = resCode;
        }

        public java.util.List<External> getExternals() {
            return externals;
        }

        public void setExternals(java.util.List<External> externals) {
            this.externals = externals;
        }

        public void addExternal(External external) {
            externals.add(external);
        }

        public void addWhethers(Condition condition) {
            whethers.add(condition);
        }

        public java.util.List<Condition> getWhethers() {
            return whethers;
        }

        public void setWhethers(java.util.List<Condition> whethers) {
            this.whethers = whethers;
        }

        public boolean isResultConstant() {
            return resultConstant;
        }

        public void setResultConstant(boolean flg) {
            resultConstant = flg;
        }

        public void addParam(Param param) {
            params.add(param);
        }

        public java.util.List<Param> getParams() {
            return params;
        }

        public void setParams(java.util.List<Param> params) {
            this.params = params;
        }

        public void setConditionId(String id) {
            this.conditionId = id;
        }

        public String getConditionId() {
            return conditionId;
        }

        public boolean isResultAppoint() {
            return resultAppoint;
        }

        public void setResultAppoint(boolean resultAppoint) {
            this.resultAppoint = resultAppoint;
        }

        public java.util.List<Result> getResults() {
            return null;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }
    }

    public class Cmb {

        private String sql;

        private String source;

        private String targetId;

        private String value;

        private String label;

        private int count;

        private int start;

        private int end;

        private String id;

        private String key;

        private java.util.List<TableRowMap> options = new ArrayList<TableRowMap>();

        public String getSql() {
            return sql;
        }

        public void setSql(String sql) {
            this.sql = sql;
        }

        public String getTargetId() {
            return targetId;
        }

        public void setTargetId(String targetId) {
            this.targetId = targetId;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getStart() {
            return start;
        }

        public void setStart(int start) {
            this.start = start;
        }

        public int getEnd() {
            return end;
        }

        public void setEnd(int end) {
            this.end = end;
        }

        public java.util.List<TableRowMap> getOptions() {
            return options;
        }

        public void setOptions(java.util.List<TableRowMap> options) {
            this.options = options;
        }

        public void addOptions(TableRowMap option) {
            options.add(option);
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        /**
         * @Description: 取得对应下拉框二级联动二级菜单查询条件的KEY,如果有多个条件KEY用空格分隔
         * @author king
         * @since Dec 13, 2012 10:29:38 AM
         * @version V1.0
         * @return 对应下拉框二级联动二级菜单查询条件的KEY,如果有多个条件KEY用空格分隔
         */
        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }
    }

    public class Excel {

        private String inName;

        private String inPath;

        private String outName;

        private String outPath;

        private boolean inAbsolute;

        private boolean outAbsolute;

        private String type;

        // <excel>标签设置的excel文件类型
        private String fileType;

        // <excel>标签设置<sheet>信息数组
        private java.util.List<Sheet> sheets = new ArrayList<Sheet>();

        public java.util.List<Sheet> getSheets() {
            return sheets;
        }

        public void setSheets(java.util.List<Sheet> sheets) {
            this.sheets = sheets;
        }

        public void addSheets(Sheet sheet) {
            sheets.add(sheet);
        }

        public String getInName() {
            return inName;
        }

        public void setInName(String name) {
            this.inName = name;
        }

        public String getInPath() {
            return inPath;
        }

        public void setInPath(String path) {
            this.inPath = path;
        }

        public boolean isInAbsolute() {
            return inAbsolute;
        }

        public void setInAbsolute(boolean absolute) {
            this.inAbsolute = absolute;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getOutName() {
            return outName;
        }

        public void setOutName(String outName) {
            this.outName = outName;
        }

        public String getOutPath() {
            return outPath;
        }

        public void setOutPath(String outPath) {
            this.outPath = outPath;
        }

        public boolean isOutAbsolute() {
            return outAbsolute;
        }

        public void setOutAbsolute(boolean outAbsolute) {
            this.outAbsolute = outAbsolute;
        }

        public String getFileType() {
            return fileType;
        }

        public void setFileType(String fileType) {
            this.fileType = fileType;
        }
    }

    public class Sheet {

        private String name;

        // <sheet>标签设置<row>信息数组
        private java.util.List<Row> rows = new ArrayList<Row>();

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public java.util.List<Row> getRows() {
            return rows;
        }

        public void setRows(java.util.List<Row> rows) {
            this.rows = rows;
        }

        public void addRows(Row row) {
            rows.add(row);
        }
    }

    public class Row {
        private int site;

        private float height;

        private String targetId;

        private String className;

        private int start;

        private int end;

        private boolean alternate;

        private String oddColor;

        private String evenColor;

        private java.util.List<Cell> cells = new ArrayList<Cell>();

        public int getSite() {
            return site;
        }

        public void setSite(int site) {
            this.site = site;
        }

        public java.util.List<Cell> getCells() {
            return cells;
        }

        public void setCells(java.util.List<Cell> cells) {
            this.cells = cells;
        }

        public void addCell(Cell cell) {
            cells.add(cell);
        }

        public String getTargetId() {
            return targetId;
        }

        public void setTargetId(String targetId) {
            this.targetId = targetId;
        }

        public float getHeight() {
            return height;
        }

        public void setHeight(float height) {
            this.height = height;
        }

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }

        public int getStart() {
            return start;
        }

        public void setStart(int start) {
            this.start = start;
        }

        public int getEnd() {
            return end;
        }

        public void setEnd(int end) {
            this.end = end;
        }

        public boolean isAlternate() {
            return alternate;
        }

        public void setAlternate(boolean alternate) {
            this.alternate = alternate;
        }

        public String getOddColor() {
            return oddColor;
        }

        public void setOddColor(String oddColor) {
            this.oddColor = oddColor;
        }

        public String getEvenColor() {
            return evenColor;
        }

        public void setEvenColor(String evenColor) {
            this.evenColor = evenColor;
        }
    }

    public class Cell implements ICondition {

        private CellStyle style;

        private int site;

        private int width;

        private String type;

        private String value;

        private String targetId;

        private String className;

        private String conditionId;

        private java.util.List<Condition> conditions = new ArrayList<Condition>();

        public CellStyle getStyle() {
            return style;
        }

        public void setStyle(CellStyle style) {
            this.style = style;
        }

        public int getSite() {
            return site;
        }

        public void setSite(int site) {
            this.site = site;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getTargetId() {
            return targetId;
        }

        public void setTargetId(String targetId) {
            this.targetId = targetId;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }

        public void addCondition(Condition condition) {
            conditions.add(condition);
        }

        public java.util.List<Condition> getConditions() {
            return conditions;
        }

        public void setConditions(java.util.List<Condition> conditions) {
            this.conditions = conditions;
        }

        public void setConditionId(String id) {
            this.conditionId = id;
        }

        public String getConditionId() {
            return conditionId;
        }

    }

    public class CellStyle {

        private CellBorder border;

        private CellFont font;

        private CellDataFormat format;

        private CellLayout layout;

        private CellGround ground;

        private String id;

        public CellBorder getBorder() {
            return border;
        }

        public void setBorder(CellBorder border) {
            this.border = border;
        }

        public CellFont getFont() {
            return font;
        }

        public void setFont(CellFont font) {
            this.font = font;
        }

        public CellDataFormat getFormat() {
            return format;
        }

        public void setFormat(CellDataFormat format) {
            this.format = format;
        }

        public CellLayout getLayout() {
            return layout;
        }

        public void setLayout(CellLayout layout) {
            this.layout = layout;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public CellGround getGround() {
            return ground;
        }

        public void setGround(CellGround ground) {
            this.ground = ground;
        }

    }

    public class CellLayout {
        private String align;

        private String vertical;

        public String getAlign() {
            return align;
        }

        public void setAlign(String align) {
            this.align = align;
        }

        public String getVertical() {
            return vertical;
        }

        public void setVertical(String vertical) {
            this.vertical = vertical;
        }
    }

    public class CellGround {
        private String foreColor;

        private String backColor;

        private String fill;

        public String getForeColor() {
            return foreColor;
        }

        public void setForeColor(String foreColor) {
            this.foreColor = foreColor;
        }

        public String getBackColor() {
            return backColor;
        }

        public void setBackColor(String backColor) {
            this.backColor = backColor;
        }

        public String getFill() {
            return fill;
        }

        public void setFill(String fill) {
            this.fill = fill;
        }
    }

    public class CellDataFormat {
        private String format;

        private String sourceFormat;

        public String getFormat() {
            return format;
        }

        public void setFormat(String format) {
            this.format = format;
        }

        public String getSourceFormat() {
            return sourceFormat;
        }

        public void setSourceFormat(String sourceFormat) {
            this.sourceFormat = sourceFormat;
        }
    }

    public class CellBorder {

        private String wholeStyle;

        private String wholeColor;

        private String leftStyle;

        private String leftColor;

        private String rightStyle;

        private String rightColor;

        private String topStyle;

        private String topColor;

        private String bottomStyle;

        private String bottomColor;

        public String getWholeStyle() {
            return wholeStyle;
        }

        public void setWholeStyle(String wholeStyle) {
            this.wholeStyle = wholeStyle;
        }

        public String getWholeColor() {
            return wholeColor;
        }

        public void setWholeColor(String wholeColor) {
            this.wholeColor = wholeColor;
        }

        public String getLeftStyle() {
            return leftStyle;
        }

        public void setLeftStyle(String leftStyle) {
            this.leftStyle = leftStyle;
        }

        public String getLeftColor() {
            return leftColor;
        }

        public void setLeftColor(String leftColor) {
            this.leftColor = leftColor;
        }

        public String getRightStyle() {
            return rightStyle;
        }

        public void setRightStyle(String rightStyle) {
            this.rightStyle = rightStyle;
        }

        public String getRightColor() {
            return rightColor;
        }

        public void setRightColor(String rightColor) {
            this.rightColor = rightColor;
        }

        public String getTopStyle() {
            return topStyle;
        }

        public void setTopStyle(String topStyle) {
            this.topStyle = topStyle;
        }

        public String getTopColor() {
            return topColor;
        }

        public void setTopColor(String topColor) {
            this.topColor = topColor;
        }

        public String getBottomStyle() {
            return bottomStyle;
        }

        public void setBottomStyle(String bottomStyle) {
            this.bottomStyle = bottomStyle;
        }

        public String getBottomColor() {
            return bottomColor;
        }

        public void setBottomColor(String bottomColor) {
            this.bottomColor = bottomColor;
        }

    }

    public class CellFont {

        private String name;

        private String bold;

        private String color;

        private boolean italic;

        private boolean strikeout;

        private int fontHeight;

        private String typeOffset;

        private String underline;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getBold() {
            return bold;
        }

        public void setBold(String bold) {
            this.bold = bold;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public boolean isItalic() {
            return italic;
        }

        public void setItalic(boolean italic) {
            this.italic = italic;
        }

        public boolean isStrikeout() {
            return strikeout;
        }

        public void setStrikeout(boolean strikeout) {
            this.strikeout = strikeout;
        }

        public int getFontHeight() {
            return fontHeight;
        }

        public void setFontHeight(int fontHeight) {
            this.fontHeight = fontHeight;
        }

        public String getTypeOffset() {
            return typeOffset;
        }

        public void setTypeOffset(String typeOffset) {
            this.typeOffset = typeOffset;
        }

        public String getUnderline() {
            return underline;
        }

        public void setUnderline(String underline) {
            this.underline = underline;
        }

    }

    public RequestParam() {
    }

    public String getDispCode() {
        return dispCode;
    }

    public void setDispCode(String dispCode) {
        this.dispCode = dispCode;
    }

    public String getHandlerClass() {
        return handlerClass;
    }

    public void setHandlerClass(String handlerClass) {
        this.handlerClass = handlerClass;
    }

    public String getMethodCode() {
        return methodCode;
    }

    public void setMethodCode(String methodCode) {
        this.methodCode = methodCode;
    }

    public String getLogout() {
        return logout;
    }

    public void setLogout(String logout) {
        this.logout = logout;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getForward() {
        return forward;
    }

    public void setForward(String forward) {
        this.forward = forward;
    }

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }

    public String getJspName() {
        return jspName;
    }

    public void setJspName(String jspName) {
        this.jspName = jspName;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public List getList() {
        return list;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public java.util.List<Cmb> getCmbs() {
        return cmbs;
    }

    public void setCmbs(java.util.List<Cmb> cmbs) {
        this.cmbs = cmbs;
    }

    public void addCmb(Cmb cmb) {
        cmbs.add(cmb);
        cmbMaps.put(cmb.getTargetId(), cmb);
    }

    public Items getItems() {
        return items;
    }
    
    public Mails getMails() {
        return mails;
    }

    public Method getMethod() {
        if (method == null) {
            method = new Method();
        }
        return method;
    }

    public boolean hasMethod() {
        return method != null;
    }

    public boolean hasList() {
        return list != null;
    }

    public boolean hasComplex() {
        return complex != null;
    }

    public boolean hasDimension() {
        return dimension != null;
    }

    public boolean hasItem() {
        return items != null;
    }
    
    public boolean hasMail() {
        return mails != null;
    }

    public boolean hasUpdates() {
        return updates != null;
    }

    public boolean hasUpload() {
        return upload != null;
    }

    public Updates getUpdates() {
        if (updates == null) {
            updates = new Updates();
        }
        return updates;
    }

    public Upload getUpload() {
        return upload;
    }

    public String getInitJspName() {
        return initJspName;
    }

    public void setInitJspName(String initJspName) {
        this.initJspName = initJspName;
    }

    public Map<String, Cmb> getCmbMaps() {
        return cmbMaps;
    }

    public java.util.List<Style> getStyles() {
        return styles;
    }

    public void addStyle(Style style) {
        styles.add(style);
        stylesMap.put(style.getProperty(), style);
    }

    public Map<String, Style> getStylesMap() {
        return stylesMap;
    }

    public void setList(List list) {
        this.list = list;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    public void setItems(Items items) {
        this.items = items;
    }
    
    public void setMails(Mails mails) {
        this.mails = mails;
    }

    public Complex getComplex() {
        return complex;
    }

    public void setComplex(Complex complex) {
        this.complex = complex;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public java.util.List<CellStyle> getCellStyles() {
        return cellStyles;
    }

    public void setCellStyles(java.util.List<CellStyle> cellStyles) {
        this.cellStyles = cellStyles;
    }

    public void addCellStyle(CellStyle cellStyle) {
        cellStyles.add(cellStyle);
        cellStylesMap.put(cellStyle.getId(), cellStyle);
    }

    public Map<String, CellStyle> getCellStylesMap() {
        return cellStylesMap;
    }

    public String getCheckIndex() {
        return checkIndex;
    }

    public void setCheckIndex(String checkIndex) {
        this.checkIndex = checkIndex;
    }

    public Servlet getServlet() {
        return servlet;
    }

    public void setServlet(Servlet servlet) {
        this.servlet = servlet;
    }

    public boolean hasServlet() {
        return servlet != null;
    }

    public boolean isInputStream() {
        return inputStream;
    }

    public void setInputStream(boolean inputStream) {
        this.inputStream = inputStream;
    }

    public Excel getExcel() {
        return excel;
    }

    public void setExcel(Excel excel) {
        this.excel = excel;
    }

    public void setUpload(Upload upload) {
        this.upload = upload;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

}
