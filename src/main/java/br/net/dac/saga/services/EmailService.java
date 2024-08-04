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
			simpleMessage.setTo("panceri.leo@gmail.com");
			simpleMessage.setSubject("Cadastro de Gerente");
			simpleMessage.setText("Caro " + destinatario + " seu cadastro como gerente esta concluido. "
					+ "Sua senha para acesso é: " + senha);
			javaMailSender.send(simpleMessage);
			return "Email enviado";
		}catch(Exception e) {
			return "Erro ao enviar email" + e.getLocalizedMessage();
		}
	}
	
	public String emailClienteAprovado(String destinatario, String senha) {
		try {
			SimpleMailMessage simpleMessage = new SimpleMailMessage();
			simpleMessage.setFrom(remetente);
			simpleMessage.setTo("panceri.leo@gmail.com");
			simpleMessage.setSubject("Cadastro rejeitado");
			simpleMessage.setText("Caro " + destinatario + " seu cadastro como cliente foi aprovado. "
					+ "Sua senha para acesso é: " + senha);
			javaMailSender.send(simpleMessage);
			return "Email enviado";
		}catch(Exception e) {
			return "Erro ao enviar email" + e.getLocalizedMessage();
		}
	}
	
	public String emailClienteRejeitado(String destinatario, String motivo) {
		try {
			SimpleMailMessage simpleMessage = new SimpleMailMessage();
			simpleMessage.setFrom(remetente);
			simpleMessage.setTo("panceri.leo@gmail.com");
			simpleMessage.setSubject("Cadastro rejeitado");
			simpleMessage.setText("Caro " + destinatario + " seu cadastro como cliente foi rejeitado. "
					+ "Motivo da reprovação: " + motivo);
			javaMailSender.send(simpleMessage);
			return "Email enviado";
		}catch(Exception e) {
			return "Erro ao enviar email" + e.getLocalizedMessage();
		}
	}
	
	public String emailClienteFalhaCadastro(String destinatario) {
		try {
			SimpleMailMessage simpleMessage = new SimpleMailMessage();
			simpleMessage.setFrom(remetente);
			simpleMessage.setTo("panceri.leo@gmail.com");
			simpleMessage.setSubject("Erro ao processar cadastro");
			simpleMessage.setText("Caro " + destinatario + ". Ocorreu um erro ao processar sua solicitação.");
			javaMailSender.send(simpleMessage);
			return "Email enviado";
		}catch(Exception e) {
			return "Erro ao enviar email" + e.getLocalizedMessage();
		}
	}

}
