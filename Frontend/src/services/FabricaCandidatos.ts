const instanciarCandidato = (formsCandidato: HTMLFormElement) => {

     const formData = new FormData(formsCandidato);

     console.log(formData.values);

     const nome = formData.get('nome_candidato') as string;   
     console.log('Nome completo:', nome);
     
}

export default instanciarCandidato;