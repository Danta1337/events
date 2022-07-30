package cn.ch1tanda.event.mapper;

import cn.ch1tanda.event.model.UserDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(UserDO row);

    UserDO selectByPrimaryKey(Integer id);

    List<UserDO> selectAll();

    int updateByPrimaryKey(UserDO row);

    UserDO selectUsernameAndPasswordByUsername(@Param("username") String username);

    UserDO selectUserByEmail (@Param("email") String email);
}