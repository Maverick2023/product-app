package pe.globalhitss.service;

import java.util.Optional;

import pe.globalhitss.app.dto.in.ProductIn;
import pe.globalhitss.app.dto.out.ListProductOut;

public interface ProductoService {
	
	public Optional<ListProductOut> registrar(ProductIn productIn) throws Exception;

}
