package beans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 購入データ
 * @author d-yamaguchi
 *
 */
public class BuyDataBeans  implements Serializable {
	private int id;
	private int userId;
	private int totalPrice;
	private int delivertMethodId;
	private Date buyDate;

	private String deliveryMethodName;
	private int deliveryMethodPrice;




	public BuyDataBeans(int i, int j, Date date, int k, int l, int m, String string) {
		// TODO 自動生成されたコンストラクター・スタブ
	}
	public BuyDataBeans() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public BuyDataBeans(BuyDataBeans data) {
		// TODO 自動生成されたコンストラクター・スタブ
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}


	public int getDelivertMethodId() {
		return delivertMethodId;
	}
	public void setDelivertMethodId(int delivertMethodId) {
		this.delivertMethodId = delivertMethodId;
	}
	public Date getBuyDate() {
		return buyDate;
	}
	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}
	public String getDeliveryMethodName() {
		return deliveryMethodName;
	}
	public void setDeliveryMethodName(String deliveryMethodName) {
		this.deliveryMethodName = deliveryMethodName;
	}

	public String getFormatDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH時mm分");
		return sdf.format(buyDate);
	}
	public int getDeliveryMethodPrice() {
		return deliveryMethodPrice;
	}
	public void setDeliveryMethodPrice(int deliveryMethodPrice) {
		this.deliveryMethodPrice = deliveryMethodPrice;
	}


	public void add() {
		// TODO 自動生成されたメソッド・スタブ

	}

	private int buyId;
	private int itemId;



	public int getBuyId() {
		return buyId;
	}
	public void setBuyId(int buyId) {
		this.buyId = buyId;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	private String name;
	private String detail;
	private int price;
	private String fileName;



	public String getName() {
		return name;
	}
	public void setName(String itemName) {
		this.name = itemName;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int itemPrice) {
		this.price = itemPrice;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String filename) {
		this.fileName = filename;
	}





}


