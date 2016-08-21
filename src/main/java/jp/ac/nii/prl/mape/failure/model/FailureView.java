package jp.ac.nii.prl.mape.failure.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class FailureView {

	@Id
	@GeneratedValue
	@JsonIgnore
	private Long id;
	
	@OneToMany(mappedBy="failureView")
	private List<Instance> instances;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Instance> getInstances() {
		return instances;
	}

	public void setInstances(List<Instance> instances) {
		this.instances = instances;
	}
	
}
