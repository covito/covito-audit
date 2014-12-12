package org.covito.audit.api;

/**
 * @author Brent.Shen
 *
 */
public interface AuditTrailManager {

    void record(AuditActionContext auditActionContext);
}
