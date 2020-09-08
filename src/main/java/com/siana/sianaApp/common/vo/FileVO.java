package com.siana.sianaApp.common.vo;

public class FileVO {

	private String elementName;
	private String originalName;
	private String serverName;
	private String path;
	private long size;

	public FileVO(String elementName,String originalName,String serverName,
			String path,long size) {
		this.setElementName(elementName);
		this.setOriginalName(originalName);
		this.setServerName(serverName);
		this.setPath(path);
		this.setSize(size);
	}

	public String getElementName() {
		return elementName;
	}
	public void setElementName(String elementName) {
		this.elementName = elementName;
	}
	public String getOriginalName() {
		return originalName;
	}
	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}
	public String getServerName() {
		return serverName;
	}
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
}
