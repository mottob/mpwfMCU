package com.moto.server.restful.authenticate;

import org.restlet.Context;
import org.restlet.Response;
import org.restlet.data.ChallengeScheme;
import org.restlet.data.LocalReference;
import org.restlet.data.MediaType;
import org.restlet.data.Status;
import org.restlet.ext.crypto.DigestAuthenticator;
import org.restlet.ext.freemarker.TemplateRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;
import org.restlet.security.ChallengeAuthenticator;

public class DefaultAuthenticator extends ChallengeAuthenticator{
	public DefaultAuthenticator(Context context,
			ChallengeScheme challengeScheme, String realm) {
		super(context, challengeScheme, realm);
	}

	public void challenge(Response response, boolean stale) {
        // Load the FreeMarker template
        Representation ftl = new ClientResource(
                LocalReference.createClapReference(getClass().getPackage())
                        + "/Login.ftl").get();

        // Wraps the bean with a FreeMarker representation
        response.setEntity(new TemplateRepresentation(ftl, response.getRequest().getReferrerRef(), 
        		MediaType.TEXT_HTML));

        response.setStatus(Status.CLIENT_ERROR_UNAUTHORIZED);
    }


}
