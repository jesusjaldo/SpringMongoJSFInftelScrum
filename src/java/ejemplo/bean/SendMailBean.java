/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplo.bean;

import ejemplo.collection.Projects;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author loubna
 */

public class SendMailBean extends Thread{

    protected Properties properties = new Properties();
    protected Session session;
    public Projects project;
    public String emailReceptror;

    public SendMailBean(Projects project, String emailReceptror) {
        this.project = project;
        this.emailReceptror = emailReceptror;
    }

    public void run()  {
      while(true){
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.port", 587);
        properties.put("mail.smtp.user", "inftelscrum2016@gmail.com");
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(properties);
         
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("inftelscrum2016@gmail.com"));

            message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailReceptror));
            message.setSubject("Eres un nuevo miembro del proyecto :" + project.getNombre());
            message.setText(project.getAdmin() + " te ha añadido como miembro de un nuevo proyecto");
           
            Transport t = session.getTransport("smtp");
            t.connect((String) properties.get("mail.smtp.user"), "637212375");
            t.sendMessage(message, message.getAllRecipients());
          
           
            System.out.println("mensaje  mandado");
            t.close();
            this.stop();
        } catch (MessagingException me) {
            System.out.println("el mensaje no se ha mandado");
            throw new RuntimeException(me);
        }
    }
    }
}
