package pgdp.searchengine.networking;

public class HTTPResponse {
    
	private HTTPStatus status;
	private String html;
	
	public HTTPResponse(String content) {
		
	}

	public HTTPStatus getStatus() {
		return status;
	}

	public String getHtml() {
		return html;
	}
}
