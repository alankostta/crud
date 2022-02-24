/**
 * confirmação de exclusão de um contato
 * @author Alan Soares da Costa
 */

function confirmar(idcon) {
	let resposta = confirm("Confirmar a exclusão do contato?")
	if (resposta === true) {
		alert("O registro deletado é: "+idcon)
		window.location.href = "delete?idcon=" + idcon
	}
}