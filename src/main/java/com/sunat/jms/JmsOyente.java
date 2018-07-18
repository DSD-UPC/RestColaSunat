
package com.sunat.jms;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.sunat.entidad.Documento;
import com.sunat.repository.DocumentoRepository;

@Component
public class JmsOyente {

	
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private DocumentoRepository documentoRepository;

	private String nroRuc;





@JmsListener(destination="${jms.cola.destino}")
public void miMensaje(String mensajeJson) {
	
	nroRuc = null;
	//dni2 = null;
	System.out.println("Recibido:" + mensajeJson);
	//mensajeJSON a Objeto Agenda
	ObjectMapper mapper = new ObjectMapper();

	try {

		Documento documento =  mapper.readValue(mensajeJson, Documento.class);
		
		System.out.println(mensajeJson);
		
		nroRuc = documento.getNroruc();
		boolean valor; 
		valor = 	documentoRepository.findByNroruc(nroRuc).isEmpty();
		
		if (valor==true) {
		
			
			
			documento.setMensaje("NO");
		     Documento envio =  restTemplate.postForObject("http://localhost:8095/pago/mensaje", documento, Documento.class);
			System.out.println("No existe el nro ruc");		
		}
		else
		{
			documento.setMensaje("SI");
			Documento envio= restTemplate.postForObject("http://localhost:8095/pago/mensaje", documento, Documento.class);
			System.out.println("Ejecutado "+ envio.getMensaje());
			
			System.out.println("El valor ya existe");	
		}
					
	} catch (JsonParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (JsonMappingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

}
