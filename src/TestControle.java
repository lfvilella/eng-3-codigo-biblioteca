import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class TestControle {
	
	private Aluno alunoNaoVereficado;
	private Aluno alunoComDebito;
	private String raValido;
	private Controle c;
	private Livro livroVerificado1;
	private Livro livroNaoVerificado1;
	private Livro livroNaoVerificado2;

	
	@Before // setup()
    public void before() throws Exception {
        System.out.println("Setting it up!");
        alunoNaoVereficado = new Aluno("10");
        alunoComDebito = new Aluno("4");
        raValido = "123";

		c = new Controle();

		livroVerificado1 = new Livro(1);
		livroNaoVerificado1 = new Livro(2);  // 2 nao é verificado
		livroNaoVerificado2 = new Livro(4);  // 4 nao é verificado
    }

	@Test
	public void testNaoEmprestaLivrosParaAlunoNaoVerificado() {
		assertFalse(this.alunoNaoVereficado.verficaAluno());

		int[] prazos = {1,2,3};
		boolean retorno = c.emprestar(this.alunoNaoVereficado.RA, prazos, prazos.length);
		assertFalse(retorno);
	}
	
	@Test
	public void testNaoEmprestaLivrosParaAlunoComDebito() {
		assertFalse(alunoComDebito.verificaDebito());

		int[] prazos = {1,2,3};
		boolean retorno = c.emprestar(alunoComDebito.RA, prazos, prazos.length);
		assertFalse(retorno);
	}

	@Test
	public void testNaoEmprestaLivroNaoVerificado() {
		int[] prazos = {1,2,4};  // 2 e 4 nao é verificado
		assertTrue(!livroVerificado1.verificaLivro());
		assertFalse(!livroNaoVerificado1.verificaLivro());
		assertFalse(!livroNaoVerificado2.verificaLivro());
		
		boolean retorno = c.emprestar(raValido, prazos, prazos.length);
		assertTrue(retorno);  // emprestou só 1 livro
	}

	@Test
	public void testNaoEmprestaNenhumLivroNaoVerificado() {
		int[] prazos = {2,4};  // 2 e 4 nao é verificado
		assertFalse(!livroNaoVerificado1.verificaLivro());
		assertFalse(!livroNaoVerificado2.verificaLivro());
		
		boolean retorno = c.emprestar(raValido, prazos, prazos.length);
		assertFalse(retorno);
	}

	@Test
	public void testEmprestaLivros() {
		int[] prazos = {1,3,5,6};
		boolean retorno = c.emprestar(raValido, prazos, prazos.length);
		assertTrue(retorno);
	}
	
    @After // tearDown()
    public void after() throws Exception {
        System.out.println("Running: tearDown");
        alunoNaoVereficado = null;
        assertNull(alunoNaoVereficado);
        alunoComDebito = null;
        assertNull(alunoComDebito);
        raValido = null;
        assertNull(raValido);

        c = null;
        assertNull(alunoComDebito);

		livroVerificado1 = null;
        assertNull(livroVerificado1);
		livroNaoVerificado1 = null;
        assertNull(livroNaoVerificado1);
		livroNaoVerificado2 = null;
        assertNull(livroNaoVerificado2);
    }
}
