package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Model;

/**
 * Servlet implementation class AssignmentServlet
 */
@WebServlet("/main")
public class AssignmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		Model.init();
	}

	// DoGet호출
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		execute(request, response);

	}

	// DoPost호출 (UTF-8 주의!)
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		execute(request, response);
	}

	private void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		String name = request.getParameter("name");

		String targetJsp = "main.html";
		
		System.out.println(action);
		
		if ("insert".equals(action)) {
			targetJsp = "regist.html";
			if (name != null) {
				Model.insertProduct(request);
				targetJsp = "success.jsp";
			}
		}

		if ("select_all".equals(action)) {
			Model.selectProductAll(request);
			System.out.println(request.getAttribute("_product"));
			targetJsp = "select_all.jsp";
		}
		
		if ("update".equals(action)) {
			targetJsp = "update.html";
			if (name != null) {
				if (Model.getProductByName(request) != null) {
					Model.updateProduct(request);
					targetJsp = "updated.jsp";
				} else {
					targetJsp = "fail.jsp";
				}
			}
		}

		if ("select".equals(action)) {
			targetJsp = "select.html";
			if (name != null) {
				if (Model.getProductByName(request) != null) {
					Model.getProductByName(request);
					targetJsp = "select.jsp";
				} else {
					targetJsp = "fail.jsp";
				}
			}
		}
		
		
		
		if ("delete".equals(action)) {
			targetJsp = "delete.html";
			if (name != null) {
				if (Model.getProductByName(request) != null) {
					Model.deleteProduct(request);
					targetJsp = "delete.jsp";
				} else {
					targetJsp = "fail.jsp";
				}
			}
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(targetJsp);
		dispatcher.forward(request, response);
	}

}
