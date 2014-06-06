package com.example.mine.server;

import com.example.mine.client.GreetingService;
import com.example.mine.shared.FieldVerifier;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
		GreetingService {

	public String greetServer(String input) throws IllegalArgumentException {
		// Verify that the input is valid. 
		if (!FieldVerifier.isValidName(input)) {
			// If the input is not valid, throw an IllegalArgumentException back to
			// the client.
			throw new IllegalArgumentException(
					"Name must be at least 4 characters long");
		}

		
		String userAgent = getThreadLocalRequest().getHeader("User-Agent");

		// Escape data from the client to avoid cross-site script vulnerabilities.
		input = escapeHtml(input);
		userAgent = escapeHtml(userAgent);
        String[]aa=input.split(" ");
        Integer a=Integer.valueOf(aa[0]);
        Integer b=Integer.valueOf(aa[1]);
        Integer c=Integer.valueOf(aa[2]);
        int aaa=a.intValue();
        int bbb=b.intValue();
        int ccc=c.intValue();
        //String eq="the problem is"+aaa+"x^2 +"+bbb+"x +"+ccc+"=0";
        double dlta=(bbb*bbb)-4*aaa*ccc;
        String message="";
        if(dlta>0)
        {
        	int x1=(int) ((-bbb-Math.sqrt(dlta))/2*aaa);
        	int x2=(int) ((-bbb+Math.sqrt(dlta))/2*aaa);
        message="there is two answers "+x1+" and "+x2;
        }
        if(dlta==0)
        {
        	int x=-bbb/2*aaa;
        	message="there is one answer "+x ;
        }
        if(dlta<0)
        {
        	message="there is no answers";
        }
		return "Hello, the  problem is  " + aa[0]+"x^2 + "+aa[1]+" x +"+aa[2].toString()+" =0 <br>"+message ;
	}

	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html the html string to escape
	 * @return the escaped string
	 */
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;");
	}
}
