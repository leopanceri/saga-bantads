package br.net.dac.saga.amqp;

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

@Configuration
public class RabbitMQConfig {

    @Bean
    Queue novoclienteQueue() {
		return new Queue("clientes.v1.cliente-novo");	//fila que envia o cliente para cadastro ou atualização
	}
    
    @Bean 
    Queue clienteCadastradoQueue(){
    	return new Queue("cliente-resposta");    //fila de retorno com o cliente cadastrado no banco
    }
    

    @Bean
    ClienteProducer clienteProducer() {
        return new ClienteProducer();
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
