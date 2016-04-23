

package com.AlphaDevs.cloud.web.Extra;

import com.AlphaDevs.cloud.web.Entities.CustomEmailSettings;
import com.AlphaDevs.cloud.web.Entities.EmailAddress;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;
import javax.activation.DataHandler;
import javax.activation.DataSource;

/**
 *
 * @author Mihindu Gajaba Karunarathne 
 * 
 * Alpha Development Team ( Pvt ) Ltd
 * www.AlphaDevs.com
 * Info@AlphaDevs.com
 * 
 */

public class MailUtil {
    
    private String username = "";           
    private String password = "";           
    
    public int sendMail(CustomEmailSettings emailSettings, DataSource datasource) {
        
//        String username =  "alphadevs.mail@gmail.com";           
//        String password = "KnightRider";  
        
        try {
            if(emailSettings != null){
                
                setUsername(emailSettings.getFromEmailAddress());
                setPassword(emailSettings.getFromEmailPassword());
                
                Properties props = System.getProperties();
                props.put("mail.transport.protocol", "smtp");
                props.put("mail.smtp.starttls.enable", "true");
                props.put("mail.smtp.host", emailSettings.getSmtpServer());
                props.put("mail.smtp.auth", "true");

                Authenticator auth = new SMTPAuthenticator();
                Session session = Session.getInstance(props, auth);
                // -- Create a new message --
                Message msg = new MimeMessage(session);
                // -- Set the FROM and TO fields --
                msg.setFrom(new InternetAddress(emailSettings.getFromEmailAddress()));
                InternetAddress[] addressTo = new InternetAddress[0];
                if(emailSettings.getToEmailAddress() != null){
                    addressTo = new InternetAddress[emailSettings.getToEmailAddress().size()];
                    List<EmailAddress> toEmailAddress = emailSettings.getToEmailAddress();
                    if(toEmailAddress != null && !toEmailAddress.isEmpty()){
                        for (int i = 0; i < emailSettings.getToEmailAddress().size(); i++) {
                            EmailAddress emailAddress = emailSettings.getToEmailAddress().get(i);
                            if(emailAddress != null && emailAddress.getEmailAddress() != null){
                                addressTo[i] = new InternetAddress(emailAddress.getEmailAddress());
                            }
                        }
                    }
                }
                
                msg.setRecipients(Message.RecipientType.TO, addressTo);
                msg.setSubject(emailSettings.getEmailSubject());
                // -- Set some other header information --
                msg.setHeader("Alpha Devs Email Module - WEB POS ", emailSettings.getFromEmailAddress());
                msg.setSentDate(new Date());

                // create the message part 
                MimeBodyPart messageBodyPart = new MimeBodyPart();

                //fill message
                messageBodyPart.setText(emailSettings.getMessageBody());

                Multipart multipart = new MimeMultipart();
                multipart.addBodyPart(messageBodyPart);

                // Part two is attachment

                if(datasource != null){
                    messageBodyPart = new MimeBodyPart();
                    messageBodyPart.setDataHandler(new DataHandler(datasource));
                    messageBodyPart.setFileName(emailSettings.getAttachmentPrefix() + " ");
                    multipart.addBodyPart(messageBodyPart);
                }


                // Put parts in message
                msg.setContent(multipart);

                // -- Send the message --
                Transport.send(msg);
                System.out.println("Message sent to " + emailSettings.getToEmailAddress() + " OK. With Attachment");
               
            }
             return 0;
            
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Exception " + ex);
            return -1;
        }
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    private class SMTPAuthenticator extends javax.mail.Authenticator {
        @Override
        public PasswordAuthentication getPasswordAuthentication() {
            //System.out.println("Trying Auth .. using Username : " + getUsername() + " and Pasword " + getPassword());
            return new PasswordAuthentication(getUsername(), getPassword());
        }
    }
}
