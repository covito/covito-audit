package org.covito.audit.trail;

import org.covito.audit.api.AuditActionContext;

public class ConsoleAuditTrailManager extends AbstractStringAuditTrailManager {

	@Override
	public void record(AuditActionContext auditActionContext) {
		 System.out.println(toString(auditActionContext));
	}

}
