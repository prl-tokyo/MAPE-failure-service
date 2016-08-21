package jp.ac.nii.prl.mape.failure.controller;

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
	public ResponseEntity<?> createView(@RequestBody FailureView view) {
		failureViewService.save(view);
		failureViewService.analyse(view);
		
		// create response
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(view.getId()).toUri());
		return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/{viewId}", method=RequestMethod.GET)
	public FailureView getView(@PathVariable Long viewId) {
		Optional<FailureView> view = failureViewService.findById(viewId);
		if (view.isPresent()) {
			logger.debug("Found view " + view.get().getId());
			return view.get();
		}
		logger.error("Could not find view with ID " + viewId);
		throw new ViewNotFoundException(String.format("Could not find view with id %s", viewId));
	}
}
