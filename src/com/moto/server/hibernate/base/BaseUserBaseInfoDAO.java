package com.moto.server.hibernate.base;

import com.moto.server.hibernate.dao.UserBaseInfoDAO;

/**
 * This is an automatically generated DAO class which should not be edited.
 */
public abstract class BaseUserBaseInfoDAO extends com.moto.server.hibernate.dao._RootDAO {

	public BaseUserBaseInfoDAO () {}

	// query name references


	public static UserBaseInfoDAO instance;

	/**
	 * Return a singleton of the DAO
	 */
	public static UserBaseInfoDAO getInstance () {
		if (null == instance) instance = new com.moto.server.hibernate.dao.UserBaseInfoDAO();
		return instance;
	}

	public Class getReferenceClass () {
		return com.moto.server.hibernate.UserBaseInfo.class;
	}


	/**
	 * Cast the object as a com.moto.server.hibernate.UserBaseInfo
	 */
	public com.moto.server.hibernate.UserBaseInfo cast (Object object) {
		return (com.moto.server.hibernate.UserBaseInfo) object;
	}

	public com.moto.server.hibernate.UserBaseInfo load(java.lang.Integer key)
		throws org.hibernate.HibernateException {
		return (com.moto.server.hibernate.UserBaseInfo) load(getReferenceClass(), key);
	}

	public com.moto.server.hibernate.UserBaseInfo get(java.lang.Integer key)
		throws org.hibernate.HibernateException {
		return (com.moto.server.hibernate.UserBaseInfo) get(getReferenceClass(), key);
	}

	public java.util.List loadAll()
		throws org.hibernate.HibernateException {
		return loadAll(getReferenceClass());
	}



	/**
	 * Persist the given transient instance, first assigning a generated identifier. (Or using the current value
	 * of the identifier property if the assigned generator is used.) 
	 * @param userBaseInfo a transient instance of a persistent class 
	 * @return the class identifier
	 */
	public java.lang.Integer save(com.moto.server.hibernate.UserBaseInfo userBaseInfo)
		throws org.hibernate.HibernateException {
		return (java.lang.Integer) super.save(userBaseInfo);
	}

	/**
	 * Either save() or update() the given instance, depending upon the value of its identifier property. By default
	 * the instance is always saved. This behaviour may be adjusted by specifying an unsaved-value attribute of the
	 * identifier property mapping. 
	 * @param userBaseInfo a transient instance containing new or updated state 
	 */
	public void saveOrUpdate(com.moto.server.hibernate.UserBaseInfo userBaseInfo)
		throws org.hibernate.HibernateException {
		super.saveOrUpdate(userBaseInfo);
	}


	/**
	 * Update the persistent state associated with the given identifier. An exception is thrown if there is a persistent
	 * instance with the same identifier in the current session.
	 * @param userBaseInfo a transient instance containing updated state
	 */
	public void update(com.moto.server.hibernate.UserBaseInfo userBaseInfo) 
		throws org.hibernate.HibernateException {
		super.update(userBaseInfo);
	}




}