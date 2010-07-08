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

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.jboss.annotation.security.SecurityDomain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.openkm.bean.Folder;
import com.openkm.core.AccessDeniedException;
import com.openkm.core.DatabaseException;
import com.openkm.core.PathNotFoundException;
import com.openkm.core.RepositoryException;
import com.openkm.module.ModuleManager;
import com.openkm.module.RepositoryModule;

/**
 * Servlet Class
 * 
 * @web.servlet name="OKMRepository"
 * @web.servlet-mapping url-pattern="/OKMRepository"
 */

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
@SecurityDomain("OpenKM")
public class OKMRepository {
	private static Logger log = LoggerFactory.getLogger(OKMRepository.class);

	@WebMethod
	public Folder getRootFolder() throws PathNotFoundException, RepositoryException, DatabaseException {
		log.debug("getRootFolder()");
		RepositoryModule rm = ModuleManager.getRepositoryModule();
		Folder root = rm.getRootFolder();
		log.debug("getRootFolder: {}", root);
		return root;
	}

	@WebMethod
	public Folder getTrashFolder() throws PathNotFoundException, RepositoryException, DatabaseException {
		log.debug("getTrashFolder()");
		RepositoryModule rm = ModuleManager.getRepositoryModule();
		Folder trash = rm.getTrashFolder();
		log.debug("getTrashFolder: {}", trash);
		return trash;
	}

	@WebMethod
	public Folder getTemplatesFolder() throws PathNotFoundException, RepositoryException, DatabaseException {
		log.debug("getTemplatesFolder()");
		RepositoryModule rm = ModuleManager.getRepositoryModule();
		Folder templatesFolder = rm.getTemplatesFolder();
		log.debug("getTemplatesFolder: {}", templatesFolder);
		return templatesFolder;
	}

	@WebMethod
	public Folder getPersonalFolder() throws PathNotFoundException, RepositoryException, DatabaseException {
		log.debug("getPersonalFolder()");
		RepositoryModule rm = ModuleManager.getRepositoryModule();
		Folder personalFolder = rm.getPersonalFolder();
		log.debug("getPersonalFolder: {}", personalFolder);
		return personalFolder;
	}
	
	@WebMethod
	public Folder getMailFolder() throws PathNotFoundException, RepositoryException, DatabaseException {
		log.debug("getMailFolder()");
		RepositoryModule rm = ModuleManager.getRepositoryModule();
		Folder mailFolder = rm.getMailFolder();
		log.debug("getMailFolder: {}", mailFolder);
		return mailFolder;
	}
	
	@WebMethod
	public Folder getThesaurusFolder() throws PathNotFoundException, RepositoryException, DatabaseException {
		log.debug("getThesaurusFolder()");
		RepositoryModule rm = ModuleManager.getRepositoryModule();
		Folder thesaurusFolder = rm.getThesaurusFolder();
		log.debug("getThesaurusFolder: {}", thesaurusFolder);
		return thesaurusFolder;
	}

	@WebMethod
	public Folder getCategoriesFolder() throws PathNotFoundException, RepositoryException, DatabaseException {
		log.debug("getCategoriesFolder()");
		RepositoryModule rm = ModuleManager.getRepositoryModule();
		Folder categoriesFolder = rm.getCategoriesFolder();
		log.debug("getCategoriesFolder: {}", categoriesFolder);
		return categoriesFolder;
	}

	@WebMethod
	public void purgeTrash() throws AccessDeniedException, RepositoryException, DatabaseException {
		log.debug("purgeTrash()");
		RepositoryModule rm = ModuleManager.getRepositoryModule();
		rm.purgeTrash();
		log.debug("purgeTrash: void");
	}
	
	@WebMethod
	public boolean hasNode(@WebParam(name = "path") String path) throws RepositoryException, 
			DatabaseException {
		log.debug("hasNode({})", path);
		RepositoryModule rm = ModuleManager.getRepositoryModule();
		boolean ret = rm.hasNode(path);
		log.debug("hasNode: {}", ret);
		return ret;
	}
	
	@WebMethod
	public String getPath(@WebParam(name = "uuid") String uuid) throws PathNotFoundException, RepositoryException, DatabaseException {
		log.debug("getPath({})", uuid);
		RepositoryModule rm = ModuleManager.getRepositoryModule();
		String path = rm.getPath(uuid);
		log.debug("getPath: {}", path);
		return path;
	}
}
