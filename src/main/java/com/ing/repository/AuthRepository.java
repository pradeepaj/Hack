package com.ing.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ing.entity.OtpAuth;

@Repository
public interface AuthRepository extends JpaRepository<OtpAuth, Integer> {

	Optional<OtpAuth> findByOtp(int otp);

}
