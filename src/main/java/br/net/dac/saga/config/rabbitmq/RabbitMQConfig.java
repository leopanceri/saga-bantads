package br.net.dac.saga.config.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.net.dac.saga.autocadastro.ClienteProducer;
import br.net.dac.saga.cadastroGerente.GerenteProducer;

@Configuration
public class RabbitMQConfig {

	public static final String FILA_CLIENTE_CRUD = "FILA_CLIENTE_CRUD";
	public static final String FILA_CLIENTE_RESPOSTA = "FILA_CLIENTE_RESPOSTA";
	public static final String FILA_ATUALIZA_STATUS ="FILA_ATUALIZA_STATUS";
	public static final String FILA_FALHA_CADASTRO_CLIENTE ="FILA_FALHA_CADASTRO_CLIENTE";

										///FILAS CLIENTE
    @Bean
    Queue novoclienteQueue() {
		return new Queue(FILA_CLIENTE_RESPOSTA);	            //fila que envia o cliente para cadastro ou atualização
	}

    @Bean
    Queue clienteCadastradoQueue(){
    	return new Queue(FILA_ATUALIZA_STATUS);          //fila de retorno com o cliente cadastrado no banco
    }

    @Bean
    Queue falhaQueue(){
    	return new Queue(FILA_FALHA_CADASTRO_CLIENTE);    //fila com dados do cliente com erro de cadastro
    }
    								///FILAS CONTA
    @Bean
    Queue cadastraNovaContaQueue() {
    	return new Queue("FILA_REGISTRO_CONTA_CLIENTE");   //fila para cadastro de nova conta
    }

    @Bean
    Queue respostaContaQueue() {
    	return new Queue ("FILA_CONTA_RESPOSTA");          //fila resposta criação de conta
    }

    @Bean
    Queue atribuiGerenteConta() {
    	return new Queue("FILA_ATRIBUI_CONTA_GERENTE");   // envia id do gerente para conta
    }

    @Bean
    Queue distribuiContasGerente(){
    	return new Queue("FILA_DISTRIBUI_CONTAS_GERENTE"); // envia id do gerente removido para redidtribuir suas contas
    }

    @Bean
    Queue updateConta(){
    	return new Queue("FILA_UPDATE_CONTA"); // envia id do gerente removido para redidtribuir suas contas
    }

    @Bean
    ClienteProducer clienteProducer() {
        return new ClienteProducer();
    }

    @Bean
    GerenteProducer gerenteProducer() {
    	return new GerenteProducer();
    }

    @Bean
    RabbitAdmin rabbitAdmin(ConnectionFactory connectionFacctory) {
		return new RabbitAdmin(connectionFacctory);
	}

    @Bean
    ApplicationListener<ApplicationReadyEvent> appListener(RabbitAdmin rabbitAdmin){
		return event -> rabbitAdmin.initialize();
	}

    @Bean
    Jackson2JsonMessageConverter messageConverter() {
		return new Jackson2JsonMessageConverter();
	}

    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFacctory,
                               Jackson2JsonMessageConverter messageConverter) {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFacctory);
		rabbitTemplate.setMessageConverter(messageConverter);
		return rabbitTemplate;
	}


}
