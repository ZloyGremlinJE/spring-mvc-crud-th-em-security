package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import web.dao.UserDAO;

import javax.transaction.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserDAO userDAO;

    @Autowired
    public UserDetailsServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) {
        return userDAO.getUserByName(s);
    }
}

//    @Override
//    @Transactional
//    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//without implements UserDetail and GrantedAuthority
        /* User currentUser = userDAO.getUserByName(s);
        if (currentUser == null) {
            throw new UsernameNotFoundException("Unknown user: " + s);
        }

        UserDetails user = org.springframework.security.core.userdetails.User.builder()
                .username(currentUser.getUserName())
                .password(currentUser.getPassword())
                .roles(currentUser.getRoles().stream().map(Role::getName).toArray(String[]::new))
                .build();
        return user;*/
//       return userDAO.getUserByName(s);
//    }
