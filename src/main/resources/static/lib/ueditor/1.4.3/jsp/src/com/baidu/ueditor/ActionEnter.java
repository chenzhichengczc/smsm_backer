package com.baidu.ueditor;

import com.alibaba.fastjson.JSONObject;
import com.baidu.ueditor.define.ActionMap;
import com.baidu.ueditor.define.BaseState;
import com.baidu.ueditor.define.State;
import com.baidu.ueditor.hunter.FileManager;
import com.baidu.ueditor.hunter.ImageHunter;
import com.baidu.ueditor.upload.Base64Uploader;
import com.baidu.ueditor.upload.BinaryUploader;
import com.baidu.ueditor.upload.IStorageManager;
import com.baidu.ueditor.upload.StorageManager;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public class ActionEnter
{
	private HttpServletRequest request;
	private String rootPath;
	private String contextPath;
	private String actionType;
	private ConfigManager configManager;
	private IStorageManager storage;

	public ActionEnter(HttpServletRequest request, String rootPath)
	{
		this(new StorageManager(), request, rootPath, null);
	}

	public ActionEnter(HttpServletRequest request, String rootPath, String configPath) {
		this(new StorageManager(), request, rootPath, configPath);
	}

	public ActionEnter(IStorageManager storage, HttpServletRequest request, String rootPath) {
		this(storage, request, rootPath, null);
	}

	public ActionEnter(IStorageManager storage, HttpServletRequest request, String rootPath, String configPath)
	{
		this.request = null;

		this.rootPath = null;
		this.contextPath = null;

		this.actionType = null;

		this.configManager = null;

		this.storage = storage;

		this.request = request;
		this.rootPath = rootPath;
		this.actionType = request.getParameter("action");
		this.contextPath = request.getContextPath();
		if (configPath == null) {
			configPath = request.getParameter("configPath");
			if (configPath == null) {
				configPath = request.getRequestURI();
			}
		}
		this.configManager = ConfigManager.getInstance(this.rootPath, this.contextPath, configPath);
	}

	public String exec()
	{
		String callbackName = this.request.getParameter("callback");

		if (callbackName != null)
		{
			if (!(validCallbackName(callbackName))) {
				return new BaseState(false, 401).toJSONString();
			}

			return callbackName + "(" + invoke() + ");";
		}

		return invoke();
	}

	public String invoke()
	{
		if ((this.actionType == null) || (!(ActionMap.mapping.containsKey(this.actionType)))) {
			return new BaseState(false, 101).toJSONString();
		}

		if ((this.configManager == null) || (!(this.configManager.valid()))) {
			return new BaseState(false, 102).toJSONString();
		}

		State state = null;

		int actionCode = ActionMap.getType(this.actionType);

		Map conf = null;

		switch (actionCode)
		{
			case 0:
				return this.configManager.getAllConfig().toString();
			case 1:
			case 2:
			case 3:
			case 4:
				conf = this.configManager.getConfig(actionCode);
				String filedName = (String)conf.get("fieldName");
				if ("true".equals(conf.get("isBase64"))) {
					state = new Base64Uploader(this.storage).save(this.request.getParameter(filedName), conf); break label311:
				}
				state = new BinaryUploader(this.storage).save(this.request, conf);

				break;
			case 5:
				conf = this.configManager.getConfig(actionCode);
				String[] list = this.request.getParameterValues((String)conf.get("fieldName"));
				state = new ImageHunter(this.storage, conf).capture(list);
				break;
			case 6:
			case 7:
				conf = this.configManager.getConfig(actionCode);
				int start = getStartIndex();
				state = new FileManager(conf).listFile(start);
		}

		label311: return state.toJSONString();
	}

	public int getStartIndex()
	{
		String start = this.request.getParameter("start");
		try
		{
			return Integer.parseInt(start); } catch (Exception e) {
		}
		return 0;
	}

	public boolean validCallbackName(String name)
	{
		return (name.matches("^[a-zA-Z_]+[\\w0-9_]*$"));
	}
}