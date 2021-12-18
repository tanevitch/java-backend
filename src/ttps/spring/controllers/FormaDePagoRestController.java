package ttps.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ttps.spring.model.FormaDePago ;
import ttps.spring.services.FormaDePagoService;

@CrossOrigin
@RestController
@RequestMapping("/api/formas_pago")
public class FormaDePagoRestController {
	@Autowired
	FormaDePagoService formaDePagoService;

	@GetMapping("")
	public ResponseEntity<List<FormaDePago>> getFormasDePago(){
		List<FormaDePago> formasDePago = formaDePagoService.obtenerTodas();
		if (formasDePago == null) {
			return new ResponseEntity("No hay resultados", HttpStatus.NO_CONTENT);		
		}
		return new ResponseEntity<List<FormaDePago>>(formasDePago, HttpStatus.OK);
	}

}
