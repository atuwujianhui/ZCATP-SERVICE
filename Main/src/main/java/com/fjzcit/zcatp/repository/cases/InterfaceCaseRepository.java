package com.fjzcit.zcatp.repository.cases;

import com.fjzcit.zcatp.model.cases.TInterfaceCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InterfaceCaseRepository extends JpaRepository<TInterfaceCase, Integer> {

    @Query("select t from TInterfaceCase t where t.iteration.id = :iterationId " +
            " AND t.state = 1 ")
    public List<TInterfaceCase> findByIterationIdId(@Param("iterationId") Integer iterationId);

    public List<TInterfaceCase> findByIteration_idAndState(Integer iterationId, Integer state);

}
