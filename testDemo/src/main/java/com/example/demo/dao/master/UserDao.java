package com.example.demo.dao.master;

import com.example.demo.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by hong on 2017/5/2.
 */
@Mapper
public interface UserDao {

    List<User> findAll();
}
