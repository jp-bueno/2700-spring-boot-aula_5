package med.voll.api.domain.consulta.validacoes.agendamento;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.consulta.validacoes.agendamento.ValidadorAgendamentoConsultas;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
@Component
public class ValidadorHorarioFuncionamentoClinica implements ValidadorAgendamentoConsultas {

    public void validar(DadosAgendamentoConsulta dados) {
        var dataConsulta = dados.data();

        // Checar se a data está no domingo
        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);

        // Checar os horarios de funcionamento da clinica
        var antesDaAberturaDaClinica = dataConsulta.getHour() < 7;
        var depoisDoEncerramentoDaClinica = dataConsulta.getHour() > 18;

        if(domingo || antesDaAberturaDaClinica || depoisDoEncerramentoDaClinica) {
            throw new ValidacaoException
                    ("Consulta fora do horário de funcionamento da clinica", HttpStatus.CONFLICT);
        }
    }
}
