package org.covito.audit.resolver;

import java.util.ArrayList;
import java.util.List;

import org.covito.audit.AbstractAuditResourceResolver;

public class ParametersAsStringResourceResolver extends AbstractAuditResourceResolver {

	@Override
	protected String[] createResource(Object[] args) {
		final List<String> stringArgs = new ArrayList<String>();

		for (final Object arg : args) {
			stringArgs.add(arg.toString());
		}

		return stringArgs.toArray(new String[stringArgs.size()]);
	}

}
