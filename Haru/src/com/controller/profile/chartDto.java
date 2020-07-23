package com.controller.profile;

public class chartDto {

	private String title;
	private int month;
	private int classpk;
	private int sumprice;


	//----------------------------
		private int firstday;
		private int secondday;
		private int thirdday;
		
		
		
	public chartDto(int firstday, int secondday, int thirdday) {
			super();
			this.firstday = firstday;
			this.secondday = secondday;
			this.thirdday = thirdday;
		}
	public int getFirstday() {
			return firstday;
		}
		public void setFirstday(int firstday) {
			this.firstday = firstday;
		}
		public int getSecondday() {
			return secondday;
		}
		public void setSecondday(int secondday) {
			this.secondday = secondday;
		}
		public int getThirdday() {
			return thirdday;
		}
		public void setThirdday(int thirdday) {
			this.thirdday = thirdday;
		}
		//--------------------------	
	
	public chartDto() {
		super();
	}
	public chartDto(String title, int month, int classpk, int sumprice) {
		super();
		this.title = title;
		this.month = month;
		this.classpk = classpk;
		this.sumprice = sumprice;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getClasspk() {
		return classpk;
	}
	public void setClasspk(int classpk) {
		this.classpk = classpk;
	}
	public int getSumprice() {
		return sumprice;
	}
	public void setSumprice(int sumprice) {
		this.sumprice = sumprice;
	}
	
}
