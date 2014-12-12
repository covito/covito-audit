package org.covito.audit.demo;

import org.covito.audit.api.annotation.Audit;

public class NOSpring {

	@Audit(actResolver = "", action = "保存信息", resResolver = "")
	public String saveSomeThing(String name){
		return name;
	}
}
