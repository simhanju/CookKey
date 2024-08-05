package com.ck.model.dto;

public class UserDTO {
	private String userid;
	private String userpw;
	private String username;
	private String nickname;
	private String phone;
	private String email;
	private String gender;
	private String birth;
	private String zipcode;
	private String addr;
	private String addrdetail;
	private String addretc;
	
	@Override
	public String toString() {
		return "ReplyDTO [userid=" + userid + ", userpw=" + userpw + ", username=" + username + ", nickname=" + nickname
				+ ", phone=" + phone + ", email=" + email + ", gender=" + gender + ", birth=" + birth + ", zipcode="
				+ zipcode + ", addr=" + addr + ", addrdetail=" + addrdetail + ", addretc=" + addretc + "]";
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUserpw() {
		return userpw;
	}

	public void setUserpw(String userpw) {
		this.userpw = userpw;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getAddrdetail() {
		return addrdetail;
	}

	public void setAddrdetail(String addrdetail) {
		this.addrdetail = addrdetail;
	}

	public String getAddretc() {
		return addretc;
	}

	public void setAddretc(String addretc) {
		this.addretc = addretc;
	}
}
