package generadorDeAtuendos;

import java.util.ArrayList;
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
		
		prendasDisponibles.stream().forEach(prenda->{if(prenda.sePuedeAgregarA(combinacion, colores))	
					combinacion.add(prenda);
		});
		
		return combinacion;
	}
}

class Prenda{
	TipoPrenda tipo;
	String material;
	List<String> colores = new LinkedList<>();
	
	Prenda(TipoPrenda unTipo, String unColor, String deQueEstaHecha){
		tipo=unTipo;
		this.agregarColor(unColor);
		material = deQueEstaHecha;
	}
	
	void agregarColor(String nuevoColor){
		colores.add(nuevoColor);
	}
	
	Categoria categoria(){
		return tipo.categoria();
	}
	
	boolean sePuedeAgregarA(List<Prenda> combinacion, List<String>coloresPermitidos){
		return !this.categoriaPresenteEn(combinacion) && coloresPermitidos.containsAll(colores);
	}
	
	boolean categoriaPresenteEn(List<Prenda>combinacion){
		return combinacion.stream().anyMatch(unaPrenda->unaPrenda.mismaCategoriaQue(this));
	}
	
	boolean mismaCategoriaQue(Prenda otraPrenda){
		return this.categoria() == otraPrenda.categoria();
	}
}

interface TipoPrenda{
	Categoria categoria();
}

class Remera implements TipoPrenda{
	public Categoria categoria(){
		return Categoria.parteSuperior;
	}
}
	
class Zapatos implements TipoPrenda{
	public Categoria categoria(){
		return Categoria.calzado;
	}
}

class camisaMangasCortas implements TipoPrenda{
	public Categoria categoria(){
		return Categoria.parteSuperior;
	}
}
	
class pantalon{
	public Categoria categoria(){
		return Categoria.parteInferior;
	}
}
	
class anteojos{
	public Categoria categoria(){
		return Categoria.accesorios;
	}
}


enum Categoria{
	parteSuperior,
	calzado,
	parteInferior,
	accesorios
}