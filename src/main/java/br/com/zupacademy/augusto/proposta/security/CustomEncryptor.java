package br.com.zupacademy.augusto.proposta.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.stereotype.Component;

@Component
public class CustomEncryptor {
	
	@Value(value = "${encryptor.salt}")
	String salt;
	
	public String encrypt(String value, String secret) {
		TextEncryptor encryptor = Encryptors.text(secret, salt);
		return encryptor.encrypt(value);
	}
	
	public String decrypt(String value, String secret) {
		TextEncryptor decryptor = Encryptors.text(secret, salt);
		return decryptor.decrypt(value);
	}
}
