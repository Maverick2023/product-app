package pe.globalhitss.app.dto.in;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ProductIn {
	
	private Long id;
	private String nombre;
	private LocalDateTime fechaRegistro;
	
}
