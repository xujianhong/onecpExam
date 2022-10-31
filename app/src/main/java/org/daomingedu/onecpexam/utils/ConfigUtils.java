package org.daomingedu.onecpexam.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

/**
 * SharedPreferences操作类
 * @author stephen
 * 
 *         2014-8-20
 */
public class ConfigUtils {
	private static final String CONFIG = "config";
	private static SharedPreferences sp;


	public static void setParam(Context con, Map<String, String> map) {

		// 实例化SharedPreferences.Editor对象（第二步）
		SharedPreferences.Editor editor = sp.edit();
		// 用putString的方法保存数据

		for (String key : map.keySet()) {
			editor.putString(key, map.get(key));
		}
		// 提交当前数据
		editor.commit();
	}

	public static String getParam(Context con, String key,String defalut)
	{

		return sp.getString(key, defalut);
	}

	public static void setParam(Context con, String key, String value) {
//		// 实例化SharedPreferences对象（第一步）
//		SharedPreferences mySharedPreferences = con.getSharedPreferences(
//				CON_FILE_NAME, Activity.MODE_PRIVATE);
		// 实例化SharedPreferences.Editor对象（第二步）
		SharedPreferences.Editor editor = sp.edit();
		// 用putString的方法保存数据

		editor.putString(key, value);
		// 提交当前数据
		editor.commit();
	}
//--------------------------------------------------------------------------------------------------------



	public ConfigUtils() {
	}

	public static SharedPreferences init(Context ctx) {
		if(sp == null) {
			sp = ctx.getSharedPreferences(CONFIG, 0);
		}

		return sp;
	}

	public static void saveString(String key, String value) {
		sp.edit().putString(key, value).commit();
	}

	public static String getString(String key, String value) {
		return sp.getString(key, value);
	}

	public static String getString(String key) {
		return getString(key, null);
	}

	public static void saveInt(String key, int value) {
		sp.edit().putInt(key, value).commit();
	}

	public static int getInt(String key, int defValue) {
		return sp.getInt(key, defValue);
	}

	public static int getInt(String key) {
		return getInt(key, 0);
	}

	public static void saveLong(String key, long value) {
		sp.edit().putLong(key, value).commit();
	}

	public static long getLong(String key, long defValue) {
		return sp.getLong(key, defValue);
	}

	public static long getLong(String key) {
		return sp.getLong(key, 0L);
	}

	public static void saveBoolean(String key, boolean value) {
		sp.edit().putBoolean(key, value).commit();
	}

	public static boolean getBoolean(String key, boolean defValue) {
		return sp.getBoolean(key, defValue);
	}

	public static boolean getBoolean(String key) {
		return getBoolean(key, false);
	}

	public static void clear() {
		sp.edit().clear().commit();
	}

	public static void remove(String key) {
		sp.edit().remove(key).commit();
	}

}
