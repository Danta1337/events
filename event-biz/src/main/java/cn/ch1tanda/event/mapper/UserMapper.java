package cn.ch1tanda.event.mapper;

import cn.ch1tanda.event.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(User row);

    User selectByPrimaryKey(Integer id);

    List<User> selectAll();

    int updateByPrimaryKey(User row);

    User selectUsernameAndPasswordByUsername(@Param("username") String username);

    User selectUserByEmail (@Param("email") String email);
}