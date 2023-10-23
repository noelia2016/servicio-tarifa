package dto.requets;

import org.springframework.http.HttpStatus;

public class ErrorResponse {
	HttpStatus http_status;
	String mensaje;

	public ErrorResponse(HttpStatus http_status, String mensaje) {
		this.http_status = http_status;
		this.mensaje = mensaje;
	}

	public HttpStatus get_http_status() {
		return this.http_status;
	}

	public void set_http_status(HttpStatus http_status) {
		this.http_status = http_status;
	}

	public String get_mensaje() {
		return this.mensaje;
	}

	public void set_mensaje(String mensaje) {
		this.mensaje = mensaje;
	}
}
