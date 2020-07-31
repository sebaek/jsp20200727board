package article.service;

import java.util.Map;

import article.model.Writer;

public class WriteRequest {
	private Writer writer;
	private String title;
	private String content;
	private String fileName;

	public WriteRequest(Writer writer, String title,
			String content) {
		this(writer, title, content, "");
	}

	public WriteRequest(Writer writer, String title,
			String content, String fileName) {

		this.writer = writer;
		this.title = title;
		this.content = content;
		this.fileName = fileName;
	}
	
	public String getFileName() {
		return fileName;
	}

	public Writer getWriter() {
		return writer;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public void validate(Map<String, Boolean> errors) {
		if (title == null || title.trim().isEmpty()) {
			errors.put("title", true);
		}
	}
}
