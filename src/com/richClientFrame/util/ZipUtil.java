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
 * zipѹ���ͽ�ѹ������.
 * 
 * @author King
 * @since 2012.01.17
 */
public final class ZipUtil {
    
    /**
     * ���캯��.
     */
    private ZipUtil() {
        
    }

    /**
     * ѹ���ַ���Ϊ byte[] �������ʹ��new sun.misc.BASE64Encoder().encodeBuffer(byte[] b)����.
     * 
     * @param str ѹ��ǰ���ı�
     * @return ѹ������ַ���
     * @throws RichClientWebException RichClientWebException
     */
    public static String compressStr(String str) throws RichClientWebException {
        if (CommonUtil.isEmpty(str)) {
            return null;
        }
        // Ϊ�˱����ѹ��֮���������룬ѹ��ǰ���Ľ���ת��,�ַ���ת�������ʽ
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
     * ��ѹ����� byte[] ���ݽ�ѹ��.
     * 
     * @param str ѹ���������
     * @return ��ѹ����ַ���
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
        
        // ��ѹ��֮��ԭ����
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
     * ���Է���.
     * @param args args
     * @throws Exception Exception
     */
    public static void main(String[] args) throws Exception {
        final String str = "�����Ѽӿ�ݷѹ��ͻ����������ָ���Ӱ�������ͷ����Ƿ񰢿����ݻָ�skf�Ͽδ��㷵�������ļ��ʹ���";
        System.out.println("ѹ��ǰת�봮��С��" + str.length());
        System.out.println("ѹ��ǰת�봮Ϊ��" + str);
        final String result = compressStr(str);
        System.out.println("ѹ����ת�봮��С��" + result.length());
        System.out.println("ѹ����ת�봮Ϊ��" + result);
        final String deRes = decompressStr(result);
        System.out.println(deRes);
    }

}
