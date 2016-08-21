package jp.ac.nii.prl.mape.failure.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Instance {

	@Id
	@GeneratedValue
	@JsonIgnore
	private Long id;
	
	private String instID;
	
	private String instType;
	
	private int instResponseTime;
	
	@ManyToOne
	private FailureView failureView;

	public FailureView getFailureView() {
		return failureView;
	}

	public Long getId() {
		return id;
	}

	public String getInstID() {
		return instID;
	}

	public int getInstResponseTime() {
		return instResponseTime;
	}

	public String getInstType() {
		return instType;
	}

	public void setFailureView(FailureView failureView) {
		this.failureView = failureView;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setInstID(String instID) {
		this.instID = instID;
	}

	public void setInstResponseTime(int instResponseTime) {
		this.instResponseTime = instResponseTime;
	}

	public void setInstType(String instType) {
		this.instType = instType;
	}
}
