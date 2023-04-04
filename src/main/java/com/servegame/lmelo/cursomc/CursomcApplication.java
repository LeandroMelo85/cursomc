package com.servegame.lmelo.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.servegame.lmelo.cursomc.domain.Categoria;
import com.servegame.lmelo.cursomc.domain.Cidade;
import com.servegame.lmelo.cursomc.domain.Cliente;
import com.servegame.lmelo.cursomc.domain.Endereco;
import com.servegame.lmelo.cursomc.domain.Estado;
import com.servegame.lmelo.cursomc.domain.Pagamento;
import com.servegame.lmelo.cursomc.domain.PagamentoComBoleto;
import com.servegame.lmelo.cursomc.domain.PagamentoComCartao;
import com.servegame.lmelo.cursomc.domain.Pedido;
import com.servegame.lmelo.cursomc.domain.Produto;
import com.servegame.lmelo.cursomc.domain.enums.EstadoPagamento;
import com.servegame.lmelo.cursomc.domain.enums.TipoCliente;
import com.servegame.lmelo.cursomc.repositories.CategoriaRepository;
import com.servegame.lmelo.cursomc.repositories.CidadeRepository;
import com.servegame.lmelo.cursomc.repositories.ClienteRepository;
import com.servegame.lmelo.cursomc.repositories.EnderecoRepository;
import com.servegame.lmelo.cursomc.repositories.EstadoRepository;
import com.servegame.lmelo.cursomc.repositories.PagamentoRepository;
import com.servegame.lmelo.cursomc.repositories.PedidoRepository;
import com.servegame.lmelo.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null,"Informática");
		Categoria cat2 = new Categoria(null,"Escritório");
				
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));

		Estado est1 = new Estado(null, "Paraíba");
		Estado est2 = new Estado(null, "Pernambuco");
		
		Cidade c1 = new Cidade(null, "João Pessoa", est1);
		Cidade c2 = new Cidade(null, "Recife", est2);
		Cidade c3 = new Cidade(null, "Olinda", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		Cliente cli1 = new Cliente(null, "Leandro Melo", "leandro@gmail.com", "05567495455", TipoCliente.PESSOAFISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("8132029902","81982904044"));
		
		Endereco e1 = new Endereco(null, "Estrada do Encanamento", "922", "Casa", "Casa Forte", "52070000", cli1, c2);
		Endereco e2 = new Endereco(null, "Rua Morada Nova", "360", "Casa", "Passarinho", "52165053", cli1, c3);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("13/02/23 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("13/03/23 19:35"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/03/23 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
	}
	
	

}
