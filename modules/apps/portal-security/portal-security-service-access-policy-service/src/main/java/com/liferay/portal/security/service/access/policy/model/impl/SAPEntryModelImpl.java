/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

package com.liferay.portal.security.service.access.policy.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;

import com.liferay.exportimport.kernel.lar.StagedModelType;

import com.liferay.petra.string.StringBundler;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.LocaleException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.security.service.access.policy.model.SAPEntry;
import com.liferay.portal.security.service.access.policy.model.SAPEntryModel;
import com.liferay.portal.security.service.access.policy.model.SAPEntrySoap;

import java.io.Serializable;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * The base model implementation for the SAPEntry service. Represents a row in the &quot;SAPEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link SAPEntryModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link SAPEntryImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SAPEntryImpl
 * @see SAPEntry
 * @see SAPEntryModel
 * @generated
 */
@JSON(strict = true)
@ProviderType
public class SAPEntryModelImpl extends BaseModelImpl<SAPEntry>
	implements SAPEntryModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a sap entry model instance should use the {@link SAPEntry} interface instead.
	 */
	public static final String TABLE_NAME = "SAPEntry";
	public static final Object[][] TABLE_COLUMNS = {
			{ "uuid_", Types.VARCHAR },
			{ "sapEntryId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "allowedServiceSignatures", Types.VARCHAR },
			{ "defaultSAPEntry", Types.BOOLEAN },
			{ "enabled", Types.BOOLEAN },
			{ "name", Types.VARCHAR },
			{ "title", Types.VARCHAR }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("sapEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("allowedServiceSignatures", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("defaultSAPEntry", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("enabled", Types.BOOLEAN);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("title", Types.VARCHAR);
	}

	public static final String TABLE_SQL_CREATE = "create table SAPEntry (uuid_ VARCHAR(75) null,sapEntryId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,allowedServiceSignatures STRING null,defaultSAPEntry BOOLEAN,enabled BOOLEAN,name VARCHAR(75) null,title STRING null)";
	public static final String TABLE_SQL_DROP = "drop table SAPEntry";
	public static final String ORDER_BY_JPQL = " ORDER BY sapEntry.sapEntryId ASC";
	public static final String ORDER_BY_SQL = " ORDER BY SAPEntry.sapEntryId ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.security.service.access.policy.service.util.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.portal.security.service.access.policy.model.SAPEntry"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.security.service.access.policy.service.util.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.portal.security.service.access.policy.model.SAPEntry"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.portal.security.service.access.policy.service.util.ServiceProps.get(
				"value.object.column.bitmask.enabled.com.liferay.portal.security.service.access.policy.model.SAPEntry"),
			true);
	public static final long COMPANYID_COLUMN_BITMASK = 1L;
	public static final long DEFAULTSAPENTRY_COLUMN_BITMASK = 2L;
	public static final long NAME_COLUMN_BITMASK = 4L;
	public static final long UUID_COLUMN_BITMASK = 8L;
	public static final long SAPENTRYID_COLUMN_BITMASK = 16L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static SAPEntry toModel(SAPEntrySoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		SAPEntry model = new SAPEntryImpl();

		model.setUuid(soapModel.getUuid());
		model.setSapEntryId(soapModel.getSapEntryId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setAllowedServiceSignatures(soapModel.getAllowedServiceSignatures());
		model.setDefaultSAPEntry(soapModel.isDefaultSAPEntry());
		model.setEnabled(soapModel.isEnabled());
		model.setName(soapModel.getName());
		model.setTitle(soapModel.getTitle());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<SAPEntry> toModels(SAPEntrySoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<SAPEntry> models = new ArrayList<SAPEntry>(soapModels.length);

		for (SAPEntrySoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.security.service.access.policy.service.util.ServiceProps.get(
				"lock.expiration.time.com.liferay.portal.security.service.access.policy.model.SAPEntry"));

	public SAPEntryModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _sapEntryId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setSapEntryId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _sapEntryId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return SAPEntry.class;
	}

	@Override
	public String getModelClassName() {
		return SAPEntry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("sapEntryId", getSapEntryId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("allowedServiceSignatures", getAllowedServiceSignatures());
		attributes.put("defaultSAPEntry", isDefaultSAPEntry());
		attributes.put("enabled", isEnabled());
		attributes.put("name", getName());
		attributes.put("title", getTitle());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long sapEntryId = (Long)attributes.get("sapEntryId");

		if (sapEntryId != null) {
			setSapEntryId(sapEntryId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String allowedServiceSignatures = (String)attributes.get(
				"allowedServiceSignatures");

		if (allowedServiceSignatures != null) {
			setAllowedServiceSignatures(allowedServiceSignatures);
		}

		Boolean defaultSAPEntry = (Boolean)attributes.get("defaultSAPEntry");

		if (defaultSAPEntry != null) {
			setDefaultSAPEntry(defaultSAPEntry);
		}

		Boolean enabled = (Boolean)attributes.get("enabled");

		if (enabled != null) {
			setEnabled(enabled);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String title = (String)attributes.get("title");

		if (title != null) {
			setTitle(title);
		}
	}

	@JSON
	@Override
	public String getUuid() {
		if (_uuid == null) {
			return "";
		}
		else {
			return _uuid;
		}
	}

	@Override
	public void setUuid(String uuid) {
		if (_originalUuid == null) {
			_originalUuid = _uuid;
		}

		_uuid = uuid;
	}

	public String getOriginalUuid() {
		return GetterUtil.getString(_originalUuid);
	}

	@JSON
	@Override
	public long getSapEntryId() {
		return _sapEntryId;
	}

	@Override
	public void setSapEntryId(long sapEntryId) {
		_sapEntryId = sapEntryId;
	}

	@JSON
	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_columnBitmask |= COMPANYID_COLUMN_BITMASK;

		if (!_setOriginalCompanyId) {
			_setOriginalCompanyId = true;

			_originalCompanyId = _companyId;
		}

		_companyId = companyId;
	}

	public long getOriginalCompanyId() {
		return _originalCompanyId;
	}

	@JSON
	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return "";
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	@JSON
	@Override
	public String getUserName() {
		if (_userName == null) {
			return "";
		}
		else {
			return _userName;
		}
	}

	@Override
	public void setUserName(String userName) {
		_userName = userName;
	}

	@JSON
	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	@JSON
	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public boolean hasSetModifiedDate() {
		return _setModifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_setModifiedDate = true;

		_modifiedDate = modifiedDate;
	}

	@JSON
	@Override
	public String getAllowedServiceSignatures() {
		if (_allowedServiceSignatures == null) {
			return "";
		}
		else {
			return _allowedServiceSignatures;
		}
	}

	@Override
	public void setAllowedServiceSignatures(String allowedServiceSignatures) {
		_allowedServiceSignatures = allowedServiceSignatures;
	}

	@JSON
	@Override
	public boolean getDefaultSAPEntry() {
		return _defaultSAPEntry;
	}

	@JSON
	@Override
	public boolean isDefaultSAPEntry() {
		return _defaultSAPEntry;
	}

	@Override
	public void setDefaultSAPEntry(boolean defaultSAPEntry) {
		_columnBitmask |= DEFAULTSAPENTRY_COLUMN_BITMASK;

		if (!_setOriginalDefaultSAPEntry) {
			_setOriginalDefaultSAPEntry = true;

			_originalDefaultSAPEntry = _defaultSAPEntry;
		}

		_defaultSAPEntry = defaultSAPEntry;
	}

	public boolean getOriginalDefaultSAPEntry() {
		return _originalDefaultSAPEntry;
	}

	@JSON
	@Override
	public boolean getEnabled() {
		return _enabled;
	}

	@JSON
	@Override
	public boolean isEnabled() {
		return _enabled;
	}

	@Override
	public void setEnabled(boolean enabled) {
		_enabled = enabled;
	}

	@JSON
	@Override
	public String getName() {
		if (_name == null) {
			return "";
		}
		else {
			return _name;
		}
	}

	@Override
	public void setName(String name) {
		_columnBitmask |= NAME_COLUMN_BITMASK;

		if (_originalName == null) {
			_originalName = _name;
		}

		_name = name;
	}

	public String getOriginalName() {
		return GetterUtil.getString(_originalName);
	}

	@JSON
	@Override
	public String getTitle() {
		if (_title == null) {
			return "";
		}
		else {
			return _title;
		}
	}

	@Override
	public String getTitle(Locale locale) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getTitle(languageId);
	}

	@Override
	public String getTitle(Locale locale, boolean useDefault) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getTitle(languageId, useDefault);
	}

	@Override
	public String getTitle(String languageId) {
		return LocalizationUtil.getLocalization(getTitle(), languageId);
	}

	@Override
	public String getTitle(String languageId, boolean useDefault) {
		return LocalizationUtil.getLocalization(getTitle(), languageId,
			useDefault);
	}

	@Override
	public String getTitleCurrentLanguageId() {
		return _titleCurrentLanguageId;
	}

	@JSON
	@Override
	public String getTitleCurrentValue() {
		Locale locale = getLocale(_titleCurrentLanguageId);

		return getTitle(locale);
	}

	@Override
	public Map<Locale, String> getTitleMap() {
		return LocalizationUtil.getLocalizationMap(getTitle());
	}

	@Override
	public void setTitle(String title) {
		_title = title;
	}

	@Override
	public void setTitle(String title, Locale locale) {
		setTitle(title, locale, LocaleUtil.getDefault());
	}

	@Override
	public void setTitle(String title, Locale locale, Locale defaultLocale) {
		String languageId = LocaleUtil.toLanguageId(locale);
		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		if (Validator.isNotNull(title)) {
			setTitle(LocalizationUtil.updateLocalization(getTitle(), "Title",
					title, languageId, defaultLanguageId));
		}
		else {
			setTitle(LocalizationUtil.removeLocalization(getTitle(), "Title",
					languageId));
		}
	}

	@Override
	public void setTitleCurrentLanguageId(String languageId) {
		_titleCurrentLanguageId = languageId;
	}

	@Override
	public void setTitleMap(Map<Locale, String> titleMap) {
		setTitleMap(titleMap, LocaleUtil.getDefault());
	}

	@Override
	public void setTitleMap(Map<Locale, String> titleMap, Locale defaultLocale) {
		if (titleMap == null) {
			return;
		}

		setTitle(LocalizationUtil.updateLocalization(titleMap, getTitle(),
				"Title", LocaleUtil.toLanguageId(defaultLocale)));
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(PortalUtil.getClassNameId(
				SAPEntry.class.getName()));
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			SAPEntry.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public String[] getAvailableLanguageIds() {
		Set<String> availableLanguageIds = new TreeSet<String>();

		Map<Locale, String> titleMap = getTitleMap();

		for (Map.Entry<Locale, String> entry : titleMap.entrySet()) {
			Locale locale = entry.getKey();
			String value = entry.getValue();

			if (Validator.isNotNull(value)) {
				availableLanguageIds.add(LocaleUtil.toLanguageId(locale));
			}
		}

		return availableLanguageIds.toArray(new String[availableLanguageIds.size()]);
	}

	@Override
	public String getDefaultLanguageId() {
		String xml = getTitle();

		if (xml == null) {
			return "";
		}

		Locale defaultLocale = LocaleUtil.getDefault();

		return LocalizationUtil.getDefaultLanguageId(xml, defaultLocale);
	}

	@Override
	public void prepareLocalizedFieldsForImport() throws LocaleException {
		Locale defaultLocale = LocaleUtil.fromLanguageId(getDefaultLanguageId());

		Locale[] availableLocales = LocaleUtil.fromLanguageIds(getAvailableLanguageIds());

		Locale defaultImportLocale = LocalizationUtil.getDefaultImportLocale(SAPEntry.class.getName(),
				getPrimaryKey(), defaultLocale, availableLocales);

		prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	@SuppressWarnings("unused")
	public void prepareLocalizedFieldsForImport(Locale defaultImportLocale)
		throws LocaleException {
		Locale defaultLocale = LocaleUtil.getDefault();

		String modelDefaultLanguageId = getDefaultLanguageId();

		String title = getTitle(defaultLocale);

		if (Validator.isNull(title)) {
			setTitle(getTitle(modelDefaultLanguageId), defaultLocale);
		}
		else {
			setTitle(getTitle(defaultLocale), defaultLocale, defaultLocale);
		}
	}

	@Override
	public SAPEntry toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (SAPEntry)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		SAPEntryImpl sapEntryImpl = new SAPEntryImpl();

		sapEntryImpl.setUuid(getUuid());
		sapEntryImpl.setSapEntryId(getSapEntryId());
		sapEntryImpl.setCompanyId(getCompanyId());
		sapEntryImpl.setUserId(getUserId());
		sapEntryImpl.setUserName(getUserName());
		sapEntryImpl.setCreateDate(getCreateDate());
		sapEntryImpl.setModifiedDate(getModifiedDate());
		sapEntryImpl.setAllowedServiceSignatures(getAllowedServiceSignatures());
		sapEntryImpl.setDefaultSAPEntry(isDefaultSAPEntry());
		sapEntryImpl.setEnabled(isEnabled());
		sapEntryImpl.setName(getName());
		sapEntryImpl.setTitle(getTitle());

		sapEntryImpl.resetOriginalValues();

		return sapEntryImpl;
	}

	@Override
	public int compareTo(SAPEntry sapEntry) {
		long primaryKey = sapEntry.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SAPEntry)) {
			return false;
		}

		SAPEntry sapEntry = (SAPEntry)obj;

		long primaryKey = sapEntry.getPrimaryKey();

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
	public boolean isEntityCacheEnabled() {
		return ENTITY_CACHE_ENABLED;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
		SAPEntryModelImpl sapEntryModelImpl = this;

		sapEntryModelImpl._originalUuid = sapEntryModelImpl._uuid;

		sapEntryModelImpl._originalCompanyId = sapEntryModelImpl._companyId;

		sapEntryModelImpl._setOriginalCompanyId = false;

		sapEntryModelImpl._setModifiedDate = false;

		sapEntryModelImpl._originalDefaultSAPEntry = sapEntryModelImpl._defaultSAPEntry;

		sapEntryModelImpl._setOriginalDefaultSAPEntry = false;

		sapEntryModelImpl._originalName = sapEntryModelImpl._name;

		sapEntryModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<SAPEntry> toCacheModel() {
		SAPEntryCacheModel sapEntryCacheModel = new SAPEntryCacheModel();

		sapEntryCacheModel.uuid = getUuid();

		String uuid = sapEntryCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			sapEntryCacheModel.uuid = null;
		}

		sapEntryCacheModel.sapEntryId = getSapEntryId();

		sapEntryCacheModel.companyId = getCompanyId();

		sapEntryCacheModel.userId = getUserId();

		sapEntryCacheModel.userName = getUserName();

		String userName = sapEntryCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			sapEntryCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			sapEntryCacheModel.createDate = createDate.getTime();
		}
		else {
			sapEntryCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			sapEntryCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			sapEntryCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		sapEntryCacheModel.allowedServiceSignatures = getAllowedServiceSignatures();

		String allowedServiceSignatures = sapEntryCacheModel.allowedServiceSignatures;

		if ((allowedServiceSignatures != null) &&
				(allowedServiceSignatures.length() == 0)) {
			sapEntryCacheModel.allowedServiceSignatures = null;
		}

		sapEntryCacheModel.defaultSAPEntry = isDefaultSAPEntry();

		sapEntryCacheModel.enabled = isEnabled();

		sapEntryCacheModel.name = getName();

		String name = sapEntryCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			sapEntryCacheModel.name = null;
		}

		sapEntryCacheModel.title = getTitle();

		String title = sapEntryCacheModel.title;

		if ((title != null) && (title.length() == 0)) {
			sapEntryCacheModel.title = null;
		}

		return sapEntryCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append("{uuid=");
		sb.append(getUuid());
		sb.append(", sapEntryId=");
		sb.append(getSapEntryId());
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
		sb.append(", allowedServiceSignatures=");
		sb.append(getAllowedServiceSignatures());
		sb.append(", defaultSAPEntry=");
		sb.append(isDefaultSAPEntry());
		sb.append(", enabled=");
		sb.append(isEnabled());
		sb.append(", name=");
		sb.append(getName());
		sb.append(", title=");
		sb.append(getTitle());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(40);

		sb.append("<model><model-name>");
		sb.append(
			"com.liferay.portal.security.service.access.policy.model.SAPEntry");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>uuid</column-name><column-value><![CDATA[");
		sb.append(getUuid());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>sapEntryId</column-name><column-value><![CDATA[");
		sb.append(getSapEntryId());
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
			"<column><column-name>allowedServiceSignatures</column-name><column-value><![CDATA[");
		sb.append(getAllowedServiceSignatures());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>defaultSAPEntry</column-name><column-value><![CDATA[");
		sb.append(isDefaultSAPEntry());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>enabled</column-name><column-value><![CDATA[");
		sb.append(isEnabled());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>title</column-name><column-value><![CDATA[");
		sb.append(getTitle());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = SAPEntry.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			SAPEntry.class, ModelWrapper.class
		};
	private String _uuid;
	private String _originalUuid;
	private long _sapEntryId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private String _allowedServiceSignatures;
	private boolean _defaultSAPEntry;
	private boolean _originalDefaultSAPEntry;
	private boolean _setOriginalDefaultSAPEntry;
	private boolean _enabled;
	private String _name;
	private String _originalName;
	private String _title;
	private String _titleCurrentLanguageId;
	private long _columnBitmask;
	private SAPEntry _escapedModel;
}