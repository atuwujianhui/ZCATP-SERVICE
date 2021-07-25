package com.fjzcit.zcatp.service.test;

import com.fjzcit.zcatp.model.zcatp.InterfaceCase;
import com.fjzcit.zcatp.repository.cases.IInterfaceCaseRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class InterfaceCaseService {

    @Resource
    IInterfaceCaseRepository interfaceCaseRepository;

    /**
     * 查询所有用例
     *
     * @return
     */
    public List<InterfaceCase> findAll() {
        return this.interfaceCaseRepository.findAll();
    }

    /**
     * 根据ID查询用例
     *
     * @param id
     * @return
     */
    public Optional<InterfaceCase> findById(Integer id) {
        return this.interfaceCaseRepository.findById(id);
    }

    /**
     * 根据systemID，查询所有可执行的用例
     * @param iterationId
     * @return
     */
    public List<InterfaceCase> findByIterationId(Integer iterationId) {
//        return this.interfaceCaseRepository.findByIterationIdId(iterationId);
        return this.interfaceCaseRepository.findByIteration_idAndState(iterationId, 1);
    }
}