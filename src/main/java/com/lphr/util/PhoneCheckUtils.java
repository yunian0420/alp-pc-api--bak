package com.lphr.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: YN
 * @Date: 2019年4月29日下午10:17:40
 *
 */
public class PhoneCheckUtils {
	public static boolean isPhone(String phone) {
	    String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
	    if (phone.length() != 11) {
	        //MToast.showToast("手机号应为11位数");
	        return false;
	    } else {
	        Pattern p = Pattern.compile(regex);
	        Matcher m = p.matcher(phone);
	        boolean isMatch = m.matches();
	        //LogUtil.e(isMatch);
	        if (!isMatch) {
	            //MToast.showToast("请填入正确的手机号");
	        	return false;
	        }
	        return isMatch;
	    }
	}
	
	public static void main(String[] args) {
		boolean a = isPhone("13720890470");
		System.out.println(a);
	}
}
