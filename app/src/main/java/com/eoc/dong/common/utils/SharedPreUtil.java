package com.eoc.dong.common.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.eoc.dong.activity.MyApplication;

import java.util.ArrayList;
import java.util.List;

public class SharedPreUtil {
	
	private static final String FILE_NAME = "data";
	private static volatile SharedPreferences sp;
	private static Context context= MyApplication.getContext();

	/**
	 * 保存boolean变量
	 */
	public static void saveData(String key, boolean value){
		saveBoolean(key, value);
	}

	/**
	 * 保存String变量
	 */
	public static void saveData(String key, String value){
		saveString(key, value);
	}

	/**
	 * 保存int变量
	 */
	public static void saveData(String key, int value){
		saveInt(key, value);
	}

	/**
	 * 保存long变量
	 */
	public static void saveData(String key, long value){
		saveLong(key, value);
	}

	/**
	 * 保存float变量
	 */
	public static void saveData(String key, float value){
		saveFloat(key, value);
	}

	/**
	 * 获取布局型变量的值
	 * @param key 
	 * @param defValue 获取不到时，给定的默认的值
	 * @return
	 */
	public static boolean getBoolean(String key, boolean defValue) {

		return getSP(context).getBoolean(key, defValue);
	}
	
	/**
	 * 保存boolean变量
	 */
	public static void saveBoolean(String key, boolean value) {
		getSP(context).edit().putBoolean(key, value).commit();
	}
	
	
	/**
	 * 获取字符串型变量的值
	 * @param key 
	 * @param defValue 获取不到时，给定的默认的值
	 * @return
	 */
	public static String getString(String key, String defValue) {

		return getSP(context).getString(key, defValue);
	}

	/**
	 * 保存字符串变量
	 */
	public static void saveString(String key, String value) {
		getSP(context).edit().putString(key, value).commit();
	}

	/**
	 * 保存小数变量
	 */
	public static void saveFloat(String key, float value) {
		getSP(context).edit().putFloat(key, value).commit();
	}

	/**
	 * 获取整型变量的值
	 * @param key
	 * @param defValue 获取不到时，给定的默认的值
	 * @return
	 */
	public static int getInt(String key, int defValue) {
		return getSP(context).getInt(key,defValue);
	}

	/**
	 * 获取浮点型变量的值
	 * @param key
	 * @param defValue 获取不到时，给定的默认的值
	 * @return
	 */
	public static float getFloat(String key, float defValue) {
		return getSP(context).getFloat(key,defValue);
	}

	/**
	 * 保存整型变量
	 */
	public static void saveInt(String key, int value) {
		getSP(context).edit().putInt(key,value).commit();
	}

	/**
	 * 获取Long变量的值
	 * @param key
	 * @param defValue 获取不到时，给定的默认的值
	 * @return
	 */
	public static long getLong(String key, long defValue) {
		return getSP(context).getLong(key,defValue);
	}

	/**
	 * 保存Long变量
	 */
	public static void saveLong(String key, long value) {
		getSP(context).edit().putLong(key,value).commit();
	}

	/**
	 * 11.     * 保存List
	 * 12.     * @param tag
	 * 13.     * @param datalist
	 * 14.
	 */
	public static  <T> void setDataList(String tag, List<T> datalist) {
		SharedPreferences.Editor editor = getSP(context).edit();

		if (null == datalist || datalist.size() <= 0)
			return;

		Gson gson = new Gson();
		//转换成json数据，再保存
		String strJson = gson.toJson(datalist);
		editor.putString(tag, strJson);
		editor.commit();

	}

	/**
	 * 获取List
	 *
	 * @param tag
	 * @return
	 */
	public static <T> List<T> getDataList(String tag) {
		List<T> datalist = new ArrayList<T>();
		String strJson = getSP(context).getString(tag, null);
		if (null == strJson) {
			return datalist;
		}
		Gson gson = new Gson();
		datalist = gson.fromJson(strJson, new TypeToken<List<T>>() {
		}.getType());
		return datalist;

	}
	/**
	 * 获取List
	 *
	 * @param tag
	 * @return
	 */
	public static <T> List<T> getDataList(String tag, Class<T> cls) {
		String strJson = getSP(context).getString(tag, null);
		List<T> list = new ArrayList<T>();
		try {
			Gson gson = new Gson();
			JsonArray arry = new JsonParser().parse(strJson).getAsJsonArray();
			for (JsonElement jsonElement : arry) {
				list.add(gson.fromJson(jsonElement, cls));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;


	}

	/**
	 * 获取sp实例
	 */
	private static  SharedPreferences getSP(Context context){
		if(sp==null){
			synchronized (SharedPreUtil.class){
				if(sp==null){
					sp=context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
				}
			}

		}
		return sp;
	}

	/**
	 * 以json形式保存对象
	 * 注意，obj不要有多层嵌套
	 */
	public static void saveObj(String key,Object obj){
		String json= new Gson().toJson(obj);
		saveString(key,json);
	}

	/**
	 * 取出以json形式保存的对象
	 */
	public static <T> T getObj(String key,Class<T> type){
		String json = getString(key, null);
		if (json==null)return null;

		T obj = new Gson().fromJson(json, type);
		return obj;
	}
}
