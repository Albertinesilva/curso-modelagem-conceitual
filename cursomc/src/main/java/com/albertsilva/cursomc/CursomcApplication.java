package com.albertsilva.cursomc;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.albertsilva.cursomc.domain.Categoria;
import com.albertsilva.cursomc.domain.Cidade;
import com.albertsilva.cursomc.domain.Cliente;
import com.albertsilva.cursomc.domain.Endereco;
import com.albertsilva.cursomc.domain.Estado;
import com.albertsilva.cursomc.domain.Produto;
import com.albertsilva.cursomc.domain.enums.TipoCliente;
import com.albertsilva.cursomc.repositories.CategoriaRepository;
import com.albertsilva.cursomc.repositories.CidadeRepository;
import com.albertsilva.cursomc.repositories.ClienteRepository;
import com.albertsilva.cursomc.repositories.EnderecoRepository;
import com.albertsilva.cursomc.repositories.EstadoRepository;
import com.albertsilva.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	// private final CategoriaRepository categoriaRepository;
	// private final ProdutoRepository produtoRepository;
	// private final EstadoRepository estadoRepository;
	// private final CidadeRepository cidadeRepository;
	// private final ClienteRepository clienteRepository;
	// private final EnderecoRepository enderecoRepository;

	// public CursomcApplication(CategoriaRepository categoriaRepository,
	// ProdutoRepository produtoRepository,
	// EstadoRepository estadoRepository,
	// CidadeRepository cidadeRepository, ClienteRepository clienteRepository,
	// EnderecoRepository enderecoRepository) {
	// this.categoriaRepository = categoriaRepository;
	// this.produtoRepository = produtoRepository;
	// this.estadoRepository = estadoRepository;
	// this.cidadeRepository = cidadeRepository;
	// this.clienteRepository = clienteRepository;
	// this.enderecoRepository = enderecoRepository;
	// }

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// /* Categorias e Produtos */
		// Categoria cat1 = new Categoria(null, "Informática");
		// Categoria cat2 = new Categoria(null, "Escritório");

		// Produto p1 = new Produto(null, "Computador", 2000.00);
		// Produto p2 = new Produto(null, "Impressora", 800.00);
		// Produto p3 = new Produto(null, "Mouse", 80.00);

		// cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		// cat2.getProdutos().addAll(Arrays.asList(p2));

		// p1.getCategorias().addAll(Arrays.asList(cat1));
		// p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		// p3.getCategorias().addAll(Arrays.asList(cat1));

		// categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		// produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

		// /* Estados e Cidades */
		// Estado est1 = new Estado(null, "Minas Gerais");
		// Estado est2 = new Estado(null, "São Paulo");

		// Cidade c1 = new Cidade(null, "Uberlândia", est1);
		// Cidade c2 = new Cidade(null, "São Paulo", est2);
		// Cidade c3 = new Cidade(null, "Campinas", est2);

		// est1.getCidades().addAll(Arrays.asList(c1));
		// est2.getCidades().addAll(Arrays.asList(c2, c3));

		// estadoRepository.saveAll(Arrays.asList(est1, est2));
		// cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));

		// /* Clientes e Endereços */
		// Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com",
		// "36378912377", TipoCliente.PESSOAFISICA);
		// cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));

		// Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim",
		// "38220834", cli1, c1);
		// Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800",
		// "Centro", "38777012", cli1, c2);

		// cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

		// clienteRepository.save(cli1);
		// enderecoRepository.save(e1);
		// enderecoRepository.save(e2);

	}

}
