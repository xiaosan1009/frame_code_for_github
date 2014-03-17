package com.richClientFrame.data.response;

import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.util.List;

/**
 * ��Ŀ���� �� Web2.0��������.
 * ������ �� ResponseExcel
 * ������ �� ��������Ӧexcel��Ϣ�ı�����
 * ������ �� ����
 * ��ϵ��ʽ �� xiaosan9528@163.com
 * QQ �� 26981791
 * ����ʱ�� �� 2010.03.19
 * @author king
 */
public class ResponseExcel {
    
    private Workbook workbook;
    
    private String title;
    
    private List<File> fileList;

    public Workbook getWorkbook() {
        return workbook;
    }

    public void setWorkbook(Workbook workbook) {
        this.workbook = workbook;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<File> getFileList() {
        return fileList;
    }

    public void setFileList(List<File> fileList) {
        this.fileList = fileList;
    }

}
