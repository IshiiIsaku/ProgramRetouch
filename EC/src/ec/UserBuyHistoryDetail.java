package ec;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BuyDataBeans;
import dao.BuyDAO;

/**
 * 購入履歴画面
 * @author d-yamaguchi
 *
 */
@WebServlet("/UserBuyHistoryDetail")
public class UserBuyHistoryDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



		try {
			//GETパラメータとしてIDを受け取る
			 String i = request.getParameter("buy_id");

			 int id = Integer.parseInt(request.getParameter("buy_id"));

			//上の表を表示

			 BuyDAO	buyDAO = new BuyDAO();


			BuyDataBeans hyouji = buyDAO.shousai(id);


			request.setAttribute("hyouji",hyouji);



            //下の表を表示

			BuyDAO buyDAO1 = new BuyDAO();

			List<BuyDataBeans> shouhin = buyDAO1.shouhin(id);


			request.setAttribute("shouhin",shouhin);


			request.getRequestDispatcher(EcHelper.USER_BUY_HISTORY_DETAIL_PAGE).forward(request, response);



		} catch (SQLException e) {

			e.printStackTrace();
		}











	}
}
