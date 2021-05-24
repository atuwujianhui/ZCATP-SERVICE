package com.fjzcit.zcatp.repository.cases;

import com.fjzcit.zcatp.model.cases.TInterfaceCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IInterfaceCaseRepository extends JpaRepository<TInterfaceCase, Integer> {

    @Query("select t from TInterfaceCase t where t.iteration.id = :iterationId " +
            " AND t.state = 1 ")
    List<TInterfaceCase> findByIterationIdId(@Param("iterationId") Integer iterationId);

    List<TInterfaceCase> findByIteration_idAndState(Integer iterationId, Integer state);

}
