package com.fastcampus.loan.service;

import com.fastcampus.loan.dto.TermsDto;
import com.fastcampus.loan.dto.TermsDto.Request;
import com.fastcampus.loan.dto.TermsDto.Response;

import java.util.List;

public interface TermsService {

    Response create(Request request);

    List<Response> getAll();
}
