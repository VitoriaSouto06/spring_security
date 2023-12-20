package med.vol.api.tratamentoerros;

public class TratamentoDeErros extends RuntimeException {
	public  TratamentoDeErros(String mensagem) {
		super(mensagem);
	}

}
