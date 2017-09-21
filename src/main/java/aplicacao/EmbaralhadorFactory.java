package aplicacao;

import interfaces.IEmbaralhador;
import strategies.EmbaralhadorDificil;
import strategies.EmbaralhadorFacil;
import strategies.EmbaralhadorMedio;
import strategies.EmbaralhadorMuitoDificil;

public class EmbaralhadorFactory {
	
	IEmbaralhador embaralhador;
	
	public EmbaralhadorFactory (int strategy) {
		if (strategy == 0) {
			embaralhador = EmbaralhadorFacil.getEmbaralhadorFacil();
		}
		else 
			if (strategy == 1) {
				embaralhador = EmbaralhadorMedio.getEmbaralhadorMedio();
			}
			else
				if (strategy == 2) {
					embaralhador = EmbaralhadorDificil.getEmbaralhadorDificil();
				}
				else
					if (strategy == 3) {
						embaralhador = EmbaralhadorMuitoDificil.getEmbaralhadorMuitoDificil();
					}
		
		
	}

	public IEmbaralhador getEmbaralhador() {
		return embaralhador;
	}

}
