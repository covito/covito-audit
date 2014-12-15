package org.covito.audit.api;

import java.io.Serializable;
import java.util.Date;

import org.covito.audit.api.util.Assert;

public final class AuditActionContext implements Serializable {

	/**
	 * Unique Id for serialization.
	 */
	private static final long serialVersionUID = -3530737409883959089L;

	/** This is <i>WHO</i> */
	private final String principal;

	/** This is <i>WHAT</i> */
	private final String resource;

	/** This is <i>ACTION</i> */
	private final String action;

	/** This is <i>Application from which operation has been performed</i> */
	private final String applicationCode;

	/** This is <i>WHEN</i> */
	private final Date performedDate;

	/** Client IP Address */
	private final String clientIpAddress;

	/** Server IP Address */
	private final String serverIpAddress;

	public AuditActionContext(String principal, String resource, String action,String applicationCode, String clientIpAddress,
			String serverIpAddress, AuditPointRuntimeInfo runtimeInfo) {
		Assert.assertNotNull(principal, "'principal' cannot be null.\n" + getDiagnosticInfo(runtimeInfo));
		Assert.assertNotNull(resource, "'resource' cannot be null.\n" + getDiagnosticInfo(runtimeInfo));
		Assert.assertNotNull(action, "'action' cannot be null.\n" + getDiagnosticInfo(runtimeInfo));
		Assert.assertNotNull(applicationCode, "'applicationCode' cannot be null.\n" + getDiagnosticInfo(runtimeInfo));
		Assert.assertNotNull(clientIpAddress, "'clientIpAddress' cannot be null.\n" + getDiagnosticInfo(runtimeInfo));
		Assert.assertNotNull(serverIpAddress, "'serverIpAddress' cannot be null.\n" + getDiagnosticInfo(runtimeInfo));
		this.principal = principal;
		this.resource = resource;
		this.action = action;
		this.applicationCode = applicationCode;
		this.performedDate = new Date();
		this.clientIpAddress = clientIpAddress;
		this.serverIpAddress = serverIpAddress;
	}

	private String getDiagnosticInfo(AuditPointRuntimeInfo runtimeInfo) {
		return "Check the correctness of @Audit annotation at the following audit point: " + runtimeInfo.asString();
	}

	public String getPrincipal() {
		return principal;
	}

	public String getResourceOperatedUpon() {
		return resource;
	}

	public String getApplicationCode() {
		return applicationCode;
	}

	public String getClientIpAddress() {
		return clientIpAddress;
	}

	public String getServerIpAddress() {
		return serverIpAddress;
	}

	public String getAction() {
		return action;
	}

	public Date getPerformedDate() {
		return performedDate;
	}
	
}
