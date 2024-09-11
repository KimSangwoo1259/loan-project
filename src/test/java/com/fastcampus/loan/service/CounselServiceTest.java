package com.fastcampus.loan.service;

import com.fastcampus.loan.domain.Counsel;
import com.fastcampus.loan.dto.CounselDto;
import com.fastcampus.loan.dto.CounselDto.Request;
import com.fastcampus.loan.dto.CounselDto.Response;
import com.fastcampus.loan.repository.CounselRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CounselServiceTest {

    @InjectMocks
    CounselServiceImpl counselService;

    @Mock
    private CounselRepository counselRepository;

    @Spy
    private ModelMapper modelMapper;

    @Test
    void Should_ReturnResponseDtoOfNewCounselEntity_When_RequestCounsel() {
        Counsel entity = Counsel.builder()
                .name("Kim")
                .cellPhone("010-0000-0000")
                .email("asd@mail.com")
                .memo("대출 원함")
                .zipCode("16677")
                .address("경기도 수원시 영통구 매탄동")
                .addressDetail("102호")
                .build();

        Request request = Request.builder()
                .name("Kim")
                .cellPhone("010-0000-0000")
                .email("asd@mail.com")
                .memo("대출 원함")
                .zipCode("16677")
                .address("경기도 수원시 영통구 매탄동")
                .addressDetail("102호")
                .build();

        when(counselRepository.save(ArgumentMatchers.any(Counsel.class))).thenReturn(entity);


        Response actual = counselService.create(request);

        assertThat(actual.getName()).isSameAs(entity.getName());
    }

    @Test
    void Should_ReturnResponseDtoOfUpdateCounselEntity_When_RequestCounselId() {
        Long counselId = 1L;

        Counsel entity = Counsel.builder()
                .counselId(counselId)
                .build();

        when(counselRepository.findById(counselId)).thenReturn(Optional.ofNullable(entity));

        Response actual = counselService.get(counselId);

        assertThat(actual.getCounselId()).isSameAs(counselId);
    }

}
