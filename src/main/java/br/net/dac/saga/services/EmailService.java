package br.net.dac.saga.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Value("${spring.mail.username}")
	private String remetente;

	public String enviaEmailGerente(String destinatario, String senha) {
		try {
			SimpleMailMessage simpleMessage = new SimpleMailMessage();
			simpleMessage.setFrom(remetente);
			simpleMessage.setTo(destinatario);
			simpleMessage.setSubject("Cadastro de Gerente");
			simpleMessage.setText("Caro" + destinatario + "seu cadastro come gerente esta comcluido."
					+ "Sua senha para acesso é:" + senha);
			javaMailSender.send(simpleMessage);
			return "Email enviado";
		}catch(Exception e) {
			return "Erro ao enviar email" + e.getLocalizedMessage();
		}
	}

}
