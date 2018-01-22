package com.bank.xy.utils;

public class DirUtils {
	public static String getDir(String name) {
		if (name != null) {
			int code = name.hashCode();
			return "/" + (code & 15) + "/" + (code >>> 4 & 15);
		}
		return null;
	}
}
