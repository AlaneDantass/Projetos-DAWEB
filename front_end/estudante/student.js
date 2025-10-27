const form = document.getElementById("formEstudante");
const resposta = document.getElementById("resposta");

form.addEventListener("submit", async (event) => {
  event.preventDefault();

  const estudante = {
    nomeEstudante: document.getElementById("nome").value,
    matricula: document.getElementById("matricula").value,
    curso: document.getElementById("curso").value,
    ano: document.getElementById("ano").value
  };

  try {
    const resp = await fetch("http://localhost:8080/api/estudantes", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(estudante)
    });

    const dados = await resp.json();
    resposta.textContent = "Cadastro realizado!\n" + JSON.stringify(dados, null, 2);
  } catch (erro) {
    resposta.textContent = "Erro: " + erro.message;
  }
});
