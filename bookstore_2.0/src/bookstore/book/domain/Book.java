package bookstore.book.domain;

import bookstore.category.domain.Category;

/*
 * ͼ����
 */
public class Book {

	private String bid; //��ID
	private String bname; //����
	private String briefing; //���
	private double price; //�۸�
	private String author; //����
	private String image; //����ͼƬ
	private Category category; //������Ϣ
	private boolean del; //�Ƿ�ɾ��

	public String getBriefing() {
		return briefing;
	}

	public void setBriefing(String briefing) {
		this.briefing = briefing;
	}

	public boolean isDel() {
		return del;
	}

	public void setDel(boolean del) {
		this.del = del;
	}

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public String getBname() {
		return bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
