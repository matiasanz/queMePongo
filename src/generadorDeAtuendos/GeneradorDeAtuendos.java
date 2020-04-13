package generadorDeAtuendos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

class GeneradorDeAtuendos{
	List<Prenda> prendasDisponibles = new ArrayList<>();
	
	void agregarPrenda(TipoPrenda tipo, String color, String material){
		this.enlistarPrenda(tipo,color, null, material);
	}
	
	void agregarPrenda(TipoPrenda tipo, String colorPrimario, String colorSecundario, String material){
		this.enlistarPrenda(tipo,colorPrimario, Optional.of(colorSecundario), material);
	}
	
	void enlistarPrenda(TipoPrenda tipo, String color, Optional<String> color2, String material){
		Prenda nuevaPrenda = new Prenda(tipo, color, material);
		
		if(color2.isPresent()){
			nuevaPrenda.agregarColor(color2.get());
		}
		
		prendasDisponibles.add(nuevaPrenda);
	}
	
	List<Prenda> generarAtuendo(String colorUnico) throws Exception{
		return this.nuevoAtuendo(colorUnico, null);
	}
	
	List<Prenda> generarAtuendo(String colorPrimario, String colorSecundario) throws Exception{
		return this.nuevoAtuendo(colorPrimario,Optional.of(colorSecundario));
	}
		
	List<Prenda> nuevoAtuendo(String colorPrimario, Optional<String>colorSecundario) throws Exception{
		this.validarPrendas();
		
		Atuendo unAtuendo = new Atuendo(prendasDisponibles, colorPrimario);
		
		if(colorSecundario.isPresent()){
			unAtuendo.agregarColor(colorSecundario.get());
		}
		
		return unAtuendo.generarCombinacion();
	}
	
	void validarPrendas() throws Exception{
		if(prendasDisponibles.isEmpty())throw new Exception("No se ingreso ninguna prenda");
	}
	void validarAtuendo(List<Prenda>atuendo)throws Exception{
		if(atuendo.isEmpty()) throw new Exception("Ninguna prenda cumple los requisitos");
	}
}

class Atuendo{
	List<Prenda> prendasDisponibles;
	List<String> colores = new LinkedList<>();
	
	Atuendo(List<Prenda>prendas, String color){
		prendasDisponibles = prendas;
		this.agregarColor(color);
	}
	
	void agregarColor(String color){
		colores.add(color);
	}
	
	List<Prenda> generarCombinacion(){
		List<Prenda> combinacion = new LinkedList<>();
		
		Collections.shuffle(prendasDisponibles);
		prendasDisponibles.stream().forEach(prenda->{if(prenda.sePuedeAgregarA(combinacion, colores))	
					combinacion.add(prenda);
		});
		
		return combinacion;
	}
}