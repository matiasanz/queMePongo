package generadorDeAtuendos;

public class uniforme
{
	class Uniforme{
		String institucion;
		Prenda prendaSuperior;
		Prenda prendaInferior;
		Prenda calzado;
		
		Uniforme(String unaInstitucion, Prenda superior, Prenda inferior, Prenda unCalzado){
			institucion = unaInstitucion;
			prendaSuperior = superior;
			prendaInferior = inferior;
			calzado = unCalzado;
		}
		
		void validarDatos(){
			if(prendaSuperior == null) throw new RuntimeException("Falta prenda superior");
			if(prendaInferior == null) throw new RuntimeException("Falta prenda inferior");
			if(calzado == null) throw new RuntimeException("Falta calzado");
		}
	}
}
