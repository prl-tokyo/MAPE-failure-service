package jp.ac.nii.prl.mape.failure.service;

import java.util.Optional;

import jp.ac.nii.prl.mape.failure.model.FailureView;

public interface FailureViewService {

	FailureView save(FailureView view);
	
	Optional<FailureView> findById(Long viewId);
	
	FailureView analyse(Long viewId) throws Exception;
	
	FailureView analyse(FailureView view);
}
