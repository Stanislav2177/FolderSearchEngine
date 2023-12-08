package com.string.search.via.web.String.search.service.ipService;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

@Service
public interface RequestService {

    String getClientIp(HttpServletRequest request);

}
