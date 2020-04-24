package generadorDeAtuendos;

import java.util.List;

class Prenda{
	TipoPrenda tipo;
	Tela tela;
	
	Prenda(TipoPrenda unTipo, Tela unaTela, String unColor){
		tipo=unTipo;
		tela = unaTela;
	}
	
	void validarDatos(){
		this.validarTipo();
		tela.validarDatos();
	}
	
	//Getters	
	List<String>colores(){
		return tela.colores();
	}
	
	Categoria categoria(){
		return tipo.categoria();
	}
	
	//validaciones
	void validarTipo(){	
		if(tipo == null) throw new RuntimeException("No se ingreso el tipo de la prenda");
	}								
	
	//Para armar atuendo	
	boolean sePuedeAgregarA(List<Prenda> combinacion, List<String>coloresPermitidos){
		return !this.categoriaPresenteEn(combinacion) && coloresPermitidos.containsAll(this.colores());
	}
	
	boolean categoriaPresenteEn(List<Prenda>combinacion){
		return combinacion.stream().anyMatch(unaPrenda->unaPrenda.mismaCategoriaQue(this));
	}
	
	boolean mismaCategoriaQue(Prenda otraPrenda){
		return this.categoria() == otraPrenda.categoria();
	}
}