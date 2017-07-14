package com.it.action;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.it.entity.ExtendMassage;
import com.it.entity.Massage;
import com.it.serverImpl.MassageServerImpl;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class MassageAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5503324608858406452L;
	Map<String,Object> map;
	public List<ExtendMassage> listMassage;
	public MassageServerImpl massageServer;
	HashMap<String ,Object> msg;
	public MassageServerImpl getMassageServer() {
		return massageServer;
	}
	public void setMassageServer(MassageServerImpl massageServer) {
		this.massageServer = massageServer;
	}
	public List<ExtendMassage> getListMassage() {
		return listMassage;
	}
	public void setListMassage(List<ExtendMassage> listMassage) {
		this.listMassage = listMassage;
	}
	
	
	
	public String listMassage(){
		map=new HashMap<String,Object>();
		@SuppressWarnings("unchecked")
		List<Massage> list=massageServer.getMassageDao().findAll();
		map.put("massage", list);
		return "list";
	}
	
	public String listExtendMassage(){
		listMassage=massageServer.getMassage();
		return "listExtendMassage";
	}
	public String listMarkExtendMassage(){
		listMassage=massageServer.getMassage();
		msg=new HashMap<String,Object>();
		msg.put("markMassages", listMassage);
		return "listMarkExtendMassage";
	}
	
	public HashMap<String, Object> getMsg() {
		return msg;
	}
	public void setMsg(HashMap<String, Object> msg) {
		this.msg = msg;
	}
	public Map<String, Object> getMap() {
		return map;
	}
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public String receiveMassage(){
		ActionContext ctx=ActionContext.getContext();
		try{
			massageServer.getRequestBody(ctx);
//			HttpServletRequest request=(HttpServletRequest)ctx.get(ServletActionContext.HTTP_REQUEST);
////			HttpServletRequest request = ServletActionContext.getRequest();
//			Map<String, String[]> map = request.getParameterMap();  
//	        Set<Entry<String, String[]>> set = map.entrySet();  
//	        Iterator<Entry<String, String[]>> it = set.iterator();  
//	        while (it.hasNext()) {  
//	            Entry<String, String[]> entry = it.next();  
//	  
//	            System.out.println("KEY:"+entry.getKey());  
//	            for (String i : entry.getValue()) {  
//	                System.out.println(i);  
//	            } 
//	        }
	        //失败的方法
//			InputStream inputStream;
//			inputStream=request.getInputStream();
//			String strMassage="";
//			String strResponse="";
//			BufferedReader reader;
//			reader=new BufferedReader(new InputStreamReader(inputStream));
//			while((strMassage=reader.readLine())!=null){
//				strResponse+=strMassage;
//			}
//			System.out.println("看看有没有执行");
//			System.out.println(strResponse);
//			reader.close();
//			inputStream.close();
			return "success";
	}catch(Exception e){
			return "failure";
	}
	}
	
}
