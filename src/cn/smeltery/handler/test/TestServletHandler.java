package cn.smeltery.handler.test;

import com.richClientFrame.exception.RichClientWebException;
import com.richClientFrame.handler.AbstractHandler;
import com.richClientFrame.util.CommonUtil;
import com.richClientFrame.util.DateUtils;
import com.richClientFrame.util.FileUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

public class TestServletHandler extends AbstractHandler {
    
    public boolean uploadimag() 
        throws RichClientWebException {
        final String filePath = CommonUtil.getUploadRealPath() + File.separator;
        final String fileName = FileUtil.createFileId(DateUtils.getNowTime(DateUtils.FORMAT_YYYYMMDDHHSS3)) + ".jpg";
        try {
            final InputStream inputStream = getRequest().getInputStream();
            final FileOutputStream outputStream = new FileOutputStream(new File(filePath + fileName));
            
            final int formlength = getRequest().getContentLength();
            final byte[] formcontent = new byte[formlength];
            int totalread = 0;
            int nowread = 0;
            while (totalread < formlength) {
                nowread = inputStream.read(formcontent, totalread, formlength);
                totalread += nowread;
            }
            
            outputStream.write(formcontent);
            outputStream.flush();
            outputStream.close();
            inputStream.close();
            final PrintWriter out = getResponse().getWriter();
            out.print(fileName);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;

    }

}
