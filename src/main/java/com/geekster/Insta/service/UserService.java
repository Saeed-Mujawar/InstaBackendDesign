package com.geekster.Insta.service;

import com.geekster.Insta.dto.SignInInput;
import com.geekster.Insta.dto.SignInOutput;
import com.geekster.Insta.dto.SignUpInput;
import com.geekster.Insta.dto.SignUpOutput;
import com.geekster.Insta.model.AuthenticationToken;
import com.geekster.Insta.model.User;
import com.geekster.Insta.repository.IUserRepository;
import jakarta.xml.bind.DatatypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class UserService {

    @Autowired
    IUserRepository userRepository;

    @Autowired
    AuthenticationService authenticationService;


    public SignUpOutput signUp(SignUpInput signUpInput) {
        User user1 = userRepository.findFirstByEmail(signUpInput.getEmail());

        if(user1 != null){
            throw new IllegalStateException("User already exists..");
        }


        String encryptedPassword = null;
        try{
            encryptedPassword = encryptPassword(signUpInput.getUserPassword());
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }


        User user = new User(signUpInput.getFirstName(),signUpInput.getLastName(),signUpInput.getAge(),signUpInput.getEmail(),encryptedPassword,signUpInput.getPhoneNumber());

        userRepository.save(user);

        AuthenticationToken token = new AuthenticationToken(user);
        authenticationService.saveToken(token);
        return new SignUpOutput(HttpStatus.ACCEPTED,"User registered Successfully");
    }

    private String encryptPassword(String userPassword) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(userPassword.getBytes());
        byte[] digested =  md5.digest();

        String hash = DatatypeConverter.printHexBinary(digested);
        return hash;
    }

    public SignInOutput signIn(SignInInput signInInput){
        User user = userRepository.findFirstByEmail(signInInput.getEmail());
        if(user == null){
            throw new IllegalStateException(" Invalid User..");
        }

        String encryptedPassword = null;
        try{
            encryptedPassword = encryptPassword(signInInput.getPassword());
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }

        boolean isPasswordValid = encryptedPassword.equals(user.getPassword());
        if(!isPasswordValid){
            throw new IllegalStateException("Invalid User..");
        }
        AuthenticationToken token = authenticationService.getToken(user);
        return new SignInOutput(HttpStatus.OK,token.getToken());

    }

    public void updateUser(SignUpInput signUpInput) {
        User user1 = userRepository.findFirstByEmail(signUpInput.getEmail());
        if(user1 == null){
            throw new IllegalStateException("Invalid User..");
        }
        String encryptedPassword = null;
        if(signUpInput.getEmail() != null)
        {
            try {
                encryptedPassword = encryptPassword(signUpInput.getUserPassword());
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }


        User user = new User(user1.getUserID(),signUpInput.getFirstName(),signUpInput.getLastName(),signUpInput.getAge(),signUpInput.getEmail(),encryptedPassword,signUpInput.getPhoneNumber());

        userRepository.save(user);
    }
}
