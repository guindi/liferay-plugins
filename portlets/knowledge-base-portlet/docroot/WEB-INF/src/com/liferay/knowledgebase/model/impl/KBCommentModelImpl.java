/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.knowledgebase.model.impl;

import com.liferay.knowledgebase.model.KBComment;
import com.liferay.knowledgebase.model.KBCommentModel;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.PortalUtil;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import java.io.Serializable;

import java.sql.Types;

import java.util.Date;

/**
 * The base model implementation for the KBComment service. Represents a row in the &quot;KBComment&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link com.liferay.knowledgebase.model.KBCommentModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link KBCommentImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KBCommentImpl
 * @see com.liferay.knowledgebase.model.KBComment
 * @see com.liferay.knowledgebase.model.KBCommentModel
 * @generated
 */
public class KBCommentModelImpl extends BaseModelImpl<KBComment>
	implements KBCommentModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a k b comment model instance should use the {@link com.liferay.knowledgebase.model.KBComment} interface instead.
	 */
	public static final String TABLE_NAME = "KBComment";
	public static final Object[][] TABLE_COLUMNS = {
			{ "uuid_", Types.VARCHAR },
			{ "kbCommentId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "classNameId", Types.BIGINT },
			{ "classPK", Types.BIGINT },
			{ "content", Types.VARCHAR },
			{ "helpful", Types.BOOLEAN }
		};
	public static final String TABLE_SQL_CREATE = "create table KBComment (uuid_ VARCHAR(75) null,kbCommentId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,classNameId LONG,classPK LONG,content STRING null,helpful BOOLEAN)";
	public static final String TABLE_SQL_DROP = "drop table KBComment";
	public static final String ORDER_BY_JPQL = " ORDER BY kbComment.modifiedDate DESC";
	public static final String ORDER_BY_SQL = " ORDER BY KBComment.modifiedDate DESC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.knowledgebase.model.KBComment"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.knowledgebase.model.KBComment"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.column.bitmask.enabled.com.liferay.knowledgebase.model.KBComment"),
			true);
	public static long GROUPID_COLUMN_BITMASK = 1L;
	public static long CLASSNAMEID_COLUMN_BITMASK = 2L;
	public static long USERID_COLUMN_BITMASK = 4L;
	public static long UUID_COLUMN_BITMASK = 8L;
	public static long CLASSPK_COLUMN_BITMASK = 16L;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.com.liferay.knowledgebase.model.KBComment"));

	public KBCommentModelImpl() {
	}

	public long getPrimaryKey() {
		return _kbCommentId;
	}

	public void setPrimaryKey(long primaryKey) {
		setKbCommentId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_kbCommentId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	public Class<?> getModelClass() {
		return KBComment.class;
	}

	public String getModelClassName() {
		return KBComment.class.getName();
	}

	public String getUuid() {
		if (_uuid == null) {
			return StringPool.BLANK;
		}
		else {
			return _uuid;
		}
	}

	public void setUuid(String uuid) {
		if (_originalUuid == null) {
			_originalUuid = _uuid;
		}

		_uuid = uuid;
	}

	public String getOriginalUuid() {
		return GetterUtil.getString(_originalUuid);
	}

	public long getKbCommentId() {
		return _kbCommentId;
	}

	public void setKbCommentId(long kbCommentId) {
		_kbCommentId = kbCommentId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_columnBitmask |= GROUPID_COLUMN_BITMASK;

		if (!_setOriginalGroupId) {
			_setOriginalGroupId = true;

			_originalGroupId = _groupId;
		}

		_groupId = groupId;
	}

	public long getOriginalGroupId() {
		return _originalGroupId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_columnBitmask |= USERID_COLUMN_BITMASK;

		if (!_setOriginalUserId) {
			_setOriginalUserId = true;

			_originalUserId = _userId;
		}

		_userId = userId;
	}

	public String getUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getUserId(), "uuid", _userUuid);
	}

	public void setUserUuid(String userUuid) {
		_userUuid = userUuid;
	}

	public long getOriginalUserId() {
		return _originalUserId;
	}

	public String getUserName() {
		if (_userName == null) {
			return StringPool.BLANK;
		}
		else {
			return _userName;
		}
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public String getClassName() {
		if (getClassNameId() <= 0) {
			return StringPool.BLANK;
		}

		return PortalUtil.getClassName(getClassNameId());
	}

	public long getClassNameId() {
		return _classNameId;
	}

	public void setClassNameId(long classNameId) {
		_columnBitmask |= CLASSNAMEID_COLUMN_BITMASK;

		if (!_setOriginalClassNameId) {
			_setOriginalClassNameId = true;

			_originalClassNameId = _classNameId;
		}

		_classNameId = classNameId;
	}

	public long getOriginalClassNameId() {
		return _originalClassNameId;
	}

	public long getClassPK() {
		return _classPK;
	}

	public void setClassPK(long classPK) {
		_columnBitmask |= CLASSPK_COLUMN_BITMASK;

		if (!_setOriginalClassPK) {
			_setOriginalClassPK = true;

			_originalClassPK = _classPK;
		}

		_classPK = classPK;
	}

	public long getOriginalClassPK() {
		return _originalClassPK;
	}

	public String getContent() {
		if (_content == null) {
			return StringPool.BLANK;
		}
		else {
			return _content;
		}
	}

	public void setContent(String content) {
		_content = content;
	}

	public boolean getHelpful() {
		return _helpful;
	}

	public boolean isHelpful() {
		return _helpful;
	}

	public void setHelpful(boolean helpful) {
		_helpful = helpful;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public KBComment toEscapedModel() {
		if (isEscapedModel()) {
			return (KBComment)this;
		}
		else {
			if (_escapedModelProxy == null) {
				_escapedModelProxy = (KBComment)ProxyUtil.newProxyInstance(_classLoader,
						_escapedModelProxyInterfaces,
						new AutoEscapeBeanHandler(this));
			}

			return _escapedModelProxy;
		}
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		if (_expandoBridge == null) {
			_expandoBridge = ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
					KBComment.class.getName(), getPrimaryKey());
		}

		return _expandoBridge;
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		getExpandoBridge().setAttributes(serviceContext);
	}

	@Override
	public Object clone() {
		KBCommentImpl kbCommentImpl = new KBCommentImpl();

		kbCommentImpl.setUuid(getUuid());
		kbCommentImpl.setKbCommentId(getKbCommentId());
		kbCommentImpl.setGroupId(getGroupId());
		kbCommentImpl.setCompanyId(getCompanyId());
		kbCommentImpl.setUserId(getUserId());
		kbCommentImpl.setUserName(getUserName());
		kbCommentImpl.setCreateDate(getCreateDate());
		kbCommentImpl.setModifiedDate(getModifiedDate());
		kbCommentImpl.setClassNameId(getClassNameId());
		kbCommentImpl.setClassPK(getClassPK());
		kbCommentImpl.setContent(getContent());
		kbCommentImpl.setHelpful(getHelpful());

		kbCommentImpl.resetOriginalValues();

		return kbCommentImpl;
	}

	public int compareTo(KBComment kbComment) {
		int value = 0;

		value = DateUtil.compareTo(getModifiedDate(),
				kbComment.getModifiedDate());

		value = value * -1;

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		KBComment kbComment = null;

		try {
			kbComment = (KBComment)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = kbComment.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public void resetOriginalValues() {
		KBCommentModelImpl kbCommentModelImpl = this;

		kbCommentModelImpl._originalUuid = kbCommentModelImpl._uuid;

		kbCommentModelImpl._originalGroupId = kbCommentModelImpl._groupId;

		kbCommentModelImpl._setOriginalGroupId = false;

		kbCommentModelImpl._originalUserId = kbCommentModelImpl._userId;

		kbCommentModelImpl._setOriginalUserId = false;

		kbCommentModelImpl._originalClassNameId = kbCommentModelImpl._classNameId;

		kbCommentModelImpl._setOriginalClassNameId = false;

		kbCommentModelImpl._originalClassPK = kbCommentModelImpl._classPK;

		kbCommentModelImpl._setOriginalClassPK = false;

		_columnBitmask = 0;
	}

	@Override
	public CacheModel<KBComment> toCacheModel() {
		KBCommentCacheModel kbCommentCacheModel = new KBCommentCacheModel();

		kbCommentCacheModel.uuid = getUuid();

		String uuid = kbCommentCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			kbCommentCacheModel.uuid = null;
		}

		kbCommentCacheModel.kbCommentId = getKbCommentId();

		kbCommentCacheModel.groupId = getGroupId();

		kbCommentCacheModel.companyId = getCompanyId();

		kbCommentCacheModel.userId = getUserId();

		kbCommentCacheModel.userName = getUserName();

		String userName = kbCommentCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			kbCommentCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			kbCommentCacheModel.createDate = createDate.getTime();
		}
		else {
			kbCommentCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			kbCommentCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			kbCommentCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		kbCommentCacheModel.classNameId = getClassNameId();

		kbCommentCacheModel.classPK = getClassPK();

		kbCommentCacheModel.content = getContent();

		String content = kbCommentCacheModel.content;

		if ((content != null) && (content.length() == 0)) {
			kbCommentCacheModel.content = null;
		}

		kbCommentCacheModel.helpful = getHelpful();

		return kbCommentCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", kbCommentId=");
		sb.append(getKbCommentId());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", classNameId=");
		sb.append(getClassNameId());
		sb.append(", classPK=");
		sb.append(getClassPK());
		sb.append(", content=");
		sb.append(getContent());
		sb.append(", helpful=");
		sb.append(getHelpful());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(40);

		sb.append("<model><model-name>");
		sb.append("com.liferay.knowledgebase.model.KBComment");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>kbCommentId</column-name><column-value><![CDATA[");
		sb.append(getKbCommentId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>groupId</column-name><column-value><![CDATA[");
		sb.append(getGroupId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userName</column-name><column-value><![CDATA[");
		sb.append(getUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>classNameId</column-name><column-value><![CDATA[");
		sb.append(getClassNameId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>classPK</column-name><column-value><![CDATA[");
		sb.append(getClassPK());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>content</column-name><column-value><![CDATA[");
		sb.append(getContent());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>helpful</column-name><column-value><![CDATA[");
		sb.append(getHelpful());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static ClassLoader _classLoader = KBComment.class.getClassLoader();
	private static Class<?>[] _escapedModelProxyInterfaces = new Class[] {
			KBComment.class
		};
	private String _uuid;
	private String _originalUuid;
	private long _kbCommentId;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private long _companyId;
	private long _userId;
	private String _userUuid;
	private long _originalUserId;
	private boolean _setOriginalUserId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _classNameId;
	private long _originalClassNameId;
	private boolean _setOriginalClassNameId;
	private long _classPK;
	private long _originalClassPK;
	private boolean _setOriginalClassPK;
	private String _content;
	private boolean _helpful;
	private transient ExpandoBridge _expandoBridge;
	private long _columnBitmask;
	private KBComment _escapedModelProxy;
}