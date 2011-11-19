/**
 * Copyright 2005-2011 Noelios Technologies.
 * 
 * The contents of this file are subject to the terms of one of the following
 * open source licenses: LGPL 3.0 or LGPL 2.1 or CDDL 1.0 or EPL 1.0 (the
 * "Licenses"). You can select the license that you prefer but you may not use
 * this file except in compliance with one of these Licenses.
 * 
 * You can obtain a copy of the LGPL 3.0 license at
 * http://www.opensource.org/licenses/lgpl-3.0.html
 * 
 * You can obtain a copy of the LGPL 2.1 license at
 * http://www.opensource.org/licenses/lgpl-2.1.php
 * 
 * You can obtain a copy of the CDDL 1.0 license at
 * http://www.opensource.org/licenses/cddl1.php
 * 
 * You can obtain a copy of the EPL 1.0 license at
 * http://www.opensource.org/licenses/eclipse-1.0.php
 * 
 * See the Licenses for the specific language governing permissions and
 * limitations under the Licenses.
 * 
 * Alternatively, you can obtain a royalty free commercial license with less
 * limitations, transferable or non-transferable, directly at
 * http://www.noelios.com/products/restlet-engine
 * 
 * Restlet is a registered trademark of Noelios Technologies.
 */

package com.moto.server.restful.resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import org.restlet.resource.ServerResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.moto.server.bean.Avatar;
import com.moto.server.bean.Diary;
import com.moto.server.bean.Result;
import com.moto.server.bean.User;
import com.moto.server.service.AvatarService;
import com.moto.server.service.DiaryService;
import com.moto.server.service.UserService;
import com.moto.server.util.ImageUtil;

/**
 * Resource which has only one representation.
 */
@Path("/user/{userId}")
@Component
public class UserResource extends ServerResource {

	public UserResource() {

	}

	@Autowired
	private UserService userService;
	@Autowired
	private DiaryService diaryService;
	@Autowired
    private AvatarService avatarService;
	@Context
	UriInfo uri;

	@GET
	@Produces("application/xml")
	public User getUser(@PathParam("userId") int id) {
		return userService.getUser(id);
	}

	@PUT
	@Produces("application/xml")
	public Result updateUser(@PathParam("userId") int id,
			@FormParam("username") String username,
			@FormParam("email") String email, @FormParam("state") String state) {
		if (userService.modify(id, username, email, state)) {
			return new Result("update success");
		} else {
			return new Result("update failed");
		}
	}

	@Path("/password")
	@PUT
	@Produces("application/xml")
	public Result changePassword(@PathParam("userId") int id,
			@FormParam("password") String password) {
		if (userService.changePassword(id, password)) {
			return new Result("change passwrod success");
		} else {
			return new Result("change passwrod failed");
		}
	}

	@DELETE
	@Produces("application/xml")
	public Result deleteUser(@PathParam("userId") int id) {
		if (userService.unregister(id)) {
			return new Result("unregister success");
		} else {
			return new Result("unregister failed");
		}
	}

	@Path("/diary")
	@GET
	@Produces("application/xml")
	public List<Diary> getDiaryByUserId(@PathParam("userId") int userId) {
		return diaryService.getDiaryByUserId(userId);
	}

	@Path("/diary")
	@PUT
	@Produces("application/xml")
	public Result createDiary(@FormParam("title") String title,
			@FormParam("content") String content,
			@PathParam("userId") int userId) {
		int diaryId = diaryService.createDiary(title, content, userId);
		return new Diary(diaryId, title, content, userId, null);
	}
	
	@Path("/avatar")
	@POST
	@Produces("application/xml")
	public Result createAvatar(@PathParam("userId") int userId,
			@FormParam("description") @DefaultValue("") String description,
			@FormParam("w") int width,
			@FormParam("h") int height,
			@FormParam("x1") int x1,
			@FormParam("y1") int y1,
			@FormParam("path") String path)
	{
		File file = ImageUtil.cut(System.getProperty("webapp.root")+path, width, height, x1, y1);
		if(null == file)
		{
			return new Result("store avatar failed!");
		}
		FileInputStream fis=null;
		byte[] b = new byte[(int) file.length()];
		try {
			fis = new FileInputStream(file);
			fis.read(b);
			int avatarId = avatarService.createAvatar(description, file, userId);
			fis.close();
			file.delete();
			return new Avatar(avatarId, description, b, userId);
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally
		{
			if (fis != null) {
                try {
                	fis.close();
                } catch (IOException ex) {
                }
            }
		}
		return new Avatar();
	}
}
