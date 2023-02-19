import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import java.lang.IllegalArgumentException;
import org.junit.jupiter.api.Assertions;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class TestEmprestimo {
	
	private Livro livro1;
	private Livro livro2;
	private Livro livro3;
	private List<Livro> livros;
	private List<Livro> livrosBatchOf2;
	private List<Livro> livrosBatchOf3;
	private List<Livro> livrosBatchOf7;  // Excede o limite
	private Emprestimo emprestimo;

	private Calendar calendar;
	
	@Before // setup()
    public void before() throws Exception {
        System.out.println("Setting it up!");
		livro1 = new Livro(0);
		livro2 = new Livro(1);
		livro3 = new Livro(2);
		livros = new ArrayList<Livro>();  

		livrosBatchOf2 = new ArrayList<Livro>();  
		livrosBatchOf2.add(livro1);
		livrosBatchOf2.add(livro2);

		livrosBatchOf3 = new ArrayList<Livro>();  
		livrosBatchOf3.add(livro1);
		livrosBatchOf3.add(livro2);
		livrosBatchOf3.add(livro3);

		livrosBatchOf7 = new ArrayList<Livro>();  
		for(int i=0; i < 8;i++) {
        	livrosBatchOf7.add(new Livro(i));
        }

		emprestimo = new Emprestimo();

		calendar = Calendar.getInstance();

	}

	@Test
	public void testEmpresta1LivroNaoAplicaDataExtra() {
		livros.add(livro2);
		emprestimo.emprestar(livros);
		
		Item ultimoItem = emprestimo.item.get(0);

		Date now = new Date(System.currentTimeMillis());
		calendar.setTime(now);
		calendar.add(Calendar.DAY_OF_MONTH, 2);  // 2 days
		assertEquals(ultimoItem.dataDevolucao.getDay(), calendar.getTime().getDay());
	}

	@Test
	public void testEmpresta2LivroNaoAplicaDataExtra() {
		emprestimo.emprestar(livrosBatchOf2);
		Item ultimoItem = emprestimo.item.get(1);

		Date now = new Date(System.currentTimeMillis());
		calendar.setTime(now);
		calendar.add(Calendar.DAY_OF_MONTH, 2);  // 2 days
		assertEquals(ultimoItem.dataDevolucao.getDay(), calendar.getTime().getDay());
	}

	@Test
	public void testEmpresta3LivroAplicaDataExtra() {
		Emprestimo emprestimo = new Emprestimo();
		emprestimo.emprestar(livrosBatchOf3);
		Item ultimoItem = emprestimo.item.get(2);

		Date now = new Date(System.currentTimeMillis());
		calendar.setTime(now);
		calendar.add(Calendar.DAY_OF_MONTH, 5);  // 3 days
		assertEquals(ultimoItem.dataDevolucao.getDay(), calendar.getTime().getDay());
	}
	
	@Test
	public void testEmprestaMaisDe7LivrosExcedeLimite() {
		IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
			emprestimo.emprestar(livrosBatchOf7);
		});
		Assertions.assertEquals("Excedeu limite maximo de 7 livros", thrown.getMessage());
	}

	@After // tearDown()
    public void after() throws Exception {
        System.out.println("Running: tearDown");
		livro1 = null;
        assertNull(livro1);
		livro2 = null;
        assertNull(livro2);
		livros = null;
        assertNull(livros);

		livrosBatchOf2 = null;
        assertNull(livrosBatchOf2);
		livrosBatchOf3 = null;
        assertNull(livrosBatchOf3);
		livrosBatchOf7 = null; 
        assertNull(livrosBatchOf7);

		emprestimo = null;
        assertNull(emprestimo);

		calendar = null;
        assertNull(calendar);
    }
}
