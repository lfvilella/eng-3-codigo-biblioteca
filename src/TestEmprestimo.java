import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import java.lang.IllegalArgumentException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TestEmprestimo {

	@Test
	void testEmpresta1LivroNaoAplicaDataExtra() {
		Livro l = new Livro(1);
		List<Livro> livros = new ArrayList<Livro>();  
		livros.add(l);
		Emprestimo emprestimo = new Emprestimo();
		emprestimo.emprestar(livros);
		
		Item ultimoItem = emprestimo.item.get(0);

		Date now = new Date(System.currentTimeMillis());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(now);
		calendar.add(Calendar.DAY_OF_MONTH, 2);  // 2 days
		assertEquals(ultimoItem.dataDevolucao.getDay(), calendar.getTime().getDay());
	}

	@Test
	void testEmpresta2LivroNaoAplicaDataExtra() {
		List<Livro> livros = new ArrayList<Livro>();  
        for(int i=0; i < 2;i++) {
        	Livro l = new Livro(i);
        	livros.add(l);
        }
        
		Emprestimo emprestimo = new Emprestimo();
		emprestimo.emprestar(livros);
		Item ultimoItem = emprestimo.item.get(1);

		Date now = new Date(System.currentTimeMillis());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(now);
		calendar.add(Calendar.DAY_OF_MONTH, 2);  // 2 days
		assertEquals(ultimoItem.dataDevolucao.getDay(), calendar.getTime().getDay());
	}

	@Test
	void testEmpresta3LivroAplicaDataExtra() {
		List<Livro> livros = new ArrayList<Livro>();  
        for(int i=0; i < 3;i++) {
        	Livro l = new Livro(i);
        	livros.add(l);
        }
        
		Emprestimo emprestimo = new Emprestimo();
		emprestimo.emprestar(livros);
		Item ultimoItem = emprestimo.item.get(2);

		Date now = new Date(System.currentTimeMillis());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(now);
		calendar.add(Calendar.DAY_OF_MONTH, 5);  // 3 days
		assertEquals(ultimoItem.dataDevolucao.getDay(), calendar.getTime().getDay());
	}
	
	@Test
	void testEmprestaMaisDe7LivrosExcedeLimite() {
		List<Livro> livros = new ArrayList<Livro>();  
        for(int i=0; i < 8;i++) {
        	Livro l = new Livro(i);
        	livros.add(l);
        }
		Emprestimo emprestimo = new Emprestimo();
		IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
			emprestimo.emprestar(livros);
		});
		Assertions.assertEquals("Excedeu limite maximo de 7 livros", thrown.getMessage());
	}

}
