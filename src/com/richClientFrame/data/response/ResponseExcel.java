package com.richClientFrame.data.response;

import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.util.List;

/**
 * 项目名称 ： Web2.0开发引擎.
 * 类名称 ： ResponseExcel
 * 类描述 ： 服务器响应excel信息的保持类
 * 创建人 ： 金雷
 * 联系方式 ： xiaosan9528@163.com
 * QQ ： 26981791
 * 创建时间 ： 2010.03.19
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
