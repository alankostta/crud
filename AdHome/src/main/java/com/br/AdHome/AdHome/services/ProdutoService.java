package com.br.AdHome.AdHome.services;

import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.br.AdHome.AdHome.models.Produto;
import com.br.AdHome.AdHome.repositories.ProdutoRepository;

/*
* Conhecida como camada Beans
* Service: realiza opeações de negócio.
* Um método da camada Service deve ter um SIGNIFICADO relacionado ao negócio,
* executar várias operações.Ex; requisitar[verificar estoque, salvar pedido, baixar estque,
* enviar e-mail]
*/

@Service
public class ProdutoService {
	
		final ProdutoRepository produtoRepository;
		public ProdutoService(ProdutoRepository produtoRepository) {
			this.produtoRepository = produtoRepository;
		}
		@Transactional
		public Produto salvarProduto(Produto produto) {
			return produtoRepository.save(produto);
		}
		public Page<Produto> findAll(Pageable pageable){
			return produtoRepository.findAll(pageable);
		}
		public Optional<Produto> findById(Long id){
			return produtoRepository.findById(id);
		}
		@Transactional
		public void deleteProduto(Produto produto) {
			produtoRepository.delete(produto);
		}
}
