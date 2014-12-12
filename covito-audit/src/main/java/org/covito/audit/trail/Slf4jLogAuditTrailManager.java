package org.covito.audit.trail;

import org.covito.audit.api.AuditActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Slf4jLogAuditTrailManager extends AbstractStringAuditTrailManager {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void record(AuditActionContext auditActionContext) {
		logger.info(toString(auditActionContext));
	}

}
