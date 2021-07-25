package com.fjzcit.zcatp.service.test;

import com.fjzcit.zcatp.model.zcatp.ExecuteResult;
import com.fjzcit.zcatp.repository.cases.IExecuteResultRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ExecuteResultService {

    @Resource
    public IExecuteResultRepository executeResultRepository;

    public List<ExecuteResult> findAll() {
        return this.executeResultRepository.findAll();
    }

    /**
     * 批量保存执行结果
     * @param list
     */
    public void saveAll(List<ExecuteResult> list) {
        this.executeResultRepository.saveAll(list);
    }
}
