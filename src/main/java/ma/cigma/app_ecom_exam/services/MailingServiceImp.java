package ma.cigma.app_ecom_exam.services;

import java.io.File;

import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
@Service
@Transactional
public class MailingServiceImp implements MailingService {

	@Autowired
    private JavaMailSender javaMailSender;
	@Override
	public void sendEmail(String body, String title, String to, String from,String fileName,File attachement) throws Exception {
		  MimeMessage msg = javaMailSender.createMimeMessage();

	        // true = multipart message
	        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
	        helper.setTo(to);

	        helper.setSubject(title);
	        helper.setFrom(from);

	        // default = text/plain
	        //helper.setText("Check attachment for image!");

	        // true = text/html
	        helper.setText(body, true);
	        if(fileName != null)
	        helper.addAttachment(fileName, attachement);
			// hard coded a file path
	        //FileSystemResource file = new FileSystemResource(new File("path/android.png"));

	        //helper.addAttachment("my_photo.png", new ClassPathResource("android.png"));
	        javaMailSender.send(msg);

	}


	
}
