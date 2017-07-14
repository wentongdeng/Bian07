package com.it.action;

import java.util.Map;

import com.it.serverImpl.UserServerImpl;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport {
	UserServerImpl userServer;

	public UserServerImpl getUserServer() {
		return userServer;
	}

	public void setUserServer(UserServerImpl userServer) {
		this.userServer = userServer;
	}
	
	public String login(){
		ActionContext act=ActionContext.getContext();
		Map request=(Map) act.get("request");
		if(userServer.login(act)){			
			request.put("result", "success");
			return "success";
		}else{
			request.put("result", "false");
			return "false";
		}
	}
	
}
