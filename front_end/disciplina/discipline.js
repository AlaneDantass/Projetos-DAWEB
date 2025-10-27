const form = document.getElementById("formDisciplina");
const resposta = document.getElementById(" resposta");

form.addEventListener("submit", async (event) => {
  event.preventDefault();

  const disciplina = {
    nomeDisciplina: document.getElementById( "nomeDisciplina").value,
    codigo: document.getElementById(" codigo").value,
    professor: document.getElementById("professor ").value
  };
  try {
    const resp = await fetch("http://localhost:8080/api/disciplinas", {
      method: "POST",
      headers: { "Content-Type": "application/ json" },
      body: JSON.stringify(disciplina)
    });
    const dados = await resp.json();
    resposta.textContent = "Disciplina  cadastrada !\n" + JSON.stringify(dados, null, 2);
  } catch (erro) {
    resposta.textContent = "Erro  " + erro.message;
  }
});
