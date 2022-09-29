package pgdp.searchengine.networking;

import java.io.IOException;
import java.net.UnknownHostException;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class HTTPRequest {
    
	private String host;
	private String path;
	
	public HTTPRequest(String host, String path) {
		this.host = host;
		this.path = path;
	}

	public String getHost() {
		return host;
	}

	public String getPath() {
		return path;
	}
	
	public HTTPResponse send(int port) {
		SSLSocketFactory socketFactory = (SSLSocketFactory)SSLSocketFactory.getDefault();
		try {
			SSLSocket socket = (SSLSocket) socketFactory.createSocket(host, port);
			socket.getOutputStream();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new HTTPResponse(host);
	}
}
