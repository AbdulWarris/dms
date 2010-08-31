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

package com.openkm.dao;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.openkm.core.DatabaseException;
import com.openkm.dao.bean.Role;
import com.openkm.dao.bean.User;
import com.openkm.util.SecureStore;

public class AuthDAO {
	private static Logger log = LoggerFactory.getLogger(AuthDAO.class);

	private AuthDAO() {}
			
	/**
	 * Create user in database
	 */
	public static void createUser(User user) throws DatabaseException {
		log.debug("createUser({})", user);
		Session session = null;
		Transaction tx = null;
		
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			user.setPassword(SecureStore.md5Encode(user.getPassword().getBytes()));
			session.save(user);
			tx.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback(tx);
			throw new DatabaseException(e.getMessage(), e);
		} catch (NoSuchAlgorithmException e) {
			HibernateUtil.rollback(tx);
			throw new DatabaseException(e.getMessage(), e);
		}
		
		log.debug("createUser: void");
	}

	/**
	 * Update user in database
	 */
	public static void updateUser(User user) throws DatabaseException {
		log.debug("updateUser({})", user);
		String qs = "select u.password from User u where u.id=:id";
		Session session = null;
		Transaction tx = null;
		
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			Query q = session.createQuery(qs);
			q.setParameter("id", user.getId());
			String password = (String) q.setMaxResults(1).uniqueResult();
			user.setPassword(password);
			session.update(user);
			tx.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback(tx);
			throw new DatabaseException(e.getMessage(), e);
		}
		
		log.debug("updateUser: void");
	}
	
	/**
	 * Update user password in database 
	 */
	public static void updateUserPassword(String usrId, String usrPassword) throws DatabaseException {
		log.debug("updateUserPassword({}, {})", usrId, usrPassword);
		String qs = "update User u set u.password=:password where u.id=:id";
		Session session = null;
		Transaction tx = null;
		
		try {
			if (usrPassword != null && usrPassword.trim().length() > 0) {
				session = HibernateUtil.getSession();
				tx = session.beginTransaction();
				Query q = session.createQuery(qs);
				q.setString("password", SecureStore.md5Encode(usrPassword.getBytes()));
				q.setString("id", usrId);
				q.executeUpdate();
				tx.commit();
			}
		} catch (HibernateException e) {
			HibernateUtil.rollback(tx);
			throw new DatabaseException(e.getMessage(), e);
		} catch (NoSuchAlgorithmException e) {
			HibernateUtil.rollback(tx);
			throw new DatabaseException(e.getMessage(), e);
		}
		
		log.debug("updateUserPassword: void");
	}
	
	/**
	 * Update user email in database
	 */
	public static void updateUserEmail(String usrId, String usrEmail) throws DatabaseException {
		log.debug("updateUserEmail({}, {})", usrId, usrEmail);
		String qs = "update User set u.email=:email where u.id=:id";
		Session session = null;
		Transaction tx = null;
		
		try {
			if (usrEmail != null && usrEmail.trim().length() > 0) {
				session = HibernateUtil.getSession();
				tx = session.beginTransaction();
				Query q = session.createQuery(qs);
				q.setString("email", usrEmail);
				q.setString("id", usrId);
				q.executeUpdate();
				tx.commit();
			}
		} catch (HibernateException e) {
			HibernateUtil.rollback(tx);
			throw new DatabaseException(e.getMessage(), e);
		}
		
		log.debug("updateUserEmail: void");
	}
	
	/**
	 * Delete user from database
	 * 
	 * Note: This should be made manually because the user is not always in the database
	 * and may be located in an external source like LDAP.
	 */
	public static void deleteUser(String usrId) throws DatabaseException {
		log.debug("deleteUser({})", usrId);
		String qsMail = "delete from MailAccount ma where ma.user=:user";
		String qsTwitter = "delete from TwitterAccount ta where ta.user=:user";
		String qsBookmark = "delete from Bookmark bm where bm.user=:user";
		String qsConfig = "delete from UserConfig uc where uc.user=:user";
		String qsItems = "delete from UserItems ui where ui.user=:user";
		String qsDocument = "delete from UserDocumentKeywords udk where udk.user=:user";
		String qsLock = "delete from LockToken lt where lt.user=:user";
		String qsQuery = "delete from QueryParams qp where qp.user=:user";
		Session session = null;
		Transaction tx = null;
		
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			User user = (User) session.load(User.class, usrId);
			session.delete(user);
			
			// Clean OKM_MAIL_ACCOUNT table
			Query qMail = session.createQuery(qsMail);
			qMail.setString("user", usrId);
			qMail.executeUpdate();
			
			// Clean OKM_TWITTER_ACCOUNT table
			Query qTwitter = session.createQuery(qsTwitter);
			qTwitter.setString("user", usrId);
			qTwitter.executeUpdate();
			
			// Clean OKM_BOOKMARK table
			Query qBookmark = session.createQuery(qsBookmark);
			qBookmark.setString("user", usrId);
			qBookmark.executeUpdate();
			
			// Clean OKM_USER_CONFIG table
			Query qConfig = session.createQuery(qsConfig);
			qConfig.setString("user", usrId);
			qConfig.executeUpdate();
			
			// Clean OKM_USER_ITEMS table
			Query qItems = session.createQuery(qsItems);
			qItems.setString("user", usrId);
			qItems.executeUpdate();
			
			// Clean OKM_USER_DOCUMENT table
			Query qDocument = session.createQuery(qsDocument);
			qDocument.setString("user", usrId);
			qDocument.executeUpdate();
			
			// Clean OKM_LOCK_TOKEN table
			Query qLock = session.createQuery(qsLock);
			qLock.setString("user", usrId);
			qLock.executeUpdate();
			
			// Clean OKM_QUERY_PARAMS table
			Query qQuery = session.createQuery(qsQuery);
			qQuery.setString("user", usrId);
			qQuery.executeUpdate();
						
			tx.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback(tx);
			throw new DatabaseException(e.getMessage(), e);
		}
		
		log.debug("deleteUser: void");
	}
	
	/**
	 * Get all users in database
	 */
	@SuppressWarnings("unchecked")
	public static List<User> findAllUsers(boolean filterByActive) throws DatabaseException {
		log.debug("findAllUsers({})", filterByActive);
		String qs = "from User u "+(filterByActive?"where u.active=:active":"")+" order by u.id";
		Session session = null;
		
		try {
			session = HibernateUtil.getSession();
			Query q = session.createQuery(qs);
			
			if (filterByActive) {
				q.setBoolean("active", true);
			}
			
			List<User> ret = q.list();
			log.debug("findAllUsers: {}", ret);
			return ret;
		} catch (HibernateException e) {
			throw new DatabaseException(e.getMessage(), e);
		}
	}

	/**
	 * Get all users within a role
	 */
	@SuppressWarnings("unchecked")
	public static List<User> findUsersByRole(String rolId, boolean filterByActive) throws DatabaseException {
		log.debug("findUsersByRole({}, {})", rolId, filterByActive);
		String qs = "select u from User u, Role r where r.id=:rolId and r in elements(u.roles) " + 
			(filterByActive?"and u.active=:active":"")+" order by u.id";
		Session session = null;
		
		try {
			session = HibernateUtil.getSession();
			Query q = session.createQuery(qs);
			q.setString("rolId", rolId);
			
			if (filterByActive) {
				q.setBoolean("active", true);
			}
			
			List<User> ret = q.list();
			log.debug("findUsersByRole: {}", ret);
			return ret;
		} catch (HibernateException e) {
			throw new DatabaseException(e.getMessage(), e);
		}
	}
	
	/**
	 * Get all users within a role
	 */
	@SuppressWarnings("unchecked")
	public static List<Role> findRolesByUser(String usrId, boolean filterByActive) throws DatabaseException {
		log.debug("findRolesByUser({}, {})", usrId, filterByActive);
		String qs = "select r from User u, Role r where u.id=:usrId and r in elements(u.roles) " + 
			(filterByActive?"and r.active=:active":"")+" order by r.id";
		Session session = null;
		
		try {
			session = HibernateUtil.getSession();
			Query q = session.createQuery(qs);
			q.setString("usrId", usrId);
			
			if (filterByActive) {
				q.setBoolean("active", true);
			}
			
			List<Role> ret = q.list();
			log.debug("findRolesByUser: {}", ret);
			return ret;
		} catch (HibernateException e) {
			throw new DatabaseException(e.getMessage(), e);
		}
	}
	
	/**
	 * Get user from database
	 */
	public static User findUserByPk(String usrId) throws DatabaseException {
		log.debug("findUserByPk({})", usrId);
		String qs = "from User u where u.id=:id";
		Session session = null;
		
		try {
			session = HibernateUtil.getSession();
			Query q = session.createQuery(qs);
			q.setString("id", usrId);
			User ret = (User) q.setMaxResults(1).uniqueResult();
			log.debug("findUserByPk: {}", ret);
			return ret;
		} catch (HibernateException e) {
			throw new DatabaseException(e.getMessage(), e);
		}
	}

	/**
	 * Create role in database
	 */
	public static void createRole(Role role) throws DatabaseException {
		log.debug("createRole({})", role);
		Session session = null;
		Transaction tx = null;
		
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			session.save(role);
			tx.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback(tx);
			throw new DatabaseException(e.getMessage(), e);
		}
		
		log.debug("createRole: void");
	}
	
	/**
	 * Update role in database
	 */
	public static void updateRole(Role role) throws DatabaseException {
		log.debug("updateRole({})", role);
		Session session = null;
		Transaction tx = null;
		
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			session.update(role);
			tx.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback(tx);
			throw new DatabaseException(e.getMessage(), e);
		}
		
		log.debug("updateRole: void");
	}

	/**
	 * Delete role from database
	 */
	public static void deleteRole(String rolId) throws DatabaseException {
		log.debug("deleteRole({})", rolId);
		Session session = null;
		Transaction tx = null;
		
		try {			
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			Role role = (Role) session.load(Role.class, rolId);
			session.delete(role);
			tx.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback(tx);
			throw new DatabaseException(e.getMessage(), e);
		}
		
		log.debug("deleteRole: void");
	}

	/**
	 * Get all roles in database
	 */
	@SuppressWarnings("unchecked")
	public static List<Role> findAllRoles() throws DatabaseException {
		log.debug("findAllRoles()");
		String qs = "from Role";
		Session session = null;
		
		try {
			session = HibernateUtil.getSession();
			Query q = session.createQuery(qs);
			List<Role> ret = q.list();
			log.debug("findAllRoles: {}", ret);
			return ret;
		} catch (HibernateException e) {
			throw new DatabaseException(e.getMessage(), e);
		}
	}
	
	/**
	 * Find role by pk
	 */
	public static Role findRoleByPk(String rolId) throws DatabaseException {
		log.debug("findRoleByPk({})", rolId);
		String qs = "from Role r where r.id=:id";
		Session session = null;
		
		try {
			session = HibernateUtil.getSession();
			Query q = session.createQuery(qs);
			q.setString("id", rolId);
			Role ret = (Role) q.setMaxResults(1).uniqueResult();
			log.debug("findRoleByPk: {}", ret);
			return ret;
		} catch (HibernateException e) {
			throw new DatabaseException(e.getMessage(), e);
		}
	}

	/**
	 * Grant role to user
	 */
	public static void grantRole(String usrId, String rolId) throws DatabaseException {
		log.debug("grantRole({}, {})", usrId, rolId);
		Session session = null;
		Transaction tx = null;
		
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			User user = (User) session.load(User.class, usrId);
			Role role = (Role) session.load(Role.class, rolId);
			user.getRoles().add(role);
			session.update(user);
			tx.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback(tx);
			throw new DatabaseException(e.getMessage(), e);
		}
		
		log.debug("grantRole: void");
	}

	/**
	 * Revoke role from user
	 */
	public void revokeRole(String usrId, String rolId) throws DatabaseException {
		log.debug("revokeRole({}, {})", usrId, rolId);
		Session session = null;
		Transaction tx = null;
		
		try {
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			User user = (User) session.load(User.class, usrId);
			Role role = (Role) session.load(Role.class, rolId);
			user.getRoles().remove(role);
			session.update(user);
			tx.commit();
		} catch (HibernateException e) {
			HibernateUtil.rollback(tx);
			throw new DatabaseException(e.getMessage(), e);
		}
		
		log.debug("revokeRole: void");
	}
}
