package com.resumeevaluater.service;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.resumeevaluater.model.ChatGptRequest;
import com.resumeevaluater.model.Messages;

@Component
public class ChatGPT {

	public static void main(String[] args) throws IOException {
		String apiKey = "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";
		String endpoint = "https://api.openai.com/v1/chat/completions";

//		Scanner scanner = new Scanner(System.in);
//		System.out.print("Enter the resume text: ");
//		String resumeText = scanner.nextLine();

		ChatGptRequest chatGptRequest = new ChatGptRequest();
		Messages messages = new Messages();

		List<Messages> list = new ArrayList<>();

		messages.setRole("user");
		messages.setContent(
				"Resume Akshay Jain aj3223@drexel.edu Philadelphia, PA 19104 | www.linkedin.com/in/akshay-jain-62251923 | 267-258-7702 EDUCATION Drexel University, College of Computing and Informatics Master of Science in Computer Science, GPA: 3.86 Jaypee Institute of Information Technology Bachelor of Technology in Electronics and Communication Eng.• Member of Robotics Club (Won 1st prize at a zonal autonomous robotics event, 2013) SKILLS Programming Languages: Java, Python, HTML, CSS, C, JavaScript Tools: SWIFT- GPS, Photoshop, Eclipse Software: Visual Studio, IntelliJ Idea, Microsoft Word, Excel, PowerPoint PROJECTS Digital Locking system: Philadelphia, PA June 2024 Noida, India June 2014 • The project involved creating a digital locking system for a carparking using circuit technology and programming the microcontroller using C. • Programming included sensor and actuator control ,security features, and user interface using Cand Python. • The system proved to be secure and efficient , and successful completion was due to the team's programming skills. Categorized Secure Phone Book: • The project was developed using Java and the Android SDK,making use of the Android Studio development environment.• Implemented robust security measures such as encryption for the stored data, using AES (Advanced Encryption Standard) algorithm with 256-bit key, and access control for the application to prevent unauthorized access.• The application was adopted by the NTPC (Government of India with over 10k+ users,resulting in increased efficiency and productivity as employees were able to quickly access and manage important contacts. EXPERIENCE Prakhar Software Solutions Pvt. Ltd New Delhi, India Software developer June 2014 - June 2015 • Developed software using C and JavaScript for various projects and systems• Expertise in web development ,utilizing HTML, CSS, and JavaScript forcreating responsiveanduser-friendlywebsites, resulting in a 20% increase in website traffic and engagement.• Conductedthoroughanalysisofuserfeedbacktoidentifyareasofimprovementandimplementchangestoenhanceuser experience, resulting in a 45% increase in customer satisfaction ratings. Bank Of India Vadodara, India Manager Foreign Exchange Department July 2015 – September 2022• ProgressedfromAssistantManager(Delhi,India)toManageroftheForeignExchangeDepartmentatBankofIndia (Govt of India), effectively leading a team of currency traders and processors.• Implementedandmonitoredcurrencytradingoperations,includingthedevelopmentandexecutionoftradingstrategies, resulting in a 13% increase in revenue for the department.• Expertlymanagedimport-exportrelateddutiesandacceleratedgovernmentliaison. job description Are you an experienced Specialty or Emergency Veterinarian that's looking for a dynamic leadership role as Medical Director of a brand new specialty hospital? If you'd like to be a part of a company that is rooted in providing an empathetic, collaborative, supportive, and trustworthy culture- Thrive Pet Healthcare is the spot for you! Our newly constructed space will be home to seven specialties! Our floor plan offers a modern and open concept feel including a large break area with all the amenities to relax and enjoy where you work. There is a thoughtfully planned patient care area and so much more. Take a sneak peek here! The Medical Director is a working part of the team, and in this role, we seek: Excellent diagnostic capabilities Compassionate individual, dedicated to client and patient care Outstanding referring veterinarian communicator Partnership with the Practice Manager and Support Office on recruiting, interviewing, and hiring of veterinarians.Involvement in budgetary decisions regarding inventory, products, and staff labor Comfort with coaching, issuing performance improvement plans, and disciplinary action Collaboration with Regional Operations team and support initiatives and policies Scheduling for the doctor team and to assist leads with their team schedules if needed A person to plan & lead regular doctor team meetings Attendance in all operational meetings and workshops (some travel required) Commitment to core values and coaching of other team members as well High emotional intelligence and comfort delivering regular performance evaluations for doctors and leadership team");

		list.add(messages);
		chatGptRequest.setMessages(list);
		chatGptRequest.setModel("gpt-3.5-turbo");

		// Make the API request
		URL url = new URL(endpoint);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setDoOutput(true);
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json");
		con.setRequestProperty("Authorization", "Bearer " + apiKey);
		ObjectMapper Obj = new ObjectMapper();

		String postData = Obj.writeValueAsString(chatGptRequest);
		con.getOutputStream().write(postData.getBytes());

		// Read in the API response
		BufferedInputStream reader = new BufferedInputStream(con.getInputStream());

		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

		int result2 = reader.read();
		StringBuilder responseBuilder = new StringBuilder();

		while (result2 != -1) {
			byteArrayOutputStream.write((byte) result2);
			responseBuilder.append(reader.read());
		}
		String response = responseBuilder.toString();

		System.out.println(response);

		reader.close();
		con.disconnect();
	}
}
