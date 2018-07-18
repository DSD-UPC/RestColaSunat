package com.sunat.entidad;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TP_CLIENTESRUC")
public class UsuariosRuc implements Serializable{

		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private int codigo;		
		private String nroruc;
		private String nombre;
		private String direccion;
		
		public int getCodigo() {
			return codigo;
		}
		public void setCodigo(int codigo) {
			this.codigo = codigo;
		}
		public String getNroruc() {
			return nroruc;
		}
		public void setNroruc(String nroruc) {
			this.nroruc = nroruc;
		}
		public String getNombre() {
			return nombre;
		}
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		public String getDireccion() {
			return direccion;
		}
		public void setDireccion(String direccion) {
			this.direccion = direccion;
		}
		
		
		
}