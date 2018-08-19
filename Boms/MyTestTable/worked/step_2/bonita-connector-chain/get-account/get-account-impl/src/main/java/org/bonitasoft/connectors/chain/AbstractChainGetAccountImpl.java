package org.bonitasoft.connectors.chain;

import org.bonitasoft.engine.connector.AbstractConnector;
import org.bonitasoft.engine.connector.ConnectorValidationException;

public abstract class AbstractChainGetAccountImpl extends AbstractConnector {

    protected final static String URL_INPUT_PARAMETER = "url";
    protected final static String ACCOUNTTOKEN_INPUT_PARAMETER = "accountToken";
    protected final String ACCOUNT_OUTPUT_PARAMETER = "account";
    protected final String PORT_INPUT_OARAMETER = "port";


    protected final java.lang.String getAccountToken() {
        return (java.lang.String) getInputParameter(ACCOUNTTOKEN_INPUT_PARAMETER);
    }

    protected final java.lang.String getUrl() {
        return (java.lang.String) getInputParameter(URL_INPUT_PARAMETER);
    }

    protected final java.lang.String getPort() {
        return (java.lang.String) getInputParameter(PORT_INPUT_OARAMETER);
    }

    protected final void setAsset(org.bonitasoft.connectors.chain.Asset asset) {
        setOutputParameter(ACCOUNT_OUTPUT_PARAMETER, asset);
    }


    //protected final java.lang.String getAlias() {
    //	return (java.lang.String) getInputParameter(ALIAS_INPUT_PARAMETER);
    //}

    protected final void setAccount(String account) {
        setOutputParameter(ACCOUNT_OUTPUT_PARAMETER, account);
    }

    @Override
    public void validateInputParameters() throws ConnectorValidationException {
        try {
            getUrl();
        } catch (ClassCastException cce) {
            throw new ConnectorValidationException("url type is invalid");
        }
        try {
            getAccountToken();
        } catch (ClassCastException cce) {
            throw new ConnectorValidationException(
                    "accountToken type is invalid");
        }
        try {
            getPort();
        } catch (ClassCastException cce) {
            throw new ConnectorValidationException("alias type is invalid");
        }
    }
}
