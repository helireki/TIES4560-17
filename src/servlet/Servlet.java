package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import clientToSOAPws.SOAPwsClient;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SOAPwsClient client = new SOAPwsClient();

		String country = request.getParameter("country").toString();
		String amount = request.getParameter("amount").toString();
		PrintWriter out = response.getWriter();
		if(amount.equals("")){
			out.write("error: Please, provide some value!");  
		}else{
			try {
				String amt = amount;
				if (amount.contains(",")) amt = amt.replace(',', '.');
				Double.parseDouble(amt);
				out.write(client.sendRequest(country, amt)); 
			}
			catch (URISyntaxException e){
			    e.printStackTrace();
		    }
			catch (NumberFormatException e) {
				out.write("error: Please, provide a numerical value!");
			};
			}
					 
		out.flush();
	    out.close();

	}

}
