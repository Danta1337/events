package cn.ch1tanda.event.mapper;

import cn.ch1tanda.event.model.AuthorityDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AuthorityMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table authority
     *
     * @mbg.generated Wed Jul 13 23:55:47 GMT+08:00 2022
     */
    int insert(AuthorityDO row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table authority
     *
     * @mbg.generated Wed Jul 13 23:55:47 GMT+08:00 2022
     */
    List<AuthorityDO> selectAll();

    List<String> selectAuthorityByUsername(@Param("username") String username);
}