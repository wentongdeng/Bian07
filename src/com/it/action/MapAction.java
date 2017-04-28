package com.it.action;

import java.util.List;

import com.it.entity.ExtendMap;
import com.it.entity.MapDAO;
import com.opensymphony.xwork2.ActionSupport;

public class MapAction extends ActionSupport {
	
	public MapDAO mapDao;
	public List<ExtendMap> listMap;
	public MapDAO getMapDao() {
		return mapDao;
	}
	public void setMapDao(MapDAO mapDao) {
		this.mapDao = mapDao;
	}
	public List<ExtendMap> getListMap() {
		return listMap;
	}
	public void setListMap(List<ExtendMap> listMap) {
		this.listMap = listMap;
	}
	public String getMaps(){
		try{
			listMap=mapDao.findExtendMap();
			return "success";
		}catch(Exception e){
			System.out.println(e);
			return "faliur";
		}
		
	}
}
