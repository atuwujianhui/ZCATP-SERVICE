package com.fjzcit.zcatp.repository.test;

import com.fjzcit.zcatp.model.test.InterfaceCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IInterfaceCaseRepository extends JpaRepository<InterfaceCase, Integer> {

    @Query("select t from InterfaceCase t where t.iteration.id = :iterationId " +
            " AND t.state = 1 ")
    List<InterfaceCase> findByIterationIdId(@Param("iterationId") Integer iterationId);

    List<InterfaceCase> findByIteration_idAndState(Integer iterationId, Integer state);

}
