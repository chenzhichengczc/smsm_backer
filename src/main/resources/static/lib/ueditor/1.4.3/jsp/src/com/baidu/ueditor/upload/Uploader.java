package com.baidu.ueditor.upload;

import com.baidu.ueditor.define.State;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public class Uploader
{
	private IStorageManager storage;
	private HttpServletRequest request;
	private Map<String, Object> conf;

	public Uploader(HttpServletRequest request, Map<String, Object> conf)
	{
		this(new StorageManager(), request, conf);
	}

	public Uploader(IStorageManager storage, HttpServletRequest request, Map<String, Object> conf)
	{
		this.request = null;
		this.conf = null;

		this.storage = storage;
		this.request = request;
		this.conf = conf;
	}

	public final State doExec() {
		String filedName = (String)this.conf.get("fieldName");
		State state = null;

		if ("true".equals(this.conf.get("isBase64")))
			state = new Base64Uploader(this.storage).save(this.request.getParameter(filedName), this.conf);
		else {
			state = new BinaryUploader(this.storage).save(this.request, this.conf);
		}

		return state;
	}
}