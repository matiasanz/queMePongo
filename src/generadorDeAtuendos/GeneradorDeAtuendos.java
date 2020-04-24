package generadorDeAtuendos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import generadorDeAtuendos.uniforme.Uniforme;

class GeneradorDeAtuendos{
	List<Prenda> prendasDisponibles = new ArrayList<>();
	List<Uniforme> uniformesSugeridos = new LinkedList<>();
	
	Optional<Prenda>prendaBorrador;
	
	void agregarPrenda(Prenda nuevaPrenda){
		
		try{
			nuevaPrenda.validarDatos();
			prendasDisponibles.add(nuevaPrenda);
		} catch(RuntimeException t){ //(FaltaTipoException t){
			System.out.println("Datos insuficientes, se agrego la prenda a borrador");
			prendaBorrador = Optional.of(nuevaPrenda);
		}
	}
	
	Prenda getPrendaBorrador(){
		this.validarPrendaBorrador();
		Prenda prendaSinCompletar = prendaBorrador.get();
		prendaBorrador = null;
		return prendaSinCompletar;
	}
	
	void sugerirUniforme(Uniforme unUniforme){
		unUniforme.validarDatos();
		uniformesSugeridos.add(unUniforme);
	}
	
	List<Prenda> generarAtuendo(String colorUnico){
		return this.nuevoAtuendo(colorUnico, null);
	}
	
	List<Prenda> generarAtuendo(String colorPrimario, String colorSecundario){
		return this.nuevoAtuendo(colorPrimario,Optional.of(colorSecundario));
	}
		
	List<Prenda> nuevoAtuendo(String colorPrimario, Optional<String>colorSecundario){
		this.validarPrendas();
		
		Atuendo unAtuendo = new Atuendo(prendasDisponibles, colorPrimario);
		
		if(colorSecundario.isPresent()){
			unAtuendo.agregarColor(colorSecundario.get());
		}
		
		return unAtuendo.generarCombinacion();
	}
	
	void validarPrendas(){
		if(prendasDisponibles.isEmpty())throw new RuntimeException("No se ingreso ninguna prenda");
												//NoTengoPrendasException();
	}
	void validarAtuendo(List<Prenda>atuendo){
		if(atuendo.isEmpty()) throw new RuntimeException("Ninguna prenda cumple los requisitos");
										//PrendasNoCumplenException();
	}
	
	void validarPrendaBorrador(){
		if(!prendaBorrador.isPresent()) throw new RuntimeException("No se cargo prenda borrador");
	}
}

class Atuendo{ //TODO Pasar a generador de atuendos, no tiene comportamiento diferencial
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