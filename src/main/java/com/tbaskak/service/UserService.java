package com.tbaskak.service;

import com.tbaskak.authentication.UserDetails;
import com.tbaskak.constant.Constant;
import com.tbaskak.entity.User;
import com.tbaskak.exception.AuthenticationFailException;
import com.tbaskak.exception.CustomException;
import com.tbaskak.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException, CustomException {
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isEmpty())
            throw new CustomException(Constant.USER_NOT_FOUND);
        return new UserDetails(user.get().getEmail(), user.get().getPassword());
    }

    public User login(String email,String password) throws AuthenticationFailException,NoSuchAlgorithmException{
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isEmpty()){
            throw new AuthenticationFailException(Constant.USER_NOT_FOUND);
        }
        if(!user.get().getPassword().equals(hashPassword(password))){
            throw new AuthenticationFailException(Constant.WRONG_PASSWORD);
        }
        return user.get();
    }

    @Transactional
    public User singUp(User user) throws CustomException{
        Optional<User> dbUser = userRepository.findByEmail(user.getEmail());
        if(!dbUser.isEmpty()){
            throw new AuthenticationFailException(Constant.USER_ALREADY_EXIST);
        }
        String encryptedPassword = null;
        try {
            encryptedPassword = hashPassword(user.getPassword());
        }catch (Exception e){
            e.printStackTrace();
            throw new CustomException(e.getMessage());
        }
        User newUser = new User(user.getFirstName(),user.getLastName(), user.getEmail(), encryptedPassword);

        return userRepository.save(newUser);
    }

    public String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance(Constant.HASH_ALGORITHM);
        messageDigest.update(password.getBytes());
        byte[] digits = messageDigest.digest();
        return DatatypeConverter.printHexBinary(digits).toUpperCase();
    }


}
