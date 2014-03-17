
package com.richClientFrame.util;

import com.richClientFrame.exception.RichClientWebException;
import com.richClientFrame.exception.data.EngineExceptionEnum;
import com.richClientFrame.info.ControlConfig;

import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTPClient;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletResponse;

/**
 * �ļ��Ĺ�ͨ������.
 * 
 * @author King
 * @since 2011.07.22
 */
public final class FileUtil {
    
    public static final Object LOCK = new Object();
    
    private static final long MAX_SIZE = 10000;
    
    private static long sSequenceNo = 1;
    
    private static LogUtil sLog = new LogUtil(FileUtil.class, true);

    /**
     * ���캯��.
     */
    private FileUtil() {
    }

    /**
     * �����ļ�.
     * 
     * @param filePath �ļ�·��
     * @return �ļ�
     * @throws RichClientWebException RichClientWebException
     * @throws UnsupportedEncodingException 
     */
    public static File createFile(String filePath) 
        throws RichClientWebException {
        final String path = getFileInfo(filePath)[0];
        final String name = getFileInfo(filePath)[1];
        return createFile(path, name);

    }
    
    /**
     * �����ļ�.
     * 
     * @param path �ļ�·��
     * @param fileName �ļ���
     * @return �ļ�
     * @throws RichClientWebException RichClientWebException
     */
    public static File createFile(String path, String fileName) 
        throws RichClientWebException {
        final File file = new File(path, fileName);
        try {
            createFolder(path);
            
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RichClientWebException(EngineExceptionEnum.FM_COM_IO_ERROR, e);
        }
        return file;
    }
    
    /**
     * �����ļ���.
     * 
     * @param path �ļ���·��
     * @return �ļ���
     */
    public static File createFolder(String path) {
        final File folder = new File(path);
        
        if (!folder.exists()) {
            folder.mkdirs();
        }
        return folder;
    }
    
    /**
     * ɾ���ļ�.
     * 
     * @param path �ļ�·��
     */
    public static void deleteFile(String path) {
        if (CommonUtil.isNotEmpty(path)) {
            final File file = new File(path);
            
            if (file.exists()) {
                file.delete();
            }
        }
    }
    
    /**
     * �ļ���Ϣ��ȡ.
     * 
     * @param path �ļ�·��
     * @return �ļ���Ϣ
     */
    public static List<String> getFileData(String path) {

        String str = ConstantsUtil.Str.EMPTY;

        final List<String> list1 = new ArrayList<String>();

        final File f = new File(path);

        BufferedReader br = null;

        try {
            if (f.exists()) {

                br = new BufferedReader(new InputStreamReader(new FileInputStream(f), "UTF-8"));

                while ((str = br.readLine()) != null) {
                    if (!ConstantsUtil.Str.EMPTY.equals(str)) {
                        list1.add(str);
                    }
                }
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                return null;
            }
        }

        return list1;
    }
    
    /**
     * �ļ���Ϣ��ȡ.
     * 
     * @param path �ļ�·��
     * @return �ļ���Ϣ
     */
    public static String getFileDataString(String path) {
        return getFileDataString(path, "utf-8");
    }
    
    /**
     * �ļ���Ϣ��ȡ.
     * 
     * @param path �ļ�·��
     * @param encode �����ʽ
     * @return �ļ���Ϣ
     */
    public static String getFileDataString(String path, String encode) {
        
        String str = ConstantsUtil.Str.EMPTY;
        
        final StringBuffer dataStr = new StringBuffer();
        
        final File f = new File(path);
        
        BufferedReader br = null;
        
        try {
            if (f.exists()) {
                
                br = new BufferedReader(new InputStreamReader(new FileInputStream(f), encode));
                
                while ((str = br.readLine()) != null) {
                    if (!ConstantsUtil.Str.EMPTY.equals(str)) {
                        dataStr.append(str);
                    }
                }
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                return null;
            }
        }
        
        return dataStr.toString();
    }
    
