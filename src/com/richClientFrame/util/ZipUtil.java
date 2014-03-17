package com.richClientFrame.util;

import com.richClientFrame.exception.RichClientWebException;
import com.richClientFrame.exception.data.EngineExceptionEnum;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * zip压缩和解压缩工具.
 * 
 * @author King
 * @since 2012.01.17
 */
public final class ZipUtil {
    
    /**
     * 构造函数.
     */
    private ZipUtil() {
        
    }

    /**
     * 压缩字符串为 byte[] 储存可以使用new sun.misc.BASE64Encoder().encodeBuffer(byte[] b)方法.
     * 
     * @param str 压缩前的文本
     * @return 压缩后的字符串
     * @throws RichClientWebException RichClientWebException
     */
    public static String compressStr(String str) throws RichClientWebException {
        if (CommonUtil.isEmpty(str)) {
            return null;
        }
        // 为了避免解压缩之后中文乱码，压缩前中文进行转码,字符串转码网络格式
        try {
            str = URLEncoder.encode(str, ConstantsUtil.Frame.ENCODING);
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
            throw new RichClientWebException(EngineExceptionEnum.FM_COM_UNSUPPORTED_ENCODING_ERROR, e1);
        }
        
        byte[] compressed;
        ByteArrayOutputStream out = null;
        ZipOutputStream zout = null;
        try {
            out = new ByteArrayOutputStream();
            zout = new ZipOutputStream(out);
            zout.putNextEntry(new ZipEntry("0"));
            zout.write(str.getBytes());
            zout.closeEntry();
            compressed = out.toByteArray();
        } catch (IOException e) {
            compressed = null;
        } finally {
            if (zout != null) {
                try {
                    zout.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new RichClientWebException(EngineExceptionEnum.FM_COM_IO_ERROR, e);
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new RichClientWebException(EngineExceptionEnum.FM_COM_IO_ERROR, e);
                }
            }
        }
        final String result = (new BASE64Encoder()).encode(compressed);
        
        return result;
    }

    /**
     * 将压缩后的 byte[] 数据解压缩.
     * 
     * @param str 压缩后的数据
     * @return 解压后的字符串
     * @throws RichClientWebException RichClientWebException
     */
    public static String decompressStr(String str) throws RichClientWebException {
        if (CommonUtil.isEmpty(str)) {
            return null;
        }
        byte[] compressed = null;
        try {
            compressed = new BASE64Decoder().decodeBuffer(str);
        } catch (IOException e1) {
            e1.printStackTrace();
            throw new RichClientWebException(EngineExceptionEnum.FM_COM_IO_ERROR, e1);
        }
        ByteArrayOutputStream out = null;
        ByteArrayInputStream in = null;
        ZipInputStream zin = null;
        String decompressed;
        try {
            out = new ByteArrayOutputStream();
            in = new ByteArrayInputStream(compressed);
            zin = new ZipInputStream(in);
            ZipEntry entry = zin.getNextEntry();
            final byte[] buffer = new byte[1024];
            int offset = -1;
            while ((offset = zin.read(buffer)) != -1) {
                out.write(buffer, 0, offset);
            }
            decompressed = out.toString();
        } catch (IOException e) {
            decompressed = null;
            return null;
        } finally {
            try {
                if (zin != null) {
                    zin.close();
                }
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                throw new RichClientWebException(EngineExceptionEnum.FM_COM_IO_ERROR, e);
            }
        }
        
        // 解压缩之后还原中文
        String result = ConstantsUtil.Str.EMPTY;
        try {
            result = URLDecoder.decode(decompressed, ConstantsUtil.Frame.ENCODING);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RichClientWebException(EngineExceptionEnum.FM_COM_UNSUPPORTED_ENCODING_ERROR, e);
        }
        return result;
    }

    /**
     * 测试方法.
     * @param args args
     * @throws Exception Exception
     */
    public static void main(String[] args) throws Exception {
        final String str = "会撒谎加快递费哈客户发简历卡恢复快接啊蝴蝶结客服哈是否阿克数据恢复skf上课大红点返回拉萨的减肥哈克";
        System.out.println("压缩前转码串大小：" + str.length());
        System.out.println("压缩前转码串为：" + str);
        final String result = compressStr(str);
        System.out.println("压缩后转码串大小：" + result.length());
        System.out.println("压缩后转码串为：" + result);
        final String deRes = decompressStr(result);
        System.out.println(deRes);
    }

}
