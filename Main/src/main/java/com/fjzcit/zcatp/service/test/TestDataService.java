package com.fjzcit.zcatp.service.test;

import com.fjzcit.zcatp.model.test.TestData;
import com.fjzcit.zcatp.repository.test.ITestDataRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TestDataService {
    @Resource
    ITestDataRepository dataRepository;

    /**
     * 根据用例查找对应数据
     * @param caseId
     * @return
     */
    public List<TestData> findByCaseId(Integer caseId) {
        return this.dataRepository.findByCaseId(caseId);
    }
}
