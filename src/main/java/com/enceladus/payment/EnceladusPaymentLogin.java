package com.enceladus.payment;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.enceladus.shoppingcart.domain.ShoppingCartProperties;
import com.enceladus.shoppingcart.manager.ShoppingCartPropertyManager;
import com.enceladus.shoppingcart.workflow.tasks.ShoppingCartTask;

public class EnceladusPaymentLogin extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(EnceladusPaymentLogin.class);

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doPost(request, response);
    }

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		log.info("Reading Property Variables ...");
		ShoppingCartProperties scProperties = ShoppingCartPropertyManager.populate();
		scProperties.print();

		log.info("Lets start the runs");
		String noOfErrorsStr = request.getParameter("noOfErrors");
		int noOfErrors = 20;
		try {
			noOfErrors = Integer.parseInt(noOfErrorsStr);
		} catch (Exception e) {
			log.error("Defaulting to noOfErrors {}", noOfErrors);
		}

		String threadName = Thread.currentThread().getName();
		ShoppingCartTask task = new ShoppingCartTask(scProperties, threadName);
		for (int i=0; i < noOfErrors; i++) {
			log.info("Generating error {}", i);
			task.run();
		}
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Enceladus-Payment</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Enceladus-Payment - Completed Generating " + noOfErrors + " Errors ....</h1>");
        out.println("<br/><br/>");
        out.println("<p><a href=\"../../index.html\">Enceladus Home</a></p>");
        out.println("</body>");
        out.println("</html>");
	}
	
}