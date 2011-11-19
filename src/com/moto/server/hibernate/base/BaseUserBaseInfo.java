package com.moto.server.hibernate.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the userbaseinfo table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 *
 * @hibernate.class
 *  table="userbaseinfo"
 */

public abstract class BaseUserBaseInfo  implements Serializable {

	public static String REF = "UserBaseInfo";
	public static String PROP_U_AVATAR = "UAvatar";
	public static String PROP_U_STATE = "UState";
	public static String PROP_U_HOMETOWN = "UHometown";
	public static String PROP_U_PWD = "UPwd";
	public static String PROP_U_JOB = "UJob";
	public static String PROP_ID = "Id";
	public static String PROP_U_EMAIL = "UEmail";
	public static String PROP_U_NAME = "UName";
	public static String PROP_U_MOBILE = "UMobile";
	public static String PROP_U_MOOD = "UMood";


	// constructors
	public BaseUserBaseInfo () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseUserBaseInfo (java.lang.Integer id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Integer id;

	// fields
	private java.lang.String uName;
	private java.lang.String uEmail;
	private java.lang.String uMood;
	private java.lang.String uPwd;
	private java.lang.String uState;
	private java.lang.String uHometown;
	private java.lang.String uJob;
	private java.lang.Integer uMobile;
	private byte[] uAvatar;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="native"
     *  column="u_id"
     */
	public java.lang.Integer getId () {
		return id;
	}

	/**
	 * Set the unique identifier of this class
	 * @param id the new ID
	 */
	public void setId (java.lang.Integer id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}




	/**
	 * Return the value associated with the column: u_name
	 */
	public java.lang.String getUName () {
		return uName;
	}

	/**
	 * Set the value related to the column: u_name
	 * @param uName the u_name value
	 */
	public void setUName (java.lang.String uName) {
		this.uName = uName;
	}



	/**
	 * Return the value associated with the column: u_email
	 */
	public java.lang.String getUEmail () {
		return uEmail;
	}

	/**
	 * Set the value related to the column: u_email
	 * @param uEmail the u_email value
	 */
	public void setUEmail (java.lang.String uEmail) {
		this.uEmail = uEmail;
	}



	/**
	 * Return the value associated with the column: u_mood
	 */
	public java.lang.String getUMood () {
		return uMood;
	}

	/**
	 * Set the value related to the column: u_mood
	 * @param uMood the u_mood value
	 */
	public void setUMood (java.lang.String uMood) {
		this.uMood = uMood;
	}



	/**
	 * Return the value associated with the column: u_pwd
	 */
	public java.lang.String getUPwd () {
		return uPwd;
	}

	/**
	 * Set the value related to the column: u_pwd
	 * @param uPwd the u_pwd value
	 */
	public void setUPwd (java.lang.String uPwd) {
		this.uPwd = uPwd;
	}



	/**
	 * Return the value associated with the column: u_state
	 */
	public java.lang.String getUState () {
		return uState;
	}

	/**
	 * Set the value related to the column: u_state
	 * @param uState the u_state value
	 */
	public void setUState (java.lang.String uState) {
		this.uState = uState;
	}



	/**
	 * Return the value associated with the column: u_hometown
	 */
	public java.lang.String getUHometown () {
		return uHometown;
	}

	/**
	 * Set the value related to the column: u_hometown
	 * @param uHometown the u_hometown value
	 */
	public void setUHometown (java.lang.String uHometown) {
		this.uHometown = uHometown;
	}



	/**
	 * Return the value associated with the column: u_job
	 */
	public java.lang.String getUJob () {
		return uJob;
	}

	/**
	 * Set the value related to the column: u_job
	 * @param uJob the u_job value
	 */
	public void setUJob (java.lang.String uJob) {
		this.uJob = uJob;
	}



	/**
	 * Return the value associated with the column: u_mobile
	 */
	public java.lang.Integer getUMobile () {
		return uMobile;
	}

	/**
	 * Set the value related to the column: u_mobile
	 * @param uMobile the u_mobile value
	 */
	public void setUMobile (java.lang.Integer uMobile) {
		this.uMobile = uMobile;
	}



	/**
	 * Return the value associated with the column: u_avatar
	 */
	public byte[] getUAvatar () {
		return uAvatar;
	}

	/**
	 * Set the value related to the column: u_avatar
	 * @param uAvatar the u_avatar value
	 */
	public void setUAvatar (byte[] uAvatar) {
		this.uAvatar = uAvatar;
	}




	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.moto.server.hibernate.UserBaseInfo)) return false;
		else {
			com.moto.server.hibernate.UserBaseInfo userBaseInfo = (com.moto.server.hibernate.UserBaseInfo) obj;
			if (null == this.getId() || null == userBaseInfo.getId()) return false;
			else return (this.getId().equals(userBaseInfo.getId()));
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getId()) return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":" + this.getId().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}


	public String toString () {
		return super.toString();
	}


}