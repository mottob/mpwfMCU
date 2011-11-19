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

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.moto.server.bean.Diary;
import com.moto.server.bean.Result;
import com.moto.server.service.DiaryService;

/**
 * Resource which has only one representation.
 */
@Path("/diary/{diaryId}")
@Component
public class DiaryResource {

    public DiaryResource() {
	}
    
    @Autowired
    private DiaryService diaryService;

    @GET
    @Produces("application/xml")
    public Diary getDiaryById(@PathParam("diaryId") int id) {
    	return diaryService.getDiaryById(id);
    }
    
    @PUT
    @Produces("application/xml")
    public Result updateDiary(@PathParam("diaryId") int id,
    		@FormParam("title") String title,
    		@FormParam("content") String content,
    		@FormParam("userId") int userId)
    {
    	if(diaryService.modifyDiary(id, title, content, userId))
    	{
    		return new Result("update diary success");
    	}
    	else
    	{
    		return new Result("update diary failed");
    	}
    }
    
    

    
    @DELETE
    @Produces("application/xml")
    public Result deleteDiary(@PathParam("diaryId") int id)
    {
    	if(diaryService.deleteDiray(id))
    	{
    		return new Result("delete diary success");
    	}
    	else
    	{
    		return new Result("delete diary failed");
    	}
    }
}
