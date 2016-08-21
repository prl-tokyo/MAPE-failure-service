package jp.ac.nii.prl.mape.failure.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.ac.nii.prl.mape.failure.model.FailureView;
import jp.ac.nii.prl.mape.failure.model.Instance;
import jp.ac.nii.prl.mape.failure.repository.FailureViewRepository;

@Service("failureViewService")
public class FailureViewServiceImpl implements FailureViewService {

	private final FailureViewRepository failureViewRepository;
	
	@Autowired
	public FailureViewServiceImpl(FailureViewRepository failureViewRepository) {
		this.failureViewRepository = failureViewRepository;
	}
	
	@Override
	public FailureView save(FailureView view) {
		return failureViewRepository.save(view);
	}

	@Override
	public Optional<FailureView> findById(Long viewId) {
		return failureViewRepository.findById(viewId);
	}

	@Override
	public FailureView analyse(Long viewId) throws Exception {
		Optional<FailureView> view = findById(viewId);
		if (view.isPresent())
			return analyse(view.get());
		throw new Exception();
	}

	@Override
	public FailureView analyse(FailureView view) {
		List<Instance> instances = view.getInstances();
		for (Instance instance:instances) {
			if (instance.getInstResponseTime() > 500) {
				instances.remove(instance);
				Instance newInstance = new Instance();
				newInstance.setFailureView(view);
				newInstance.setInstID("");
				newInstance.setInstType(instance.getInstType());
				newInstance.setInstResponseTime(-1);
				instances.add(newInstance);
			}
		}
		save(view);
		return view;
	}

}
