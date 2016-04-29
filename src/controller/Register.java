package controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class Register extends ActionSupport {

	private String username;

	private File uploadFile[];// 是File的数组类型

	private String uploadFileFileName[];// 由于上传多文件，所以文件名也是数组类型

	@Override
	public void validate() {

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public File[] getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(File[] uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String[] getUploadFileFileName() {
		return uploadFileFileName;
	}

	public void setUploadFileFileName(String[] uploadFileFileName) {
		this.uploadFileFileName = uploadFileFileName;
	}

	public String execute() throws IOException {
		System.out.println("username的值是：" + username);
		// 取得上传后文件所存放的路径
		String targetDirectory = ServletActionContext.getRequest().getRealPath(
				"/upload");
		// 循环得到File[]数组中的每一个对象，然后分别复制File对象，实现上传文件
		for (int i = 0; i < uploadFile.length; i++) {
			// 在文件名上加上时间来标识每一个上传的文件
			File target = new File(targetDirectory, new SimpleDateFormat(
					"yyyy_MM_dd_HH_mm_ss").format(new Date()).toString()
					+ System.nanoTime() + uploadFileFileName[i]);

			FileUtils.copyFile(uploadFile[i], target);
		}

		return "register";
	}

}
