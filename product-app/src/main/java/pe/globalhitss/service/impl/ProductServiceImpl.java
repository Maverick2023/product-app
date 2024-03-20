package pe.globalhitss.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import pe.globalhitss.app.dto.in.ProductIn;
import pe.globalhitss.app.dto.out.ListProductOut;
import pe.globalhitss.app.dto.out.ProductOut;
import pe.globalhitss.domain.Producto;
import pe.globalhitss.service.ProductoService;

@Slf4j
@Service("productoService")
public class ProductServiceImpl implements ProductoService{

	
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public Optional<ListProductOut> registrar(ProductIn productIn) throws Exception {
		
		log.info("[SERVICE][REGISTRAR PRODUCTO][" + productIn.toString() + "]");
				
		StoredProcedureQuery proc = em.createNamedStoredProcedureQuery("PRC_REG_PRODUCTO");
		proc.setParameter("P_ID_PRODUCTO", productIn.getId());
		proc.setParameter("P_FECHA_PRODUCTO", productIn.getFechaRegistro());
		proc.setParameter("P_NOMBRE_PRODUCTO", productIn.getNombre());
		proc.execute();
		
		Integer outCodigo = (Integer) proc.getOutputParameterValue("OUT_CODIGO");
		String outMensaje = (String) proc.getOutputParameterValue("OUT_MENSAJE");
		List<ProductOut> listaProductOut = new ArrayList<ProductOut>();
		List<Object[]> listaObjeto = new ArrayList<Object[]>();
		if(outCodigo==1) {
			listaObjeto =  proc.getResultList();
			for(Object[] x:listaObjeto) {
				ProductOut po = new ProductOut();
				po.setId((long) Integer.parseInt(String.valueOf(x[0])));
				po.setFechaRegistro(String.valueOf(x[1]));
				po.setNombre(String.valueOf(x[2]));
				listaProductOut.add(po);
			}
			
		}

		log.info("outCodigo : " + outCodigo);
		log.info("outMensaje : " + outMensaje);
		
		ListProductOut lista = new ListProductOut();
		lista.setCodigo(outCodigo);
		lista.setMensaje(outMensaje);
		lista.setListaProducto(listaProductOut);
		
		return Optional.ofNullable(lista);
	}

}
