package com.kh.jsp.controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PizzaServlet
 */
@WebServlet("/confirmPizza.do")
public class PizzaOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PizzaOrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//피자주문 요청 처리 -> 결제페이지
		
		//요청시 전달한 데이터를 추출, 데이터를 가공 -> 변수나 객체 넘겨받은 값을 기록
		String name = request.getParameter("userName");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String message = request.getParameter("message");

		String pizza = request.getParameter("pizza");
		String[] toppingArr = request.getParameter("topping"); //메서드명이 동일한데 당연히 같은 형식의 값을 리턴할 수 없다
		String[] sideArr = request.getParameter("side");
		String[] payment = request.getParameter("payment");
		
		//service > dao > db
		
		int price = 0;
		switch(pizza) {
		case "콤비네이션": price += 20000; break;
		case "포테이토": 
		case "고구마": price += 50000; break;
		default:  price += 30000; break;
		}
		if(toppingArr != null) {
			for(String topping : toppingArr) {
				switch(topping) {
				case"베이컨":
				case"파인애플": price += 3000; break;
				case"치즈크러스트":
				case"치즈바이트":price += 2000; break;
				default: price+= 3000;
				}
			}
		}
		if(sideArr != null) {
			for(String topping : sideArr) {
				switch(side) {
				case"콜라":
				case"사이다": price += 3000; break;
				case"핫소스":
				case"파마산":price += 2000; break;
				default: price+= 1000;
				}
			}
		}
		// 응답페이지에 필요한 정보를 request의 attribute영역에 담아서 전달
		request.setAttribute("name", name);
		request.setAttribute("phone", phone);
		request.setAttribute("address", address);
		request.setAttribute("message", message);

		request.setAttribute("pizza", pizza);
		request.setAttribute("toppingArr", toppingArr);
		request.setAttribute("sideArr", sideArr);
		request.setAttribute("payment", payment);

		// 요청처리 후 사용자가 보게될 응답페이지를 만들어서 전달
		request.getRequestDispatcher("views/pizzaPayment.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}