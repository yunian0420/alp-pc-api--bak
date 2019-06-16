package com.lphr.util.chinaArea;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.lphr.entity.Area;

/**
 * @author: YN
 * @Date: 2019年4月30日下午5:49:02
 *
 */
public class JSONUtils {
	
	public static void main(String[] args) {
		List<ChinaAreaDTO> provinces = getData();
		for(ChinaAreaDTO province : provinces) {
			System.out.println(province.getSname());
		}
	}
	
	
	public static List<ChinaAreaDTO> getData() {
		JsonParser parse =new JsonParser();  //创建json解析器
		List<ChinaAreaDTO> provinces = new ArrayList<>();
        try {
//          JsonObject json = (JsonObject) parse.parse(new FileReader("E:\\china.json"));  //创建jsonObject对象
//        	JsonArray array= json.getAsJsonArray();    //得到为json的数组
        	
        	JsonArray array = (JsonArray) parse.parse(new FileReader("E:\\china.json"));  //创建jsonObject对象
            for(int i=0;i<array.size();i++){
                JsonObject subObject = array.get(i).getAsJsonObject();
                System.out.println("---------------");
                System.out.println("code="+subObject.get("code").getAsInt());
                ChinaAreaDTO chinaAreaDTO = new ChinaAreaDTO();
                chinaAreaDTO.setItype(1);
                chinaAreaDTO.setParentId(0);
                chinaAreaDTO.setSname(subObject.get("name").getAsString());
                JsonArray cityJson = (JsonArray) subObject.get("cityList").getAsJsonArray();
                List<CityDTO> citys = new ArrayList<>();
                for(int j=0;j<cityJson.size();j++) {
                	JsonObject cityObject = cityJson.get(j).getAsJsonObject();
                	CityDTO cityDTO = new CityDTO();
                	cityDTO.setItype(2);
                	cityDTO.setSname(cityObject.get("name").getAsString());
                	JsonArray AreaJson = (JsonArray) cityObject.get("areaList").getAsJsonArray();
                	List<Area> areas = new ArrayList<>();
                	for(int s=0;s<AreaJson.size();s++) {
                		JsonObject areaObject = AreaJson.get(s).getAsJsonObject();
                    	Area area = new Area();
                    	area.setItype((byte)3);
                    	area.setSname(areaObject.get("name").getAsString());
                    	areas.add(area);
                	}
                	cityDTO.setAreaList(areas);
                	citys.add(cityDTO);
                }
                chinaAreaDTO.setCityList(citys);
                provinces.add(chinaAreaDTO);
            }
            System.out.println(provinces.size());
            
        } catch (JsonIOException e) {
            e.printStackTrace();
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return provinces;
	}
}
