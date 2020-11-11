package com.vvm.sh.api.modelos.envio;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Risco {

	@SerializedName("idRisco")
	public String idRisco;

	@SerializedName("idRiscoEspecifico")
	public String idRiscoEspecifico;

	@SerializedName("consequencias")
	public String consequencias;

	@SerializedName("nc")
	public String nc;

	@SerializedName("nd")
	public String nd;

	@SerializedName("ne")
	public String ne;

	@SerializedName("idsMedidasExistentes")
	public List<Integer> idsMedidasExistentes;

	@SerializedName("idsMedidasRecomendadas")
	public List<Integer> idsMedidasRecomendadas;

	@SerializedName("Album")
	public List<Imagem> album;


}
