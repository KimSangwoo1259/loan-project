package com.fastcampus.loan.service;

import com.fastcampus.loan.domain.Terms;

import com.fastcampus.loan.dto.TermsDto.Request;
import com.fastcampus.loan.dto.TermsDto.Response;
import com.fastcampus.loan.repository.TermsRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TermsServiceImpl implements TermsService {

    private final TermsRepository termsRepository;
    private final ModelMapper modelMapper;


    @Override
    public Response create(Request request) {

        Terms terms = modelMapper.map(request, Terms.class);
        Terms created = termsRepository.save(terms);
        return modelMapper.map(created, Response.class);
    }

    @Override
    public List<Response> getAll() {
        return termsRepository.findAll().stream().map(t -> modelMapper.map(t, Response.class)).collect(Collectors.toList());
    }
}
