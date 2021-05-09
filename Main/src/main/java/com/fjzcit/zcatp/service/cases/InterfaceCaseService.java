package com.fjzcit.zcatp.service.cases;

import com.fjzcit.zcatp.model.cases.TInterfaceCase;
import com.fjzcit.zcatp.repository.cases.InterfaceCaseRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class InterfaceCaseService {

    @Resource
    InterfaceCaseRepository interfaceCaseRepository;

    /**
     * 查询所有用例
     *
     * @return
     */
    public List<TInterfaceCase> findAll() {
        return this.interfaceCaseRepository.findAll();
    }

    /**
     * 根据ID查询用例
     *
     * @param id
     * @return
     */
    public Optional<TInterfaceCase> findById(Integer id) {
        return this.interfaceCaseRepository.findById(id);
    }

    /**
     * 根据systemID，查询所有可执行的用例
     * @param iterationId
     * @return
     */
    public List<TInterfaceCase> findByIterationId(Integer iterationId) {
//        return this.interfaceCaseRepository.findByIterationIdId(iterationId);
        return this.interfaceCaseRepository.findByIteration_idAndState(iterationId, 1);
    }
}