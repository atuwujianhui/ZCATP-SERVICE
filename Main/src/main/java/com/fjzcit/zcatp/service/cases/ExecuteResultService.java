package com.fjzcit.zcatp.service.cases;

import com.fjzcit.zcatp.model.cases.TExecuteResult;
import com.fjzcit.zcatp.repository.cases.ExecuteResultRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ExecuteResultService {

    @Resource
    public ExecuteResultRepository executeResultRepository;

    public List<TExecuteResult> findAll() {
        return this.executeResultRepository.findAll();
    }

    /**
     * 批量保存执行结果
     * @param list
     */
    public void saveAll(List<TExecuteResult> list) {
        this.executeResultRepository.saveAll(list);
    }
}
