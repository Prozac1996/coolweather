package cn.lfz.coolweather.util;

import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.lfz.coolweather.db.City;
import cn.lfz.coolweather.db.Country;
import cn.lfz.coolweather.db.Province;

/**
 * Created by Administrator on 2017/10/5.
 */

public class Utility {
    /*
     *处理省级数据
     */
    public static boolean handleProvinceResponse(String response){
        if(!TextUtils.isEmpty(response)){

            try {
                JSONArray provinces = new JSONArray(response);
                for (int i = 0; i < provinces.length(); i++) {
                    JSONObject provinceObject = provinces.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceCode(provinceObject.getInt("id"));
                    province.setProvinceName(provinceObject.getString("name"));
                    province.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    /*
     *处理市级数据
     */
    public static boolean handleCityResponse(String response,int provinceId){
        if(!TextUtils.isEmpty(response)){

            try {
                JSONArray citys = new JSONArray(response);
                for (int i = 0; i < citys.length(); i++) {
                    JSONObject cityObject = citys.getJSONObject(i);
                    City city = new City();
                    city.setCityCode(cityObject.getInt("id"));
                    city.setCityName(cityObject.getString("name"));
                    city.setProvinceId(provinceId);
                    city.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    /*
     *处理县级数据
     */
    public static boolean handleCountryResponse(String response,int cityId){
        if(!TextUtils.isEmpty(response)){

            try {
                JSONArray countrys = new JSONArray(response);
                for (int i = 0; i < countrys.length(); i++) {
                    JSONObject cityObject = countrys.getJSONObject(i);
                    Country country = new Country();
                    country.setWeatherId(cityObject.getString("weather_id"));
                    country.setCountryName(cityObject.getString("name"));
                    country.setCityId(cityId);
                    country.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
