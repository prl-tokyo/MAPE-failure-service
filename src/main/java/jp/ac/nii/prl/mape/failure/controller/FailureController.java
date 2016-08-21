package jp.ac.nii.prl.mape.failure.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jp.ac.nii.prl.mape.failure.ViewNotFoundException;
import jp.ac.nii.prl.mape.failure.model.FailureView;
import jp.ac.nii.prl.mape.failure.model.Instance;
import jp.ac.nii.prl.mape.failure.service.FailureViewService;

@RestController
@Component
@RequestMapping("/failure")
public class FailureController {
	
	private static Logger logger = LoggerFactory.getLogger(FailureController.class);
	
	private final FailureViewService failureViewService;

	@Autowired
	public FailureController(FailureViewService failureViewService) {
		this.failureViewService = failureViewService;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public FailureView createView(@RequestBody FailureView view) {
		return failureViewService.analyse(view);
	}
	
	@RequestMapping(value="/test", method=RequestMethod.GET)
	public FailureView test() {
		FailureView view = new FailureView();
		List<Instance> instances = new ArrayList<>();
		Instance inst1 = new Instance();
		inst1.setInstID("inst-1");
		inst1.setInstResponseTime(300);
		inst1.setInstType("t2.micro");
		inst1.setFailureView(view);
		instances.add(inst1);
		
		Instance inst2 = new Instance();
		inst2.setInstID("inst-2");
		inst2.setInstResponseTime(300);
		inst2.setInstType("t3.micro");
		inst2.setFailureView(view);
		instances.add(inst2);
		
		view.setInstances(instances);
		return view;
	}
}
