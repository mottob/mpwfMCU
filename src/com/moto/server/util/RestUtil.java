package com.moto.server.util;

public class RestUtil {
	static public String s_ok()
	{
		return "<?xml version=\"1.0\" encoding=\"UTF-8\"?><result>success</result>";
	}
	static public String s_fail()
	{
		return "<?xml version=\"1.0\" encoding=\"UTF-8\"?><result>fail</result>";
	}

}
