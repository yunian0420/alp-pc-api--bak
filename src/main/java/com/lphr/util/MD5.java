package com.lphr.util;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


/**
 * md5工具
 * 
 * @author 喻聪
 * @date 2016-06-10
 *
 */
public class MD5 {
	
	public static void main(String[] args) {
		//生成的值：code=1234&phone=15102123715&key=b9b8309ca3a04a7c906c67f6ed24ec9d
		//signValue:c3cb151ab53fe4c2488ba2f06bb8b603
		//String a = getMD5("code=1234&phone=15102123715&key=b9b8309ca3a04a7c906c67f6ed24ec9d");
		//System.out.println(a);
		//System.out.println("c3cb151ab53fe4c2488ba2f06bb8b603");
		
		System.out.println("b9d11b3be25f5a1a7dc8ca04cd310b28");
		System.out.println(getMD5("123456admin"));
		
	}
	
	 /**
     * 计算字符串的MD5值(UTF-8)
     *
     * @param source
     * @return MD5值
     */
    public static String getMD5(String source) {
        return getMD5(source, null);
    }
	
    /**
     * 获取密码及密码盐
     * @param password
     * @return
     */
    public static final String CHAR_KEY = "key";
    public static final String CHAR_PASSWORD = "password";
    public static Map<String, String> getPwd(String password){
    	String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		Map<String, String> map= new HashMap<String, String>();
		map.put(CHAR_KEY, uuid);
		map.put(CHAR_PASSWORD, MD5.getMD5(password.concat(uuid)));
    	return map;
    }
    
    /**
     * 校验密码是否正常
     * @param password
     * @param key
     * @param oldPassword
     * @return
     */
    public static boolean checkPassword(String password,String key,String oldPassword){
    	String pwd = getMD5(password.concat(key));
    	if(pwd.equals(oldPassword)){
    		return true;
    	} else {
    		return false;
    	}
    }    
    
    
    
    
    /**
     * 计算字符串的MD5值(UTF-8)
     *
     * @param source
     * @return MD5值
     */
    public static String getMD5(String source, String update) {
        return getMD5(source, update, Charset.forName("UTF-8"));
    }
    
    /**
     * 计算32位MD5值
     */
    public static String getMD5(String source, String update, Charset charset) {
    	String hashValue = "";
        try {
        	MessageDigest md = MessageDigest.getInstance("MD5");
        	md.update(source.getBytes(charset));
        	if (update != null) {
        		hashValue = byte2hex(md.digest(update.getBytes(charset)));
            } else {
            	hashValue = byte2hex(md.digest());
            }
        } catch (NoSuchAlgorithmException e) {
        	System.err.println("计算MD5值出错");
        }
        return hashValue;
    }
    
    /** 
     * 字节数组转十六进制字符串 
     */
    public static String byte2hex(byte[] b) {
    	if (b == null) return "";
    	String hex = "",tmp = "";
        for (int n = 0; n < b.length; n++) {
            tmp = (Integer.toHexString(b[n] & 0xff));
            if (tmp.length() == 1) {
            	hex = hex + "0" + tmp;
            } else {
            	hex = hex + tmp;
            }
        }
        return hex.toLowerCase();
    }
    
    /**
     * 计算文件md5
     */
    public static String getFileMD5(String fileStr) {
		File file = new File(fileStr);
    	if (!file.isFile()) {
    		System.err.println("文件不存在,fileStr:" + fileStr);
			return null;
		}
		MessageDigest digest = null;
		FileInputStream in = null;
		byte buffer[] = new byte[1024];
		int len;
		try {
			digest = MessageDigest.getInstance("MD5");
			in = new FileInputStream(file);
			while ((len = in.read(buffer, 0, 1024)) != -1) {
				digest.update(buffer, 0, len);
			}
			in.close();
		} catch (Exception e) {
			System.err.println("计算md5出错,fileStr:" + fileStr);
			return null;
		}
		BigInteger bigInt = new BigInteger(1, digest.digest());
		return bigInt.toString(16);
	}


}
