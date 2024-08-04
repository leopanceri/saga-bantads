package br.net.dac.saga.services;

import java.security.SecureRandom;

import org.springframework.stereotype.Service;

@Service
public class SenhaService {
	
	public String passwordGen() {
		String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String lowerCase = "abcdefghijklmnopqrstuvwxyz";
		String numbers = "0123456789";
		
		String caracteresPermitidos = upperCase+lowerCase+numbers;
		
		SecureRandom random = new SecureRandom();
		StringBuilder password = new StringBuilder(6);
		
		for (int i = 0; i < 6; i++) {
            int index = random.nextInt(caracteresPermitidos.length());
            password.append(caracteresPermitidos.charAt(index));
        }
		
		return password.toString();

	}
}
