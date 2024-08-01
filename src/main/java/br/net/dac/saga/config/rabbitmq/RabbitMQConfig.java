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

										///FILAS CLIENTE
    @Bean
    Queue novoclienteQueue() {
		return new Queue("FILA-CLIENTE-CRUD");	            //fila que envia o cliente para cadastro ou atualização
	}

    @Bean
    Queue clienteCadastradoQueue(){
    	return new Queue("FILA-CLIENTE-RESPOSTA");          //fila de retorno com o cliente cadastrado no banco
    }

    @Bean
    Queue falhaQueue(){
    	return new Queue("FILA-FALHA-CADASTRO-CLIENTE");    //fila com dados do cliente com erro de cadastro
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
