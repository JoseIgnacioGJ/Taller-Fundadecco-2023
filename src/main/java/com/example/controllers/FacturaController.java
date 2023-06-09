package com.example.controllers;

import com.example.entities.Averia;
import com.example.entities.Cliente;
import com.example.entities.Factura;

import com.example.entities.Taller;
import com.example.service.AveriaService;
import com.example.service.ClienteService;
import com.example.service.FacturaService;
import com.example.service.TallerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Controller
public class FacturaController {
	private final FacturaService facturaService;
	private final ClienteService clienteService;
	private final TallerService tallerService;
	private final AveriaService averiaService;

	@GetMapping("facturas") // GET http://localhost:8080/facturas
	public String findAll(Model model) {
		List<Factura> facturas = facturaService.findAll();
		model.addAttribute("facturas", facturas);
		return "factura/factura-list";
	}
	
	@GetMapping("facturas/{id}")
	public String findById(Model model, @PathVariable Long id) {
		Optional<Factura> facturaOptional = facturaService.findById(id);
		if (facturaOptional.isPresent())
		{
			model.addAttribute("factura", facturaOptional.get());
			model.addAttribute("clientes", facturaOptional.get().getCliente());
			model.addAttribute("talleres", facturaOptional.get().getTaller());
			model.addAttribute("averias", facturaOptional.get().getAveria());
		}
		else
			model.addAttribute("error", "Factura not found");
		
		return "factura/factura-detail";
	}
	
	@GetMapping("facturas/monto/{monto}")
	public String findByMonto(Model model, @PathVariable Double monto){
		model.addAttribute("facturas", facturaService.findAllByMonto(monto));
		return "factura/factura-list";
	}

	@GetMapping("facturas/cliente/{cliente}")
	public String findByCliente(Model model, @PathVariable Cliente cliente){
		model.addAttribute("facturas", facturaService.findAllByCliente(cliente));
		return "factura/factura-list";
	}

	@GetMapping("facturas/taller/{taller}")
	public String findByTaller(Model model, @PathVariable Taller taller){
		model.addAttribute("facturas", facturaService.findAllByTaller(taller));
		return "factura/factura-list";
	}
	@GetMapping("facturas/averia/{averia}")
	public String findByAveria(Model model, @PathVariable Averia averia){
		model.addAttribute("facturas", facturaService.findAllByAveria(averia));
		return "factura/factura-list";
	}

	@GetMapping("facturas/create")
	public String createForm(Model model) {
		Factura factura = new Factura();
		model.addAttribute("factura", factura);
		model.addAttribute("clientes", clienteService.findAll());
		model.addAttribute("talleres", tallerService.findAll());
		model.addAttribute("averias", averiaService.findAll());

		return "factura/factura-form";
	}
	
	@GetMapping("facturas/{id}/edit")
	public String editForm(Model model, @PathVariable Long id) {
		Optional<Factura> facturaOptional = facturaService.findById(id);
		if (facturaOptional.isPresent()) {
			model.addAttribute("factura", facturaOptional.get());

			model.addAttribute("cliente", facturaOptional.get().getCliente());
			model.addAttribute("taller", facturaOptional.get().getTaller());
			model.addAttribute("averia", facturaOptional.get().getAveria());

			model.addAttribute("clientes", clienteService.findAll());
			model.addAttribute("talleres", tallerService.findAll());
			model.addAttribute("averias", averiaService.findAll());

		} else {
			model.addAttribute("error", "Factura not found");
		}
		
		return "factura/factura-form";
	}
	
	@PostMapping("facturas")
	public String saveForm(@ModelAttribute Factura factura) {
		facturaService.save(factura);
		return "redirect:/facturas";
	}
	
	@GetMapping("facturas/{id}/delete")
	public String deleteById(@PathVariable Long id) {
		facturaService.deleteById(id);
		return "redirect:/facturas";
	}
}
