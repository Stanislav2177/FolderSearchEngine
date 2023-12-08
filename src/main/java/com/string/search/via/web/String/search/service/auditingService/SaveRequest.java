package com.string.search.via.web.String.search.service.auditingService;

import com.string.search.via.web.String.search.entity.RequestEntity;
import com.string.search.via.web.String.search.repository.RequestEntityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SaveRequest {


    @Autowired
    private RequestEntityRepo repo;

    public void save(String clientIp, String type,String methodType, String location, String text) {
        RequestEntity entity = new RequestEntity();
        LocalDateTime localDateTime = LocalDateTime.now();

        entity.setIp(clientIp);
        entity.setType(type);
        entity.setLocation(location);
        entity.setMethodType("Get Request");
        entity.setText(text);
        entity.setLocalDateTime(localDateTime);

        repo.save(entity);
    }

    public List<RequestEntity> getAll(){
        return repo.findAll();
    }
}
