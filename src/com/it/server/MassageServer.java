package com.it.server;

import java.util.List;

import com.it.entity.ExtendMassage;
import com.opensymphony.xwork2.ActionContext;

public interface MassageServer {
	public List<ExtendMassage> getMassage();
	public void receiveMassage();
	public  String getRequestBody(ActionContext ctx);
}
