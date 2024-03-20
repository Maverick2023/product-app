package pe.globalhitss.app.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pe.globalhitss.app.dto.in.ProductIn;
import pe.globalhitss.app.dto.out.ListProductOut;
import pe.globalhitss.service.ProductoService;

@Slf4j
@RestController
@RequestMapping("/productos")
public class ProductController {
	
	@Autowired
	ProductoService productoService;
	
	@PostMapping(produces = "application/json", consumes = "application/json")
	public ResponseEntity<ListProductOut> registrar(@RequestBody ProductIn productIn) {
		Optional<ListProductOut> rpta = null;
		log.info("[CONTROLLER PRODUCTO]");
		try {
			rpta = productoService.registrar(productIn);
		} catch (Exception e) {
			log.error(e.toString(),e);
			new ResponseEntity<ListProductOut>(rpta.isEmpty() ? new ListProductOut() :rpta.get(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<ListProductOut>(rpta.isEmpty() ? new ListProductOut() :rpta.get(), HttpStatus.OK);
	}
	

}
