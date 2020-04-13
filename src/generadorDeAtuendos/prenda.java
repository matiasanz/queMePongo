package generadorDeAtuendos;

import java.util.LinkedList;
import java.util.List;

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