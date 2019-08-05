/**
 * 
 */
package factory;

import br.com.targettrust.ApplicationTest;
import br.com.targettrust.traccadastros.controller.ReservaController;
import br.com.targettrust.traccadastros.entidades.Equipamento;
import br.com.targettrust.traccadastros.entidades.Reserva;
import br.com.targettrust.traccadastros.entidades.Veiculo;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Set;


/**
 * @author Emerson Sena
 *
 */
public class ReservaControllerTest extends ApplicationTest {

	private MockMvc mockMvc;

	@Autowired
	private ReservaController reservaController;

/*	@Autowired
	private SalarioMinimoService salarioMinimoService;*/


	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(reservaController).build();
	}


	@Test
	public void testGETListAllReservasController() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/listaReservas")).andExpect(MockMvcResultMatchers.status().isOk());
	}

	public static Reserva get(Veiculo veiculo, LocalDate dataInicial, LocalDate dataFinal, LocalDate dataCancelamento, Set<Equipamento> equipamentos) {
		
		return new Reserva() {
			/**
			 * 
			 */
	    //	private static final long serialVersionUID = 6114617201661866907L;

			{
				setVeiculo(veiculo);
				setDataInicial(dataInicial);
				setDataFinal(dataFinal);
				setDataCancelamento(dataCancelamento);
				setEquipamentos(equipamentos);


			}
		};
		
	}

}
