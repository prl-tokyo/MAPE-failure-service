package jp.ac.nii.prl.mape.failure.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.ac.nii.prl.mape.failure.model.FailureView;

public interface FailureViewRepository extends JpaRepository<FailureView, Long> {

	FailureView save(FailureView view);

	Optional<FailureView> findById(Long viewId);

}
