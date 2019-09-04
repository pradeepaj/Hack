package com.ing.service;

import com.ing.dto.LoginReqDto;
import com.ing.dto.LoginResDto;

public interface LoginService {

	LoginResDto userLogin(LoginReqDto loginDto);

}
