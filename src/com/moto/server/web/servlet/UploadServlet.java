package com.moto.server.web.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UploadServlet extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8717500135391552065L;

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		InputStream is = null; // 当前上传文件的InputStream对象
		FileOutputStream fos = null;
		PrintWriter out = null;
		try
		{
			request.setCharacterEncoding("UTF-8"); // 设置处理请求参数的编码格式
			response.setContentType("text/html;charset=UTF-8"); // 设置Content-Type字段值
			out = response.getWriter();
		
			// 下面的代码开始使用Commons-UploadFile组件处理上传的文件数据
			FileItemFactory factory = new DiskFileItemFactory(); // 建立FileItemFactory对象
			ServletFileUpload upload = new ServletFileUpload(factory);
			// 分析请求，并得到上传文件的FileItem对象
			@SuppressWarnings("unchecked")
			List<FileItem> items = upload.parseRequest(request);
			// 从web.xml文件中的参数中得到上传文件的路径
			String uploadPath =  request.getSession().getServletContext().getRealPath("/")+"upload_pic";
			File file = new File(uploadPath);
			if (!file.exists())
			{
				file.mkdir();
			}
			String filename = ""; // 上传文件保存到服务器的文件名
			
			// 循环处理上传文件
			for (FileItem item : items)
			{
				// 处理普通的表单域
				if (item.isFormField())
				{
					if (item.getFieldName().equals("filename"))
					{
						// 如果新文件不为空，将其保存在filename中
						if (!item.getString().equals(""))
							filename = item.getString("UTF-8");
					}
				}
				// 处理上传文件
				else if (item.getName() != null && !item.getName().equals(""))
				{
					// 从客户端发送过来的上传文件路径中截取文件名
					filename = item.getName().substring(
							item.getName().lastIndexOf("\\") + 1);
					is = item.getInputStream(); // 得到上传文件的InputStream对象
				}
			}
			if(filename.isEmpty())
			{
				out.print("file is null");
				is.close(); // InputStream对象
				out.close();
				return;
			}
			String temp = Long.toString(new Date().getTime());
			// 将路径和上传文件名组合成完整的服务端路径
			filename = uploadPath + File.separator + temp;
			// 如果服务器已经存在和上传文件同名的文件，则输出提示信息
			if (new File(filename).exists())
			{
				new File(filename).delete();
			}
			// 开始上传文件
			if (!filename.equals(""))
			{
				// 用FileOutputStream打开服务端的上传文件
				fos = new FileOutputStream(filename);
				byte[] buffer = new byte[8192]; // 每次读8K字节
				int count = 0;
				// 开始读取上传文件的字节，并将其输出到服务端的上传文件输出流中
				while ((count = is.read(buffer)) > 0)
				{
					fos.write(buffer, 0, count); // 向服务端文件写入字节流
					
				}
				fos.close(); // 关闭FileOutputStream对象
				is.close(); // InputStream对象
				out.print("upload_pic" + File.separator + temp);
				out.close();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(out!=null)
			{
				out.close();
			}
			if (is != null) {
                try {
                	is.close();
                } catch (IOException ex) {
                }
            }
			if (fos != null) {
                try {
                	fos.close();
                } catch (IOException ex) {
                }
            }
		}
	}
}

