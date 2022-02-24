/**
 * Validação de Formulário
 *@author Alan Soares
 */
 function validar(){
	let nome = frmContato.nome.value
	let fone = frmContato.telefone.value
	
	if(nome === ""){
		alert('Preencha o campo nome')
		frmContato.nome.focus()
		return false
	}else if(fone === ""){
		alert("Preencha o campo fone")
		frmContato.telefone.focus()
		return false
	}else{
		document.forms[frmContatos].submit()
	}
}
 