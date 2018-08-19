package org.bonitasoft.connectors.chain;

import org.bonitasoft.engine.connector.AbstractConnector;
import org.bonitasoft.engine.connector.ConnectorValidationException;

public abstract class AbstractChainSpendAssetImpl extends AbstractConnector {

	protected final static String URL_INPUT_PARAMETER = "url";

	protected final static String MODEL_INPUT_PARAMETER = "model";
	protected final static String COLOR_INPUT_PARAMETER = "color";
	protected final static String WEIGHT_INPUT_PARAMETER = "weight";
	protected final static String AMOUNT_INPUT_PARAMETER = "amount";
	protected final static String CURRENCY_INPUT_PARAMETER = "currency";


	protected final String TRANSACTION_RESPONSE_OUTPUT_PARAMETER = "transactionResponse";

	protected final java.lang.String getUrl() {
		return (java.lang.String) getInputParameter(URL_INPUT_PARAMETER);
	}

	protected final java.lang.String getModel() {
		return (java.lang.String) getInputParameter(MODEL_INPUT_PARAMETER);
	}

	protected final java.lang.String getColor() {
		return (java.lang.String) getInputParameter(COLOR_INPUT_PARAMETER);
	}

	protected final java.lang.String getWeight() {
		return (java.lang.String) getInputParameter(WEIGHT_INPUT_PARAMETER);
	}

	protected final java.lang.Integer getAmount() {
		return (java.lang.Integer) getInputParameter(AMOUNT_INPUT_PARAMETER);
	}

	protected final java.lang.String getCurrency() {
		return (java.lang.String) getInputParameter(CURRENCY_INPUT_PARAMETER);
	}

	
	protected final void setTransactionResponse(org.bonitasoft.connectors.chain.TransactionResponse transactionResponse) {
		setOutputParameter(TRANSACTION_RESPONSE_OUTPUT_PARAMETER, transactionResponse);
	}

	@Override
	public void validateInputParameters() throws ConnectorValidationException {
		try {
			getUrl();
		} catch (ClassCastException cce) {
			throw new ConnectorValidationException("url type is invalid");
		}

		try {
			getModel();
		} catch (ClassCastException cce) {
			throw new ConnectorValidationException("model type is invalid");
		}

		try {
			getColor();
		} catch (ClassCastException cce) {
			throw new ConnectorValidationException("color type is invalid");
		}

		try {
			getWeight();
		} catch (ClassCastException cce) {
			throw new ConnectorValidationException("weight type is invalid");
		}

		try {
			getAmount();
		} catch (ClassCastException cce) {
			throw new ConnectorValidationException("amount type is invalid");
		}

		try {
			getCurrency();
		} catch (ClassCastException cce) {
			throw new ConnectorValidationException("currency type is invalid");
		}

	}

}
