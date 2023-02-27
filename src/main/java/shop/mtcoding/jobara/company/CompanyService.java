package shop.mtcoding.jobara.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import shop.mtcoding.jobara.common.ex.CustomException;
import shop.mtcoding.jobara.common.util.PathUtil;
import shop.mtcoding.jobara.common.util.Verify;
import shop.mtcoding.jobara.company.dto.CompanyReq.CompanyJoinReqDto;
import shop.mtcoding.jobara.company.dto.CompanyReq.CompanyLoginReqDto;
import shop.mtcoding.jobara.company.dto.CompanyReq.CompanyUpdateReqDto;
import shop.mtcoding.jobara.company.model.Company;
import shop.mtcoding.jobara.company.model.CompanyRepository;

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

    @Transactional
    public Company updateCompany(CompanyUpdateReqDto companyUpdateReqDto, Integer coPrincipalId,
            MultipartFile profile) {
        String uuidImageName = PathUtil.writeImageFile(profile);

        Company companyTemp = new Company(
                coPrincipalId, companyUpdateReqDto.getPassword(), companyUpdateReqDto.getEmail(),
                companyUpdateReqDto.getAddress(), companyUpdateReqDto.getDetailAddress(),
                companyUpdateReqDto.getCompanyName(), companyUpdateReqDto.getCompanyScale(),
                companyUpdateReqDto.getCompanyField(),
                companyUpdateReqDto.getTel(), uuidImageName);

        try {
            companyRepository.updateById(companyTemp);
        } catch (Exception e) {
            throw new CustomException("회원 수정 실패", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return companyRepository.findById(coPrincipalId);
    }

}
