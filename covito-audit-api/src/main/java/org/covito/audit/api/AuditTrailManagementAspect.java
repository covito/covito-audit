package org.covito.audit.api;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.covito.audit.api.annotation.Audit;
import org.covito.audit.api.annotation.Audits;
import org.covito.audit.api.web.ClientInfo;
import org.covito.audit.api.web.ClientInfoResolver;

@Aspect
public final class AuditTrailManagementAspect  {

	private final PrincipalResolver principalResolver;

	private final Map<String, AuditActionResolver> actionResolvers;

	private final Map<String, AuditResourceResolver> resourceResolvers;

	private final List<AuditTrailManager> trailManagers;

	private final String applicationCode;

	private ClientInfoResolver clientInfoResolver = new DefaultClientInfoResolver();

	public AuditTrailManagementAspect(final String applicationCode, final PrincipalResolver principalResolver,
			final List<AuditTrailManager> trailManagers,
			final Map<String, AuditActionResolver> actionResolverMap,
			final Map<String, AuditResourceResolver> resourceResolverMap) {
		this.principalResolver = principalResolver;
		this.trailManagers = trailManagers;
		this.applicationCode = applicationCode;
		this.actionResolvers = actionResolverMap;
		this.resourceResolvers = resourceResolverMap;

	}

	@Around(value = "@annotation(audits)", argNames = "audits")
	public Object handleAuditTrail(final ProceedingJoinPoint joinPoint, final Audits audits) throws Throwable {
		Object retVal = null;
		String currentPrincipal = null;
		final String[] actions = new String[audits.value().length];
		final String[][] auditableResources = new String[audits.value().length][];
		try {
			retVal = joinPoint.proceed();
			currentPrincipal = this.principalResolver.resolveFrom(joinPoint, retVal);

			if (currentPrincipal != null) {
				for (int i = 0; i < audits.value().length; i++) {
					final AuditActionResolver auditActionResolver = this.actionResolvers.get(audits.value()[i]
							.actResolver());
					final AuditResourceResolver auditResourceResolver = this.resourceResolvers
							.get(audits.value()[i].resResolver());
					auditableResources[i] = auditResourceResolver.resolveFrom(joinPoint, retVal);
					actions[i] = auditActionResolver.resolveFrom(joinPoint, retVal, audits.value()[i]);
				}
			}
			return retVal;
		} catch (final Exception e) {
			currentPrincipal = this.principalResolver.resolveFrom(joinPoint, e);

			if (currentPrincipal != null) {
				for (int i = 0; i < audits.value().length; i++) {
					auditableResources[i] = this.resourceResolvers.get(audits.value()[i].resResolver())
							.resolveFrom(joinPoint, e);
					actions[i] = actionResolvers.get(audits.value()[i].actResolver()).resolveFrom(joinPoint, e, audits.value()[i]);
				}
			}
			throw e;
		} finally {
			for (int i = 0; i < audits.value().length; i++) {
				executeAuditCode(currentPrincipal, auditableResources[i], joinPoint, retVal, actions[i],audits.value()[i]);
			}
		}
	}

	@Around(value = "@annotation(audit)", argNames = "audit")
	public Object handleAuditTrail(final ProceedingJoinPoint joinPoint, final Audit audit) throws Throwable {
		final AuditActionResolver auditActionResolver = this.actionResolvers.get(audit.actResolver());
		final AuditResourceResolver auditResourceResolver = this.resourceResolvers.get(audit
				.resResolver());

		String currentPrincipal = null;
		String[] auditResource = new String[] { null };
		String action = null;
		Object retVal = null;
		try {
			retVal = joinPoint.proceed();

			currentPrincipal = this.principalResolver.resolveFrom(joinPoint, retVal);
			auditResource = auditResourceResolver.resolveFrom(joinPoint, retVal);
			action = auditActionResolver.resolveFrom(joinPoint, retVal, audit);

			return retVal;
		} catch (final Exception e) {
			currentPrincipal = this.principalResolver.resolveFrom(joinPoint, e);
			auditResource = auditResourceResolver.resolveFrom(joinPoint, e);
			action = auditActionResolver.resolveFrom(joinPoint, e, audit);
			throw e;
		} finally {
			executeAuditCode(currentPrincipal, auditResource, joinPoint, retVal, action, audit);
		}
	}

	private void executeAuditCode(final String currentPrincipal, final String[] auditableResources,
			final ProceedingJoinPoint joinPoint, final Object retVal, final String action, final Audit audit) {
		final String applicationCode = (audit.appCode() != null && audit.appCode().length() > 0) ? audit
				.appCode() : this.applicationCode;
		final ClientInfo clientInfo = this.clientInfoResolver.resolveFrom(joinPoint, retVal);
		final Date actionDate = new Date();
		final AuditPointRuntimeInfo runtimeInfo = new AspectJAuditPointRuntimeInfo(joinPoint);
		for (final String auditableResource : auditableResources) {
			final AuditActionContext auditContext = new AuditActionContext(currentPrincipal, auditableResource, action,
					applicationCode, actionDate, clientInfo.getClientIpAddress(), clientInfo.getServerIpAddress(),
					runtimeInfo);
			// Finally record the audit trail info
			for (AuditTrailManager manager : trailManagers) {
				manager.record(auditContext);
			}
		}
	}

	public void setClientInfoResolver(final ClientInfoResolver factory) {
		this.clientInfoResolver = factory;
	}
}
