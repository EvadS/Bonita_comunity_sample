package org.bonitasoft.connectors.chain;

import org.bonitasoft.engine.connector.AbstractConnector;
import org.bonitasoft.engine.connector.ConnectorValidationException;

public abstract class AbstractChainGetAssetImpl extends AbstractConnector {

	protected final static String URL_INPUT_PARAMETER = "url";
	protected final String ASSET_OUTPUT_PARAMETER = "asset";

	protected final java.lang.String getUrl() {
		return (java.lang.String) getInputParameter(URL_INPUT_PARAMETER);
	}

	protected final void setAsset(java.util.List asset) {
		setOutputParameter(ASSET_OUTPUT_PARAMETER, asset);
	}

	@Override
	public void validateInputParameters() throws ConnectorValidationException {
		try {
			getUrl();
		} catch (ClassCastException cce) {
			throw new ConnectorValidationException("url type is invalid");
		}



	}

}
