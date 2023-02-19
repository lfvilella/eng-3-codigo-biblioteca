import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;


class TestControle {

	@Test
	void testNaoEmprestaLivrosParaAlunoNaoVerificado() {
		String ra = "10";
		Aluno aluno = new Aluno(ra);
		assertFalse(aluno.verficaAluno());

		int[] prazos = {1,2,3};
		Controle c = new Controle();
		boolean retorno = c.emprestar(ra, prazos, prazos.length);
		assertFalse(retorno);
	}

	@Test
	void testNaoEmprestaLivrosParaAlunoComDebito() {
		String ra = "4";
		Aluno aluno = new Aluno(ra);
		assertFalse(aluno.verificaDebito());

		int[] prazos = {1,2,3};
		Controle c = new Controle();
		boolean retorno = c.emprestar(ra, prazos, prazos.length);
		assertFalse(retorno);
	}

	@Test
	void testNaoEmprestaLivroNaoVerificado() {
		String ra = "123";
		int[] prazos = {1,2,4};  // 2 e 4 nao é verificado
		Livro livro0 = new Livro(1);
		assertTrue(!livro0.verificaLivro());
		Livro livro1 = new Livro(2);
		assertFalse(!livro1.verificaLivro());
		Livro livro2 = new Livro(4);
		assertFalse(!livro2.verificaLivro());
		
		Controle c = new Controle();
		boolean retorno = c.emprestar(ra, prazos, prazos.length);
		assertTrue(retorno);  // emprestou só 1 livro
	}

	@Test
	void testNaoEmprestaNenhumLivroNaoVerificado() {
		String ra = "123";
		int[] prazos = {2,4};  // 2 e 4 nao é verificado
		Livro livro1 = new Livro(2);
		assertFalse(!livro1.verificaLivro());
		Livro livro2 = new Livro(4);
		assertFalse(!livro2.verificaLivro());
		
		Controle c = new Controle();
		boolean retorno = c.emprestar(ra, prazos, prazos.length);
		assertFalse(retorno);
	}

	@Test
	void testEmprestaLivros() {
		String ra = "123";
		int[] prazos = {1,3,5,6};
		Controle c = new Controle();
		boolean retorno = c.emprestar(ra, prazos, prazos.length);
		assertTrue(retorno);
	}
}
