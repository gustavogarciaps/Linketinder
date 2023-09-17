const instanciarCandidato = (formsCandidato: HTMLFormElement) => {

     const formData = new FormData(formsCandidato);

     const nome = formData.get('name');

     console.log('Nome:', nome);
}

export default instanciarCandidato;