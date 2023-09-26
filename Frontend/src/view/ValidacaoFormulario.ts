export const invalido = (element: HTMLInputElement) => {
	element.classList.add('is-invalid');
}

export const valido = (element: HTMLInputElement) => {
	element.classList.add('is-valid');
}

export const campoVazio = (campo: string) => {
	const regex: RegExp = /^\s*$/;
	return regex.test(campo);
}


export const validarNome = (nome: string): boolean => {
	const regex: RegExp = /^[A-Za-z\s]{3,}$/;
	return regex.test(nome);
}

export const validarEmail = (email: string): boolean => {
	const regex: RegExp = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
	return regex.test(email);
}

export const validarCPF = (cpf: string): boolean => {
	const regex: RegExp = /^\d{3}\.\d{3}\.\d{3}\-\d{2}$/;
	return regex.test(cpf);
}
