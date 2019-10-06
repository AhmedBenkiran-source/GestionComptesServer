package org.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.FetchProfile;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.Flags.Flag;
import javax.mail.internet.ContentType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.FlagTerm;

import org.entities.Compte;
import org.entities.Mail;
import org.jsoup.Jsoup;
import org.repositories.CompteRepository;
import org.repositories.MailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmailServiceImpl implements EmailService {
	
	@Autowired
	private CompteRepository compteRepository;
	@Autowired
	private MailRepository mailRepository;
	Compte co = null;

	@Override
	public void sendmail(Mail mail) {
		List<Compte> l = compteRepository.findAll();

		for (Compte compte : l) {
			if (compte.getMail().equals(mail.getMail_emetteur())) {
				co = compte;
				System.out.println("mail d =>" + co.getMail());

				break;
			}
		}
		final String username = mail.getMail_emetteur();
		final String passwd = co.getMtp();
		final String from = mail.getMail_emetteur();

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", co.getSmtpServer());
		props.put("mail.smtp.port", co.getSmtpPort());

		Session session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, passwd);
			}
		});

		try {
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(from));
			msg.setRecipients(Message.RecipientType.TO, mail.getMail_recepteur());
			msg.setSubject(mail.getMail_recepteur());
			msg.setSentDate(new Date());
			msg.setText(mail.getContents());

			Transport.send(msg);

		} catch (MessagingException e) {
			System.out.println("send failed, exception: " + e);
		}
		System.out.println("Sent Ok");
	}

	Folder inbox;

	// Constructor of the calss.
	public static  String USERNAME = null;
	public static  String PASSWORD = null;

	@Override
	public void receivemail(Long id_user) {
		List<Compte> ListeCompte =  compteRepository.findAll();
		for (Compte compte : ListeCompte) {
			if(compte.getUser_id()==id_user) {
				System.out.println(compte.getMail());
		
			printemail(compte);
			}
			

		}
	}

	public void printemail(Compte compte) {

		/* Set the mail properties */
		Properties props = System.getProperties();
		props.setProperty("mail.store.protocol", "imaps");

		try {
			/* Create the session and get the store for read the mail. */
			Session session = Session.getDefaultInstance(props, null);
			Store store = session.getStore("imaps");
			store.connect(compte.getImapServer(), compte.getMail(), compte.getMtp());
			
			// store.connect("imap-mail.outlook.com",USERNAME, PASSWORD);

			/* Mention the folder name which you want to read. */
			inbox = store.getFolder("Inbox");
			//System.out.println("No of Unread Messages : " + inbox.getUnreadMessageCount());

			/* Open the inbox using store. */
			inbox.open(Folder.READ_WRITE);

			/* Get the messages which is unread in the Inbox */
			Message messages[] = inbox.search(new FlagTerm(new Flags(Flag.SEEN), false));

			/* Use a suitable FetchProfile */
			FetchProfile fp = new FetchProfile();
			fp.add(FetchProfile.Item.ENVELOPE);
			fp.add(FetchProfile.Item.CONTENT_INFO);
			// fp.add(FetchProfile.Item.FLAGS);

			inbox.fetch(messages, fp);

			try {
				printAllMessages(messages, compte.getMail());

				inbox.close(true);
				store.close();
			} catch (Exception ex) {
				System.out.println("Exception arise at the time of read mail");
				ex.printStackTrace();
			}
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (MessagingException e) {
			e.printStackTrace();
			System.exit(2);
		}
	}

	public void printAllMessages(Message[] msgs, String mail) throws Exception {
		for (int i = 0; i < 1; i++) {
			System.out.println("MESSAGE #" + (i + 1) + ":");
			printEnvelope(msgs[i], mail);

		}
	}

	public void printEnvelope(Message message, String mail) throws Exception {
		Mail mailRecive = new Mail();
		String body = null;
		/*// FROM
		System.out.println("---------------------------------");
		System.out.println("Email Number " + message.getMessageNumber());
		System.out.println("Subject: " + message.getSubject());
		System.out.println("From: " + message.getFrom()[0]);
		System.out.println("Date " + message.getSentDate());
		
		Address[] toAddress;
		toAddress = message.getRecipients(Message.RecipientType.TO);
		*/mailRecive.setDate(message.getSentDate());
		mailRecive.setObject(message.getSubject());
		mailRecive.setType("recive");
		mailRecive.setMail_emetteur(message.getFrom()[0].toString());
		

		mailRecive.setMail_recepteur(mail);

		if (message.isMimeType("text/plain")) {

			body = (String) message.getContent();
			//System.out.println("CONTENT TEXT:" + body);

		} else if (message.isMimeType("multipart/*")) {
			MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
			body = getTextFromMimeMultipart(mimeMultipart);
			//System.out.println("CONTENT MULTIPart:" + body);

		}
		mailRecive.setContents(body);

		mailRepository.save(mailRecive);

	}

	public String getTextFromMimeMultipart(MimeMultipart mimeMultipart) throws IOException, MessagingException {

		int count = mimeMultipart.getCount();
		if (count == 0)
			throw new MessagingException("Multipart with no body parts not supported.");
		boolean multipartAlt = new ContentType(mimeMultipart.getContentType()).match("multipart/alternative");
		if (multipartAlt)
			// alternatives appear in an order of increasing
			// faithfulness to the original content. Customize as req'd.
			return getTextFromBodyPart(mimeMultipart.getBodyPart(count - 1));
		String result = "";
		for (int i = 0; i < count; i++) {
			BodyPart bodyPart = mimeMultipart.getBodyPart(i);
			result += getTextFromBodyPart(bodyPart);
		}
		return result;
	}

	private String getTextFromBodyPart(BodyPart bodyPart) throws IOException, MessagingException {

		String result = "";
		if (bodyPart.isMimeType("text/plain")) {
			result = (String) bodyPart.getContent();
		} else if (bodyPart.isMimeType("text/html")) {
			String html = (String) bodyPart.getContent();
			result = Jsoup.parse(html).text();
		} else if (bodyPart.getContent() instanceof MimeMultipart) {
			result = getTextFromMimeMultipart((MimeMultipart) bodyPart.getContent());
		}
		return result;
	}
}
