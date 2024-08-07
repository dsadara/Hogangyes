package com.dsadara.hogangYesApi.realestate.repository;

import com.dsadara.hogangYesApi.realestate.entity.RealEstate;
import com.dsadara.hogangYesApi.realestate.type.RealEstateType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles("dev")
@SpringBootTest
@Transactional
public class RealEstateRepositoryTest {

    @Autowired
    private RealEstateRepository realEstateRepository;

    @Test
    @DisplayName("성공-findByName()")
    void findByName_Success() {
        //given
        String name = "강변힐스테이트";

        //when
        Pageable pageable = PageRequest.of(0, 5);
        Page<RealEstate> pageList = realEstateRepository.findByName(name, pageable);

        //then
        assertEquals(name, pageList.getContent().get(0).getName());
    }

    @Test
    @DisplayName("성공-findBybeopJeongDongCode()")
    void findBybeopJeongDongCode_Success() {
        //given
        Integer beopJeongDongCode = 11500;

        //when
        Pageable pageable = PageRequest.of(0, 5);
        Page<RealEstate> pageList = realEstateRepository.findBybeopJeongDongCode(beopJeongDongCode, pageable);

        //then
        assertEquals(3, pageList.getContent().size());
        pageList.getContent().forEach(realEstate -> assertEquals(11500, realEstate.getBeopJeongDongCode()));
    }

    @Test
    @DisplayName("성공-findByBeopJeongDong()")
    void findByBeopJeongDong_Success() {
        //given
        String beopJeongDong = "염창동";

        //when
        Pageable pageable = PageRequest.of(0, 5);
        Page<RealEstate> pageList = realEstateRepository.findByBeopJeongDong(beopJeongDong, pageable);

        //then
        assertEquals(3, pageList.getContent().size());
        pageList.getContent().forEach(realEstate -> assertEquals("염창동", realEstate.getBeopJeongDong()));
    }

    @Test
    @DisplayName("성공-findByParcelNumber()")
    void findByParcelNumber_Success() {
        //given
        String parcelNumber = "299";

        //when
        Pageable pageable = PageRequest.of(0, 5);
        Page<RealEstate> pageList = realEstateRepository.findByParcelNumber(parcelNumber, pageable);

        //then
        assertEquals(parcelNumber, pageList.getContent().get(0).getParcelNumber());
    }

    @Test
    @DisplayName("성공-findByRealEstateType()")
    void findByRealEstateType_Success() {
        //given
        RealEstateType realEstateType = RealEstateType.APT_RENT;

        //when
        Pageable pageable = PageRequest.of(0, 5);
        Page<RealEstate> pageList = realEstateRepository.findByRealEstateType(realEstateType, pageable);

        //then
        assertEquals(realEstateType, pageList.getContent().get(0).getRealEstateType());
    }

    @Test
    @DisplayName("성공-findById()")
    void findById_Success() {
        //given
        Integer id = realEstateRepository.findAll().get(0).getId();

        //when
        RealEstate realEstate = realEstateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("findById() 테스트 실패"));

        //then
        assertEquals(id, realEstate.getId());
    }

    @Test
    @DisplayName("성공-existByName()")
    void existByName_Success() {
        //given
        String name = "강변힐스테이트";

        //when
        Boolean isExist = realEstateRepository.existsByName(name);

        //then
        assertTrue(isExist);
    }

    @Test
    @DisplayName("성공-existById()")
    void existById_Success() {
        //given
        Integer id = realEstateRepository.findAll().get(0).getId();

        //when
        Boolean isExist = realEstateRepository.existsById(id);

        //then
        assertTrue(isExist);
    }

}
