package com.fastcampus.loan.controller;

import com.fastcampus.loan.dto.ApplicationDto;
import com.fastcampus.loan.dto.ResponseDTO;
import com.fastcampus.loan.dto.TermsDto;
import com.fastcampus.loan.dto.TermsDto.Request;
import com.fastcampus.loan.dto.TermsDto.Response;
import com.fastcampus.loan.service.TermsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.fastcampus.loan.dto.ResponseDTO.ok;

@RequiredArgsConstructor
@RestController
@RequestMapping("/terms")
public class TermsController {

    private final TermsService termsService;

    @PostMapping
    public ResponseDTO<Response> create(@RequestBody Request request) {
        return ok(termsService.create(request));
    }

    @GetMapping()
    public ResponseDTO<List<Response>> getAll() {
        return ok(termsService.getAll());
    }
}
