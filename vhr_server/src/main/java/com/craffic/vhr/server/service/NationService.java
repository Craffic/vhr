package com.craffic.vhr.server.service;

import com.craffic.vhr.server.dao.NationMapper;
import com.craffic.vhr.server.domain.Nation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NationService {
    @Autowired
    NationMapper nationMapper;

    public List<Nation> getAllNations() {
        return nationMapper.getAllNations();
    }
}
