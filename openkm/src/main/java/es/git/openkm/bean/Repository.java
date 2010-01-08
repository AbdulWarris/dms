/**
 *  OpenKM, Open Document Management System (http://www.openkm.com)
 *  Copyright (C) 2006  GIT Consultors
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

package es.git.openkm.bean;

import java.io.Serializable;

/**
 * @author pavila
 * 
 */
public class Repository implements Serializable {
	private static final long serialVersionUID = -6920884124466924375L;

	public static final String OKM = "okm";
	public static final String OKM_URI = "http://www.openkm.org/1.0";
	public static final String ROOT = "okm:root";
	public static final String HOME = "okm:home";
	public static final String TRASH = "okm:trash";
	public static final String TEMPLATES = "okm:templates";
	public static final String THESAURUS = "okm:thesaurus";
	public static final String SYS_CONFIG = "okm:config";
	public static final String SYS_CONFIG_TYPE = "okm:sysConfig";
	public static final String SYS_CONFIG_UUID = "okm:uuid";
	public static final String SYS_CONFIG_VERSION = "okm:version";
	public static final String PERSONAL = "okm:personal";
	public static final String MAIL = "okm:mail";
	public static final String USER_CONFIG = "okm:config";
	public static final String USER_CONFIG_TYPE = "okm:userConfig";
	public static final String LOCK_TOKENS = "okm:lockTokens";
	
	private static String uuid;
	private static String updateMsg; 
	private String id;
	private String name;
	private String description;
		
	/**
	 * Get generated UUID
	 * 
	 * @return The UUID generated
	 */
	public static String getUuid() {
		return uuid;
	}

	/**
	 * Set the generated UUI
	 * 
	 * @param uuid
	 */
	public static void setUuid(String uuid) {
		Repository.uuid = uuid;
	}

	/**
	 * Get the retrieved update message
	 * 
	 * @param updateMsg
	 */
	public static String getUpdateMsg() {
		return updateMsg;
	}

	/**
	 * Set retrieved update message
	 * 
	 * @return The update message
	 */
	public static void setUpdateMsg(String updateMsg) {
		Repository.updateMsg = updateMsg;
	}

	/**
	 * @return
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set repository name.
	 * 
	 * @param name
	 *            The respository name.
	 */
	public void setName(String name) {
		this.name = name;
	}
}
