package article.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import article.service.ArticleData;
import article.service.ArticleNotFoundException;
import article.service.DeleteArticleService;
import article.service.DeleteRequest;
import article.service.DeleteRequest;
import article.service.PermissionDeniedException;
import article.service.ReadArticleService;
import auth.service.User;
import member.service.InvalidPasswordException;
import mvc.controller.CommandHandler;

public class DeleteArticleHandler implements CommandHandler {

	private static String FORM_VIEW = "/WEB-INF/view/deleteForm.jsp";
	private DeleteArticleService deleteService = new DeleteArticleService();
	private ReadArticleService readService = new ReadArticleService();

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
			HttpServletResponse res) throws IOException {
		try {
			String noVal = req.getParameter("no");
			int no = Integer.parseInt(noVal);

			ArticleData articleData = readService.getArticle(no,
					false);
			User authUser = (User) req.getSession()
					.getAttribute("authUser");

			if (!canModify(authUser, articleData)) {
				res.sendError(HttpServletResponse.SC_FORBIDDEN);
				return null;
			}

			DeleteRequest delReq = new DeleteRequest(
					authUser.getId(), no, null);

			req.setAttribute("delReq", delReq);
			return FORM_VIEW;
		} catch (ArticleNotFoundException e) {
			e.printStackTrace();
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
		return null;
	}

	private String processSubmit(HttpServletRequest req,
			HttpServletResponse res) throws IOException {

		User authUser = (User) req.getSession()
				.getAttribute("authUser");
		String noVal = req.getParameter("no");
		int no = Integer.parseInt(noVal);

		DeleteRequest delReq = new DeleteRequest(
				authUser.getId(), no,
				req.getParameter("password"));
		req.setAttribute("delReq", delReq);

		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		delReq.validate(errors);

		if (!errors.isEmpty()) {
			return FORM_VIEW;
		}

		try {
			deleteService.delete(delReq);
			return "/WEB-INF/view/deleteSuccess.jsp";
		} catch (ArticleNotFoundException e) {
			e.printStackTrace();
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
		} catch (PermissionDeniedException e) {
			e.printStackTrace();
			res.sendError(HttpServletResponse.SC_FORBIDDEN);
		} catch (InvalidPasswordException e) {
			errors.put("invalidPassword", true);
			return FORM_VIEW;
		}

		return null;

	}

	private boolean canModify(User authUser,
			ArticleData articleData) {

		String writerId = articleData.getArticle().getWriter()
				.getId();
		return authUser.getId().equals(writerId);
	}

}
