package com.fastcampus.loan.service;

import com.fastcampus.loan.dto.ApplicationDto.Request;
import com.fastcampus.loan.dto.ApplicationDto.Response;
import com.fastcampus.loan.dto.ApplicationDto.AcceptTerms;

public interface ApplicationService {

    Response create(Request request);

    Response get(Long applicationId);

    Response update(Long applicationId, Request request);

    void delete(Long applicationId);

    Boolean acceptTerms(Long applicationId, AcceptTerms request);
}
