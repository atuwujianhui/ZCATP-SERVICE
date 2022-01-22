package com.fjzcit.zcatp.repository.test;

import com.fjzcit.zcatp.model.test.TestData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ITestDataRepository extends JpaRepository<TestData, Integer> {

    /**
     * 根据用例查找对应的数据
     * @param caseId
     * @return
     */
    @Query("select d from TestData d, TestCaseRefData drc where d.id = drc.dataId and drc.caseId = :caseId")
    List<TestData> findByCaseId(@Param("caseId") Integer caseId);

    /**
     * TODO: 根据迭代查找所有用例的数据
     * @param iterationId
     * @return
     */
    @Query("select d from TestData d, TestCaseRefData drc where d.id = drc.dataId and drc.caseId = :iterationId")
    List<TestData> findByIterationId(@Param("iterationId") Integer iterationId);
}
