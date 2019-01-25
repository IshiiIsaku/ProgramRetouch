package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import base.DBManager;
import beans.BuyDataBeans;

/**
 *
 * @author d-yamaguchi
 *
 */
public class BuyDAO {


	/**
	 * 購入情報登録処理
	 * @param bdb 購入情報
	 * @throws SQLException 呼び出し元にスローさせるため
	 */
	public static int insertBuy(BuyDataBeans bdb) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		int autoIncKey = -1;
		try {
			con = DBManager.getConnection();
			st = con.prepareStatement(
					"INSERT INTO t_buy(user_id,total_price,delivery_method_id,create_date) VALUES(?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, bdb.getUserId());
			st.setInt(2, bdb.getTotalPrice());
			st.setInt(3, bdb.getDelivertMethodId());
			st.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
			st.executeUpdate();

			ResultSet rs = st.getGeneratedKeys();
			if (rs.next()) {
				autoIncKey = rs.getInt(1);
			}
			System.out.println("inserting buy-datas has been completed");

			return autoIncKey;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

	/**
	 * 購入IDによる購入情報検索
	 * @param buyId
	 * @return BuyDataBeans
	 * 				購入情報のデータを持つJavaBeansのリスト
	 * @throws SQLException
	 * 				呼び出し元にスローさせるため
	 */
	public static BuyDataBeans getBuyDataBeansByBuyId(int buyId) throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = DBManager.getConnection();

			st = con.prepareStatement(
					"SELECT * FROM t_buy"
							+ " JOIN m_delivery_method"
							+ " ON t_buy.delivery_method_id = m_delivery_method.id"
							+ " WHERE t_buy.id = ?");
			st.setInt(1, buyId);

			ResultSet rs = st.executeQuery();

			BuyDataBeans bdb = new BuyDataBeans();
			if (rs.next()) {
				bdb.setId(rs.getInt("id"));
				bdb.setTotalPrice(rs.getInt("total_price"));
				bdb.setBuyDate(rs.getTimestamp("create_date"));
				bdb.setDelivertMethodId(rs.getInt("delivery_method_id"));
				bdb.setUserId(rs.getInt("user_id"));
				bdb.setDeliveryMethodPrice(rs.getInt("price"));
				bdb.setDeliveryMethodName(rs.getString("name"));
			}

			System.out.println("searching BuyDataBeans by buyID has been completed");

			return bdb;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

	//表の表示

	public  List<BuyDataBeans> hyou() throws SQLException {
		Connection con = null;
		PreparedStatement st = null;
		List<BuyDataBeans> shousaiList = new ArrayList<BuyDataBeans>();
		try {
			con = DBManager.getConnection();

			st = con.prepareStatement(
					"SELECT * FROM t_buy JOIN m_delivery_method ON t_buy.delivery_method_id = m_delivery_method.id  ORDER BY t_buy.id DESC");

			ResultSet rs = st.executeQuery();


			while (rs.next()) {
				BuyDataBeans bdb = new BuyDataBeans();

				bdb.setId(rs.getInt("id"));
				bdb.setTotalPrice(rs.getInt("total_price"));
				bdb.setBuyDate(rs.getTimestamp("create_date"));
				bdb.setDelivertMethodId(rs.getInt("delivery_method_id"));
				bdb.setUserId(rs.getInt("user_id"));
				bdb.setDeliveryMethodPrice(rs.getInt("price"));
				bdb.setDeliveryMethodName(rs.getString("name"));



				shousaiList.add(bdb);
			}


		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}

		return shousaiList;
	}






	  //購入詳細

	 public BuyDataBeans shousai(int id) throws SQLException{
	        Connection con = null;
	        PreparedStatement st = null;
	        try {
	            // データベースへ接続
	        	con = DBManager.getConnection();


	            // SELECT文を準備
	        	st = con.prepareStatement("SELECT * FROM t_buy JOIN m_delivery_method ON t_buy.delivery_method_id = m_delivery_method.id WHERE t_buy.id=?");


	             // SELECTを実行し、結果表を取得
	            // 実行し、結果表を取得

	        	st.setInt(1, id);

	        	ResultSet rs = st.executeQuery();

	             // 主キーに紐づくレコードは1件のみなので、rs.next()は1回だけ行う
	            if (!rs.next()) {
	                return null;
	            }

	            BuyDataBeans bdb = new BuyDataBeans();

					bdb.setId(rs.getInt("id"));
					bdb.setTotalPrice(rs.getInt("total_price"));
					bdb.setBuyDate(rs.getTimestamp("create_date"));
					bdb.setDelivertMethodId(rs.getInt("delivery_method_id"));
					bdb.setUserId(rs.getInt("user_id"));
					bdb.setDeliveryMethodPrice(rs.getInt("price"));
					bdb.setDeliveryMethodName(rs.getString("name"));

	            return bdb;

	        } catch (SQLException e) {
	            e.printStackTrace();
	            return null;
	        }


	 }




//	 public BuyDataBeans shousai(int id) {
//	        Connection conn = null;
//	        try {
//	            // データベースへ接続
//	            conn = DBManager.getConnection();
//
//	            // SELECT文を準備
//	            String sql = "SELECT * FROM t_buy JOIN m_delivery_method ON t_buy.delivery_method_id = m_delivery_method.id WHERE t_buy.id="+"'"+id+"'";
//	            //UserJouhoushousaiServlet?id=${user.loginId};
//
//	             // SELECTを実行し、結果表を取得
//	            // 実行し、結果表を取得
//	            PreparedStatement pStmt = conn.prepareStatement(sql);
//	            pStmt.setInt(1,id);
//
//	            ResultSet rs = pStmt.executeQuery();
//
//	             // 主キーに紐づくレコードは1件のみなので、rs.next()は1回だけ行う
//	            if (!rs.next()) {
//	                return null;
//	            }
//
//	            int id1=rs.getInt("id");
//				int totalPrice=rs.getInt("total_price");
//				Timestamp createDate=rs.getTimestamp("create_date");
//				int deriveryMethod=rs.getInt("delivery_method_id");
//				int userId=rs.getInt("user_id");
//				int price=rs.getInt("price");
//				String name=rs.getString("name");
//
//
//	            return new BuyDataBeans(id1,totalPrice,createDate,deriveryMethod,userId,price,name);
//
//	        } catch (SQLException e) {
//	            e.printStackTrace();
//	            return null;
//	        } finally {
//	            // データベース切断
//	            if (conn != null) {
//	                try {
//	                    conn.close();
//	                } catch (SQLException e) {
//	                    e.printStackTrace();
//	                    return null;
//	                }
//	            }}}


//商品詳細

	            //sql=SELECT * FROM t_buy_detail JOIN m_item ON t_buy_detail.item_id=m_item.id WHERE t_buy_detail.buy_id=id

public  List<BuyDataBeans> shouhin(int id) throws SQLException {
	Connection con = null;
	PreparedStatement st = null;
	List<BuyDataBeans> shouhinList = new ArrayList<BuyDataBeans>();
	try {
		con = DBManager.getConnection();

		st = con.prepareStatement(
				"SELECT * FROM t_buy_detail JOIN m_item ON t_buy_detail.item_id=m_item.id WHERE t_buy_detail.buy_id=?");


		st.setInt(1, id);
		ResultSet rs = st.executeQuery();


		while (rs.next()) {
			BuyDataBeans bdb = new BuyDataBeans();


			bdb.setId(rs.getInt("id"));
			bdb.setBuyId(rs.getInt("buy_id"));
			bdb.setId(rs.getInt("item_id"));
			bdb.setName(rs.getString("name"));
			bdb.setPrice(rs.getInt("price"));
			bdb.setDeliveryMethodPrice(rs.getInt("price"));
			bdb.setDeliveryMethodName(rs.getString("name"));

			shouhinList.add(bdb);
		}


	} catch (SQLException e) {
		System.out.println(e.getMessage());
		throw new SQLException(e);
	} finally {
		if (con != null) {
			con.close();
		}
	}

	return shouhinList;
}

}












