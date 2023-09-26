export const invalido = (element: HTMLInputElement) => {
	element.classList.remove('is-valid');
	element.classList.add('is-invalid');
}

export const valido = (element: HTMLInputElement) => {
	element.classList.remove('is-invalid');
	element.classList.add('is-valid');
}

export const campoVazio = (campo: string) => {
	const regex: RegExp = /^\s*$/;
	return regex.test(campo);
}

export const validarLinkedin = (linkedin: string): boolean => {
    const regex: RegExp = /^https?:\/\/(www\.)?linkedin\.com\/in\//i;
    return regex.test(linkedin);
}

export const validarNome = (nome: string): boolean => {
	const regex: RegExp = /^[A-Za-zçõ\s]{2,}$/;
	return regex.test(nome);
}

export const validarEmail = (email: string): boolean => {
	const regex: RegExp = /^[\w\.-]+@[\w\.-]+\.\w+$/;
	return regex.test(email);
}

export const validarCPF = (cpf: string): boolean => {
	const regex: RegExp = /^\d{3}\.\d{3}\.\d{3}\-\d{2}$/;
	return regex.test(cpf);
}

export const validarCNPJ = (cnpj: string): boolean => {
	const regex: RegExp = /^\d{2}\.\d{3}\.\d{3}\/\d{4}\-\d{2}$/;
	return regex.test(cnpj);
}

export const validarCEP = (cep: string): boolean => {
	const regex: RegExp = /^\d{2}\.\d{3}\-\d{3}$/;
	return regex.test(cep);
}
