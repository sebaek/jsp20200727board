package article.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.controller.CommandHandler;

public class DeleteArticleHandler implements CommandHandler {

	private static String FORM_VIEW = "/WEB-INF/view/deleteArticleForm.jsp";
	private DeleteArticleService service = new DeleteArticleService();

	@Override
	public String process(HttpServletRequest req,
			HttpServletResponse res) throws Exception {

		String method = req.getMethod();

		switch (method) {
		
		case "GET":
			return processForm(req, res);
			
		case "POST":
			return processSubmit(req, res);

		default:
			res.sendError(
					HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;

		}

	}

	private String processForm(HttpServletRequest req,
			HttpServletResponse res) {
		return FORM_VIEW;
	}

	private String processSubmit(HttpServletRequest req,
			HttpServletResponse res) {

		
		service.deleteArticle();
		return null;
	}

}













