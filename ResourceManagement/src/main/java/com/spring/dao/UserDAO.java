package com.spring.dao;

import java.util.List;
import com.spring.model.User;

public interface UserDAO {
   long save(User user);
   User get(int id);
   List<User> list();
   void update(int id, User user);
   void delete(int id);
}
