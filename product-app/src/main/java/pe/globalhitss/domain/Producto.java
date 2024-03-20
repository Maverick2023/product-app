package pe.globalhitss.domain;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import lombok.Data;
import pe.globalhitss.app.dto.out.ProductOut;

@NamedStoredProcedureQuery(
		name = "PRC_REG_PRODUCTO", 
		procedureName = "PRC_REG_PRODUCTO", 
		//resultClasses = {CommonStoredProcedureResult.class }, 
		parameters = {
				@StoredProcedureParameter(name = "OUT_CURSOR", type = Producto.class, mode = ParameterMode.REF_CURSOR),
				@StoredProcedureParameter(name = "OUT_CODIGO", type = Integer.class, mode = ParameterMode.OUT),
				@StoredProcedureParameter(name = "OUT_MENSAJE", type = String.class, mode = ParameterMode.OUT),
				@StoredProcedureParameter(name = "P_ID_PRODUCTO", type = Long.class, mode = ParameterMode.IN),
				@StoredProcedureParameter(name = "P_FECHA_PRODUCTO", type = LocalDateTime.class, mode = ParameterMode.IN),
				@StoredProcedureParameter(name = "P_NOMBRE_PRODUCTO", type = String.class, mode = ParameterMode.IN) 
		})

@Entity
@Table(name="PRODUCTO")
public class Producto {
	
	@Id
	@NotNull
	@Column(name="ID_PRODUCTO")
	private Long idProducto;
	
	@Basic(optional=false)
	@NotNull
	@Column(name="NO_PRODUCTO", length = 150)
	private String nombre;

	@Basic(optional=false)
	@NotNull
	@Column(name="FE_REGISTRO")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaRegistro;
	

	public Long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	
		
}
