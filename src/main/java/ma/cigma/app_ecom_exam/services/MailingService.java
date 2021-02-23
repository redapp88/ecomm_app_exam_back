package ma.cigma.app_ecom_exam.services;

import java.io.File;

public interface MailingService {
	public void sendEmail(String body,String title,String to,String from,String fileName,File attachement) throws Exception;
}
