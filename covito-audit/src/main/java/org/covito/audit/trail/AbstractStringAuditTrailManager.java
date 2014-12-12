package org.covito.audit.trail;

import org.covito.audit.api.AuditActionContext;
import org.covito.audit.api.AuditTrailManager;

public abstract class AbstractStringAuditTrailManager implements AuditTrailManager  {

	 /** Use multi-line output by default **/
    private boolean useSingleLine = false;

    /** Separator for single line log entries */
    private String entrySeparator = ",";

    protected String getEntrySeparator() {
            return this.entrySeparator;
    }

    public void setEntrySeparator(final String separator) {
        this.entrySeparator = separator;
    }

    public void setUseSingleLine(final boolean useSingleLine) {
        this.useSingleLine = useSingleLine;
    }

    protected String toString(final AuditActionContext auditActionContext) {
        if(this.useSingleLine) {
            return getSingleLineAuditString(auditActionContext);
        } else {
            return getMultiLineAuditString(auditActionContext);
        }
    }
        
    protected String getMultiLineAuditString(final AuditActionContext auditActionContext) {
        final StringBuilder builder = new StringBuilder();
        builder.append("Audit trail record BEGIN\n");
        builder.append("=============================================================\n");
        builder.append("WHO: ");
        builder.append(auditActionContext.getPrincipal());
        builder.append("\n");
        builder.append("WHAT: ");
        builder.append(auditActionContext.getResourceOperatedUpon());
        builder.append("\n");
        builder.append("ACTION: ");
        builder.append(auditActionContext.getAction());
        builder.append("\n");
        builder.append("APPLICATION: ");
        builder.append(auditActionContext.getApplicationCode());
        builder.append("\n");
        builder.append("WHEN: ");
        builder.append(auditActionContext.getPerformedDate());
        builder.append("\n");
        builder.append("CLIENT IP ADDRESS: ");
        builder.append(auditActionContext.getClientIpAddress());
        builder.append("\n");
        builder.append("SERVER IP ADDRESS: ");
        builder.append(auditActionContext.getServerIpAddress());
        builder.append("\n");
        builder.append("=============================================================");
        builder.append("\n\n");

        return builder.toString();
    }

    protected String getSingleLineAuditString(final AuditActionContext auditActionContext) {
        final StringBuilder builder = new StringBuilder();
        builder.append(auditActionContext.getPerformedDate());
        builder.append(getEntrySeparator());
        builder.append(auditActionContext.getApplicationCode());
        builder.append(getEntrySeparator());
        builder.append(auditActionContext.getResourceOperatedUpon());
        builder.append(getEntrySeparator());
        builder.append(auditActionContext.getAction());
        builder.append(getEntrySeparator());
        builder.append(auditActionContext.getPrincipal());
        builder.append(getEntrySeparator());
        builder.append(auditActionContext.getClientIpAddress());
        builder.append(getEntrySeparator());
        builder.append(auditActionContext.getServerIpAddress());

        return builder.toString();
    }

}
