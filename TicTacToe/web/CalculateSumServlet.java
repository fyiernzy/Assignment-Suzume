package TicTacToe.web;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("CalculateSumServlet")
public class CalculateSumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String numberInput = request.getParameter("numberInput"); // Get value from input field
		int sum = calculateSumOfDigits(numberInput); // Calculate sum of digits
		response.setContentType("text/plain"); // Set content type to plain text
		response.setCharacterEncoding("UTF-8"); // Set character encoding to UTF-8
		response.getWriter().write(Integer.toString(sum)); // Write sum of digits to response
	}
	
	// Calculate sum of digits of a number
	private int calculateSumOfDigits(String numberInput) {
		int sum = 0;
		for (int i = 0; i < numberInput.length(); i++) {
			char digit = numberInput.charAt(i);
			if (Character.isDigit(digit)) {
				sum += Character.getNumericValue(digit);
			}
		}
		System.out.println(sum);
		return sum;
	}
}


