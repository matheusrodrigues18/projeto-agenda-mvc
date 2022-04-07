package br.com.cotiinformatica.messages;

import java.util.Properties;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

/*
 * Classe utilizada para realizar o envio de emaiol autom�tico do sistema
 */
public class MailMessage {

	// parametros para envio de email
	private static final String CONTA = "cotiaulasnoreply@gmail.com";
	private static final String SENHA = "@Admin123456";
	private static final String SMTP = "smtp.gmail.com";
	private static final Integer PORTA = 587;

	// m�todo para fazer o envio do email
	public static void send(final String email, final String assunto, final String conteudo) throws Exception {

		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

		mailSender.setHost(SMTP);
		mailSender.setPort(PORTA);
		mailSender.setUsername(CONTA);
		mailSender.setPassword(SENHA);

		Properties javaMailProperties = new Properties();
		javaMailProperties.put("mail.smtp.starttls.enable", "true");
		javaMailProperties.put("mail.smtp.auth", "true");
		javaMailProperties.put("mail.smtp.transport.protocol", "smtp");
		javaMailProperties.put("mail.debug", "true");

		mailSender.setJavaMailProperties(javaMailProperties);

		MimeMessagePreparator preparator = new MimeMessagePreparator() {

			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {

				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

				helper.setTo(email);
				helper.setFrom(CONTA);

				helper.setSubject(assunto);
				helper.setText(conteudo);
			}
		};

		mailSender.send(preparator);
	}
}



