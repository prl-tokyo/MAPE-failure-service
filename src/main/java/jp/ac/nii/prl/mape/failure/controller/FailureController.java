package jp.ac.nii.prl.mape.failure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jp.ac.nii.prl.mape.failure.model.FailureView;
import jp.ac.nii.prl.mape.failure.service.FailureViewService;

@RestController
@Component
@RequestMapping("/failure")
public class FailureController {
	
	private final FailureViewService failureViewService;

	@Autowired
	public FailureController(FailureViewService failureViewService) {
		this.failureViewService = failureViewService;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public FailureView createView(@RequestBody FailureView view) {
		return failureViewService.analyse(view);
	}
}
