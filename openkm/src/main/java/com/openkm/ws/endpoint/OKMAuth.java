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

package com.openkm.ws.endpoint;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.openkm.core.AccessDeniedException;
import com.openkm.core.PathNotFoundException;
import com.openkm.core.RepositoryException;
import com.openkm.core.UserAlreadyLoggerException;
import com.openkm.module.AuthModule;
import com.openkm.module.ModuleManager;
import com.openkm.ws.util.BytePair;
import com.openkm.ws.util.BytePairArray;
import com.openkm.ws.util.StringArray;

/**
 * Servlet Class
 * 
 * @web.servlet name="OKMAuth"
 * @web.servlet-mapping url-pattern="/OKMAuth"
 */

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class OKMAuth {
	private static Logger log = LoggerFactory.getLogger(OKMAuth.class);
	
	/* (non-Javadoc)
	 * @see com.openkm.module.AuthModule#login(java.lang.String, java.lang.String)
	 */
	public String login(String user, String pass) throws AccessDeniedException,
			UserAlreadyLoggerException, RepositoryException {
		log.debug("login(" + user + ", " + pass + ")");
		AuthModule am = ModuleManager.getAuthModule();
		String token = am.login(user, pass);
		log.debug("login: " + token);
		return token;
	}

	/* (non-Javadoc)
	 * @see com.openkm.module.AuthModule#logout(java.lang.String)
	 */
	public void logout(String token) throws AccessDeniedException, RepositoryException {
		log.debug("logout("+token+")");
		AuthModule am = ModuleManager.getAuthModule();
		am.logout(token); 
		log.debug("logout: void");
	}

	/* (non-Javadoc)
	 * @see com.openkm.module.AuthModule#getGrantedRoles(java.lang.String, java.lang.String)
	 */
	public BytePairArray getGrantedRoles(String token, String nodePath) throws PathNotFoundException,
			AccessDeniedException, RepositoryException {
		log.debug("getGrantedRoles("+token+", "+nodePath+")");
		AuthModule am = ModuleManager.getAuthModule();
		Map<String, Byte> hm = am.getGrantedRoles(token, nodePath);
		Set<String> keys = hm.keySet();
		BytePair[] tmp = new BytePair[keys.size()];
		int i=0;
		
		for (Iterator<String> it = keys.iterator(); it.hasNext(); ) {
			String key = it.next();
			BytePair p = new BytePair();
			p.setKey(key);
			p.setValue((Byte) hm.get(key));
			tmp[i++] = p;
		}
		
		BytePairArray uh = new BytePairArray();
		uh.setValue(tmp);
		log.debug("getGrantedRoles: " + uh);
		return uh;
	}

	/* (non-Javadoc)
	 * @see com.openkm.module.AuthModule#getGrantedUsers(java.lang.String, java.lang.String)
	 */
	public BytePairArray getGrantedUsers(String token, String nodePath) throws PathNotFoundException,
			AccessDeniedException, RepositoryException {
		log.debug("getGrantedUsers("+token+", "+nodePath+")");
		AuthModule am = ModuleManager.getAuthModule();
		Map<String, Byte> hm = am.getGrantedUsers(token, nodePath);
		Set<String> keys = hm.keySet();
		BytePair[] tmp = new BytePair[keys.size()];
		int i=0;
		
		for (Iterator<String> it = keys.iterator(); it.hasNext(); ) {
			String key = it.next();
			BytePair p = new BytePair();
			p.setKey(key);
			p.setValue((Byte) hm.get(key));
			tmp[i++] = p;
		}
		
		BytePairArray uh = new BytePairArray();
		uh.setValue(tmp);
		log.debug("getGrantedUsers: " + uh);
		return uh;
	}

	/* (non-Javadoc)
	 * @see com.openkm.module.AuthModule#getRoles(java.lang.String)
	 */
	public StringArray getRoles(String token) throws RepositoryException {
		log.debug("getRoles("+token+")");
		AuthModule am = ModuleManager.getAuthModule();
		StringArray sa = new StringArray();
		sa.setValue((String[]) am.getRoles(token).toArray(new String[0])); 
		log.debug("getRoles: " + sa);
		return sa;
	}

	/* (non-Javadoc)
	 * @see com.openkm.module.AuthModule#getUsers(java.lang.String)
	 */
	public StringArray getUsers(String token) throws RepositoryException {
		log.debug("getUsers("+token+")");
		AuthModule am = ModuleManager.getAuthModule();
		StringArray sa = new StringArray();
		sa.setValue((String[]) am.getUsers(token).toArray(new String[0])); 
		log.debug("getUsers: " + sa);
		return sa;
	}

	/* (non-Javadoc)
	 * @see com.openkm.module.AuthModule#grantRole(java.lang.String, java.lang.String, java.lang.String, int, boolean)
	 */
	public void grantRole(String token, String nodePath, String role, int permissions, boolean recursive)
			throws PathNotFoundException, AccessDeniedException, RepositoryException {
		log.debug("grantRole("+token+", "+nodePath+", "+role+", "+permissions+", "+recursive+")");
		AuthModule am = ModuleManager.getAuthModule();
		am.grantRole(token, nodePath, role, permissions, recursive); 
		log.debug("grantRole: void");
	}

	/* (non-Javadoc)
	 * @see com.openkm.module.AuthModule#grantUser(java.lang.String, java.lang.String, java.lang.String, int, boolean)
	 */
	public void grantUser(String token, String nodePath, String user, int permissions, boolean recursive)
			throws PathNotFoundException, AccessDeniedException, RepositoryException {
		log.debug("grantUser("+token+", "+nodePath+", "+user+", "+permissions+", "+recursive+")");
		AuthModule am = ModuleManager.getAuthModule();
		am.grantUser(token, nodePath, user, permissions, recursive); 
		log.debug("grantUser: void");
	}

	/* (non-Javadoc)
	 * @see com.openkm.module.AuthModule#revokeRole(java.lang.String, java.lang.String, java.lang.String, int, boolean)
	 */
	public void revokeRole(String token, String nodePath, String user, int permissions, boolean recursive)
			throws PathNotFoundException, AccessDeniedException, RepositoryException {
		log.debug("revokeRole("+token+", "+nodePath+", "+user+", "+permissions+", "+recursive+")");
		AuthModule am = ModuleManager.getAuthModule();
		am.revokeRole(token, nodePath, user, permissions, recursive); 
		log.debug("revokeRole: void");
	}

	/* (non-Javadoc)
	 * @see com.openkm.module.AuthModule#revokeUser(java.lang.String, java.lang.String, java.lang.String, int, boolean)
	 */
	public void revokeUser(String token, String nodePath, String user, int permissions, boolean recursive)
			throws PathNotFoundException, AccessDeniedException, RepositoryException {
		log.debug("revokeUser("+token+", "+nodePath+", "+user+", "+permissions+", "+recursive+")");
		AuthModule am = ModuleManager.getAuthModule();
		am.revokeUser(token, nodePath, user, permissions, recursive); 
		log.debug("revokeUser: void");
	}
}
