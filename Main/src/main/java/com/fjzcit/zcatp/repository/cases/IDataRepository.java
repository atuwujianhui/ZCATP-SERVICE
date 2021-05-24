package com.fjzcit.zcatp.repository.cases;

import com.fjzcit.zcatp.model.cases.TData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IDataRepository extends JpaRepository<TData, Integer> {

    /**
     * 根据用例查找对应的数据
     * @param caseId
     * @return
     */
    @Query("select d from TData d, TDataRefCase drc where d.id = drc.dataId and drc.caseId = :caseId")
    List<TData> findByCaseId(@Param("caseId") Integer caseId);
}
