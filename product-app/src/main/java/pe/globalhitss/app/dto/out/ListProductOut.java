package pe.globalhitss.app.dto.out;

import java.util.List;

import lombok.Data;

@Data
public class ListProductOut {
	
	private Integer codigo;
	private String mensaje;
	
	private List<ProductOut> listaProducto;

}
