/**
 *  OpenKM, Open Document Management System (http://www.openkm.com)
 *  Copyright (c) 2006-2010  Paco Avila & Josep Llort
 *
 *  No bytes were intentionally harmed during the development of this application.
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *  
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License along
 *  with this program; if not, write to the Free Software Foundation, Inc.,
 *  51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package com.openkm.dao.bean.extension;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Contact
 * 
 * @author jllort
 *
 */
public class Contact implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name = "";
	private String mail = "";
	private String phone = "";
	private String fax = "";
	private String mobile = "";
	private String address = "";
	private String city = "";
	private String pc = "";
	private String province = "";
	private String country = "";
	private String web = "";
	private String notes = "";
	private Set<DocumentContact> uuids = new HashSet<DocumentContact>();

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPc() {
		return pc;
	}

	public void setPc(String pc) {
		this.pc = pc;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	public Set<DocumentContact> getUuids() {
		return uuids;
	}

	public void setUuids(Set<DocumentContact> uuids) {
		this.uuids = uuids;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		sb.append("id="); sb.append(id);
		sb.append(", name="); sb.append(name);
		sb.append(", mail="); sb.append(mail);
		sb.append(", phone="); sb.append(phone);
		sb.append(", fax="); sb.append(fax);
		sb.append(", mobile="); sb.append(mobile);
		sb.append(", address="); sb.append(address);
		sb.append(", city="); sb.append(city);
		sb.append(", pc="); sb.append(pc);
		sb.append(", province="); sb.append(province);
		sb.append(", country="); sb.append(country);
		sb.append(", web="); sb.append(web);
		sb.append(", notes="); sb.append(notes);
		sb.append(", uuids="); sb.append(uuids);
		sb.append("}");
		return sb.toString();
	}
}
