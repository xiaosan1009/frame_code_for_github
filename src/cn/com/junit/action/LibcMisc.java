// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   LibcMisc.java

package cn.com.junit.action;


public class LibcMisc
{

	public LibcMisc()
	{
	}

	public static void memcpy(byte dst[], int offset, int src, int len)
	{
		byte byte_src[] = new byte[4];
		byte_src[3] = (byte)((src & 0xff000000) >> 24);
		byte_src[2] = (byte)((src & 0xff0000) >> 16);
		byte_src[1] = (byte)((src & 0xff00) >> 8);
		byte_src[0] = (byte)(src & 0xff);
		System.arraycopy(byte_src, 0, dst, offset, len);
	}

	public static void memcpy(byte dst[], int offset, short src, int len)
	{
		byte byte_src[] = new byte[4];
		byte_src[0] = (byte)(src & 0xff);
		byte_src[1] = (byte)((src & 0xff00) >> 8);
		System.arraycopy(byte_src, 0, dst, offset, len);
	}

	public static void memcpy(byte dst[], int src, int len)
	{
		memcpy(dst, 0, src, len);
	}

	public static void memcpy(byte src[], int srcpos, byte dst[], int dstpos, int len)
	{
		for (int i = 0; i < len; i++)
			src[srcpos + i] = dst[dstpos + i];

	}

	public static short get_short(byte ary[], int offset)
	{
		short value = (short)(ary[offset] & 0xff | ary[offset + 1] << 8 & 0xff00);
		return value;
	}

	public static short[] get_short_array(byte ary[], int offset)
	{
		short value[] = new short[ary.length - offset >> 1];
		for (int i = 0; i < ary.length - offset; i += 2)
			value[i >> 1] = (short)(ary[offset + i] & 0xff | ary[offset + i + 1] << 8 & 0xff00);

		return value;
	}

	public static int get_int(byte ary[], int offset)
	{
		int value = ary[offset] & 0xff | ary[offset + 1] << 8 & 0xff00 | ary[offset + 2] << 16 & 0xff0000 | ary[offset + 3] << 24 & 0xff000000;
		return value;
	}

	public static int memcmp(byte src[], int srcpos, byte dst[], int dstpos, int len)
	{
		for (int i = 0; i < len; i++)
		{
			if (src[srcpos + i] < dst[dstpos + i])
				return -1;
			if (src[srcpos + i] != dst[dstpos + i])
				return 1;
		}

		return 0;
	}

	public static void memset(byte content[], int j, int len)
	{
		for (int i = 0; i < len; i++)
			content[i] = (byte)j;

	}

	public static String array2string(byte data[], int offset, int len)
	{
		String out = new String();
		for (int i = offset; i < offset + len; i++)
			out = (new StringBuilder(String.valueOf(out))).append(String.format("%02x", new Object[] {
				Byte.valueOf(data[i])
			})).toString();

		return out;
	}
}
