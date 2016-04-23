/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.AlphaDevs.cloud.web.Extra;

/**
 *
 * @author mkarunarathne
 */
public enum GravatarDefaultImage {

	GRAVATAR_ICON(""),

	IDENTICON("identicon"),

	MONSTERID("monsterid"),

	WAVATAR("wavatar"),

	HTTP_404("404");

	private String code;

	private GravatarDefaultImage(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

}