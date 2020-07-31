package article.service;

import java.io.IOException;

import javax.servlet.http.Part;

public class WriteFileService {

	public void write(Part part) {
		try {
			part.write("c:/tempfiles/" + part.getSubmittedFileName());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
