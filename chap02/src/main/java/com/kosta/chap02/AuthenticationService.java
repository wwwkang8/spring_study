package com.kosta.chap02;

import com.sun.tools.internal.ws.wscompile.AuthInfo;

public class AuthenticationService {

    private UserRepository userRepository;
    private AuthFailLogger failLogger;

    public AuthInfo authenticate(String id, String password){
        User user = userRepository.findUserById(id);
        if(user == null){
            throw new UserNotFoundException();
        }

        if(!user.matchPassword(password)){
            failLogger.insertBadPw(id, password);
            throw new AuthException();
        }

        return new AuthInfo(user.getId());
    }

    public void setUserRepository(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void setFailLogger(AuthFailLogger failLogger) {
        this.failLogger = failLogger;
    }
}
