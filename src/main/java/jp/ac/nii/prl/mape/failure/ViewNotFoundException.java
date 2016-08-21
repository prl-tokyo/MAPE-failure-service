package jp.ac.nii.prl.mape.failure;

import org.springframework.web.client.RestClientException;

public class ViewNotFoundException extends RestClientException {

	public ViewNotFoundException(String msg) {
		super(msg);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -1984683077384987749L;

}
