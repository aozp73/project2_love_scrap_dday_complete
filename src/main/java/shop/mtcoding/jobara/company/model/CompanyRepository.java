package shop.mtcoding.jobara.company.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CompanyRepository {

    public List<Company> findAll();

    public Company findById(int id);

    public Company findByUsernameAndPassword(Company company);

    public Company findByUsername(String username);

    public int insert(Company company);

    public int updateById(Company company);

    public int deleteById(int id);
}
