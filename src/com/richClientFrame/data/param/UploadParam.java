package com.richClientFrame.data.param;

/**
 * ��Ŀ���� �� Web2.0��������.
 * ������ �� UpdateParam
 * ������ �� ���´������������.
 * ������ �� ����
 * ��ϵ��ʽ �� xiaosan9528@163.com
 * QQ �� 26981791
 * ����ʱ�� �� 2011.09.07
 * @author king
 */
public class UploadParam {
    
    private String realPath;
    
    private String storeFileName;
    
    private String fileName;
    
    private String fileRealName;
    
    private boolean dbResult;
    
    private String url;
    
    private long size;

    public String getRealPath() {
        return realPath;
    }

    public void setRealPath(String path) {
        this.realPath = path;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public boolean isDbResult() {
        return dbResult;
    }

    public void setDbResult(boolean dbResult) {
        this.dbResult = dbResult;
    }

    public String getStoreFileName() {
        return storeFileName;
    }

    public void setStoreFileName(String storeFileName) {
        this.storeFileName = storeFileName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getFileRealName() {
        return fileRealName;
    }

    public void setFileRealName(String fileRealName) {
        this.fileRealName = fileRealName;
    }

}
