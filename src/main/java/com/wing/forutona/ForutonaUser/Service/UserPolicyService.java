package com.wing.forutona.ForutonaUser.Service;

import com.wing.forutona.ForutonaUser.Domain.UserPolicy;
import com.wing.forutona.ForutonaUser.Dto.UserPolicyResDto;
import com.wing.forutona.ForutonaUser.Repository.UserPolicyDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import java.io.IOException;

@Service
public class UserPolicyService {

    @Autowired
    private UserPolicyDataRepository userPolicyDataRepository;

    @Async
    @Transactional
    public UserPolicyResDto getPolicy(String policy) {
        UserPolicy userPolicy = userPolicyDataRepository.findById(policy).get();
        return new UserPolicyResDto(userPolicy);
    }
}
