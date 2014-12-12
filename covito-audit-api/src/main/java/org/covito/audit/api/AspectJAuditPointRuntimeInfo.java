package org.covito.audit.api;

import org.aspectj.lang.JoinPoint;

public class AspectJAuditPointRuntimeInfo implements AuditPointRuntimeInfo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8801413914382175952L;

	private JoinPoint currentJoinPoint;

	public AspectJAuditPointRuntimeInfo(JoinPoint currentJoinPoint) {
		this.currentJoinPoint = currentJoinPoint;
	}

	@Override
	public String asString() {
		return currentJoinPoint.toString();
	}

}
