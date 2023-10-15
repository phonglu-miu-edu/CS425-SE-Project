package com.swe.lms.auth.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.swe.lms.auth.api.constant.LmsConst;
import com.swe.lms.auth.api.util.DateTimeUtil;

public class CommonTokenDTO 
{
	protected String user_id;
	
	protected String role_cd;

	@JsonIgnore
	protected long cre_dt;

	@JsonProperty("cre_dt")
	protected String strCreDt;
	
	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public long getCre_dt() {
		return cre_dt;
	}

	public void setCre_dt(long cre_dt) {
		this.cre_dt = cre_dt;
		this.strCreDt = DateTimeUtil.formatDateTime(LmsConst.DATE_TIME_UI, cre_dt);
	}

	public String getStrCreDt() {
		return strCreDt;
	}

	public void setStrCreDt(String strCreDt) {
		this.strCreDt = strCreDt;
	}

	public String getRole_cd() {
		return role_cd;
	}

	public void setRole_cd(String role_cd) {
		this.role_cd = role_cd;
	}
}
