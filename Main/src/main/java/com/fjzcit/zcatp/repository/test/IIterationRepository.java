package com.fjzcit.zcatp.repository.test;

import com.fjzcit.zcatp.model.test.Iteration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IIterationRepository extends JpaRepository<Iteration, Integer> {
}
