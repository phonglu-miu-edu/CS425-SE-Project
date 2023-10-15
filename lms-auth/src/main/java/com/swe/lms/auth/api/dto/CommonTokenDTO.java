package com.swe.lms.auth.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.swe.lms.auth.api.constant.LmsConst;
import com.swe.lms.auth.api.util.DateTimeUtil;
import lombok.Data;

@Data
public class CommonTokenDTO
{
	protected String userName;
	
	protected String roleCd;

	@JsonIgnore
	protected long creDt;

	@JsonProperty("creDt")
	protected String strCreDt;
	
	public void setCreDt(long cre_dt) {
		this.creDt = cre_dt;
		this.strCreDt = DateTimeUtil.formatDateTime(LmsConst.DATE_TIME_UI, cre_dt);
	}
}
