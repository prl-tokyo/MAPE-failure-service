package jp.ac.nii.prl.mape.failure.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import jp.ac.nii.prl.mape.failure.model.FailureView;
import jp.ac.nii.prl.mape.failure.model.Instance;

@Service("failureViewService")
public class FailureViewServiceImpl implements FailureViewService {
	
	private static final Logger logger = LoggerFactory.getLogger(FailureViewServiceImpl.class);

	public FailureViewServiceImpl() {
	}

	@Override
	public FailureView analyse(FailureView view) {
		List<Instance> instances = view.getInstances();
		List<Instance> delete = new ArrayList<>();
		List<Instance> add = new ArrayList<>();
		for (Instance instance:instances) {
			if (instance.getInstResponseTime() > 500) {
				logger.debug(String.format("Instance %s has a response time > 500ms: %d", instance.getInstID(), instance.getInstResponseTime()));
				delete.add(instance);
				Instance newInstance = new Instance();
				newInstance.setFailureView(view);
				newInstance.setInstID(UUID.randomUUID().toString());
				newInstance.setInstType(instance.getInstType());
				newInstance.setInstResponseTime(-1);
				add.add(newInstance);
			} else {
				logger.debug(String.format("Instance %s is fine: response time is %d", instance.getInstID(), instance.getInstResponseTime()));
			}
		}
		instances.removeAll(delete);
		instances.addAll(add);
		return view;
	}

}
