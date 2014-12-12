package org.covito.audit;

import org.covito.audit.api.AuditActionResolver;
import org.covito.audit.api.util.Assert;

public abstract class AbstractAuditActionResolver implements AuditActionResolver {

	private final String successSuffix;

	private final String failureSuffix;

	protected AbstractAuditActionResolver(final String successSuffix, final String failureSuffix) {

		Assert.assertNotNull(successSuffix, "successSuffix cannot be null.");
		Assert.assertNotNull(failureSuffix, "failureSuffix cannot be null.");
		this.successSuffix = successSuffix;
		this.failureSuffix = failureSuffix;
	}

	protected final String getSuccessSuffix() {
		return this.successSuffix;
	}

	protected final String getFailureSuffix() {
		return this.failureSuffix;
	}
}
