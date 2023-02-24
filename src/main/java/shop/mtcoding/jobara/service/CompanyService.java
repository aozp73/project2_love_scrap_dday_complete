package shop.mtcoding.jobara.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.mtcoding.jobara.dto.company.CompanyReq.CompanyJoinReqDto;
import shop.mtcoding.jobara.dto.company.CompanyReq.CompanyLoginReqDto;
import shop.mtcoding.jobara.ex.CustomException;
import shop.mtcoding.jobara.model.Company;
import shop.mtcoding.jobara.model.CompanyRepository;
import shop.mtcoding.jobara.util.Verify;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Transactional(readOnly = true)
    public Company getCompany(CompanyLoginReqDto companyLoginReqDto) {
        Company companyPS = companyRepository.findByUsernameAndPassword(
                new Company(companyLoginReqDto.getUsername(), companyLoginReqDto.getPassword()));
        Verify.validateObject(companyPS, "유저네임이나 암호를 확인해주세요.");
        return companyPS;
    }

    @Transactional
    public void insertCompany(CompanyJoinReqDto companyJoinReqDto) {
        Company companyTemp = new Company(companyJoinReqDto.getUsername(), companyJoinReqDto.getPassword(),
                companyJoinReqDto.getEmail(), companyJoinReqDto.getAddress(), companyJoinReqDto.getDetailAddress(),
                companyJoinReqDto.getCompanyNumb(), companyJoinReqDto.getCompanyName());
        try {
        companyRepository.insert(companyTemp);
        } catch (Exception e) {
        throw new CustomException("회원 가입 실패", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
