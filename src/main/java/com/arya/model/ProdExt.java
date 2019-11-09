package com.arya.model;

public class ProdExt {
		int prodid;
		String prodname;
		int quantity;
		int limit;
		String low;
		public int getProdid() {
			return prodid;
		}
		public void setProdid(int prodid) {
			this.prodid = prodid;
		}
		public String getProdname() {
			return prodname;
		}
		public void setProdname(String prodname) {
			this.prodname = prodname;
		}
		public int getQuantity() {
			return quantity;
		}
		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}
		public int getLimit() {
			return limit;
		}
		public void setLimit(int limit) {
			this.limit = limit;
		}
		public String getLow() {
			return low;
		}
		public void setLow(String low) {
			this.low = low;
		}
		public ProdExt() {
			super();
			// TODO Auto-generated constructor stub
		}
		public ProdExt(int prodid, String prodname, int quantity, int limit, String low) {
			super();
			this.prodid = prodid;
			this.prodname = prodname;
			this.quantity = quantity;
			this.limit = limit;
			this.low = low;
		}
		

}
