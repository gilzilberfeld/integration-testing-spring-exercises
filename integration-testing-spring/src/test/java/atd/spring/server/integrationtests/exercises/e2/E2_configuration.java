package atd.spring.server.integrationtests.exercises.e2;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.mock;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

import atd.spring.server.compliance.logging.Registrar;
import atd.spring.server.configuration.ComplianceControllerConfiguration;

@Configuration
@Import(ComplianceControllerConfiguration.class)
public class E2_configuration {

	@Bean
	@Profile("test")
	public Registrar testRegistrar() {
		Registrar mockRegistrar =Mockito.mock(Registrar.class);
		Mockito.when(mockRegistrar.isTempered()).thenReturn(true); 
		return mockRegistrar;
	}
	
	@Bean
	@Profile("dev")
	public Registrar devRegistrar() {
		Registrar mockRegistrar =Mockito.mock(Registrar.class, RETURNS_DEEP_STUBS);
		Mockito.when(mockRegistrar.isTempered()).thenReturn(false); 
		return mockRegistrar;
	}
	
}