    /**
     * �ļ���Ϣд��.
     * 
     * @param path �ļ�·��
     * @param fileName �ļ���
     * @param data ��Ϣ
     * @throws RichClientWebException RichClientWebException
     */
    public static void writeFileData(String path, String fileName, String data) 
        throws RichClientWebException {

        try {

            final File file = createFile(path, fileName);

            final BufferedWriter output = new BufferedWriter(new FileWriter(file));
            output.write(data);
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RichClientWebException(EngineExceptionEnum.FM_COM_SYSTEM_ERROR);
        }
    }
    
    /**
     * ͨ��FTP�ϴ��ļ�.
     * 
     * @param fis �ļ���
     * @param fileName �ļ���
     * @throws RichClientWebException RichClientWebException
     */
    public static void ftpUpload(InputStream fis, String fileName) throws RichClientWebException {
        final FTPClient ftpClient = new FTPClient();

        try {
            ftpClient.connect(ControlConfig.getInstance().getConfiguration().getFtpUrl());
            ftpClient.login(ControlConfig.getInstance().getConfiguration().getFtpUser(), 
                    ControlConfig.getInstance().getConfiguration().getFtpPassword());

            // �����ϴ�Ŀ¼
            ftpClient.changeWorkingDirectory(ControlConfig.getInstance().getConfiguration().getFtpPath());
            ftpClient.setBufferSize(ControlConfig.getInstance().getConfiguration().getFtpSize());
            ftpClient.setControlEncoding(ControlConfig.getInstance().getConfiguration().getFtpEncoding());
            // �����ļ����ͣ������ƣ�
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            final boolean result = ftpClient.storeFile("temp", fis);
            if (result) {
                ftpClient.rename("temp", new String(fileName.getBytes("UTF-8"), "iso-8859-1"));
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RichClientWebException(EngineExceptionEnum.FM_COM_IO_ERROR, e);
        } finally {
            IOUtils.closeQuietly(fis);
            try {
                ftpClient.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
                throw new RichClientWebException(EngineExceptionEnum.FM_COM_IO_ERROR, e);
            }
        }
    }
    
    /**
     * ͨ��FTP�����ļ�.
     * 
     * @param ftpFileName �������ļ���
     * @param filePath �����ļ�·��
     * @throws RichClientWebException 
     * @throws RichClientWebException RichClientWebException
     */
    public static void ftpDownload(String ftpFileName, String filePath) throws RichClientWebException {
        final FTPClient ftpClient = new FTPClient();
        FileOutputStream fos = null;

        try {
            ftpClient.connect(ControlConfig.getInstance().getConfiguration().getFtpUrl());
            ftpClient.login(ControlConfig.getInstance().getConfiguration().getFtpUser(), 
                    ControlConfig.getInstance().getConfiguration().getFtpPassword());

            final String remoteFileName = ControlConfig.getInstance().getConfiguration().getFtpPath() + ftpFileName;
            fos = new FileOutputStream(filePath);

            ftpClient.setBufferSize(ControlConfig.getInstance().getConfiguration().getFtpSize());
            // �����ļ����ͣ������ƣ�
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftpClient.retrieveFile(remoteFileName, fos);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RichClientWebException(EngineExceptionEnum.FM_COM_IO_ERROR, e);
        } finally {
            IOUtils.closeQuietly(fos);
            try {
                ftpClient.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
                throw new RichClientWebException(EngineExceptionEnum.FM_COM_IO_ERROR, e);
            }
        }
    }
    
    /**
     * ȡ��Ӳ����Ϣ.
     * 
     * @param drive ��·��
     * @return Ӳ����Ϣ
     */
    public static String getSerialNumber(String drive) {
        String result = ConstantsUtil.Str.EMPTY;
        try {
            final File file = File.createTempFile("realhowto", ".vbs");
            file.deleteOnExit();
            final FileWriter fw = new java.io.FileWriter(file);

            final String vbs = "Set objFSO = CreateObject(\"Scripting.FileSystemObject\")\n"
                    + "Set colDrives = objFSO.Drives\n" + "Set objDrive = colDrives.item(\""
                    + drive + "\")\n" + "Wscript.Echo objDrive.SerialNumber";
            fw.write(vbs);
            fw.close();
            final Process p = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());
            final BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = input.readLine()) != null) {
                result += line;
            }
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.trim();
    }
    
    /** 
    * @Description: �����ļ�����[����������к�]
    * @author king
    * @since Oct 4, 2012 11:58:33 AM 
    * 
    * @param fileName �ļ���
    * @return �����������е��ļ���
    */
    public static String createFileId(String fileName) {
        int current = 10;
        synchronized (LOCK) {
            if (sSequenceNo > MAX_SIZE) {
                sSequenceNo = 10;
            }
            current += sSequenceNo++;
        }
        return fileName + current;
    }
    
    /**
     * @Description: ���ļ���������ѹ���ļ�������
     * @author king
     * @since Dec 2, 2012 2:02:56 PM 
     * @version V1.0
     * @param files �ļ�����
     * @param zipPath ѹ���ļ�·��
     * @throws RichClientWebException RichClientWebException
     */
    public static void makeFilesToZipForCreate(File[] files, String zipPath)
        throws RichClientWebException {
        ZipOutputStream zipOutputStream = null;
        try {
            createFile(zipPath);
            zipOutputStream = new ZipOutputStream(new FileOutputStream(zipPath));
            makeFilesToZip(zipOutputStream, files);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            zipOutputStreamClosed(zipOutputStream);
        }
    }
    
    /**
     * @Description: ���ļ���������ѹ���ļ����û�����
     * @author king
     * @since Dec 2, 2012 2:02:56 PM 
     * @version V1.0
     * @param response ��������Ӧ����
     * @param files �ļ�����
     * @param zipPath ѹ���ļ�·��
     * @throws RichClientWebException RichClientWebException
     */
    public static void makeFilesToZipForDownload(HttpServletResponse response, File[] files, String zipPath) 
        throws RichClientWebException {
        ZipOutputStream zipOutputStream = null;
        try {
            createFile(zipPath);
            zipOutputStream = new ZipOutputStream(new FileOutputStream(zipPath));
            makeFilesToZip(zipOutputStream, files);
            zipOutputStreamClosed(zipOutputStream);
            downloadFile(zipPath, response);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RichClientWebException(EngineExceptionEnum.FM_COM_FILE_NOT_FOUND, e);
        }
        
    }

    /**
     * @Description: �ر�ZipOutputStream
     * @author king
     * @since Dec 2, 2012 1:56:02 PM 
     * @version V1.0
     * @param zipOutputStream ѹ���ļ�������
     * @throws RichClientWebException RichClientWebException
     */
    private static void zipOutputStreamClosed(ZipOutputStream zipOutputStream)
        throws RichClientWebException {
        try {
            if (zipOutputStream != null) {
                zipOutputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RichClientWebException(EngineExceptionEnum.FM_COM_IO_ERROR, e);
        }
    }

    /**
     * @Description: ���ļ���������ѹ���ļ�
     * @author king
     * @since Dec 2, 2012 2:01:03 PM 
     * @version V1.0
     * @param zos ѹ���ļ���
     * @param files �ļ�����
     * @throws RichClientWebException RichClientWebException
     */
    public static void makeFilesToZip(ZipOutputStream zos, File[] files) throws RichClientWebException {
        for (File f : files) {
            if (f.isDirectory()) {
                makeFilesToZip(zos, f.listFiles());
            } else {
                makeFilesToZip(zos, f);
            }
        }
    }

    /**
     * @Description: ���ļ�����ѹ���ļ�
     * @author king
     * @since Dec 2, 2012 2:01:03 PM 
     * @version V1.0
     * @param zos ѹ���ļ���
     * @param file �ļ�
     * @throws RichClientWebException RichClientWebException
     */
    private static void makeFilesToZip(ZipOutputStream zos, File file) throws RichClientWebException {
        InputStream is = null;
        try {
            final ZipEntry ze = new ZipEntry(file.getName());
            zos.putNextEntry(ze);
            is = new BufferedInputStream(new FileInputStream(file));
            final byte[] buf = new byte[1024];
            for (;;) {
                final int len = is.read(buf);
                if (len < 0) {
                    break;
                }
                zos.write(buf, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RichClientWebException(EngineExceptionEnum.FM_COM_IO_ERROR, e);
        } finally {
            inputStreamClosed(is);
            file.delete();
        }
    }

    /**
     * @Description: �ر�InputStream
     * @author king
     * @since Dec 2, 2012 1:57:04 PM 
     * @version V1.0
     * @param is ������
     * @throws RichClientWebException RichClientWebException
     */
    private static void inputStreamClosed(InputStream is) throws RichClientWebException {
        try {
            if (is != null) {
                is.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RichClientWebException(EngineExceptionEnum.FM_COM_IO_ERROR, e);
        }
    }

    /**
     * @Description: �ļ����ع��ܣ����ļ�д����������Ӧ���������û�����
     * @author king
     * @since Dec 2, 2012 1:17:36 PM 
     * @version V1.0
     * @param filePath �ļ�·��
     * @param response ��������Ӧ����
     * @throws RichClientWebException RichClientWebException
     */
    public static void downloadFile(String filePath, HttpServletResponse response) 
        throws RichClientWebException {
        File file = null;
        FileInputStream fs = null;
        PrintWriter out = null;
        try {
            final String fileName = getFileInfo(filePath)[1];
            file = new File(filePath);
            fs = new FileInputStream(file);
            response.setContentType("text/plain");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
            int b = 0;
            out = response.getWriter();
            while ((b = fs.read()) != -1) {
                out.write(b);
            }
            sLog.info("downloadFile", "end", "�ļ��������.");
        } catch (Exception e) {
            e.printStackTrace();
            sLog.info("downloadFile", "end", "�����ļ�ʧ��!");
            throw new RichClientWebException(EngineExceptionEnum.FM_COM_SYSTEM_ERROR, e);
        } finally {
            fileInputStreamClosed(fs);
            if (out != null) {
                out.close();
            }
            if (file != null && file.exists()) {
                file.delete();
            }
        }
    }

    /**
     * @Description: �ر�FileInputStream
     * @author king
     * @since Dec 2, 2012 1:58:49 PM 
     * @version V1.0
     * @param fs �ļ�������
     * @throws RichClientWebException RichClientWebException
     */
    private static void fileInputStreamClosed(FileInputStream fs) throws RichClientWebException {
        try {
            if (fs != null) {
                fs.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RichClientWebException(EngineExceptionEnum.FM_COM_IO_ERROR, e);
        }
    }

    /**
     * @Description: �����ļ�·������ļ����ƺ�·��
     * @author king
     * @since Dec 2, 2012 1:18:45 PM 
     * @version V1.0
     * @param filePath �ļ�·��
     * @return ���ƺ�·��
     * @throws RichClientWebException RichClientWebException
     */
    private static String[] getFileInfo(String filePath) throws RichClientWebException {
        String path = ConstantsUtil.Str.EMPTY;
        String fileName = ConstantsUtil.Str.EMPTY;
        try {
            if (filePath.lastIndexOf(File.separator) > 0) {
                fileName = new String(filePath.substring(filePath.lastIndexOf(File.separator) + 1,
                        filePath.length()).getBytes("GB2312"), "ISO8859_1");
                path = filePath.substring(0, filePath.lastIndexOf(File.separator));
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RichClientWebException(EngineExceptionEnum.FM_COM_UNSUPPORTED_ENCODING_ERROR, e);
        }
        return new String[] {path, fileName};
    }


}
