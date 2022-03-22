package com.example.judgmentdoc.data.user;

import com.example.judgmentdoc.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    User getUserByUsername(String username);

    Long insertUser(User user);

    User getUserInfoById(Long userId);
}
