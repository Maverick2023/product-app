package pe.globalhitss.app.dto.out;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ProductOut {
	
	private Long id;
	private String nombre;
	private String fechaRegistro;

}
