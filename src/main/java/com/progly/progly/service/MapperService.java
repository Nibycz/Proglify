package com.progly.progly.service;

import com.progly.progly.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MapperService {

    @Autowired
    private IUserRepository userRepository;


}
