package com.fjzcit.zcatp.service.cases;

import com.fjzcit.zcatp.model.cases.TData;
import com.fjzcit.zcatp.repository.cases.IDataRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DataService {
    @Resource
    IDataRepository dataRepository;

    /**
     * 根据用例查找对应数据
     * @param caseId
     * @return
     */
    public List<TData> findByCaseId(Integer caseId) {
        return this.dataRepository.findByCaseId(caseId);
    }
}
