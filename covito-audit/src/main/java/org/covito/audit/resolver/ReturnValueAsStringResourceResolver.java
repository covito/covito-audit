package org.covito.audit.resolver;

import java.util.Collection;
import java.util.Iterator;

import org.aspectj.lang.JoinPoint;
import org.covito.audit.api.AuditResourceResolver;

public class ReturnValueAsStringResourceResolver implements AuditResourceResolver {

	public String[] resolveFrom(final JoinPoint auditableTarget, final Object retval) {
		if (retval instanceof Collection) {
			final Collection<?> c = (Collection<?>) retval;
			final String[] retvals = new String[c.size()];
			int i = 0;
			for (final Iterator<?> iter = c.iterator(); iter.hasNext() && i < c.size(); i++) {
				final Object o = iter.next();

				if (o != null) {
					retvals[i] = iter.next().toString();
				}
			}
			return retvals;
		}

		if (retval instanceof Object[]) {
			final Object[] vals = (Object[]) retval;
			final String[] retvals = new String[vals.length];
			for (int i = 0; i < vals.length; i++) {
				retvals[i] = vals[i].toString();
			}
			return retvals;
		}

		return new String[] { retval.toString() };
	}

	public String[] resolveFrom(final JoinPoint auditableTarget, final Exception exception) {
		final String message = exception.getMessage();
		if (message != null) {
			return new String[] { message };
		}
		return new String[] { exception.toString() };
	}
}
