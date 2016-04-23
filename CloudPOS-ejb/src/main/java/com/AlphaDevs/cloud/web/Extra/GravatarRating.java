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
public enum GravatarRating {

	GENERAL_AUDIENCES("g"),

	PARENTAL_GUIDANCE_SUGGESTED("pg"),

	RESTRICTED("r"),

	XPLICIT("x");

	private String code;

	private GravatarRating(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

}