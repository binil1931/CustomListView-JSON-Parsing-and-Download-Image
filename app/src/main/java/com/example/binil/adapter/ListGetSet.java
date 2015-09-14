package com.example.binil.adapter;

public class ListGetSet {

	private String descritpion;
    private String imageURl;

	public ListGetSet(String descritpion,String imageURl) {
		// TODO Auto-generated constructor stub
        this.descritpion = descritpion;
        this.imageURl = imageURl;
	}


	public String getdescritpion() {
		return descritpion;
	}

	public void setdescritpion(String descritpion) {
		this.descritpion = descritpion;
	}

    public String getImageURl() {
        return imageURl;
    }

    public void setImageUR(String imageURl) {
        this.imageURl = imageURl;
    }

}
