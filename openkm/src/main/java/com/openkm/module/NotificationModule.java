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

package com.openkm.module;

import java.util.Collection;
import com.openkm.core.PathNotFoundException;
import com.openkm.core.RepositoryException;
import com.openkm.core.AccessDeniedException;

public interface NotificationModule {

	/**
	 * Add user subscription to a node.
	 * 
	 * @param token The session authorization token.
	 * @param nodePath The complete path to the node.
	 * @throws PathNotFoundException If the node defined by nodePath do not exists.
	 * @throws AccessDeniedException If the token authorization information is not valid.
	 * @throws RepositoryException If there is any error accessing to the repository.
	 */
	public void subscribe(String token, String nodePath) 
			throws PathNotFoundException, AccessDeniedException, RepositoryException;
	
	/**
	 * Remove an user subscription from a node.
	 * 
	 * @param token The session authorization token.
	 * @param nodePath The complete path to the node.
	 * @throws PathNotFoundException If the node defined by nodePath do not exists.
	 * @throws AccessDeniedException If the token authorization information is not valid.
	 * @throws RepositoryException If there is any error accessing to the repository.
	 */
	public void unsubscribe(String token, String nodePath)
			throws PathNotFoundException, AccessDeniedException, RepositoryException;
	
	/** 
	 * Get user subscriptions from am item (document or folder).
	 * 
	 * @param token The session authorization token.
	 * @param nodePath The complete path to the node.
	 * @return A Collection of subscribed users.
	 * @throws PathNotFoundException If the node defined by nodePath do not exists.
	 * @throws AccessDeniedException If the token authorization information is not valid.
	 * @throws RepositoryException If there is any error accessing to the repository.
	 */
	public Collection<String> getSubscriptors(String token, String nodePath) throws PathNotFoundException,
			AccessDeniedException, RepositoryException;

	/**
	 * Send a notification message to an user list.
	 * 
	 * @param token The session authorization token.
	 * @param nodePath The complete path to the node.
	 * @param users Array of users to notify.
	 * @param message An String with the notification message.
	 * @throws PathNotFoundException If the node defined by nodePath do not exists.
	 * @throws AccessDeniedException If the token authorization information is not valid.
	 * @throws RepositoryException If there is any error accessing to the repository.
	 */
	public void notify(String token, String nodePath, Collection<String> users, String message) 
			throws PathNotFoundException, AccessDeniedException, RepositoryException;
}
