package com.it.action;

import java.util.List;

import com.it.entity.BmapDAO;
import com.it.entity.ExtendMap;
import com.it.server.MapServer;
import com.it.serverImpl.MapServerImpl;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class MapAction extends ActionSupport {
	public BmapDAO bmapDao;
	public MapServerImpl mapServer;
	public List<ExtendMap> listMap;
	
	
	public MapServerImpl getMapServer() {
		return mapServer;
	}
	public void setMapServer(MapServerImpl mapServer) {
		this.mapServer = mapServer;
	}
	
	
	public List<ExtendMap> getListMap() {
		return listMap;
	}
	public void setListMap(List<ExtendMap> listMap) {
		this.listMap = listMap;
	}
	public String getMaps(){
		try{
			listMap=bmapDao.findExtendMap();
			return "success";
		}catch(Exception e){
			System.out.println(e);
			return "faliur";
		}
		
	}
	
	public String receiveMassage(){
		try{
			ActionContext ctx=ActionContext.getContext();
			mapServer.getRequestBody(ctx);
			return "success";
		}catch (Exception e){
			System.out.println(e);
			return "failse";
		}
		
	}
	
	public BmapDAO getBmapDao() {
		return bmapDao;
	}
	public void setBmapDao(BmapDAO bmapDao) {
		this.bmapDao = bmapDao;
	}
}
