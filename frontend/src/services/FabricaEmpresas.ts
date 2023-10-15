import { Empresa } from "../models/Empresa";
import { invalido, validarCEP, validarCNPJ, validarEmail, validarNome, valido } from "../utils/ValidacaoFormulario";
import { guardarCadastro, deletarChaveCadastro } from "./ServicoArmazenamento"


export const capturarEmpresa = (forms: HTMLFormElement): any => {

	const formData = new FormData(forms);
	const dados = {
		nome: formData.get('nome_empresa') as string,
		email: formData.get('email_empresa') as string,
		inscricao: formData.get('inscricao_empresa') as string,
		cep: formData.get('CEP_empresa') as string,
		estado: formData.get('estado_empresa') as string,
		pais: formData.get('pais_empresa') as string,
		descricao: formData.get('descricao_empresa') as string,
	};

	return dados;
}

export const validacaoEmpresa = (): Boolean => {

	let forms = document.getElementById('forms_empresa') as HTMLFormElement;

	let nome: any = forms.querySelector('[name="nome_empresa"]') as HTMLInputElement
	let email: any = forms.querySelector('[name="email_empresa"]') as HTMLInputElement
	let inscricao: any = forms.querySelector('[name="inscricao_cnpj_empresa"]') as HTMLInputElement
	let cep: any = forms.querySelector('[name="CEP_empresa"]') as HTMLInputElement
	let descricao: any = forms.querySelector('[name="descricao_empresa"]') as HTMLInputElement

	if (validarNome(nome.value)) {
		nome = valido(nome);
	} else {
		nome = invalido(nome);
		return false;
	}

	if (validarEmail(email.value)) {
		email = valido(email);
	} else {
		email = invalido(email);
		return false;
	}

	if (validarCNPJ(inscricao.value)) {
		inscricao = valido(inscricao);
	} else {
		inscricao = invalido(inscricao);
		return false;
	}

	if (validarCEP(cep.value)) {
		cep = valido(cep);
	} else {
		cep = invalido(cep);
		return false;
	}

	if(descricao.value){
		descricao = valido(descricao);
	}else{
		descricao = invalido(descricao);
		return false;
	}

	return true;
}

export const instanciarEmpresa = () => {

	const forms = document.getElementById('forms_empresa') as HTMLFormElement;

	if (validacaoEmpresa()) {
		const empresa = new Empresa(capturarEmpresa(forms));
		forms.reset();
		guardarCadastro('empresas', prepararJSON(empresa));
		window.location.href = './visualizacao.html';
	}
}

const prepararJSON = (empresa: Empresa) => {
	return {
		nome: empresa.nome,
		email: empresa.email,
		inscricao: empresa.inscricao,
		cep: empresa.cep,
		estado: empresa.estado,
		pais: empresa.pais,
		descricao: empresa.descricao,
		vagas: empresa.vagas
	}
}




