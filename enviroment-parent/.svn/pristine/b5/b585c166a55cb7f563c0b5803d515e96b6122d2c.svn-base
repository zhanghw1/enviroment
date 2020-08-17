package com.briup.util;

public enum FileNameEnums {
	CLIENT_COUNT_PATH("client_count_path", "src/main/resources/count.txt"),
	RAW_DATA("raw_data", "src/main/resources/radwtmp"), 
	OLD_DATA("old_data", "src/main/resources/data.txt"),
	CLIENT_DATA("client_data","src/main/resources/client-data.txt"),
	SERVER_DATA("server_data","src/main/resources/server-data.txt"),
	JDBC_PROPERTITES("jdbc.properties","src/main/resources/jdbc.properties");
	
	private String fileName;
	private String path;

	private FileNameEnums() {

	}

	private FileNameEnums(String fileName, String path) {
		this.fileName = fileName;
		this.path = path;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
