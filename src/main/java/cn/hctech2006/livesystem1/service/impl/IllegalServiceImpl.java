package cn.hctech2006.livesystem1.service.impl;

import cn.hctech2006.livesystem1.mapper.IllagelMapper;
import cn.hctech2006.livesystem1.service.IIllegalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IllegalServiceImpl implements IIllegalService {
    @Autowired
    private IllagelMapper illagelMapper;

}
