package cat.itacademy.barcelonactiva.jimenez.leonardo.s05.t02.n01;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class S05T01N01JimenezLeonardoSpringConfig {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
